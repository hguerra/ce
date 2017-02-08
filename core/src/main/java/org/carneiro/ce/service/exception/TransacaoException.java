package org.carneiro.ce.service.exception;

import org.carneiro.ce.model.Transacao;

/**
 * @author heitor
 * @since 08/02/17
 */
public class TransacaoException extends RuntimeException {
	public TransacaoException(Transacao transacao, String message) {
		super(String.format("Erro ao tentar realizar transacao '%s'. Causa: '%s'", transacao.toString(), message));
	}
}
