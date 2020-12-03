package br.gov.sp.fatec.livraria.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.livraria.aplicacao.Papel;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoPapel;
import br.gov.sp.fatec.livraria.dao.PapelDao;
import br.gov.sp.fatec.livraria.negocio.papel.RegraNegocioPapel;
import br.gov.sp.fatec.livraria.negocio.papel.VerificarCamposObrigatoriosPapel;
import br.gov.sp.fatec.livraria.service.PapelService;

@Service
@Transactional(readOnly = false)
public class PapelServiceImpl implements PapelService {

	@Autowired
	private PapelDao daoPapel;

	private Map<String, Map<String, List<RegraNegocioPapel>>> regrasDeNegocio = new HashMap<String, Map<String, List<RegraNegocioPapel>>>();

	public PapelServiceImpl() {
		VerificarCamposObrigatoriosPapel verificaObrigatoriosPapel = new VerificarCamposObrigatoriosPapel();

		List<RegraNegocioPapel> rnsSalvarPapel = new ArrayList<RegraNegocioPapel>();
		rnsSalvarPapel.add(verificaObrigatoriosPapel);

		List<RegraNegocioPapel> rnsAlterarPapel = new ArrayList<RegraNegocioPapel>();
		rnsAlterarPapel.add(verificaObrigatoriosPapel);

		Map<String, List<RegraNegocioPapel>> rnsPapel = new HashMap<String, List<RegraNegocioPapel>>();
		rnsPapel.put("SALVAR", rnsSalvarPapel);
		rnsPapel.put("ALTERAR", rnsAlterarPapel);

		regrasDeNegocio.put(Papel.class.getName(), rnsPapel);

	}

	@Override
	public ResultadoPapel salvar(Papel papel) {

		ResultadoPapel resultado = new ResultadoPapel();

		String msg = executarRegras(papel, "SALVAR");

		if (msg == null) {
			daoPapel.save(papel);
			resultado.setMensagem("Papel inserido com sucesso.");
			resultado.addPapel(papel);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoPapel editar(Papel papel) {
		ResultadoPapel resultado = new ResultadoPapel();

		String msg = executarRegras(papel, "ALTERAR");

		if (msg == null) {
			daoPapel.update(papel);
			resultado.setMensagem("Papel editado com sucesso.");
			resultado.addPapel(papel);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoPapel excluir(Papel papel) {
		ResultadoPapel resultado = new ResultadoPapel();

		String msg = executarRegras(papel, "EXCLUIR");

		if (msg == null) {
			daoPapel.delete(papel.getId());
			resultado.setMensagem("Papel removido com sucesso!");
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoPapel buscarPorId(Papel papel) {
		ResultadoPapel resultado = new ResultadoPapel();
		resultado.addPapel(daoPapel.findById(papel.getId()));
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoPapel buscarTodos() {
		ResultadoPapel resultado = new ResultadoPapel();
		List<Papel> listaPapel = daoPapel.findAll();
		for (Papel c : listaPapel) {
			resultado.addPapel(c);
		}
		return resultado;
	}

	private String executarRegras(Papel papel, String operacao) {
		String nmClasse = papel.getClass().getName();
		StringBuilder msg = new StringBuilder();

		Map<String, List<RegraNegocioPapel>> regrasOperacao = regrasDeNegocio.get(nmClasse);

		if (regrasOperacao != null) {
			List<RegraNegocioPapel> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				for (RegraNegocioPapel strategies : regras) {
					String mensagemValidacao = strategies.processar(papel);

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
