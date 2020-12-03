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
@Table(name = "AUTORES")
public class Autor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "AUT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_AUT")
	@SequenceGenerator(name = "SQ_AUT", sequenceName = "SQ_AUT", allocationSize = 1)
	private Long id;

	@Column(name = "AUT_NOME")
	private String nome;

	@ManyToMany(mappedBy = "autores")
	private List<Livro> livros = new ArrayList<Livro>();

	public Autor() {
	}

	public Autor(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Autor other = (Autor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
