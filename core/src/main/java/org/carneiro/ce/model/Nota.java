package org.carneiro.ce.model;


/**
 * @author heitor
 * @since 02/02/17
 */
public class Nota {
	private Integer nota;
	private Integer quantidade;

	public Nota() {
	}

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
