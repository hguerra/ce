package org.carneiro.ce.engine;

import org.carneiro.ce.engine.exception.NotasInsuficientesException;
import org.carneiro.ce.engine.exception.SaldoInsuficienteException;
import org.carneiro.ce.engine.exception.ValorInvalidoException;
import org.carneiro.ce.model.Nota;
import org.carneiro.ce.model.Transacao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author heitor
 * @since 02/02/17
 */
public class OperacaoTest {
	private Operacao operacao;
	private Transacao requisicao;

	@Before
	public void setUp() throws Exception {
		this.operacao = new OperacaoSaque();
		this.requisicao = new Transacao();
		this.requisicao.setUsuario("UsuarioTest");
		this.requisicao.setCaixaEletronico("CaixaTest");

		this.requisicao.setUsuarioSaldo(2000);
	}

	@After
	public void tearDown() throws Exception {
		this.requisicao = null;
		this.operacao = null;
	}

	@Test
	public void calcularSaqueZero() throws Exception {
		setCaixaEletronicoSaldo(this.requisicao, Arrays.asList(new Nota(10, 10),
				new Nota(20, 10), new Nota(50, 10), new Nota(100, 10)));

		this.requisicao.setValor(0);
		Transacao resposta = operacao.calcular(requisicao);

		Assert.assertNotNull(resposta);
		Assert.assertEquals(this.requisicao.getUsuarioSaldo(), resposta.getUsuarioSaldo());
		Assert.assertEquals(this.requisicao.getCaixaEletronicoSaldo(), resposta.getCaixaEletronicoSaldo());

		Map<Integer, Integer> notas = getMapNotas(resposta);
		Assert.assertEquals(new Integer(10), notas.get(100));
		Assert.assertEquals(new Integer(10), notas.get(50));
		Assert.assertEquals(new Integer(10), notas.get(20));
		Assert.assertEquals(new Integer(10), notas.get(10));
	}

	@Test
	public void calcularSaqueValorMultiplo() throws Exception {
		setCaixaEletronicoSaldo(this.requisicao, Arrays.asList(new Nota(10, 10),
				new Nota(20, 10), new Nota(50, 10), new Nota(100, 10)));

		this.requisicao.setValor(200);
		Transacao resposta = operacao.calcular(requisicao);

		Assert.assertNotNull(resposta);
		Assert.assertEquals(new Integer(1800), resposta.getUsuarioSaldo());
		Assert.assertEquals(new Integer(1600), resposta.getCaixaEletronicoSaldo());
		Assert.assertEquals(new Integer(200), resposta.getValor());

		Map<Integer, Integer> notas = getMapNotas(resposta);
		Assert.assertEquals(new Integer(8), notas.get(100));
		Assert.assertEquals(new Integer(10), notas.get(50));
		Assert.assertEquals(new Integer(10), notas.get(20));
		Assert.assertEquals(new Integer(10), notas.get(10));
	}

	@Test
	public void calcularSaqueValorNaoMultiplo() throws Exception {
		setCaixaEletronicoSaldo(this.requisicao, Arrays.asList(new Nota(10, 10),
				new Nota(20, 10), new Nota(50, 10), new Nota(100, 10)));

		this.requisicao.setValor(550);
		Transacao resposta = operacao.calcular(requisicao);

		Assert.assertNotNull(resposta);
		Assert.assertEquals(new Integer(1450), resposta.getUsuarioSaldo());
		Assert.assertEquals(new Integer(1250), resposta.getCaixaEletronicoSaldo());

		Map<Integer, Integer> notas = getMapNotas(resposta);
		Assert.assertEquals(new Integer(5), notas.get(100));
		Assert.assertEquals(new Integer(9), notas.get(50));
		Assert.assertEquals(new Integer(10), notas.get(20));
		Assert.assertEquals(new Integer(10), notas.get(10));

		this.requisicao.setValor(330);
		resposta = operacao.calcular(requisicao);

		Assert.assertNotNull(resposta);
		Assert.assertEquals(new Integer(1670), resposta.getUsuarioSaldo());
		Assert.assertEquals(new Integer(1470), resposta.getCaixaEletronicoSaldo());

		notas = getMapNotas(resposta);
		Assert.assertEquals(new Integer(7), notas.get(100));
		Assert.assertEquals(new Integer(10), notas.get(50));
		Assert.assertEquals(new Integer(9), notas.get(20));
		Assert.assertEquals(new Integer(9), notas.get(10));

		this.requisicao.setValor(80);
		resposta = operacao.calcular(requisicao);

		Assert.assertNotNull(resposta);
		Assert.assertEquals(new Integer(1920), resposta.getUsuarioSaldo());
		Assert.assertEquals(new Integer(1720), resposta.getCaixaEletronicoSaldo());

		notas = getMapNotas(resposta);
		Assert.assertEquals(new Integer(10), notas.get(100));
		Assert.assertEquals(new Integer(9), notas.get(50));
		Assert.assertEquals(new Integer(9), notas.get(20));
		Assert.assertEquals(new Integer(9), notas.get(10));
	}

