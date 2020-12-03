package br.gov.sp.fatec.livraria.service.impl;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.livraria.aplicacao.Permissao;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoPermissao;
import br.gov.sp.fatec.livraria.dao.PermissaoDao;
import br.gov.sp.fatec.livraria.negocio.permissao.RegraNegocioPermissao;
import br.gov.sp.fatec.livraria.negocio.permissao.VerificarCamposObrigatoriosPermissao;
import br.gov.sp.fatec.livraria.service.PermissaoService;

@Service
@Transactional(readOnly = false)
public class PermissaoServiceImpl implements PermissaoService {

	@Autowired
	private PermissaoDao daoPermissao;

	private Map<String, Map<String, List<RegraNegocioPermissao>>> regrasDeNegocio = new HashMap<String, Map<String, List<RegraNegocioPermissao>>>();

	public PermissaoServiceImpl() {
		VerificarCamposObrigatoriosPermissao verificaObrigatoriosPermissao = new VerificarCamposObrigatoriosPermissao();

		List<RegraNegocioPermissao> rnsSalvarPermissao = new ArrayList<RegraNegocioPermissao>();
		rnsSalvarPermissao.add(verificaObrigatoriosPermissao);

		List<RegraNegocioPermissao> rnsAlterarPermissao = new ArrayList<RegraNegocioPermissao>();
		rnsAlterarPermissao.add(verificaObrigatoriosPermissao);

		Map<String, List<RegraNegocioPermissao>> rnsPermissao = new HashMap<String, List<RegraNegocioPermissao>>();
		rnsPermissao.put("SALVAR", rnsSalvarPermissao);
		rnsPermissao.put("ALTERAR", rnsAlterarPermissao);

		regrasDeNegocio.put(Permissao.class.getName(), rnsPermissao);

	}

	@Override
	public ResultadoPermissao salvar(Permissao permissao) {

		ResultadoPermissao resultado = new ResultadoPermissao();

		String msg = executarRegras(permissao, "SALVAR");

		if (msg == null) {
			daoPermissao.save(permissao);
			resultado.setMensagem("Permissao inserido com sucesso.");
			resultado.addPermissao(permissao);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoPermissao editar(Permissao permissao) {
		ResultadoPermissao resultado = new ResultadoPermissao();

		String msg = executarRegras(permissao, "ALTERAR");

		if (msg == null) {
			daoPermissao.update(permissao);
			resultado.setMensagem("Permissao editado com sucesso.");
			resultado.addPermissao(permissao);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoPermissao excluir(Permissao permissao) {
		ResultadoPermissao resultado = new ResultadoPermissao();

		String msg = executarRegras(permissao, "EXCLUIR");

		if (msg == null) {
			daoPermissao.delete(permissao.getId());
			resultado.setMensagem("Permissao removido com sucesso!");
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoPermissao buscarPorId(Permissao permissao) {
		ResultadoPermissao resultado = new ResultadoPermissao();
		resultado.addPermissao(daoPermissao.findById(permissao.getId()));
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoPermissao buscarTodos() {
		ResultadoPermissao resultado = new ResultadoPermissao();
		List<Permissao> listaPermissao = daoPermissao.findAll();
		for (Permissao c : listaPermissao) {
			resultado.addPermissao(c);
		}
		return resultado;
	}

	private String executarRegras(Permissao permissao, String operacao) {
		String nmClasse = permissao.getClass().getName();
		StringBuilder msg = new StringBuilder();

		Map<String, List<RegraNegocioPermissao>> regrasOperacao = regrasDeNegocio.get(nmClasse);

		if (regrasOperacao != null) {
			List<RegraNegocioPermissao> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				for (RegraNegocioPermissao strategies : regras) {
					String mensagemValidacao = strategies.processar(permissao);

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
