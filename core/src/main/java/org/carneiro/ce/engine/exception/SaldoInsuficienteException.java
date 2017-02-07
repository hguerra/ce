package org.carneiro.ce.engine.exception;

/**
 * @author heitor
 * @since 06/02/17
 */
public class SaldoInsuficienteException extends RuntimeException {
	public SaldoInsuficienteException(String nome, Integer saldo, Integer valor) {
		super(String.format("'%s' nao possui saldo (R$%d) suficiente para retirar o valor R$%d.", nome, saldo, valor));
	}
}
