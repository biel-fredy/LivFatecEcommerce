package br.gov.sp.fatec.livraria.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import br.gov.sp.fatec.livraria.aplicacao.Usuario;

@Entity
@Table(name = "CLIENTES")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CLI")
	@SequenceGenerator(name = "SQ_CLI", sequenceName = "SQ_CLI", allocationSize = 1)
	@Column(name = "CLI_ID", updatable = false, nullable = false)
	private Long id;

	@Column(name = "CLI_PNOME")
	private String primeiroNome;

	@Column(name = "CLI_MNOME")
	private String nomeDoMeio;

	@Column(name = "CLI_UNOME")
	private String ultimoNome;

	@Column(name = "CLI_EMAIL")
	private String email;

	@Column(name = "CLI_GENERO")
	private String genero;

	@Column(name = "CLI_DATANASC")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Column(name = "CLI_CPF")
	private String cpf;

	@Column(name = "CLI_RANKING")
	private Integer ranking;

	@Column(name = "CLI_STATUS")
	private Boolean status = true;

	@OneToOne(mappedBy = "cliente")
	private Usuario usuario;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Telefone> telefones = new ArrayList<Telefone>();

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Endereco> enderecos = new ArrayList<Endereco>();

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<Venda> vendas = new ArrayList<Venda>();

	public Cliente() {
	}

	public Cliente(Long id, String primeiroNome, String nomeDoMeio, String ultimoNome, String email, String genero,
			Date dataNascimento, String cpf, Integer ranking, Boolean status, Usuario usuario) {
		super();
		this.id = id;
		this.primeiroNome = primeiroNome;
		this.nomeDoMeio = nomeDoMeio;
		this.ultimoNome = ultimoNome;
		this.email = email;
		this.genero = genero;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.ranking = ranking;
		this.status = status;
		this.usuario = usuario;
	}

	public Cliente(Long id) {
		this.id = id;
	}

	public void addTelefone(Telefone telefone) {
		this.telefones.add(telefone);
	}

	public void removeTelefone(int indexTel) {
		this.telefones.remove(indexTel);
	}

	public void addEndereco(Endereco endereco) {
		this.enderecos.add(endereco);
	}

	public void removeEndereco(int indexEnd) {
		this.enderecos.remove(indexEnd);
	}

	public void addVenda(Venda venda) {
		this.vendas.add(venda);
	}

	public void removeVenda(int indexVenda) {
		this.removeVenda(indexVenda);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getNomeDoMeio() {
		return nomeDoMeio;
	}

	public void setNomeDoMeio(String nomeDoMeio) {
		this.nomeDoMeio = nomeDoMeio;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
