package org.carneiro.ce.service.exception;

/**
 * @author heitor
 * @since 28/01/17
 */
public class SaldoInvalidoException extends RuntimeException {
	public SaldoInvalidoException(Integer saldo) {
		super(String.format("Argumento 'saldo' nao pode ser menor que 10, valor recebido %d.", saldo));
	}
}
