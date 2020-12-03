package br.gov.sp.fatec.livraria.aplicacao.resultado;

import java.util.ArrayList;
import java.util.List;

import br.gov.sp.fatec.livraria.aplicacao.Usuario;

public class ResultadoUsuario extends Resultado {

	private List<Usuario> resultadoLista = new ArrayList<Usuario>();

	public List<Usuario> getResultadoLista() {
		return this.resultadoLista;
	}

	public void setResultadoLista(List<Usuario> resultadoLista) {
		this.resultadoLista = resultadoLista;
	}

	public void addUsuario(Usuario usuario) {
		this.resultadoLista.add(usuario);
	}

	public void removeUsuario(int idUsuario) {
		this.resultadoLista.remove(idUsuario);
	}

}
