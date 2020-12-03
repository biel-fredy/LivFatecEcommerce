package br.gov.sp.fatec.livraria.aplicacao.resultado;

import java.util.ArrayList;
import java.util.List;

import br.gov.sp.fatec.livraria.domain.Categoria;

public class ResultadoCategoria extends Resultado{
	
	private List<Categoria> resultadoLista = new ArrayList<Categoria>();

	public List<Categoria> getResultadoLista() {
		return this.resultadoLista;
	}

	public void setResultadoLista(List<Categoria> resultadoLista) {
		this.resultadoLista = resultadoLista;
	}
	
	public void addCategoria(Categoria categoria) {
		this.resultadoLista.add(categoria);
	}
	
	public void removeCategoria(int idCategoria) {
		this.resultadoLista.remove(idCategoria);
	}


}
