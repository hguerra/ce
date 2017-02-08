package org.carneiro.ce.service.exception;

/**
 * @author heitor
 * @since 01/02/17
 */
public class CadastroExistenteException extends RuntimeException{

	public CadastroExistenteException(String tipo) {
		super(String.format("Cadastro de '%s' existente.", tipo));
	}
}
