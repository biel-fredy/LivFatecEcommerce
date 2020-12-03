package br.gov.sp.fatec.livraria.service;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoAutor;
import br.gov.sp.fatec.livraria.domain.Autor;

public interface AutorService {

	public ResultadoAutor salvar(Autor autor);

	public ResultadoAutor editar(Autor autor);

	public ResultadoAutor excluir(Autor autor);

	public ResultadoAutor buscarPorId(Autor autor);

	public ResultadoAutor buscarTodos();

	public ResultadoAutor buscarPorNome(String nome);

}
