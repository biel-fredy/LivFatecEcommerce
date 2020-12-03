package br.gov.sp.fatec.livraria.aplicacao;

import br.gov.sp.fatec.livraria.domain.Carrinho;

public class Sessao {

	private String nomeUsuario = "";

	private Usuario usuario;

	private Carrinho carrinho;

	private String msg;

	public Sessao() {
	}

	public Sessao(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Sessao(Usuario usuario, Carrinho carrinho) {
		super();
		this.usuario = usuario;
		this.carrinho = carrinho;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
