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
public class TransacaoTest {
	private Transacao transacao;

	@Before
	public void setUp() throws Exception {
		this.transacao = new Transacao();
		this.transacao.setUsuario("UsuarioTest");
		this.transacao.setUsuarioSaldo(2000);
		this.transacao.setCaixaEletronico("CaixaTest");
		this.transacao.setCaixaEletronicoSaldo(1800);
		this.transacao.setValor(200);
		this.transacao.setNotas(Arrays.asList(new Nota(10, 1), new Nota(20, 1),
				new Nota(50, 1), new Nota(100, 1)));
	}

	@After
	public void tearDown() throws Exception {
		this.transacao = null;
	}

	@Test
	public void getUsuario() throws Exception {
		Assert.assertEquals("UsuarioTest", this.transacao.getUsuario());
	}

	@Test
	public void setUsuario() throws Exception {
		this.transacao.setUsuario("Usuario1");
		Assert.assertEquals("Usuario1", this.transacao.getUsuario());
	}

	@Test
	public void getCaixaEletronico() throws Exception {
		Assert.assertEquals("CaixaTest", this.transacao.getCaixaEletronico());
	}

	@Test
	public void setCaixaEletronico() throws Exception {
		this.transacao.setCaixaEletronico("Caixa1");
		Assert.assertEquals("Caixa1", this.transacao.getCaixaEletronico());
	}

	@Test
	public void getValor() throws Exception {
		Assert.assertEquals(new Integer(200), this.transacao.getValor());
	}

	@Test
	public void setValor() throws Exception {
		this.transacao.setValor(500);
		Assert.assertEquals(new Integer(500), this.transacao.getValor());
	}

	@Test
	public void getNotas() throws Exception {
		Assert.assertEquals(4, this.transacao.getNotas().size());

		Assert.assertEquals(100, this.transacao.getNotas().stream().mapToInt(Nota::getNota).max().getAsInt());
		Assert.assertEquals(10, this.transacao.getNotas().stream().mapToInt(Nota::getNota).min().getAsInt());
		Assert.assertEquals(180, this.transacao.getNotas().stream().mapToInt(Nota::getNota).sum());

		this.transacao.getNotas().forEach(nota -> Assert.assertEquals(new Integer(1), nota.getQuantidade()));
	}

	@Test
	public void setNotas() throws Exception {
		this.transacao.setNotas(null);
		Assert.assertNull(this.transacao.getNotas());
	}

	@Test
	public void equals() throws Exception {
		Transacao outra = new Transacao();
		outra.setUsuario("UsuarioTest");
		outra.setUsuarioSaldo(2000);
		outra.setCaixaEletronico("CaixaTest");
		outra.setCaixaEletronicoSaldo(1800);
		outra.setValor(200);
		outra.setNotas(Arrays.asList(new Nota(10, 1), new Nota(20, 1),
				new Nota(50, 1), new Nota(100, 1)));

		Assert.assertEquals(this.transacao, outra);
	}

	@Test
	public void toStringJSON() throws Exception {
		Assert.assertEquals("{\n" +
				"  \"usuario\" : \"UsuarioTest\",\n" +
				"  \"caixaEletronico\" : \"CaixaTest\",\n" +
				"  \"usuarioSaldo\" : 2000,\n" +
				"  \"caixaEletronicoSaldo\" : 1800,\n" +
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
				"}", this.transacao.toString());
	}
}