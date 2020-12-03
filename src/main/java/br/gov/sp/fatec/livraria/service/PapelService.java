package br.gov.sp.fatec.livraria.service;

import br.gov.sp.fatec.livraria.aplicacao.Papel;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoPapel;

public interface PapelService {

	public ResultadoPapel salvar(Papel papel);

	public ResultadoPapel editar(Papel papel);

	public ResultadoPapel excluir(Papel papel);

	public ResultadoPapel buscarPorId(Papel papel);

	public ResultadoPapel buscarTodos();

}
