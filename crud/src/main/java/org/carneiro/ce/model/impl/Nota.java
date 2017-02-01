package org.carneiro.ce.model.impl;

import org.carneiro.ce.model.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author heitor
 * @since 28/01/17
 */
@Entity
public class Nota extends AbstractEntity {
	@NotNull(message = "Argumento 'nota' deve ser maior o igual a 10.")
	@Min(value = 10)
	private Integer nota;

	@NotNull(message = "Argumento 'quantidade' deve ser maior o igual a 1.")
	@Min(value = 1)
	private Integer quantidade;

	public Nota() {}

	public Nota(Integer nota, Integer quantidade) {
		this.nota = nota;
		this.quantidade = quantidade;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Nota nota1 = (Nota) o;

		return nota.equals(nota1.nota) && quantidade.equals(nota1.quantidade);
	}

	@Override
	public int hashCode() {
		int result = nota.hashCode();
		result = 31 * result + quantidade.hashCode();
		return result;
	}
}
