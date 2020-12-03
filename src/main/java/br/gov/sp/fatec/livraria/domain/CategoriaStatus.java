package br.gov.sp.fatec.livraria.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CST_TIPO", length = 1, discriminatorType = DiscriminatorType.STRING)
@Table(name = "CATEGORIAS_STATUS")
public abstract class CategoriaStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CST_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CST")
	@SequenceGenerator(name = "SQ_CST", sequenceName = "SQ_CST", allocationSize = 1)
	private Long id;

	@Column(name = "CST_NOME")
	private String nome;

	@Column(name = "CST_DESCRICAO")
	private String descricao;

	@Column(name = "CST_TIPO", insertable = false, updatable = false)
	private Integer tipo;

	@OneToMany(mappedBy = "categoriaStatus", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<SituacaoCadastro> situacoesCadastro = new ArrayList<SituacaoCadastro>();

	public CategoriaStatus() {
	}

	public CategoriaStatus(Long id, String nome, String descricao, Integer tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.tipo = tipo;
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

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer status) {
		this.tipo = status;
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
		CategoriaStatus other = (CategoriaStatus) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
