package org.carneiro.ce.model.impl;

import org.carneiro.ce.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author heitor
 * @since 30/01/17
 */
@Entity
public class Saque extends AbstractEntity {
	@NotNull(message = "Argumento 'usuario' e obrigatorio.")
	@Column(nullable = false)
	private String usuario;

	@NotNull(message = "Argumento 'caixaEletronico' e obrigatorio.")
	@Column(nullable = false)
	private String caixaEletronico;

	@NotNull(message = "Argumento 'valor' e obrigatorio.")
	@Column(nullable = false)
	private Integer valor;

	@NotNull(message = "Argumento 'data' e obrigatorio.")
	@Column(nullable = false)
	private LocalDateTime data;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCaixaEletronico() {
		return caixaEletronico;
	}

	public void setCaixaEletronico(String caixaEletronico) {
		this.caixaEletronico = caixaEletronico;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
}
