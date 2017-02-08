package org.carneiro.ce.service;

import org.carneiro.ce.Application;
import org.carneiro.ce.model.impl.*;
import org.carneiro.ce.repository.CaixaEletronicoRepository;
import org.carneiro.ce.repository.SaqueRepository;
import org.carneiro.ce.repository.UsuarioRepository;
import org.carneiro.ce.service.exception.TransacaoInvalidaException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.Collections;

/**
 * @author heitor
 * @since 07/02/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Application.class)
public class SaqueServiceTest {
	private Usuario usuario;
	private CaixaEletronico caixaEletronico;
	private Transacao transacao;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private CaixaEletronicoService caixaEletronicoService;

	@Autowired
	private SaqueService saqueService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CaixaEletronicoRepository caixaEletronicoRepository;

	@Autowired
	private SaqueRepository saqueRepository;

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setCaixaEletronicoService(CaixaEletronicoService caixaEletronicoService) {
		this.caixaEletronicoService = caixaEletronicoService;
	}

	public void setSaqueService(SaqueService saqueService) {
		this.saqueService = saqueService;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public void setCaixaEletronicoRepository(CaixaEletronicoRepository caixaEletronicoRepository) {
		this.caixaEletronicoRepository = caixaEletronicoRepository;
	}

	public void setSaqueRepository(SaqueRepository saqueRepository) {
		this.saqueRepository = saqueRepository;
	}

	@Before
	public void setUp() throws Exception {
		this.usuarioRepository.deleteAll();
		this.caixaEletronicoRepository.deleteAll();
		this.saqueRepository.deleteAll();

		this.usuario = new Usuario();
		this.usuario.setNome("UsuarioTest");
		this.usuario.setSaldo(1000);

		this.caixaEletronico = new CaixaEletronico();
		this.caixaEletronico.setNome("CaixaTest");
		this.caixaEletronico.setSaldo(200);
		this.caixaEletronico.setNotas(Collections.singletonList(new Nota(100, 2)));

		this.usuarioService.cadastrar(this.usuario);
		this.caixaEletronicoService.cadastrar(this.caixaEletronico);

		this.transacao = new Transacao();
		this.transacao.setUsuario(this.usuario.getNome());
		this.transacao.setCaixaEletronico(this.caixaEletronico.getNome());

		this.transacao.setValor(100);
		this.transacao.setUsuarioSaldo(this.usuario.getSaldo() - transacao.getValor());
		this.transacao.setCaixaEletronicoSaldo(this.caixaEletronico.getSaldo() - transacao.getValor());

		this.caixaEletronico.getNotas().stream().findFirst().get().setQuantidade(1);
		this.transacao.setNotas(this.caixaEletronico.getNotas());
	}

	@After
	public void tearDown() throws Exception {
		this.usuarioService.deletar(this.usuario);
		this.caixaEletronicoService.deletar(this.caixaEletronico);
		this.usuario = null;
		this.caixaEletronico = null;
		this.transacao = null;
	}

	@Test
	public void sacar() throws Exception {
		Saque saque = this.saqueService.sacar(this.transacao);

		Assert.assertNotNull(saque);
		Assert.assertNotNull(saque.getId());
		Assert.assertEquals(this.usuario.getNome(), saque.getUsuario());
		Assert.assertEquals(this.caixaEletronico.getNome(), saque.getCaixaEletronico());
	}

	@Test(expected = TransacaoInvalidaException.class)
	public void sacarTransacaoInvalida() throws Exception {
		this.transacao.setUsuario("UsuarioNaoExistente");
		this.saqueService.sacar(this.transacao);
	}

	@Test
	public void buscarUsuarioSaques() throws Exception {
		this.saqueService.sacar(transacao);
		Collection<Saque> saques = this.saqueService.buscarUsuarioSaques(transacao.getUsuario());
		Assert.assertEquals(1, saques.size());

		Saque saque = saques.stream().findAny().get();

		Assert.assertNotNull(saque);
		Assert.assertEquals(this.usuario.getNome(), saque.getUsuario());
	}

	@Test
	public void buscarCaixaEletronicoSaques() throws Exception {
		this.saqueService.sacar(transacao);
		Collection<Saque> saques = this.saqueService.buscarCaixaEletronicoSaques(transacao.getCaixaEletronico());
		Assert.assertEquals(1, saques.size());

		Saque saque = saques.stream().findAny().get();

		Assert.assertNotNull(saque);
		Assert.assertEquals(this.caixaEletronico.getNome(), saque.getCaixaEletronico());
	}
}