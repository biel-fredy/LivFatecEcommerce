package br.gov.sp.fatec.livraria.service;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoCategoria;
import br.gov.sp.fatec.livraria.domain.Categoria;

public interface CategoriaService {

	public ResultadoCategoria salvar(Categoria categoria);

	public ResultadoCategoria editar(Categoria categoria);

	public ResultadoCategoria excluir(Categoria categoria);

	public ResultadoCategoria buscarPorId(Categoria categoria);

	public ResultadoCategoria buscarTodos();

}
