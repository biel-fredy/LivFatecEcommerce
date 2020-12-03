package br.gov.sp.fatec.livraria.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoAutor;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoLivro;
import br.gov.sp.fatec.livraria.dao.LivroDao;
import br.gov.sp.fatec.livraria.domain.Autor;
import br.gov.sp.fatec.livraria.domain.Categoria;
import br.gov.sp.fatec.livraria.domain.Livro;
import br.gov.sp.fatec.livraria.negocio.livro.RegraNegocioLivro;
import br.gov.sp.fatec.livraria.negocio.livro.VerificarCamposObrigatoriosLivro;
import br.gov.sp.fatec.livraria.service.AutorService;
import br.gov.sp.fatec.livraria.service.CategoriaService;
import br.gov.sp.fatec.livraria.service.LivroService;

@Service
@Transactional(readOnly = false)
public class LivroServiceImpl implements LivroService {

	@Autowired
	private LivroDao daoLivro;

	@Autowired
	private AutorService autorService;

	@Autowired
	private CategoriaService categoriaService;
	
	private Map<String, Map<String, List<RegraNegocioLivro>>> regrasDeNegocio = new HashMap<String, Map<String, List<RegraNegocioLivro>>>();

	public LivroServiceImpl() {
		VerificarCamposObrigatoriosLivro verificaObrigatoriosLivro = new VerificarCamposObrigatoriosLivro();

		List<RegraNegocioLivro> rnsSalvarLivro = new ArrayList<RegraNegocioLivro>();
		rnsSalvarLivro.add(verificaObrigatoriosLivro);

		List<RegraNegocioLivro> rnsAlterarLivro = new ArrayList<RegraNegocioLivro>();
		rnsAlterarLivro.add(verificaObrigatoriosLivro);

		Map<String, List<RegraNegocioLivro>> rnsLivro = new HashMap<String, List<RegraNegocioLivro>>();
		rnsLivro.put("SALVAR", rnsSalvarLivro);
		rnsLivro.put("ALTERAR", rnsAlterarLivro);

		regrasDeNegocio.put(Livro.class.getName(), rnsLivro);

	}

	@Override
	public ResultadoLivro salvar(Livro livro) {

		ResultadoLivro resultado = new ResultadoLivro();
		ResultadoAutor resultadoAutor = new ResultadoAutor();
		
		resultadoAutor = autorService.buscarTodos();
		Boolean temIgual = false;

		String msg = executarRegras(livro, "SALVAR");

		for (Autor autor : livro.getAutores()) {
			autor.addLivro(livro);
			
			for (Autor autorLista : resultadoAutor.getResultadoLista()) {
				if (autorLista.equals(autor)) {
					temIgual = true;
				}
			}
			
			if (!temIgual) {
				autorService.salvar(autor);
			}
		}

		livro.getDimensoes().addLivro(livro);

		livro.getCapa().setLivro(livro);

		for (Categoria cat : livro.getCategorias()) {
			cat.addLivro(livro);
			categoriaService.salvar(cat);
		}

		if (msg == null) {
			daoLivro.save(livro);
			resultado.setMensagem("Livro inserido com sucesso.");
			resultado.addLivro(livro);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoLivro editar(Livro livro) {
		ResultadoLivro resultado = new ResultadoLivro();

		String msg = executarRegras(livro, "ALTERAR");

		for (Autor autor : livro.getAutores()) {
			autor.addLivro(livro);
			autorService.editar(autor);
		}

		livro.getDimensoes().addLivro(livro);

		livro.getCapa().setLivro(livro);

		for (Categoria cat : livro.getCategorias()) {
			cat.addLivro(livro);
			categoriaService.editar(cat);
		}

		if (msg == null) {
			daoLivro.update(livro);
			resultado.setMensagem("Livro editado com sucesso.");
			resultado.addLivro(livro);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoLivro excluir(Livro livro) {
		ResultadoLivro resultado = new ResultadoLivro();

		String msg = executarRegras(livro, "EXCLUIR");

		if (msg == null) {
			daoLivro.delete(livro.getId());
			resultado.setMensagem("Livro removido com sucesso!");
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoLivro buscarPorId(Livro livro) {
		ResultadoLivro resultado = new ResultadoLivro();
		resultado.addLivro(daoLivro.findById(livro.getId()));
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoLivro buscarTodos() {
		ResultadoLivro resultado = new ResultadoLivro();
		List<Livro> listaLivro = daoLivro.findAll();
		for (Livro l : listaLivro) {
			resultado.addLivro(l);
		}
		return resultado;
	}

	private String executarRegras(Livro livro, String operacao) {
		String nmClasse = livro.getClass().getName();
		StringBuilder msg = new StringBuilder();

		Map<String, List<RegraNegocioLivro>> regrasOperacao = regrasDeNegocio.get(nmClasse);

		if (regrasOperacao != null) {
			List<RegraNegocioLivro> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				for (RegraNegocioLivro strategies : regras) {
					String mensagemValidacao = strategies.processar(livro);

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
