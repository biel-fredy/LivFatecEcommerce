package br.gov.sp.fatec.livraria.aplicacao.resultado;

import java.util.ArrayList;
import java.util.List;

import br.gov.sp.fatec.livraria.domain.Dimensao;

public class ResultadoDimensao extends Resultado {

	private List<Dimensao> resultadoLista = new ArrayList<Dimensao>();

	public List<Dimensao> getResultadoLista() {
		return this.resultadoLista;
	}

	public void setResultadoLista(List<Dimensao> resultadoLista) {
		this.resultadoLista = resultadoLista;
	}

	public void addDimensao(Dimensao dimensao) {
		this.resultadoLista.add(dimensao);
	}

	public void removeDimensao(int idDimensao) {
		this.resultadoLista.remove(idDimensao);
	}

}
