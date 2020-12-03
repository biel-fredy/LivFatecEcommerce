package br.gov.sp.fatec.livraria.service;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoLivro;
import br.gov.sp.fatec.livraria.domain.Livro;

public interface LivroService {

	public ResultadoLivro salvar(Livro livro);

	public ResultadoLivro editar(Livro livro);

	public ResultadoLivro excluir(Livro livro);

	public ResultadoLivro buscarPorId(Livro livro);

	public ResultadoLivro buscarTodos();

}
