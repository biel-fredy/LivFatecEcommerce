package br.gov.sp.fatec.livraria.aplicacao;

import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.gov.sp.fatec.livraria.domain.Cliente;

@Entity
@Table(name = "USUARIOS")
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USR_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USR")
	@SequenceGenerator(name = "SQ_USR", sequenceName = "SQ_USR", allocationSize = 1)
	private Long id;

	@Column(name = "USR_NOME_USUARIO")
	private String nomeUsuario;

	@Column(name = "USR_SENHA")
	private String senha;

	@Transient
	private String confirmaSenha;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USR_CLI_ID", referencedColumnName = "CLI_ID")
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USR_PAP_ID")
	private Papel papel;

	public Usuario() {
	}

	public Usuario(Long id, String nomeUsuario, String senha, Cliente cliente, Papel papel) {
		super();
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.cliente = cliente;
		this.papel = papel;
	}

	public Usuario(Long id) {
		this.id = id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> autorizacoes = new ArrayList<GrantedAuthority>();
		autorizacoes.add(this.papel);
		/*
		 * for (Permissao permissao : this.papel.getPermissoes()) {
		 * autorizacoes.add(permissao); }
		 */

		SimpleGrantedAuthority simple = new SimpleGrantedAuthority("ROLE_" + this.papel.getNome());
		autorizacoes.add(simple);
		return autorizacoes;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.nomeUsuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		if (this.cliente != null) {
			return this.cliente.getStatus();
		}
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

}
