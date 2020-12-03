package br.gov.sp.fatec.livraria.aplicacao.resultado;

import java.util.ArrayList;
import java.util.List;
import br.gov.sp.fatec.livraria.domain.Livro;

public class ResultadoLivro extends Resultado {

	private List<Livro> resultadoLista = new ArrayList<Livro>();

	public List<Livro> getResultadoLista() {
		return this.resultadoLista;
	}

	public void setResultadoLista(List<Livro> resultadoLista) {
		this.resultadoLista = resultadoLista;
	}

	public void addLivro(Livro livro) {
		this.resultadoLista.add(livro);
	}

	public void removeLivro(int idLivro) {
		this.resultadoLista.remove(idLivro);
	}

}
