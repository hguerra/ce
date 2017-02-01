package org.carneiro.ce.repository;

import org.carneiro.ce.model.impl.Saque;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author heitor
 * @since 01/02/17
 */
public interface SaqueRepository extends JpaRepository<Saque, Long> {
}
