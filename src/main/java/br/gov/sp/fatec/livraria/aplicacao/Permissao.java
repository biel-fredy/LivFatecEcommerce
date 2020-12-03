package br.gov.sp.fatec.livraria.aplicacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("unused")
@Entity
@Table(name = "PERMISSOES")
public class Permissao implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PER")
	@SequenceGenerator(name = "SQ_PER", sequenceName = "SQ_PER", allocationSize = 1)
	private Long id;

	@Column(name = "PER_NOME")
	private String permissao;

	/*
	 * @ManyToMany(mappedBy = "permissoes") private List<Papel> papeis = new
	 * ArrayList<Papel>();
	 */

	public Permissao() {
	}

	public Permissao(Long id, String permissao) {
		super();
		this.id = id;
		this.permissao = permissao;
	}

	@Override
	public String getAuthority() {
		return this.permissao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

}
