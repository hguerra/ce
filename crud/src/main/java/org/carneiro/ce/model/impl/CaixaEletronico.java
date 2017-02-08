package org.carneiro.ce.model.impl;

import org.carneiro.ce.model.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * @author heitor
 * @since 30/01/17
 */
@Entity
public class CaixaEletronico extends AbstractEntity {
	@NotNull(message = "Argumento 'nome' e obrigatorio.")
	@Column(nullable = false, unique = true)
	private String nome;

	@NotNull(message = "Argumento 'saldo' deve ser maior o igual a 10.")
	@Min(value = 10)
	private Integer saldo;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<Nota> notas;

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

	public Collection<Nota> getNotas() {
		return notas;
	}

	public void setNotas(Collection<Nota> notas) {
		this.notas = notas;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CaixaEletronico that = (CaixaEletronico) o;

		return nome.equals(that.nome) && saldo.equals(that.saldo) && notas.equals(that.notas);
	}

	@Override
	public int hashCode() {
		int result = nome.hashCode();
		result = 31 * result + saldo.hashCode();
		result = 31 * result + notas.hashCode();
		return result;
	}
}
