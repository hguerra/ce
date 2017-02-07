package org.carneiro.ce.engine;

import org.carneiro.ce.model.Transacao;

/**
 * @author heitor
 * @since 02/02/17
 */
public interface Operacao {
	Transacao calcular(Transacao transacao);
}
