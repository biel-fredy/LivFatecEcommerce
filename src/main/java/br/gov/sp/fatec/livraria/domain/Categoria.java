package br.gov.sp.fatec.livraria.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIAS")
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CAT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CAT")
	@SequenceGenerator(name = "SQ_CAT", sequenceName = "SQ_CAT", allocationSize = 1)
	private Long id;

	@Column(name = "CAT_NOME")
	private String nome;

	@Column(name = "CAT_DESCRICAO")
	private String descricao;

	@ManyToMany(mappedBy = "categorias")
	private List<Livro> livros = new ArrayList<Livro>();

	public Categoria() {
	}

	public Categoria(Long id, String nome, String descricao, List<Livro> livros) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.livros = livros;
	}
	
	public void addLivro(Livro livro) {
		this.livros.add(livro);
	}
	
	public void removeLivro(int indexLivro) {
		this.livros.remove(indexLivro);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
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
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
