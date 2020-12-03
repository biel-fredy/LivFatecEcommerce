package br.gov.sp.fatec.livraria.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoCategoria;
import br.gov.sp.fatec.livraria.dao.CategoriaDao;
import br.gov.sp.fatec.livraria.domain.Categoria;
import br.gov.sp.fatec.livraria.negocio.categoria.RegraNegocioCategoria;
import br.gov.sp.fatec.livraria.negocio.categoria.VerificarCamposObrigatoriosCategoria;
import br.gov.sp.fatec.livraria.service.CategoriaService;

@Service
@Transactional(readOnly = false)
public class CategoriaServiceImpl implements CategoriaService {
	@Autowired
	private CategoriaDao daoCategoria;

	private Map<String, Map<String, List<RegraNegocioCategoria>>> regrasDeNegocio = new HashMap<String, Map<String, List<RegraNegocioCategoria>>>();

	public CategoriaServiceImpl() {
		VerificarCamposObrigatoriosCategoria verificaObrigatoriosCategoria = new VerificarCamposObrigatoriosCategoria();

		List<RegraNegocioCategoria> rnsSalvarCategoria = new ArrayList<RegraNegocioCategoria>();
		rnsSalvarCategoria.add(verificaObrigatoriosCategoria);

		List<RegraNegocioCategoria> rnsAlterarCategoria = new ArrayList<RegraNegocioCategoria>();
		rnsAlterarCategoria.add(verificaObrigatoriosCategoria);

		Map<String, List<RegraNegocioCategoria>> rnsCategoria = new HashMap<String, List<RegraNegocioCategoria>>();
		rnsCategoria.put("SALVAR", rnsSalvarCategoria);
		rnsCategoria.put("ALTERAR", rnsAlterarCategoria);

		regrasDeNegocio.put(Categoria.class.getName(), rnsCategoria);

	}

	@Override
	public ResultadoCategoria salvar(Categoria categoria) {

		ResultadoCategoria resultado = new ResultadoCategoria();

		String msg = executarRegras(categoria, "SALVAR");

		if (msg == null) {
			daoCategoria.save(categoria);
			resultado.setMensagem("Categoria inserido com sucesso.");
			resultado.addCategoria(categoria);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoCategoria editar(Categoria categoria) {
		ResultadoCategoria resultado = new ResultadoCategoria();

		String msg = executarRegras(categoria, "ALTERAR");

		if (msg == null) {
			daoCategoria.update(categoria);
			resultado.setMensagem("Categoria editado com sucesso.");
			resultado.addCategoria(categoria);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoCategoria excluir(Categoria categoria) {
		ResultadoCategoria resultado = new ResultadoCategoria();

		String msg = executarRegras(categoria, "EXCLUIR");

		if (msg == null) {
			daoCategoria.delete(categoria.getId());
			resultado.setMensagem("Categoria removido com sucesso!");
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoCategoria buscarPorId(Categoria categoria) {
		ResultadoCategoria resultado = new ResultadoCategoria();
		resultado.addCategoria(daoCategoria.findById(categoria.getId()));
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoCategoria buscarTodos() {
		ResultadoCategoria resultado = new ResultadoCategoria();
		List<Categoria> listaCategoria = daoCategoria.findAll();
		for (Categoria c : listaCategoria) {
			resultado.addCategoria(c);
		}
		return resultado;
	}

	private String executarRegras(Categoria categoria, String operacao) {
		String nmClasse = categoria.getClass().getName();
		StringBuilder msg = new StringBuilder();

		Map<String, List<RegraNegocioCategoria>> regrasOperacao = regrasDeNegocio.get(nmClasse);

		if (regrasOperacao != null) {
			List<RegraNegocioCategoria> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				for (RegraNegocioCategoria strategies : regras) {
					String mensagemValidacao = strategies.processar(categoria);

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
