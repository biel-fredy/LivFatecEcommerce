package br.gov.sp.fatec.livraria.aplicacao.resultado;

import java.util.ArrayList;
import java.util.List;

import br.gov.sp.fatec.livraria.domain.Estoque;

public class ResultadoEstoque extends Resultado {
	
	private List<Estoque> resultadoLista = new ArrayList<Estoque>();

	public List<Estoque> getResultadoLista() {
		return this.resultadoLista;
	}

	public void setResultadoLista(List<Estoque> resultadoLista) {
		this.resultadoLista = resultadoLista;
	}

	public void addEstoque(Estoque estoque) {
		this.resultadoLista.add(estoque);
	}

	public void removeEstoque(int idEstoque) {
		this.resultadoLista.remove(idEstoque);
	}

}
