package br.gov.sp.fatec.livraria.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoEstoque;
import br.gov.sp.fatec.livraria.dao.EstoqueDao;
import br.gov.sp.fatec.livraria.domain.Estoque;
import br.gov.sp.fatec.livraria.negocio.estoque.RegraNegocioEstoque;
import br.gov.sp.fatec.livraria.negocio.estoque.VerificarCamposObrigatoriosEstoque;
import br.gov.sp.fatec.livraria.service.EstoqueService;

@Service
@Transactional(readOnly = false)
public class EstoqueServiceImpl implements EstoqueService {

	@Autowired
	private EstoqueDao daoEstoque;

	private Map<String, Map<String, List<RegraNegocioEstoque>>> regrasDeNegocio = new HashMap<String, Map<String, List<RegraNegocioEstoque>>>();

	public EstoqueServiceImpl() {
		VerificarCamposObrigatoriosEstoque verificaObrigatoriosEstoque = new VerificarCamposObrigatoriosEstoque();

		List<RegraNegocioEstoque> rnsSalvarEstoque = new ArrayList<RegraNegocioEstoque>();
		rnsSalvarEstoque.add(verificaObrigatoriosEstoque);

		List<RegraNegocioEstoque> rnsAlterarEstoque = new ArrayList<RegraNegocioEstoque>();
		rnsAlterarEstoque.add(verificaObrigatoriosEstoque);

		Map<String, List<RegraNegocioEstoque>> rnsEstoque = new HashMap<String, List<RegraNegocioEstoque>>();
		rnsEstoque.put("SALVAR", rnsSalvarEstoque);
		rnsEstoque.put("ALTERAR", rnsAlterarEstoque);

		regrasDeNegocio.put(Estoque.class.getName(), rnsEstoque);

	}

	@Override
	public ResultadoEstoque salvar(Estoque estoque) {

		ResultadoEstoque resultado = new ResultadoEstoque();

		String msg = executarRegras(estoque, "SALVAR");

		if (msg == null) {
			daoEstoque.save(estoque);
			resultado.setMensagem("Dimensão inserida com sucesso.");
			resultado.addEstoque(estoque);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoEstoque editar(Estoque estoque) {
		ResultadoEstoque resultado = new ResultadoEstoque();

		String msg = executarRegras(estoque, "ALTERAR");

		if (msg == null) {
			daoEstoque.update(estoque);
			resultado.setMensagem("Cliente editado com sucesso.");
			resultado.addEstoque(estoque);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoEstoque excluir(Estoque estoque) {
		ResultadoEstoque resultado = new ResultadoEstoque();

		String msg = executarRegras(estoque, "EXCLUIR");

		if (msg == null) {
			daoEstoque.delete(estoque.getId());
			resultado.setMensagem("Estoque removida com sucesso!");
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoEstoque buscarPorId(Estoque estoque) {
		ResultadoEstoque resultado = new ResultadoEstoque();
		resultado.addEstoque(daoEstoque.findById(estoque.getId()));
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoEstoque buscarTodos() {
		ResultadoEstoque resultado = new ResultadoEstoque();
		List<Estoque> listaEstoque = daoEstoque.findAll();
		for (Estoque d : listaEstoque) {
			resultado.addEstoque(d);
		}
		return resultado;
	}

	private String executarRegras(Estoque estoque, String operacao) {
		String nmClasse = estoque.getClass().getName();
		StringBuilder msg = new StringBuilder();

		Map<String, List<RegraNegocioEstoque>> regrasOperacao = regrasDeNegocio.get(nmClasse);

		if (regrasOperacao != null) {
			List<RegraNegocioEstoque> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				for (RegraNegocioEstoque strategies : regras) {
					String mensagemValidacao = strategies.processar(estoque);

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
