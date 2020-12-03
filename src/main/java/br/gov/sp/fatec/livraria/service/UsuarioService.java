package br.gov.sp.fatec.livraria.service;

import br.gov.sp.fatec.livraria.aplicacao.Usuario;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoUsuario;

public interface UsuarioService {

	public ResultadoUsuario salvar(Usuario livro);

	public ResultadoUsuario editar(Usuario livro);

	public ResultadoUsuario excluir(Usuario livro);

	public ResultadoUsuario buscarPorId(Usuario livro);

	public ResultadoUsuario buscarTodos();

	public ResultadoUsuario buscarPorNomeUsuario(String usuario);

}
