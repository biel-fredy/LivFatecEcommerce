package br.gov.sp.fatec.livraria.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "LIVROS")
public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LIV_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_LIV")
	@SequenceGenerator(name = "SQ_LIV", sequenceName = "SQ_LIV", allocationSize = 1)
	private Long id;

	@Column(name = "LIV_TITULO")
	private String titulo;

	@Column(name = "LIV_ANO")
	private Integer ano;

	@Column(name = "LIV_EDITORA")
	private String editora;

	@Column(name = "LIV_EDICAO")
	private String edicao;

	@Column(name = "LIV_ISBN")
	private String isbn;

	@Column(name = "LIV_NUM_PAG")
	private Integer numeroPaginas;

	@Column(name = "LIV_SINOPSE")
	private String sinopse;

	@Column(name = "LIV_PRECO_CAPA")
	private Double precoCapa;

	@Column(name = "LIV_PRECO_VENDA")
	private Double precoVenda;

	@Column(name = "LIV_PESO")
	private Double peso;

	@Column(name = "LIV_PROFUNDIDADE")
	private Double profundidade;

	@Column(name = "LIV_STATUS")
	private Boolean status = false;

	@ManyToMany
	@JoinTable(name = "ESCREVEM", joinColumns = @JoinColumn(name = "ESC_LIV_ID"), inverseJoinColumns = @JoinColumn(name = "ESC_AUT_ID"))
	private List<Autor> autores = new ArrayList<Autor>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIV_DIM_ID")
	private Dimensao dimensoes;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LIV_CAP_ID", referencedColumnName = "CAP_ID")
	private Capa capa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIV_PRC_ID")
	private Precificacao precificacao;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LIV_CBR_ID", referencedColumnName = "CBR_ID")
	private CodigoBarras codBarras;

	@ManyToMany
	@JoinTable(name = "CONJUNTOS_CATEGORIAS", joinColumns = @JoinColumn(name = "CCT_LIV_ID"), inverseJoinColumns = @JoinColumn(name = "CCT_CAT_ID"))
	private List<Categoria> categorias = new ArrayList<Categoria>();

	@OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<SituacaoCadastro> situacaoCadastro = new ArrayList<SituacaoCadastro>();

	@OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
	private List<Estoque> estoques = new ArrayList<Estoque>();

	@OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<ItemVenda> itensVendas = new ArrayList<ItemVenda>();

	@Transient
	private Integer quantidadeEstoque;

	public Livro() {
		this.status = false;
	}

	public Livro(Long id) {
		this.id = id;
	}

	public Livro(Long id, String titulo, Integer ano, String editora, String edicao, String isbn, Integer numeroPaginas,
			String sinopse, Double precoCapa, Double precoVenda, Double peso, Double profundidade, Dimensao dimensoes,
			Capa capa, Precificacao precificacao, CodigoBarras codBarras, Boolean status) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.ano = ano;
		this.editora = editora;
		this.edicao = edicao;
		this.isbn = isbn;
		this.numeroPaginas = numeroPaginas;
		this.sinopse = sinopse;
		this.precoCapa = precoCapa;
		this.precoVenda = precoVenda;
		this.peso = peso;
		this.profundidade = profundidade;
		this.dimensoes = dimensoes;
		this.capa = capa;
		this.precificacao = precificacao;
		this.codBarras = codBarras;
		this.status = false;
	}

	public void addAutor(Autor autor) {
		this.autores.add(autor);
	}

	public void removeAutor(int indexAutor) {
		this.autores.remove(indexAutor);
	}

	public void addCategoria(Categoria categoria) {
		this.categorias.add(categoria);
	}

	public void removeCategoria(int indexCategoria) {
		this.categorias.remove(indexCategoria);
	}

	public void addEstoque(Estoque estoque) {
		this.estoques.add(estoque);
	}

	public void removeEstoque(int indexEstoque) {
		this.estoques.remove(indexEstoque);
	}

	public void carregaQuantidadeEstoque() {
		Integer totalEstoque = 0;

		for (Estoque estoque : this.estoques) {
			totalEstoque += estoque.getQuantidade();
		}

		this.quantidadeEstoque = totalEstoque;
	}

	public void addItemVenda(ItemVenda itemVenda) {
		this.itensVendas.add(itemVenda);
	}

	public void removeItemVenda(int indexItemVenda) {
		this.itensVendas.remove(indexItemVenda);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public Dimensao getDimensoes() {
		return dimensoes;
	}

	public void setDimensoes(Dimensao dimensoes) {
		this.dimensoes = dimensoes;
	}

	public Capa getCapa() {
		return capa;
	}

	public void setCapa(Capa capa) {
		this.capa = capa;
	}

	public Precificacao getPrecificacao() {
		return precificacao;
	}

	public void setPrecificacao(Precificacao precificacao) {
		this.precificacao = precificacao;
	}

	public CodigoBarras getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(CodigoBarras codBarras) {
		this.codBarras = codBarras;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<SituacaoCadastro> getSituacaoCadastro() {
		return situacaoCadastro;
	}

	public void setSituacaoCadastro(List<SituacaoCadastro> situacaoCadastro) {
		this.situacaoCadastro = situacaoCadastro;
	}

	public List<Estoque> getEstoques() {
		return estoques;
	}

	public void setEstoques(List<Estoque> estoques) {
		this.estoques = estoques;
	}

	public List<ItemVenda> getItensVendas() {
		return itensVendas;
	}

	public void setItensVendas(List<ItemVenda> itensVendas) {
		this.itensVendas = itensVendas;
	}

	public Double getPrecoCapa() {
		return precoCapa;
	}

	public void setPrecoCapa(Double precoCapa) {
		this.precoCapa = precoCapa;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(Double profundidade) {
		this.profundidade = profundidade;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
