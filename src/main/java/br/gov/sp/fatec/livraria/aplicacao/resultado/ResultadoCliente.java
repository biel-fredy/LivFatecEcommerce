package br.gov.sp.fatec.livraria.aplicacao.resultado;

import java.util.ArrayList;
import java.util.List;

import br.gov.sp.fatec.livraria.domain.Cliente;

public class ResultadoCliente extends Resultado {

	private List<Cliente> resultadoLista = new ArrayList<Cliente>();

	public List<Cliente> getResultadoLista() {
		return this.resultadoLista;
	}

	public void setResultadoLista(List<Cliente> resultadoLista) {
		this.resultadoLista = resultadoLista;
	}
	
	public void addCliente(Cliente cliente) {
		this.resultadoLista.add(cliente);
	}
	
	public void removeCliente(int idCliente) {
		this.resultadoLista.remove(idCliente);
	}

}
