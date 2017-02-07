package org.carneiro.ce.engine;

import org.carneiro.ce.engine.exception.NotasInsuficientesException;
import org.carneiro.ce.engine.exception.SaldoInsuficienteException;
import org.carneiro.ce.engine.exception.ValorInvalidoException;
import org.carneiro.ce.model.Nota;
import org.carneiro.ce.model.Saque;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author heitor
 * @since 02/02/17
 */
public class OperacaoSaque implements Operacao {
	@Override
	public Saque calcular(Saque saque) {
		Integer valor = saque.getValor();
		if (valor % 10 != 0) {
			throw new ValorInvalidoException();
		}

		if (valor > saque.getUsuarioSaldo()) {
			throw new SaldoInsuficienteException(saque.getUsuario(), saque.getUsuarioSaldo(), valor);
		}

		if (valor > saque.getCaixaEletronicoSaldo()) {
			throw new SaldoInsuficienteException(saque.getCaixaEletronico(), saque.getCaixaEletronicoSaldo(), valor);
		}

		List<Nota> notas = new ArrayList<>(saque.getNotas());
		Collections.sort(notas);

		Saque resposta = new Saque();
		resposta.setUsuario(saque.getUsuario());
		resposta.setUsuarioSaldo(saque.getUsuarioSaldo() - valor);
		resposta.setCaixaEletronico(saque.getCaixaEletronico());
		resposta.setCaixaEletronicoSaldo(saque.getCaixaEletronicoSaldo() - valor);
		resposta.setNotas(new ArrayList<>());

		for (Nota nota: notas) {
			int quantidade = nota.getQuantidade();
			if (quantidade == 0 || valor < nota.getNota()) {
				resposta.getNotas().add(new Nota(nota.getNota(), quantidade));
				continue;
			}

			int divInt = valor / nota.getNota();
			if (divInt <= nota.getQuantidade()) {
				quantidade = nota.getQuantidade() - divInt;
			}

			valor = valor % nota.getNota();
			resposta.getNotas().add(new Nota(nota.getNota(), quantidade));
		}

		if (valor > 0) {
			throw new NotasInsuficientesException(saque.getCaixaEletronico());
		}

		resposta.setValor(valor);
		return resposta;
	}
}
