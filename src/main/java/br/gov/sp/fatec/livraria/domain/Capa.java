package br.gov.sp.fatec.livraria.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CAPAS")
public class Capa implements Serializable {
	
	 private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "CAP_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CAP")
    @SequenceGenerator(name = "SQ_CAP", sequenceName = "SQ_CAP", allocationSize = 1)
	private Long id;

	@Column(name = "CAP_URL")
	private String url;

	@Column(name = "CAP_DESCRICAO")
	private String descricao;
	
	@OneToOne(mappedBy = "capa")
	private Livro livro;
	
	public Capa() {
	}	

	public Capa(Long id, String url, String descricao, Livro livro) {
		super();
		this.id = id;
		this.url = url;
		this.descricao = descricao;
		this.livro = livro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Capa other = (Capa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
