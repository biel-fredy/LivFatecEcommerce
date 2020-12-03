package br.gov.sp.fatec.livraria.negocio.cliente;

import java.util.Date;

import br.gov.sp.fatec.livraria.domain.Cliente;

public class VerificarCamposObrigatoriosCliente implements RegraNegocioCliente {

	public String processar(Cliente cliente) {

		String primeiroNome = cliente.getPrimeiroNome();
		String ultimoNome = cliente.getUltimoNome();
		Date dataNascimento = cliente.getDataNascimento();
		String cpf = cliente.getCpf();
		String email = cliente.getEmail();

		if (primeiroNome == null) {
			return "Primeiro Nome é de preenchimento obrigatório!";
		}

		if (primeiroNome.trim().equals("")) {
			return "Primeiro Nome é de preenchimento obrigatório!";
		}

		if (ultimoNome == null) {
			return "Sobrenome é de preenchimento obrigatório!";
		}

		if (ultimoNome.trim().equals("")) {
			return "Sobrenome é de preenchimento obrigatório!";
		}

		if (dataNascimento == null) {
			return "Data de nascimento é de preenchimento obrigatório!";
		}

		if (cpf == null) {
			return "CPF é de preenchimento obrigatório!";
		}

		if (cpf.trim().equals("")) {
			return "CPF é de preenchimento obrigatório!";
		}

		if (email == null) {
			return "E-mail é de preenchimento obrigatório!";
		}

		if (email.trim().equals("")) {
			return "E-mail é de preenchimento obrigatório!";
		}

		return null;
	}
}
