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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.gov.sp.fatec.livraria.domain.enums.FormaPagamento;
import br.gov.sp.fatec.livraria.domain.enums.StatusVenda;

@Entity
@Table(name = "VENDAS")
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "VND_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_VND")
	@SequenceGenerator(name = "SQ_VND", sequenceName = "SQ_VND", allocationSize = 1)
	private Long id;

	@Column(name = "VND_STATUS")
	private StatusVenda status;

	@Column(name = "VND_VALOR_FINAL")
	private Double valorFinal;

	@Column(name = "VND_FORMA_PAGAMENTO")
	private FormaPagamento formaPagamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VND_CLI_ID")
	private Cliente cliente;

	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemVenda> itensVenda = new ArrayList<ItemVenda>();

	public Venda() {
	}

	public Venda(Long id, StatusVenda status, FormaPagamento formaPagamento, Cliente cliente) {
		super();
		this.id = id;
		this.status = status;
		this.formaPagamento = formaPagamento;
		this.cliente = cliente;
	}

	public void addItemVenda(ItemVenda itemVenda) {
		this.itensVenda.add(itemVenda);
	}

	public void removeItemVenda(int indexItemVenda) {
		this.itensVenda.remove(indexItemVenda);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusVenda getStatus() {
		return status;
	}

	public void setStatus(StatusVenda status) {
		this.status = status;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
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
		Venda other = (Venda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
