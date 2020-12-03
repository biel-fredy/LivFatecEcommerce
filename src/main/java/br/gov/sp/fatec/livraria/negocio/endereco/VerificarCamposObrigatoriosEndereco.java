package br.gov.sp.fatec.livraria.negocio.endereco;

import br.gov.sp.fatec.livraria.domain.Endereco;

public class VerificarCamposObrigatoriosEndereco implements RegraNegocioEndereco {

	public String processar(Endereco endereco) {

		String logradouro = endereco.getLogradouro();
		String numero = endereco.getNumero();

		if (logradouro == null) {
			return "Logradouro é de preenchimento obrigatório!";
		}

		if (logradouro.trim().equals("")) {
			return "Logradouro é de preenchimento obrigatório!";
		}

		if (numero == null) {
			return "Número é de preenchimento obrigatório!";
		}

		if (numero.trim().equals("")) {
			return "Número é de preenchimento obrigatório!";
		}

		return null;
	}

}
