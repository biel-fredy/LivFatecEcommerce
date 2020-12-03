package br.gov.sp.fatec.livraria.negocio.livro;

import br.gov.sp.fatec.livraria.domain.Estoque;
import br.gov.sp.fatec.livraria.domain.Livro;

public class CalcularPrecoVenda implements RegraNegocioLivro {

	@Override
	public String processar(Livro livro) {
		Double valorCusto = 0d;
		Double precoVenda = 0d;
		Double lucro = 0d;
		
		if (null != livro.getPrecificacao()) {
				valorCusto = encontrarMaiorValorCusto(livro);
				lucro = livro.getPrecificacao().getPorcentagemLucro() / 100 * valorCusto;
				precoVenda = lucro + valorCusto;
				livro.setPrecoVenda(precoVenda);
		}
		else {
			return "Livro sem precificação. Impossível calcular valor de venda.";
		}
		
		return null;
	}
	
	public Double encontrarMaiorValorCusto(Livro livro) {
		Double maiorValorCusto = 0d;
		for (Estoque estoque : livro.getEstoques()) {
			if (estoque.getQuantidade() > 0) {
				if (maiorValorCusto < estoque.getValorCusto()) {
					maiorValorCusto = estoque.getValorCusto();
				}
			}
		}
		
		if (maiorValorCusto == 0) {
			maiorValorCusto = livro.getPrecoCapa();
		}
		return maiorValorCusto;
	}

}
