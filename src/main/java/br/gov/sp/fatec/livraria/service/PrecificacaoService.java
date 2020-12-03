package br.gov.sp.fatec.livraria.service;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoPrecificacao;
import br.gov.sp.fatec.livraria.domain.Precificacao;

public interface PrecificacaoService {

	public ResultadoPrecificacao salvar(Precificacao precificacao);

	public ResultadoPrecificacao editar(Precificacao precificacao);

	public ResultadoPrecificacao excluir(Precificacao precificacao);

	public ResultadoPrecificacao buscarPorId(Precificacao precificacao);

	public ResultadoPrecificacao buscarTodos();

}
