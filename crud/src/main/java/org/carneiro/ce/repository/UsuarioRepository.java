package org.carneiro.ce.repository;

import org.carneiro.ce.model.impl.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author heitor
 * @since 01/02/17
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByNome(String nome);
}
