package org.carneiro.ce.model.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author heitor
 * @since 01/02/17
 */
public class UsuarioTest {
	private Usuario usuario;

	@Before
	public void setUp() throws Exception {
		this.usuario = new Usuario();
		this.usuario.setNome("Heitor");
		this.usuario.setSaldo(500);
	}

	@After
	public void tearDown() throws Exception {
		this.usuario = null;
	}

	@Test
	public void getNome() throws Exception {
		Assert.assertEquals("Heitor", this.usuario.getNome());
	}

	@Test
	public void setNome() throws Exception {
		this.usuario.setNome("Heitor Carneiro");
		Assert.assertEquals("Heitor Carneiro", this.usuario.getNome());
	}

	@Test
	public void getSaldo() throws Exception {
		Assert.assertEquals(new Integer(500), this.usuario.getSaldo());
	}

	@Test
	public void setSaldo() throws Exception {
		this.usuario.setSaldo(this.usuario.getSaldo() - 100);
		Assert.assertEquals(new Integer(400), this.usuario.getSaldo());
	}

	@Test
	public void equals() throws Exception {
		Usuario outro = new Usuario();
		outro.setNome(this.usuario.getNome());
		outro.setSaldo(this.usuario.getSaldo());

		Assert.assertEquals(this.usuario, outro);
	}

}