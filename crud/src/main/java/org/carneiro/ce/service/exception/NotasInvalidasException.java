package org.carneiro.ce.service.exception;

/**
 * @author heitor
 * @since 02/02/17
 */
public class NotasInvalidasException extends RuntimeException{
	public NotasInvalidasException(Integer notasSaldo, Integer caixaEletronicoSaldo) {
		super(String.format("Somatoria das notas (%d) e diferente do saldo informado (%d).", notasSaldo, caixaEletronicoSaldo));
	}
}
