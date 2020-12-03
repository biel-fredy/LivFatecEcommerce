package br.gov.sp.fatec.livraria.aplicacao.resultado;

import java.util.ArrayList;
import java.util.List;

import br.gov.sp.fatec.livraria.domain.Autor;

public class ResultadoAutor extends Resultado {

	private List<Autor> resultadoLista = new ArrayList<Autor>();

	public List<Autor> getResultadoLista() {
		return this.resultadoLista;
	}

	public void setResultadoLista(List<Autor> resultadoLista) {
		this.resultadoLista = resultadoLista;
	}

	public void addAutor(Autor autor) {
		this.resultadoLista.add(autor);
	}

	public void removeAutor(int idAutor) {
		this.resultadoLista.remove(idAutor);
	}

}
