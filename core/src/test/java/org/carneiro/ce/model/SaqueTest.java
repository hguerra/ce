package org.carneiro.ce.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author heitor
 * @since 02/02/17
 */
public class SaqueTest {
	private Saque saque;

	@Before
	public void setUp() throws Exception {
		this.saque = new Saque();
		this.saque.setUsuario("UsuarioTest");
		this.saque.setCaixaEletronico("CaixaTest");
		this.saque.setValor(200);
		this.saque.setNotas(Arrays.asList(new Nota(10, 1), new Nota(20, 1),
				new Nota(50, 1), new Nota(100, 1)));
	}

	@After
	public void tearDown() throws Exception {
		this.saque = null;
	}

	@Test
	public void getUsuario() throws Exception {
		Assert.assertEquals("UsuarioTest", this.saque.getUsuario());
	}

	@Test
	public void setUsuario() throws Exception {
		this.saque.setUsuario("Usuario1");
		Assert.assertEquals("Usuario1", this.saque.getUsuario());
	}

	@Test
	public void getCaixaEletronico() throws Exception {
		Assert.assertEquals("CaixaTest", this.saque.getCaixaEletronico());
	}

	@Test
	public void setCaixaEletronico() throws Exception {
		this.saque.setCaixaEletronico("Caixa1");
		Assert.assertEquals("Caixa1", this.saque.getCaixaEletronico());
	}

	@Test
	public void getValor() throws Exception {
		Assert.assertEquals(new Integer(200), this.saque.getValor());
	}

	@Test
	public void setValor() throws Exception {
		this.saque.setValor(500);
		Assert.assertEquals(new Integer(500), this.saque.getValor());
	}

	@Test
	public void getNotas() throws Exception {
		Assert.assertEquals(4, this.saque.getNotas().size());

		Assert.assertEquals(100, this.saque.getNotas().stream().mapToInt(Nota::getNota).max().getAsInt());
		Assert.assertEquals(10, this.saque.getNotas().stream().mapToInt(Nota::getNota).min().getAsInt());
		Assert.assertEquals(180, this.saque.getNotas().stream().mapToInt(Nota::getNota).sum());

		this.saque.getNotas().forEach(nota -> Assert.assertEquals(new Integer(1), nota.getQuantidade()));
	}

	@Test
	public void setNotas() throws Exception {
		this.saque.setNotas(null);
		Assert.assertNull(this.saque.getNotas());
	}

	@Test
	public void equals() throws Exception {
		Saque outra = new Saque();
		outra.setUsuario("UsuarioTest");
		outra.setCaixaEletronico("CaixaTest");
		outra.setValor(200);
		outra.setNotas(Arrays.asList(new Nota(10, 1), new Nota(20, 1),
				new Nota(50, 1), new Nota(100, 1)));

		Assert.assertEquals(this.saque, outra);
	}

	@Test
	public void toStringJSON() throws Exception {
		Assert.assertEquals("{\n" +
				"  \"usuario\" : \"UsuarioTest\",\n" +
				"  \"caixaEletronico\" : \"CaixaTest\",\n" +
				"  \"valor\" : 200,\n" +
				"  \"notas\" : [ {\n" +
				"    \"nota\" : 10,\n" +
				"    \"quantidade\" : 1\n" +
				"  }, {\n" +
				"    \"nota\" : 20,\n" +
				"    \"quantidade\" : 1\n" +
				"  }, {\n" +
				"    \"nota\" : 50,\n" +
				"    \"quantidade\" : 1\n" +
				"  }, {\n" +
				"    \"nota\" : 100,\n" +
				"    \"quantidade\" : 1\n" +
				"  } ]\n" +
				"}", this.saque.toString());
	}
}