package br.gov.sp.fatec.livraria.service;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoEndereco;
import br.gov.sp.fatec.livraria.domain.Endereco;

public interface EnderecoService {
	
	public ResultadoEndereco salvar(Endereco endereco);

	public ResultadoEndereco editar(Endereco endereco);

	public ResultadoEndereco excluir(Endereco endereco);

	public ResultadoEndereco buscarPorId(Endereco endereco);

	public ResultadoEndereco buscarTodos();

}
