package br.gov.sp.fatec.livraria.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Estado {
	
	@Column(name="END_ESTADO")
	private String nomeEstado;
	
	@Embedded
	private Pais pais;
	
	public Estado() {
	}

	public Estado(String nomeEstado, Pais pais) {
		super();
		this.nomeEstado = nomeEstado;
		this.pais = pais;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeEstado == null) ? 0 : nomeEstado.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estado other = (Estado) obj;
		if (nomeEstado == null) {
			if (other.nomeEstado != null)
				return false;
		} else if (!nomeEstado.equals(other.nomeEstado))
			return false;
		return true;
	}
	
}
