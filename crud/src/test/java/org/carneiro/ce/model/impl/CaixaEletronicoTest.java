package org.carneiro.ce.model.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author heitor
 * @since 01/02/17
 */
public class CaixaEletronicoTest {
	private CaixaEletronico caixaEletronico;

	@Before
	public void setUp() throws Exception {
		this.caixaEletronico = new CaixaEletronico();
		this.caixaEletronico.setNome("Caixa 1");
		this.caixaEletronico.setSaldo(180);

		this.caixaEletronico.setNotas(Arrays.asList(new Nota(10, 1), new Nota(20, 1),
				new Nota(50, 1), new Nota(100, 2)));
	}

	@After
	public void tearDown() throws Exception {
		this.caixaEletronico = null;
	}

	@Test
	public void getNome() throws Exception {
		Assert.assertEquals("Caixa 1", this.caixaEletronico.getNome());
	}

	@Test
	public void setNome() throws Exception {
		this.caixaEletronico.setNome("Caixa 2");
		Assert.assertEquals("Caixa 2", this.caixaEletronico.getNome());
	}

	@Test
	public void getSaldo() throws Exception {
		Assert.assertEquals(new Integer(180), this.caixaEletronico.getSaldo());
	}

	@Test
	public void setSaldo() throws Exception {
		this.caixaEletronico.setSaldo(200);
		Assert.assertEquals(new Integer(200), this.caixaEletronico.getSaldo());
	}

	@Test
	public void getNotas() throws Exception {
		Assert.assertEquals(4, this.caixaEletronico.getNotas().size());

		Assert.assertEquals(100, this.caixaEletronico.getNotas().stream().mapToInt(Nota::getNota).max().getAsInt());
		Assert.assertEquals(10, this.caixaEletronico.getNotas().stream().mapToInt(Nota::getNota).min().getAsInt());
		Assert.assertEquals(280, this.caixaEletronico.getNotas().stream().mapToInt(nota -> nota.getQuantidade() * nota.getNota()).sum());
	}

	@Test
	public void setNotas() throws Exception {
		this.caixaEletronico.setNotas(null);
		Assert.assertNull(this.caixaEletronico.getNotas());
	}

	@Test
	public void equals() throws Exception {
		CaixaEletronico outro = new CaixaEletronico();
		outro.setNome("Caixa 1");
		outro.setSaldo(180);

		outro.setNotas(Arrays.asList(new Nota(10, 1), new Nota(20, 1),
				new Nota(50, 1), new Nota(100, 2)));

		Assert.assertEquals(this.caixaEletronico, outro);
	}

}