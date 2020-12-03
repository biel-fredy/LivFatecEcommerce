package br.gov.sp.fatec.livraria.service;

import br.gov.sp.fatec.livraria.aplicacao.Permissao;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoPermissao;

public interface PermissaoService {
	
	public ResultadoPermissao salvar(Permissao permissao);

	public ResultadoPermissao editar(Permissao permissao);

	public ResultadoPermissao excluir(Permissao permissao);

	public ResultadoPermissao buscarPorId(Permissao permissao);

	public ResultadoPermissao buscarTodos();

}
