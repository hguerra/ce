package org.carneiro.ce.service.exception;

/**
 * @author heitor
 * @since 07/02/17
 */
public class TransacaoInvalidaException extends RuntimeException {
	public TransacaoInvalidaException() {
		super("Usuario ou Caixa Eletronico nao encontrados.");
	}
}
