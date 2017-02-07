package org.carneiro.ce.engine;

import org.carneiro.ce.engine.exception.NotasInsuficientesException;
import org.carneiro.ce.engine.exception.SaldoInsuficienteException;
import org.carneiro.ce.engine.exception.ValorInvalidoException;
import org.carneiro.ce.model.Nota;
import org.carneiro.ce.model.Transacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author heitor
 * @since 02/02/17
 */
public class OperacaoSaque implements Operacao {
	@Override
	public Transacao calcular(Transacao transacao) {
		Integer valor = transacao.getValor();
		if (valor % 10 != 0) {
			throw new ValorInvalidoException();
		}

		if (valor > transacao.getUsuarioSaldo()) {
			throw new SaldoInsuficienteException(transacao.getUsuario(), transacao.getUsuarioSaldo(), valor);
		}

		if (valor > transacao.getCaixaEletronicoSaldo()) {
			throw new SaldoInsuficienteException(transacao.getCaixaEletronico(), transacao.getCaixaEletronicoSaldo(), valor);
		}

		List<Nota> notas = new ArrayList<>(transacao.getNotas());
		Collections.sort(notas);

		Transacao resposta = new Transacao();
		resposta.setUsuario(transacao.getUsuario());
		resposta.setUsuarioSaldo(transacao.getUsuarioSaldo() - valor);
		resposta.setCaixaEletronico(transacao.getCaixaEletronico());
		resposta.setCaixaEletronicoSaldo(transacao.getCaixaEletronicoSaldo() - valor);
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
			throw new NotasInsuficientesException(transacao.getCaixaEletronico());
		}

		resposta.setValor(transacao.getValor());
		return resposta;
	}
}
