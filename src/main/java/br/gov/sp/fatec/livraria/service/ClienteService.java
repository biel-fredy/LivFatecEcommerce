package br.gov.sp.fatec.livraria.service;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoCliente;
import br.gov.sp.fatec.livraria.domain.Cliente;

public interface ClienteService {

	public ResultadoCliente salvar(Cliente cliente);

	public ResultadoCliente editar(Cliente cliente);

	public ResultadoCliente excluir(Cliente cliente);

	public ResultadoCliente buscarPorId(Cliente cliente);

	public ResultadoCliente buscarTodos();

}
