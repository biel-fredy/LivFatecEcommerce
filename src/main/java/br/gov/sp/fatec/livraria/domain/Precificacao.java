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
@Table(name = "PRECIFICACOES")
public class Precificacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PRC_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRC")
	@SequenceGenerator(name = "SQ_PRC", sequenceName = "SQ_PRC", allocationSize = 1)
	private Long id;

	@Column(name = "PRC_NOME")
	private String nome;

	@Column(name = "PRC_PORCENTAGEM_LUCRO")
	private Double porcentagemLucro;

	@Column(name = "PRC_PORCENTAGEM_DESCONTO")
	private Double porcentagemDesconto;

	@OneToMany(mappedBy = "precificacao", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<Livro> livros = new ArrayList<Livro>();

	public Precificacao() {
	}

	public Precificacao(Long id) {
		this.id = id;
	}

	public Precificacao(Long id, String nome, Double porcentagemLucro, Double porcentagemDesconto) {
		super();
		this.id = id;
		this.nome = nome;
		this.porcentagemLucro = porcentagemLucro;
		this.porcentagemDesconto = porcentagemDesconto;
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

	public Double getPorcentagemLucro() {
		return porcentagemLucro;
	}

	public void setPorcentagemLucro(Double porcentagemLucro) {
		this.porcentagemLucro = porcentagemLucro;
	}

	public Double getPorcentagemDesconto() {
		return porcentagemDesconto;
	}

	public void setPorcentagemDesconto(Double porcentagemDesconto) {
		this.porcentagemDesconto = porcentagemDesconto;
	}

	public List<Livro> getLivros() {
		return livros;
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
		Precificacao other = (Precificacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
