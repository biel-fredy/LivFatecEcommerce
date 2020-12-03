package br.gov.sp.fatec.livraria.service;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoEstoque;
import br.gov.sp.fatec.livraria.domain.Estoque;

public interface EstoqueService {
	
	public ResultadoEstoque salvar(Estoque estoque);

	public ResultadoEstoque editar(Estoque estoque);

	public ResultadoEstoque excluir(Estoque estoque);

	public ResultadoEstoque buscarPorId(Estoque estoque);

	public ResultadoEstoque buscarTodos();

}
