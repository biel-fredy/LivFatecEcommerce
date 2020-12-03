package br.gov.sp.fatec.livraria.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.gov.sp.fatec.livraria.domain.enums.TipoLogradouro;
import br.gov.sp.fatec.livraria.domain.enums.TipoResidencia;
import br.gov.sp.fatec.livraria.domain.enums.converters.TipoLogradouroConverter;
import br.gov.sp.fatec.livraria.domain.enums.converters.TipoResidenciaConverter;

@Entity
@Table(name = "ENDERECOS")
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_END")
	@SequenceGenerator(name = "SQ_END", sequenceName = "SQ_END", allocationSize = 1)
	@Column(name = "END_ID", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "END_APELIDO")
	private String apelido;
	
	@Column(name = "END_TIPO_RESIDENCIA")
	@Convert(converter = TipoResidenciaConverter.class)
	private TipoResidencia tipoResidencia;

	@Column(name = "END_TIPO_LOGRADOURO")
	@Convert(converter = TipoLogradouroConverter.class)
	private TipoLogradouro tipoLogradouro;

	@Column(name = "END_LOGRADOURO")
	private String logradouro;

	@Column(name = "END_NUMERO")
	private String numero;

	@Column(name = "END_BAIRRO")
	private String bairro;

	@Column(name = "END_CEP")
	private String cep;

	@Column(name = "END_OBS")
	private String observacoes;

	@Embedded
	private Cidade cidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "END_CLI_ID")
	private Cliente cliente;
	
	public Endereco() {
	}
		
	public Endereco(Long id, TipoResidencia tipoResidencia, TipoLogradouro tipoLogradouro, String logradouro,
			String numero, String bairro, String cep, String observacoes, Cidade cidade, Cliente cliente) {
		super();
		this.id = id;
		this.tipoResidencia = tipoResidencia;
		this.tipoLogradouro = tipoLogradouro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.observacoes = observacoes;
		this.cidade = cidade;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public TipoResidencia getTipoResidencia() {
		return tipoResidencia;
	}

	public void setTipoResidencia(TipoResidencia tipoResidencia) {
		this.tipoResidencia = tipoResidencia;
	}

	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
