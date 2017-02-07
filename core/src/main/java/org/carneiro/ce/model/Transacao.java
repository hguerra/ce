package org.carneiro.ce.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.Collection;

/**
 * @author heitor
 * @since 02/02/17
 */
public class Transacao {
	private String usuario;
	private String caixaEletronico;
	private Integer usuarioSaldo;
	private Integer caixaEletronicoSaldo;
	private Integer valor;
	private Collection<Nota> notas;

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

	public Collection<Nota> getNotas() {
		return notas;
	}

	public void setNotas(Collection<Nota> notas) {
		this.notas = notas;
	}

	public Integer getUsuarioSaldo() {
		return usuarioSaldo;
	}

	public void setUsuarioSaldo(Integer usuarioSaldo) {
		this.usuarioSaldo = usuarioSaldo;
	}

	public Integer getCaixaEletronicoSaldo() {
		return caixaEletronicoSaldo;
	}

	public void setCaixaEletronicoSaldo(Integer caixaEletronicoSaldo) {
		this.caixaEletronicoSaldo = caixaEletronicoSaldo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Transacao transacao = (Transacao) o;

		return usuario.equals(transacao.usuario) && caixaEletronico.equals(transacao.caixaEletronico) && usuarioSaldo.equals(transacao.usuarioSaldo) && caixaEletronicoSaldo.equals(transacao.caixaEletronicoSaldo) && valor.equals(transacao.valor) && notas.equals(transacao.notas);
	}

	@Override
	public int hashCode() {
		int result = usuario.hashCode();
		result = 31 * result + caixaEletronico.hashCode();
		result = 31 * result + usuarioSaldo.hashCode();
		result = 31 * result + caixaEletronicoSaldo.hashCode();
		result = 31 * result + valor.hashCode();
		result = 31 * result + notas.hashCode();
		return result;
	}

	@Override
	public String toString() {
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			return ow.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return super.toString();
		}
	}
}
