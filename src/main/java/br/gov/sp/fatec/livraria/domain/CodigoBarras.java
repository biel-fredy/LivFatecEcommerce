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
@Table(name = "CODIGOS_BARRA")
public class CodigoBarras implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CBR_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CBR")
	@SequenceGenerator(name = "SQ_CBR", sequenceName = "SQ_CBR", allocationSize = 1)
	private Long id;

	@Column(name = "CBR_CODIGO")
	private String codigo;

	@Column(name = "CBR_URL")
	private String url;

	@OneToOne(mappedBy = "codBarras")
	private Livro livro;

	public CodigoBarras() {
	}

	public CodigoBarras(Long id, String codigo, String url, Livro livro) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.url = url;
		this.livro = livro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		CodigoBarras other = (CodigoBarras) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
