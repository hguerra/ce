package org.carneiro.ce.service;

import org.carneiro.ce.Application;
import org.carneiro.ce.model.impl.*;
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

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setCaixaEletronicoService(CaixaEletronicoService caixaEletronicoService) {
		this.caixaEletronicoService = caixaEletronicoService;
	}

	public void setSaqueService(SaqueService saqueService) {
		this.saqueService = saqueService;
	}

	@Before
	public void setUp() throws Exception {
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
		Assert.assertTrue(this.saqueService.sacar(transacao));
	}

	@Test
	public void buscarUsuarioSaques() throws Exception {
		Collection<Saque> saques = this.saqueService.buscarUsuarioSaques(transacao.getUsuario());
		Assert.assertEquals(1, saques.size());

		Saque saque = saques.stream().findAny().get();

		Assert.assertNotNull(saque);
		Assert.assertEquals(this.usuario.getNome(), saque.getUsuario());
	}

	@Test
	public void buscarCaixaEletronicoSaques() throws Exception {
		Collection<Saque> saques = this.saqueService.buscarCaixaEletronicoSaques(transacao.getCaixaEletronico());
		Assert.assertEquals(1, saques.size());

		Saque saque = saques.stream().findAny().get();

		Assert.assertNotNull(saque);
		Assert.assertEquals(this.caixaEletronico.getNome(), saque.getCaixaEletronico());
	}
}