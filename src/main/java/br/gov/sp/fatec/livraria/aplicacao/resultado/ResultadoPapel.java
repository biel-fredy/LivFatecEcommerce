package br.gov.sp.fatec.livraria.aplicacao.resultado;

import java.util.ArrayList;
import java.util.List;

import br.gov.sp.fatec.livraria.aplicacao.Papel;

public class ResultadoPapel extends Resultado {

	private List<Papel> resultadoLista = new ArrayList<Papel>();

	public List<Papel> getResultadoLista() {
		return this.resultadoLista;
	}

	public void setResultadoLista(List<Papel> resultadoLista) {
		this.resultadoLista = resultadoLista;
	}

	public void addPapel(Papel papel) {
		this.resultadoLista.add(papel);
	}

	public void removePapel(int idPapel) {
		this.resultadoLista.remove(idPapel);
	}

}
