package org.carneiro.ce.service;

import org.carneiro.ce.Application;
import org.carneiro.ce.model.impl.CaixaEletronico;
import org.carneiro.ce.model.impl.Nota;
import org.carneiro.ce.model.impl.Saque;
import org.carneiro.ce.repository.SaqueRepository;
import org.carneiro.ce.service.exception.ArgumentosObrigatoriosException;
import org.carneiro.ce.service.exception.CadastroExistenteException;
import org.carneiro.ce.service.exception.NotasInvalidasException;
import org.carneiro.ce.service.exception.SaldoInvalidoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author heitor
 * @since 02/02/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Application.class)
public class CaixaEletronicoServiceTest {
	private CaixaEletronico caixaEletronico;

	@Autowired
	private CaixaEletronicoService caixaEletronicoService;

	@Autowired
	private SaqueRepository saqueRepository;

	public void setCaixaEletronicoService(CaixaEletronicoService caixaEletronicoService) {
		this.caixaEletronicoService = caixaEletronicoService;
	}

	public void setSaqueRepository(SaqueRepository saqueRepository) {
		this.saqueRepository = saqueRepository;
	}

	@Before
	public void setUp() throws Exception {
		this.caixaEletronico = new CaixaEletronico();
		this.caixaEletronico.setNome("CaixaTest");
		this.caixaEletronico.setSaldo(280);
		this.caixaEletronico.setNotas(Arrays.asList(new Nota(10, 1), new Nota(20, 1),
				new Nota(50, 1), new Nota(100, 2)));
	}

	@After
	public void tearDown() throws Exception {
		this.caixaEletronicoService.deletar(this.caixaEletronico);
		this.caixaEletronico = null;
	}

	@Test
	public void serviceNotNull() throws Exception {
		Assert.assertNotNull(this.caixaEletronicoService);
	}

	@Test
	public void cadastrar() throws Exception {
		CaixaEletronico cadastrado = this.caixaEletronicoService.cadastrar(this.caixaEletronico);

		Assert.assertNotNull(cadastrado);
		Assert.assertEquals(this.caixaEletronico, cadastrado);
	}

	@Test(expected = ArgumentosObrigatoriosException.class)
	public void cadastrarCampoNullErro() throws Exception {
		this.caixaEletronico.setNome(null);
		this.caixaEletronicoService.cadastrar(this.caixaEletronico);
	}

	@Test(expected = SaldoInvalidoException.class)
	public void cadastrarSaldoInvalidoErro() throws Exception {
		this.caixaEletronico.setSaldo(-10);
		this.caixaEletronicoService.cadastrar(this.caixaEletronico);
	}

	@Test(expected = NotasInvalidasException.class)
	public void cadastrarSaldoNotasInvalidoErro() throws Exception {
		this.caixaEletronico.setNotas(Arrays.asList(new Nota(10, 1)));
		this.caixaEletronicoService.cadastrar(this.caixaEletronico);
	}

	@Test(expected = CadastroExistenteException.class)
	public void cadastrarExistenteErro() throws Exception {
		this.caixaEletronicoService.cadastrar(this.caixaEletronico);

		CaixaEletronico duplidaco = new CaixaEletronico();
		duplidaco.setNome(this.caixaEletronico.getNome());
		duplidaco.setSaldo(this.caixaEletronico.getSaldo());
		duplidaco.setNotas(this.caixaEletronico.getNotas());

		this.caixaEletronicoService.cadastrar(duplidaco);
	}

	@Test
	public void buscar() throws Exception {
		this.caixaEletronicoService.cadastrar(this.caixaEletronico);
		CaixaEletronico encontrado = this.caixaEletronicoService.buscar("CaixaTest");

		Assert.assertNotNull(encontrado);
		Assert.assertEquals(this.caixaEletronico.getNome(), encontrado.getNome());
	}

	@Test
	public void listarCaixasEletronicos() throws Exception {
		this.caixaEletronicoService.cadastrar(this.caixaEletronico);
		Collection<CaixaEletronico> encontrados = this.caixaEletronicoService.listarCaixasEletronicos();

		Assert.assertNotNull(encontrados);
		Assert.assertEquals(1, encontrados.size());
		Assert.assertEquals(this.caixaEletronico.getNome(), encontrados.stream().findFirst().get().getNome());
	}

	@Test
	public void listarSaques() throws Exception {
		Saque saque = new Saque();
		saque.setUsuario("UsuarioTest");
		saque.setCaixaEletronico(this.caixaEletronico.getNome());
		saque.setData(LocalDateTime.now());
		saque.setValor(20);
		this.saqueRepository.save(saque);

		Collection<Saque> encontrados = this.caixaEletronicoService.listarSaques(this.caixaEletronico.getNome());

		Assert.assertNotNull(encontrados);
		Assert.assertEquals(1, encontrados.size());

		saque = encontrados.stream().findFirst().get();
		Assert.assertEquals("UsuarioTest", saque.getUsuario());
		Assert.assertEquals(this.caixaEletronico.getNome(), saque.getCaixaEletronico());
	}
}