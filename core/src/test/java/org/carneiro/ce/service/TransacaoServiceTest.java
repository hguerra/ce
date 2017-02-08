package org.carneiro.ce.service;

import org.carneiro.ce.Application;
import org.carneiro.ce.model.Nota;
import org.carneiro.ce.model.Transacao;
import org.carneiro.ce.service.exception.TransacaoException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * @author heitor
 * @since 08/02/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class TransacaoServiceTest {
	@Autowired
	private TransacaoService transacaoService;

	public void setTransacaoService(TransacaoService transacaoService) {
		this.transacaoService = transacaoService;
	}

	@Test
	public void serviceNotNull() throws Exception {
		Assert.assertNotNull(this.transacaoService);
	}

	@Test(expected = TransacaoException.class)
	public void sacarErro() throws Exception {
		Transacao transacao = new Transacao();
		transacao.setUsuario("UsuarioTest");
		transacao.setUsuarioSaldo(2000);
		transacao.setCaixaEletronico("CaixaTest");
		transacao.setCaixaEletronicoSaldo(1800);
		transacao.setValor(200);
		transacao.setNotas(Arrays.asList(new Nota(10, 1), new Nota(20, 1),
				new Nota(50, 1), new Nota(100, 1)));

		this.transacaoService.sacar(transacao);
	}
}