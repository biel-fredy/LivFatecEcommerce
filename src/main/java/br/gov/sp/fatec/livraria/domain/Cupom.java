package br.gov.sp.fatec.livraria.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CUP_STATUS", length = 1, discriminatorType = DiscriminatorType.STRING)
@Table(name = "CUPONS")
public abstract class Cupom implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CUP_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CUP")
	@SequenceGenerator(name = "SQ_CUP", sequenceName = "SQ_CUP", allocationSize = 1)
	private Long id;

	@Column(name = "CUP_CODIGO")
	private String codigo;

	@Column(name = "CUP_PORCENTAGEM_DESCONTO")
	private Double porcentagemDesconto;
	
	@Column(name = "CUP_STATUS", insertable = false, updatable = false)
	private Integer status;

	@OneToOne(mappedBy = "cupom")
	private ItemVenda itemVenda;

	public Cupom() {
	}

	public Cupom(Long id, String codigo, Double porcentagemDesconto, Integer status, ItemVenda itemVenda) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.porcentagemDesconto = porcentagemDesconto;
		this.status = status;
		this.itemVenda = itemVenda;
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

	public Double getPorcentagemDesconto() {
		return porcentagemDesconto;
	}

	public void setPorcentagemDesconto(Double porcentagemDesconto) {
		this.porcentagemDesconto = porcentagemDesconto;
	}

	public ItemVenda getItemVenda() {
		return itemVenda;
	}

	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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
		Cupom other = (Cupom) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
