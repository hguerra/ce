package org.carneiro.ce.service;


import org.carneiro.ce.model.impl.Saque;
import org.carneiro.ce.model.impl.Usuario;

import java.util.Collection;

/**
 * @author heitor
 * @since 28/01/17
 */

public interface UsuarioService {
	Usuario cadastrar(Usuario usuario);

	boolean deletar(Usuario usuario);

	Usuario buscar(String nome);

	Collection<Usuario> listarUsuarios();

	Collection<Saque> listarSaques(String nome);
}
