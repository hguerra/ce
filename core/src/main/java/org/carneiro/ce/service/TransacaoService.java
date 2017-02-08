package org.carneiro.ce.service;

import org.carneiro.ce.model.Transacao;

/**
 * @author heitor
 * @since 08/02/17
 */
public interface TransacaoService {
	Transacao sacar(Transacao transacao);
}
