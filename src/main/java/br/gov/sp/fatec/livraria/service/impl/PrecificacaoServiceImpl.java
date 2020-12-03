package br.gov.sp.fatec.livraria.service.impl;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoPrecificacao;
import br.gov.sp.fatec.livraria.dao.PrecificacaoDao;
import br.gov.sp.fatec.livraria.domain.Precificacao;
import br.gov.sp.fatec.livraria.negocio.precificacao.RegraNegocioPrecificacao;
import br.gov.sp.fatec.livraria.negocio.precificacao.VerificarCamposObrigatoriosPrecificacao;
import br.gov.sp.fatec.livraria.service.PrecificacaoService;

@Service
@Transactional(readOnly = false)
public class PrecificacaoServiceImpl implements PrecificacaoService {

	@Autowired
	private PrecificacaoDao daoPrecificacao;

	private Map<String, Map<String, List<RegraNegocioPrecificacao>>> regrasDeNegocio = new HashMap<String, Map<String, List<RegraNegocioPrecificacao>>>();

	public PrecificacaoServiceImpl() {
		VerificarCamposObrigatoriosPrecificacao verificaObrigatoriosPrecificacao = new VerificarCamposObrigatoriosPrecificacao();

		List<RegraNegocioPrecificacao> rnsSalvarPrecificacao = new ArrayList<RegraNegocioPrecificacao>();
		rnsSalvarPrecificacao.add(verificaObrigatoriosPrecificacao);

		List<RegraNegocioPrecificacao> rnsAlterarPrecificacao = new ArrayList<RegraNegocioPrecificacao>();
		rnsAlterarPrecificacao.add(verificaObrigatoriosPrecificacao);

		Map<String, List<RegraNegocioPrecificacao>> rnsPrecificacao = new HashMap<String, List<RegraNegocioPrecificacao>>();
		rnsPrecificacao.put("SALVAR", rnsSalvarPrecificacao);
		rnsPrecificacao.put("ALTERAR", rnsAlterarPrecificacao);

		regrasDeNegocio.put(Precificacao.class.getName(), rnsPrecificacao);

	}

	@Override
	public ResultadoPrecificacao salvar(Precificacao precificacao) {

		ResultadoPrecificacao resultado = new ResultadoPrecificacao();

		String msg = executarRegras(precificacao, "SALVAR");

		if (msg == null) {
			daoPrecificacao.save(precificacao);
			resultado.setMensagem("Padrão de precificacão inserido com sucesso.");
			resultado.addPrecificacao(precificacao);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoPrecificacao editar(Precificacao precificacao) {
		ResultadoPrecificacao resultado = new ResultadoPrecificacao();

		String msg = executarRegras(precificacao, "ALTERAR");

		/*
		 * for (Endereco end : precificacao.getEnderecos()) { end.setCliente(cliente); }
		 * 
		 * for (Telefone tel : cliente.getTelefones()) { tel.setCliente(cliente); }
		 */

		if (msg == null) {
			daoPrecificacao.update(precificacao);
			resultado.setMensagem("Precificacao editado com sucesso.");
			resultado.addPrecificacao(precificacao);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoPrecificacao excluir(Precificacao precificacao) {
		ResultadoPrecificacao resultado = new ResultadoPrecificacao();

		String msg = executarRegras(precificacao, "EXCLUIR");

		if (msg == null) {
			daoPrecificacao.delete(precificacao.getId());
			resultado.setMensagem("Precificacao removido com sucesso!");
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoPrecificacao buscarPorId(Precificacao precificacao) {
		ResultadoPrecificacao resultado = new ResultadoPrecificacao();
		resultado.addPrecificacao(daoPrecificacao.findById(precificacao.getId()));
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoPrecificacao buscarTodos() {
		ResultadoPrecificacao resultado = new ResultadoPrecificacao();
		List<Precificacao> listaPrecificacao = daoPrecificacao.findAll();
		for (Precificacao l : listaPrecificacao) {
			resultado.addPrecificacao(l);
		}
		return resultado;
	}

	private String executarRegras(Precificacao precificacao, String operacao) {
		String nmClasse = precificacao.getClass().getName();
		StringBuilder msg = new StringBuilder();

		Map<String, List<RegraNegocioPrecificacao>> regrasOperacao = regrasDeNegocio.get(nmClasse);

		if (regrasOperacao != null) {
			List<RegraNegocioPrecificacao> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				for (RegraNegocioPrecificacao strategies : regras) {
					String mensagemValidacao = strategies.processar(precificacao);

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