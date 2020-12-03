package br.gov.sp.fatec.livraria.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ESTOQUES")
public class Estoque implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ETQ_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ETQ")
	@SequenceGenerator(name = "SQ_ETQ", sequenceName = "SQ_ETQ", allocationSize = 1)
	private Long id;

	@Column(name = "ETQ_QUANTIDADE")
	private Integer quantidade;

	@Column(name = "ETQ_VALOR_CUSTO")
	private Double valorCusto;

	@Column(name = "ETQ_DATA_ENTRADA")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataEntrada;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ETQ_LIV_ID")
	private Livro livro;

	public Estoque() {
	}
	
	public Estoque(Long id) {
		this.id = id;
	}

	public Estoque(Long id, Integer quantidade, Double valorCusto, Date dataEntrada, Livro livro) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.valorCusto = valorCusto;
		this.dataEntrada = dataEntrada;
		this.livro = livro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(Double valorCusto) {
		this.valorCusto = valorCusto;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}
