package br.gov.sp.fatec.livraria.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoEndereco;
import br.gov.sp.fatec.livraria.dao.EnderecoDao;
import br.gov.sp.fatec.livraria.domain.Endereco;
import br.gov.sp.fatec.livraria.negocio.endereco.RegraNegocioEndereco;
import br.gov.sp.fatec.livraria.negocio.endereco.VerificarCamposObrigatoriosEndereco;
import br.gov.sp.fatec.livraria.service.EnderecoService;

@Service
@Transactional(readOnly = false)
public class EnderecoServiceImpl implements EnderecoService {
	@Autowired
	private EnderecoDao daoEndereco;

	private Map<String, Map<String, List<RegraNegocioEndereco>>> regrasDeNegocio = new HashMap<String, Map<String, List<RegraNegocioEndereco>>>();

	public EnderecoServiceImpl() {
		VerificarCamposObrigatoriosEndereco verificaObrigatoriosEndereco = new VerificarCamposObrigatoriosEndereco();

		List<RegraNegocioEndereco> rnsSalvarEndereco = new ArrayList<RegraNegocioEndereco>();
		rnsSalvarEndereco.add(verificaObrigatoriosEndereco);

		List<RegraNegocioEndereco> rnsAlterarEndereco = new ArrayList<RegraNegocioEndereco>();
		rnsAlterarEndereco.add(verificaObrigatoriosEndereco);

		Map<String, List<RegraNegocioEndereco>> rnsEndereco = new HashMap<String, List<RegraNegocioEndereco>>();
		rnsEndereco.put("SALVAR", rnsSalvarEndereco);
		rnsEndereco.put("ALTERAR", rnsAlterarEndereco);

		regrasDeNegocio.put(Endereco.class.getName(), rnsEndereco);

	}

	@Override
	public ResultadoEndereco salvar(Endereco endereco) {

		ResultadoEndereco resultado = new ResultadoEndereco();

		String msg = executarRegras(endereco, "SALVAR");

		if (msg == null) {
			daoEndereco.save(endereco);
			resultado.setMensagem("Dimensão inserida com sucesso.");
			resultado.addEndereco(endereco);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoEndereco editar(Endereco endereco) {
		ResultadoEndereco resultado = new ResultadoEndereco();

		String msg = executarRegras(endereco, "ALTERAR");

		if (msg == null) {
			daoEndereco.update(endereco);
			resultado.setMensagem("Cliente editado com sucesso.");
			resultado.addEndereco(endereco);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoEndereco excluir(Endereco endereco) {
		ResultadoEndereco resultado = new ResultadoEndereco();

		String msg = executarRegras(endereco, "EXCLUIR");

		if (msg == null) {
			daoEndereco.delete(endereco.getId());
			resultado.setMensagem("Endereco removida com sucesso!");
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoEndereco buscarPorId(Endereco endereco) {
		ResultadoEndereco resultado = new ResultadoEndereco();
		resultado.addEndereco(daoEndereco.findById(endereco.getId()));
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoEndereco buscarTodos() {
		ResultadoEndereco resultado = new ResultadoEndereco();
		List<Endereco> listaEndereco = daoEndereco.findAll();
		for (Endereco d : listaEndereco) {
			resultado.addEndereco(d);
		}
		return resultado;
	}

	private String executarRegras(Endereco endereco, String operacao) {
		String nmClasse = endereco.getClass().getName();
		StringBuilder msg = new StringBuilder();

		Map<String, List<RegraNegocioEndereco>> regrasOperacao = regrasDeNegocio.get(nmClasse);

		if (regrasOperacao != null) {
			List<RegraNegocioEndereco> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				for (RegraNegocioEndereco strategies : regras) {
					String mensagemValidacao = strategies.processar(endereco);

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
