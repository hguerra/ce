package org.carneiro.ce.model.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @author heitor
 * @since 01/02/17
 */
public class SaqueTest {
	private Saque saque;

	@Before
	public void setUp() throws Exception {
		this.saque = new Saque();
		this.saque.setCaixaEletronico("Caixa 1");
		this.saque.setUsuario("Heitor");
		this.saque.setValor(100);
		this.saque.setData(LocalDateTime.of(2017, 1, 1, 11, 30));
	}

	@After
	public void tearDown() throws Exception {
		this.saque = null;
	}

	@Test
	public void getUsuario() throws Exception {
		Assert.assertEquals("Heitor", this.saque.getUsuario());
	}

	@Test
	public void setUsuario() throws Exception {
		this.saque.setUsuario("Heitor Carneiro");
		Assert.assertEquals("Heitor Carneiro", this.saque.getUsuario());
	}

	@Test
	public void getCaixaEletronico() throws Exception {
		Assert.assertEquals("Caixa 1", this.saque.getCaixaEletronico());
	}

	@Test
	public void setCaixaEletronico() throws Exception {
		this.saque.setCaixaEletronico("Caixa 2");
		Assert.assertEquals("Caixa 2", this.saque.getCaixaEletronico());
	}

	@Test
	public void getValor() throws Exception {
		Assert.assertEquals(new Integer(100), this.saque.getValor());
	}

	@Test
	public void setValor() throws Exception {
		this.saque.setValor(200);
		Assert.assertEquals(new Integer(200), this.saque.getValor());
	}

	@Test
	public void getData() throws Exception {
		Assert.assertTrue(this.saque.getData().isEqual(LocalDateTime.of(2017, 1, 1, 11, 30)));
	}

	@Test
	public void setData() throws Exception {
		this.saque.setData(LocalDateTime.of(2017, 1, 2, 12, 0));
		Assert.assertTrue(this.saque.getData().isEqual(LocalDateTime.of(2017, 1, 2, 12, 0)));
	}
}