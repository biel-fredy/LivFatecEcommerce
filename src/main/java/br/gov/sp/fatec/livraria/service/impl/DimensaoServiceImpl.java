package br.gov.sp.fatec.livraria.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoDimensao;
import br.gov.sp.fatec.livraria.dao.DimensaoDao;
import br.gov.sp.fatec.livraria.domain.Dimensao;
import br.gov.sp.fatec.livraria.negocio.dimensao.RegraNegocioDimensao;
import br.gov.sp.fatec.livraria.negocio.dimensao.VerificarCamposObrigatoriosDimensao;
import br.gov.sp.fatec.livraria.service.DimensaoService;

@Service
@Transactional(readOnly = false)
public class DimensaoServiceImpl implements DimensaoService {

	@Autowired
	private DimensaoDao daoDimensao;

	private Map<String, Map<String, List<RegraNegocioDimensao>>> regrasDeNegocio = new HashMap<String, Map<String, List<RegraNegocioDimensao>>>();

	public DimensaoServiceImpl() {
		VerificarCamposObrigatoriosDimensao verificaObrigatoriosDimensao = new VerificarCamposObrigatoriosDimensao();

		List<RegraNegocioDimensao> rnsSalvarDimensao = new ArrayList<RegraNegocioDimensao>();
		rnsSalvarDimensao.add(verificaObrigatoriosDimensao);

		List<RegraNegocioDimensao> rnsAlterarDimensao = new ArrayList<RegraNegocioDimensao>();
		rnsAlterarDimensao.add(verificaObrigatoriosDimensao);

		Map<String, List<RegraNegocioDimensao>> rnsDimensao = new HashMap<String, List<RegraNegocioDimensao>>();
		rnsDimensao.put("SALVAR", rnsSalvarDimensao);
		rnsDimensao.put("ALTERAR", rnsAlterarDimensao);

		regrasDeNegocio.put(Dimensao.class.getName(), rnsDimensao);

	}

	@Override
	public ResultadoDimensao salvar(Dimensao dimensao) {

		ResultadoDimensao resultado = new ResultadoDimensao();

		String msg = executarRegras(dimensao, "SALVAR");

		if (msg == null) {
			daoDimensao.save(dimensao);
			resultado.setMensagem("Dimensão inserida com sucesso.");
			resultado.addDimensao(dimensao);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoDimensao editar(Dimensao dimensao) {
		ResultadoDimensao resultado = new ResultadoDimensao();

		String msg = executarRegras(dimensao, "ALTERAR");

		if (msg == null) {
			daoDimensao.update(dimensao);
			resultado.setMensagem("Cliente editado com sucesso.");
			resultado.addDimensao(dimensao);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoDimensao excluir(Dimensao dimensao) {
		ResultadoDimensao resultado = new ResultadoDimensao();

		String msg = executarRegras(dimensao, "EXCLUIR");

		if (msg == null) {
			daoDimensao.delete(dimensao.getId());
			resultado.setMensagem("Dimensao removida com sucesso!");
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoDimensao buscarPorId(Dimensao dimensao) {
		ResultadoDimensao resultado = new ResultadoDimensao();
		resultado.addDimensao(daoDimensao.findById(dimensao.getId()));
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoDimensao buscarTodos() {
		ResultadoDimensao resultado = new ResultadoDimensao();
		List<Dimensao> listaDimensao = daoDimensao.findAll();
		for (Dimensao d : listaDimensao) {
			resultado.addDimensao(d);
		}
		return resultado;
	}

	private String executarRegras(Dimensao dimensao, String operacao) {
		String nmClasse = dimensao.getClass().getName();
		StringBuilder msg = new StringBuilder();

		Map<String, List<RegraNegocioDimensao>> regrasOperacao = regrasDeNegocio.get(nmClasse);

		if (regrasOperacao != null) {
			List<RegraNegocioDimensao> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				for (RegraNegocioDimensao strategies : regras) {
					String mensagemValidacao = strategies.processar(dimensao);

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