	@Test
	public void calcularSaqueValorDiferentesQuantidadesNotas() throws Exception {
		setCaixaEletronicoSaldo(this.requisicao, Arrays.asList(new Nota(10, 10),
				new Nota(20, 0), new Nota(50, 10), new Nota(100, 10)));

		this.requisicao.setValor(80);
		Transacao resposta = operacao.calcular(requisicao);

		Assert.assertNotNull(resposta);
		Assert.assertEquals(new Integer(1920), resposta.getUsuarioSaldo());
		Assert.assertEquals(new Integer(1520), resposta.getCaixaEletronicoSaldo());

		Map<Integer, Integer> notas = getMapNotas(resposta);
		Assert.assertEquals(new Integer(10), notas.get(100));
		Assert.assertEquals(new Integer(9), notas.get(50));
		Assert.assertEquals(new Integer(0), notas.get(20));
		Assert.assertEquals(new Integer(7), notas.get(10));
	}

	@Test(expected = ValorInvalidoException.class)
	public void calcularSaqueValorInvalido() throws Exception {
		setCaixaEletronicoSaldo(this.requisicao, Arrays.asList(new Nota(10, 10),
				new Nota(20, 10), new Nota(50, 10), new Nota(100, 10)));

		this.requisicao.setValor(33);
		operacao.calcular(requisicao);
	}

	@Test(expected = NotasInsuficientesException.class)
	public void calcularSaqueNotasInsuficientes() throws Exception {
		setCaixaEletronicoSaldo(this.requisicao, Arrays.asList(new Nota(10, 0),
				new Nota(20, 2), new Nota(0, 0), new Nota(100, 0)));

		this.requisicao.setValor(30);
		operacao.calcular(requisicao);
	}

	@Test(expected = SaldoInsuficienteException.class)
	public void calcularSaqueUsuarioSaldoInsuficientes() throws Exception {
		setCaixaEletronicoSaldo(this.requisicao, Arrays.asList(new Nota(10, 10),
				new Nota(20, 10), new Nota(50, 10), new Nota(100, 10)));

		this.requisicao.setValor(10);
		this.requisicao.setUsuarioSaldo(0);
		operacao.calcular(requisicao);
	}

	@Test(expected = SaldoInsuficienteException.class)
	public void calcularCaixaEletronicoSaldoInsuficientes() throws Exception {
		setCaixaEletronicoSaldo(this.requisicao, Arrays.asList(new Nota(10, 0),
				new Nota(20, 0), new Nota(0, 0), new Nota(100, 0)));

		this.requisicao.setValor(50);
		operacao.calcular(requisicao);
	}

	private static void setCaixaEletronicoSaldo(Transacao transacao, Collection<Nota> notas) {
		transacao.setNotas(notas);
		transacao.setCaixaEletronicoSaldo(notas.stream().mapToInt(n -> n.getNota() * n.getQuantidade()).sum());
	}

	private static Map<Integer, Integer> getMapNotas(Transacao transacao) {
		return transacao.getNotas().stream().collect(Collectors.toMap(Nota::getNota, Nota::getQuantidade));
	}
}