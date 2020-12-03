package br.gov.sp.fatec.livraria.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DIMENSOES")
public class Dimensao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DIM_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DIM")
	@SequenceGenerator(name = "SQ_DIM", sequenceName = "SQ_DIM", allocationSize = 1)
	private Long id;

	@Column(name = "DIM_NOME")
	private String nome;

	@Column(name = "DIM_ALTURA")
	private Double altura;

	@Column(name = "DIM_LARGURA")
	private Double largura;

	@OneToMany(mappedBy = "dimensoes", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Livro> livros = new ArrayList<Livro>();

	public Dimensao() {
	}

	public Dimensao(Long id) {
		this.id = id;
	}

	public Dimensao(Long id, String nome, Double altura, Double largura) {
		super();
		this.id = id;
		this.nome = nome;
		this.altura = altura;
		this.largura = largura;
	}
	
	public Dimensao(String nome, Double altura, Double largura) {
		super();
		this.nome = nome;
		this.altura = altura;
		this.largura = largura;
	}

	public void addLivro(Livro livro) {
		this.livros.add(livro);
	}

	public void removeLivro(int indexLivro) {
		this.removeLivro(indexLivro);
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

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getLargura() {
		return largura;
	}

	public void setLargura(Double largura) {
		this.largura = largura;
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
		Dimensao other = (Dimensao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
