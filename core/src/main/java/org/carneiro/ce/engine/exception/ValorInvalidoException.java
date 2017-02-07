package org.carneiro.ce.engine.exception;

/**
 * @author heitor
 * @since 02/02/17
 */
public class ValorInvalidoException extends RuntimeException {
	public ValorInvalidoException() {
		super("Argumento 'valor' deve ser multiplo de 10.");
	}
}
