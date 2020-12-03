package br.gov.sp.fatec.livraria.aplicacao.resultado;

import java.util.ArrayList;
import java.util.List;

import br.gov.sp.fatec.livraria.domain.Precificacao;

public class ResultadoPrecificacao extends Resultado {
	
	private List<Precificacao> resultadoLista = new ArrayList<Precificacao>();

	public List<Precificacao> getResultadoLista() {
		return this.resultadoLista;
	}

	public void setResultadoLista(List<Precificacao> resultadoLista) {
		this.resultadoLista = resultadoLista;
	}

	public void addPrecificacao(Precificacao precificacao) {
		this.resultadoLista.add(precificacao);
	}

	public void removePrecificacao(int idPrecificacao) {
		this.resultadoLista.remove(idPrecificacao);
	}

}
