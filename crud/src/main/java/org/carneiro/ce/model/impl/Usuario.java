package org.carneiro.ce.model.impl;


import org.carneiro.ce.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author heitor
 * @since 01/02/17
 */
@Entity
public class Usuario extends AbstractEntity {
	@NotNull(message = "Argumento 'nome' e obrigatorio.")
	@Column(nullable = false, unique = true)
	private String nome;

	@NotNull(message = "Argumento 'saldo' deve ser maior o igual a 10.")
	@Min(value = 10)
	private Integer saldo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Usuario usuario = (Usuario) o;

		return nome.equals(usuario.nome) && saldo.equals(usuario.saldo);
	}

	@Override
	public int hashCode() {
		int result = nome.hashCode();
		result = 31 * result + saldo.hashCode();
		return result;
	}
}
