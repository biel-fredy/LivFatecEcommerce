package br.gov.sp.fatec.livraria.aplicacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("unused")
@Entity
@Table(name = "PAPEIS")
public class Papel implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PAP_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PER")
	@SequenceGenerator(name = "SQ_PER", sequenceName = "SQ_PER", allocationSize = 1)
	private Long id;

	@Column(name = "PAP_NOME")
	private String nome;

	@OneToMany(mappedBy = "papel", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	/*
	@ManyToMany
	@JoinTable(name = "PERMISSOES_PAPEIS", joinColumns = @JoinColumn(name = "PPA_PER_ID"), inverseJoinColumns = @JoinColumn(name = "PPA_PAP_ID"))
	private List<Permissao> permissoes = new ArrayList<Permissao>();
	*/
	
	public Papel() {
	}

	public Papel(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	/*
	public void addPermissao(Permissao permissao) {
		this.permissoes.add(permissao);
	}

	public void removePermissao(int indexPermissao) {
		this.permissoes.remove(indexPermissao);
	}
	*/

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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	/*
	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	*/
	
	@Override
	public String getAuthority() {
		return this.nome;
	}

}
