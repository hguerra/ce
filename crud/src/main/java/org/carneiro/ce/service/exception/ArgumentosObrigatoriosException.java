package org.carneiro.ce.service.exception;

import java.util.Arrays;

/**
 * @author heitor
 * @since 02/02/17
 */
public class ArgumentosObrigatoriosException extends RuntimeException {
	public ArgumentosObrigatoriosException(String... argumentos) {
		super(String.format("Argumentos %s sao obrigatorios.", Arrays.toString(argumentos)));
	}
}
