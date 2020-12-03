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
@Table(name = "SITUACOES_CADASTRO")
public class SituacaoCadastro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SCD_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SCD")
	@SequenceGenerator(name = "SQ_SCD", sequenceName = "SQ_SCD", allocationSize = 1)
	private Long id;

	@Column(name = "SCD_JUSTIFICATIVA")
	private String justificativa;

	@Column(name = "SCD_DATA_MUDANCA")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataMudancaStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCD_LIV_ID")
	private Livro livro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCD_CTS_ID")
	private CategoriaStatus categoriaStatus;

	public SituacaoCadastro() {
	}

	public SituacaoCadastro(Long id, String justificativa, Date dataMudancaStatus, Livro livro,
			CategoriaStatus categoriaStatus) {
		super();
		this.id = id;
		this.justificativa = justificativa;
		this.dataMudancaStatus = dataMudancaStatus;
		this.livro = livro;
		this.categoriaStatus = categoriaStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public Date getDataMudancaStatus() {
		return dataMudancaStatus;
	}

	public void setDataMudancaStatus(Date dataMudancaStatus) {
		this.dataMudancaStatus = dataMudancaStatus;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public CategoriaStatus getCategoriaStatus() {
		return categoriaStatus;
	}

	public void setCategoriaStatus(CategoriaStatus categoriaStatus) {
		this.categoriaStatus = categoriaStatus;
	}

}
