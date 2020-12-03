package br.gov.sp.fatec.livraria.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Pais {
	
	@Column(name="END_PAIS")
	private String nomePais;
	
	public Pais() {
	}
	
	public Pais(String nomePais) {
		super();
		this.nomePais = nomePais;
	}

	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomePais == null) ? 0 : nomePais.hashCode());
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
		Pais other = (Pais) obj;
		if (nomePais == null) {
			if (other.nomePais != null)
				return false;
		} else if (!nomePais.equals(other.nomePais))
			return false;
		return true;
	}
}
