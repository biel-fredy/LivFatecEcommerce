package br.gov.sp.fatec.livraria.service;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoDimensao;
import br.gov.sp.fatec.livraria.domain.Dimensao;

public interface DimensaoService {
	
	public ResultadoDimensao salvar(Dimensao dimensao);

	public ResultadoDimensao editar(Dimensao dimensao);

	public ResultadoDimensao excluir(Dimensao dimensao);

	public ResultadoDimensao buscarPorId(Dimensao dimensao);

	public ResultadoDimensao buscarTodos();

}
