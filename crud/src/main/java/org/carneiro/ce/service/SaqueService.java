package org.carneiro.ce.service;

import org.carneiro.ce.model.impl.Saque;
import org.carneiro.ce.model.impl.Transacao;

import java.util.Collection;

/**
 * @author heitor
 * @since 07/02/17
 */
public interface SaqueService {
	Saque sacar(Transacao transacao);

	Collection<Saque> buscarUsuarioSaques(String usuario);

	Collection<Saque> buscarCaixaEletronicoSaques(String caixaEletronico);
}
