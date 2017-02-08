package org.carneiro.ce.model.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author heitor
 * @since 01/02/17
 */
public class NotaTest {
	private Nota nota;

	@Before
	public void setUp() throws Exception {
		this.nota = new Nota();
		this.nota.setNota(10);
		this.nota.setQuantidade(5);
	}

	@After
	public void tearDown() throws Exception {
		this.nota = null;
	}

	@Test
	public void getNota() throws Exception {
		Assert.assertEquals(new Integer(10), this.nota.getNota());
	}

	@Test
	public void setNota() throws Exception {
		this.nota.setNota(50);
		Assert.assertEquals(new Integer(50), this.nota.getNota());
	}

	@Test
	public void getQuantidade() throws Exception {
		Assert.assertEquals(new Integer(5), this.nota.getQuantidade());
	}

	@Test
	public void setQuantidade() throws Exception {
		this.nota.setQuantidade(2);
		Assert.assertEquals(new Integer(2), this.nota.getQuantidade());
	}

	@Test
	public void equals() throws Exception {
		Nota outro = new Nota();
		outro.setNota(10);
		outro.setQuantidade(5);

		Assert.assertEquals(this.nota, outro);
	}

}