package org.carneiro.ce.service.impl;

import org.carneiro.ce.engine.Operacao;
import org.carneiro.ce.engine.OperacaoSaque;
import org.carneiro.ce.model.Transacao;
import org.carneiro.ce.service.TransacaoService;
import org.carneiro.ce.service.exception.TransacaoException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author heitor
 * @since 08/02/17
 */
@Service("transacaoService")
public class TransacaoServiceImpl implements TransacaoService {
	private final String URL = "http://0.0.0.0:8080/caixa/saque/";

	@Override
	public Transacao sacar(Transacao transacao) {
		Operacao saque = new OperacaoSaque();
		Transacao transacaoCalculada = saque.calcular(transacao);

		RestTemplate restTemplate = new RestTemplate();

		try {
			return restTemplate.postForObject(URL, transacaoCalculada, Transacao.class);
		} catch (HttpClientErrorException e) {
			throw new TransacaoException(transacao, e.getMessage());
		}
	}

}
