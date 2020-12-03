package br.gov.sp.fatec.livraria.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoAutor;
import br.gov.sp.fatec.livraria.dao.AutorDao;
import br.gov.sp.fatec.livraria.domain.Autor;
import br.gov.sp.fatec.livraria.negocio.autor.RegraNegocioAutor;
import br.gov.sp.fatec.livraria.negocio.autor.VerificarCamposObrigatoriosAutor;
import br.gov.sp.fatec.livraria.service.AutorService;

@Service
@Transactional(readOnly = false)
public class AutorServiceImpl implements AutorService {
	@Autowired
	private AutorDao daoAutor;

	private Map<String, Map<String, List<RegraNegocioAutor>>> regrasDeNegocio = new HashMap<String, Map<String, List<RegraNegocioAutor>>>();

	public AutorServiceImpl() {
		VerificarCamposObrigatoriosAutor verificaObrigatoriosAutor = new VerificarCamposObrigatoriosAutor();

		List<RegraNegocioAutor> rnsSalvarAutor = new ArrayList<RegraNegocioAutor>();
		rnsSalvarAutor.add(verificaObrigatoriosAutor);

		List<RegraNegocioAutor> rnsAlterarAutor = new ArrayList<RegraNegocioAutor>();
		rnsAlterarAutor.add(verificaObrigatoriosAutor);

		Map<String, List<RegraNegocioAutor>> rnsAutor = new HashMap<String, List<RegraNegocioAutor>>();
		rnsAutor.put("SALVAR", rnsSalvarAutor);
		rnsAutor.put("ALTERAR", rnsAlterarAutor);

		regrasDeNegocio.put(Autor.class.getName(), rnsAutor);

	}

	@Override
	public ResultadoAutor salvar(Autor autor) {

		ResultadoAutor resultado = new ResultadoAutor();

		String msg = executarRegras(autor, "SALVAR");

		if (msg == null) {
			daoAutor.save(autor);
			resultado.setMensagem("Autor inserido com sucesso.");
			resultado.addAutor(autor);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoAutor editar(Autor autor) {
		ResultadoAutor resultado = new ResultadoAutor();

		String msg = executarRegras(autor, "ALTERAR");

		if (msg == null) {
			daoAutor.update(autor);
			resultado.setMensagem("Autor editado com sucesso.");
			resultado.addAutor(autor);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoAutor excluir(Autor autor) {
		ResultadoAutor resultado = new ResultadoAutor();

		String msg = executarRegras(autor, "EXCLUIR");

		if (msg == null) {
			daoAutor.delete(autor.getId());
			resultado.setMensagem("Autor removido com sucesso!");
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoAutor buscarPorId(Autor autor) {
		ResultadoAutor resultado = new ResultadoAutor();
		resultado.addAutor(daoAutor.findById(autor.getId()));
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoAutor buscarTodos() {
		ResultadoAutor resultado = new ResultadoAutor();
		List<Autor> listaAutor = daoAutor.findAll();
		for (Autor c : listaAutor) {
			resultado.addAutor(c);
		}
		return resultado;
	}

	private String executarRegras(Autor autor, String operacao) {
		String nmClasse = autor.getClass().getName();
		StringBuilder msg = new StringBuilder();

		Map<String, List<RegraNegocioAutor>> regrasOperacao = regrasDeNegocio.get(nmClasse);

		if (regrasOperacao != null) {
			List<RegraNegocioAutor> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				for (RegraNegocioAutor strategies : regras) {
					String mensagemValidacao = strategies.processar(autor);

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

	@Override
	@Transactional(readOnly = true)
	public ResultadoAutor buscarPorNome(String nome) {
		ResultadoAutor resultado = new ResultadoAutor();
		List<Autor> listaAutor = daoAutor.findByNome(nome);
		for (Autor c : listaAutor) {
			resultado.addAutor(c);
		}
		return resultado;
	}

}
