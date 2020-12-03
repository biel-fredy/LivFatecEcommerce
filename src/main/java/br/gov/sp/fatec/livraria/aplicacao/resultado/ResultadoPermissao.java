package br.gov.sp.fatec.livraria.aplicacao.resultado;

import java.util.ArrayList;
import java.util.List;

import br.gov.sp.fatec.livraria.aplicacao.Permissao;

public class ResultadoPermissao extends Resultado {

	private List<Permissao> resultadoLista = new ArrayList<Permissao>();

	public List<Permissao> getResultadoLista() {
		return this.resultadoLista;
	}

	public void setResultadoLista(List<Permissao> resultadoLista) {
		this.resultadoLista = resultadoLista;
	}

	public void addPermissao(Permissao permissao) {
		this.resultadoLista.add(permissao);
	}

	public void removePermissao(int idPermissao) {
		this.resultadoLista.remove(idPermissao);
	}

}
