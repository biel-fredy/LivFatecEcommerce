package br.gov.sp.fatec.livraria.aplicacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoPrecificacao;
import br.gov.sp.fatec.livraria.domain.Precificacao;
import br.gov.sp.fatec.livraria.service.PrecificacaoService;

public class CacheAplicacao {

	@Autowired
	private PrecificacaoService precificacaoService;

	List<Precificacao> precificacoes = new ArrayList<Precificacao>();
	
	public CacheAplicacao() {
		carregaListaPrecificacao();
	}
	
	public void carregaListaPrecificacao() {
		ResultadoPrecificacao resultado = precificacaoService.buscarTodos();
		this.precificacoes = resultado.getResultadoLista();
	}

}
