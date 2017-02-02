package org.carneiro.ce.service;

import org.carneiro.ce.model.impl.CaixaEletronico;
import org.carneiro.ce.model.impl.Saque;

import java.util.Collection;

/**
 * @author heitor
 * @since 02/02/17
 */
public interface CaixaEletronicoService {
	CaixaEletronico cadastrar(CaixaEletronico caixaEletronico);

	boolean deletar(CaixaEletronico caixaEletronico);

	CaixaEletronico buscar(String nome);

	Collection<CaixaEletronico> listarCaixasEletronicos();

	Collection<Saque> listarSaques(String nome);
}
