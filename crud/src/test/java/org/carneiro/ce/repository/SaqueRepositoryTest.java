package org.carneiro.ce.repository;

import org.carneiro.ce.Application;
import org.carneiro.ce.model.impl.Saque;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author heitor
 * @since 01/02/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Application.class)
public class SaqueRepositoryTest {
	private Saque saque;

	@Autowired
	private SaqueRepository saqueRepository;

	public void setSaqueRepository(SaqueRepository saqueRepository) {
		this.saqueRepository = saqueRepository;
	}

	@Before
	public void setUp() throws Exception {
		this.saque = new Saque();
		this.saque.setUsuario("UsuarioTest");
		this.saque.setCaixaEletronico("CaixaTest");
		this.saque.setData(LocalDateTime.now());
		this.saque.setValor(20);
		saqueRepository.save(this.saque);
	}

	@After
	public void tearDown() throws Exception {
		saqueRepository.delete(this.saque);
		this.saque = null;
	}

	@Test
	public void repositoryNotNull() throws Exception {
		Assert.assertNotNull(saqueRepository);
	}

	@Test
	public void findByUsuario() throws Exception {
		Collection<Saque> byUsuario = saqueRepository.findByUsuario("UsuarioTest");

		Assert.assertNotNull(byUsuario);
		Assert.assertEquals(1, byUsuario.size());

		saque = byUsuario.stream().findFirst().get();
		Assert.assertEquals("UsuarioTest", saque.getUsuario());
		Assert.assertEquals("CaixaTest", saque.getCaixaEletronico());
	}

	@Test
	public void findByCaixaEletronico() throws Exception {
		Collection<Saque> byCaixa = saqueRepository.findByCaixaEletronico("CaixaTest");

		Assert.assertNotNull(byCaixa);
		Assert.assertEquals(1, byCaixa.size());

		saque = byCaixa.stream().findFirst().get();
		Assert.assertEquals("UsuarioTest", saque.getUsuario());
		Assert.assertEquals("CaixaTest", saque.getCaixaEletronico());
	}
}