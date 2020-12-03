package br.gov.sp.fatec.livraria.aplicacao.resultado;

import java.util.ArrayList;
import java.util.List;

import br.gov.sp.fatec.livraria.domain.Endereco;

public class ResultadoEndereco extends Resultado {
	
	private List<Endereco> resultadoLista = new ArrayList<Endereco>();

	public List<Endereco> getResultadoLista() {
		return this.resultadoLista;
	}

	public void setResultadoLista(List<Endereco> resultadoLista) {
		this.resultadoLista = resultadoLista;
	}

	public void addEndereco(Endereco endereco) {
		this.resultadoLista.add(endereco);
	}

	public void removeEndereco(int idEndereco) {
		this.resultadoLista.remove(idEndereco);
	}


}
