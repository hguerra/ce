package org.carneiro.ce.engine;

import org.carneiro.ce.model.Saque;

/**
 * @author heitor
 * @since 02/02/17
 */
public interface Operacao {
	Saque calcular(Saque saque);
}
