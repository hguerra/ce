package org.carneiro.ce.engine.exception;

/**
 * @author heitor
 * @since 06/02/17
 */
public class NotasInsuficientesException extends RuntimeException {
	public NotasInsuficientesException(String caixaEletronico) {
		super(String.format("Caixa eletronico '%s' nao possui notas suficientes para o saque desejado.", caixaEletronico));
	}
}
