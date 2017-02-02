package org.carneiro.ce.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author heitor
 * @since 02/02/17
 */
public class NotaTest {
	private Nota nota;

	@Before
	public void setUp() throws Exception {
		this.nota = new Nota(50, 1);
	}

	@After
	public void tearDown() throws Exception {
		this.nota = null;
	}

	@Test
	public void getNota() throws Exception {
		Assert.assertNotNull(this.nota);
		Assert.assertEquals(new Integer(50), this.nota.getNota());
	}

	@Test
	public void setNota() throws Exception {
		this.nota.setNota(10);

		Assert.assertNotNull(this.nota);
		Assert.assertEquals(new Integer(10), this.nota.getNota());
	}

	@Test
	public void getQuantidade() throws Exception {
		Assert.assertNotNull(this.nota);
		Assert.assertEquals(new Integer(1), this.nota.getQuantidade());
	}

	@Test
	public void setQuantidade() throws Exception {
		this.nota.setQuantidade(5);

		Assert.assertNotNull(this.nota);
		Assert.assertEquals(new Integer(5), this.nota.getQuantidade());
	}

	@Test
	public void equals() throws Exception {
		Nota outra = new Nota(50, 1);
		Assert.assertNotNull(outra);
		Assert.assertEquals(this.nota, outra);
	}
}