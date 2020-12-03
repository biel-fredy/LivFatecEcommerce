package br.gov.sp.fatec.livraria.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoCliente;
import br.gov.sp.fatec.livraria.dao.ClienteDao;
import br.gov.sp.fatec.livraria.domain.Cliente;
import br.gov.sp.fatec.livraria.domain.Endereco;
import br.gov.sp.fatec.livraria.domain.Telefone;
import br.gov.sp.fatec.livraria.negocio.cliente.RegraNegocioCliente;
import br.gov.sp.fatec.livraria.negocio.cliente.VerificarCamposObrigatoriosCliente;
import br.gov.sp.fatec.livraria.service.ClienteService;

@Service
@Transactional(readOnly = false)
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteDao daoCliente;
		
	private Map<String, Map<String, List<RegraNegocioCliente>>> regrasDeNegocio = new HashMap<String, Map<String, List<RegraNegocioCliente>>>();
	
	public ClienteServiceImpl() {
		VerificarCamposObrigatoriosCliente verificaObrigatoriosCliente = new VerificarCamposObrigatoriosCliente();

		List<RegraNegocioCliente> rnsSalvarCliente = new ArrayList<RegraNegocioCliente>();
		rnsSalvarCliente.add(verificaObrigatoriosCliente);

		List<RegraNegocioCliente> rnsAlterarCliente = new ArrayList<RegraNegocioCliente>();
		rnsAlterarCliente.add(verificaObrigatoriosCliente);

		Map<String, List<RegraNegocioCliente>> rnsCliente = new HashMap<String, List<RegraNegocioCliente>>();
		rnsCliente.put("SALVAR", rnsSalvarCliente);
		rnsCliente.put("ALTERAR", rnsAlterarCliente);

		regrasDeNegocio.put(Cliente.class.getName(), rnsCliente);

	}

	@Override
	public ResultadoCliente salvar(Cliente cliente) {

		ResultadoCliente resultado = new ResultadoCliente();

		String msg = executarRegras(cliente, "SALVAR");
		
		for (Endereco end : cliente.getEnderecos()) {
			end.setCliente(cliente);
		}
		
		for (Telefone tel : cliente.getTelefones()) {
			tel.setCliente(cliente);
		}

		if (msg == null) {
			daoCliente.save(cliente);
			resultado.setMensagem("Cliente inserido com sucesso.");
			resultado.addCliente(cliente);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoCliente editar(Cliente cliente) {
		ResultadoCliente resultado = new ResultadoCliente();

		String msg = executarRegras(cliente, "ALTERAR");

		for (Endereco end : cliente.getEnderecos()) {
			end.setCliente(cliente);
		}

		for (Telefone tel : cliente.getTelefones()) {
			tel.setCliente(cliente);
		}

		if (msg == null) {
			daoCliente.update(cliente);
			resultado.setMensagem("Cliente editado com sucesso.");
			resultado.addCliente(cliente);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoCliente excluir(Cliente cliente) {
		ResultadoCliente resultado = new ResultadoCliente();

		String msg = executarRegras(cliente, "EXCLUIR");

		if (msg == null) {
			System.out.println("aqui car4io");
			daoCliente.delete(cliente.getId());
			resultado.setMensagem("Cliente removido com sucesso!");
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoCliente buscarPorId(Cliente cliente) {
		ResultadoCliente resultado = new ResultadoCliente();
		resultado.addCliente(daoCliente.findById(cliente.getId()));
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoCliente buscarTodos() {
		ResultadoCliente resultado = new ResultadoCliente();
		List<Cliente> listaCliente = daoCliente.findAll();
		for (Cliente c : listaCliente) {
			resultado.addCliente(c);
		}
		return resultado;
	}

	private String executarRegras(Cliente cliente, String operacao) {
		String nmClasse = cliente.getClass().getName();
		StringBuilder msg = new StringBuilder();

		Map<String, List<RegraNegocioCliente>> regrasOperacao = regrasDeNegocio.get(nmClasse);

		if (regrasOperacao != null) {
			List<RegraNegocioCliente> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				for (RegraNegocioCliente strategies : regras) {
					String mensagemValidacao = strategies.processar(cliente);

					if (mensagemValidacao != null) {
						msg.append(mensagemValidacao);
						msg.append("\n");
					}
				}
			}

		}

		if (msg.length() > 0)
			return msg.toString();
		else
			return null;
	}
}
