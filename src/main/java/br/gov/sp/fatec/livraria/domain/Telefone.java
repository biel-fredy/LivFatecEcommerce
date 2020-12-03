package br.gov.sp.fatec.livraria.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.gov.sp.fatec.livraria.domain.enums.TipoTelefone;
import br.gov.sp.fatec.livraria.domain.enums.converters.TipoTelefoneConverter;

@Entity
@Table(name = "TELEFONES")
public class Telefone implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TEL")
	@SequenceGenerator(name = "SQ_TEL", sequenceName = "SQ_TEL", allocationSize = 1)
	@Column(name = "TEL_ID", updatable = false, nullable = false)
	private Long id;

	@Column(name = "TEL_DDI")
	private String ddi;

	@Column(name = "TEL_DDD")
	private String ddd;

	@Column(name = "TEL_NUMERO")
	private String numero;

	@Column(name = "TEL_TIPO")
	@Convert(converter = TipoTelefoneConverter.class)
	private TipoTelefone tipoTelefone;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEL_CLI_ID")
	private Cliente cliente;
	
	public Telefone() {
	}

	public Telefone(Long id, String ddi, String ddd, String numero, TipoTelefone tipoTelefone, Cliente cliente) {
		super();
		this.id = id;
		this.ddi = ddi;
		this.ddd = ddd;
		this.numero = numero;
		this.tipoTelefone = tipoTelefone;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDdi() {
		return ddi;
	}

	public void setDdi(String ddi) {
		this.ddi = ddi;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		Telefone other = (Telefone) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
