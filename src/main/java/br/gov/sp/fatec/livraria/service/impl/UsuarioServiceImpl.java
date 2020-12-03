package br.gov.sp.fatec.livraria.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.livraria.aplicacao.Usuario;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoUsuario;
import br.gov.sp.fatec.livraria.dao.UsuarioDao;
import br.gov.sp.fatec.livraria.domain.Endereco;
import br.gov.sp.fatec.livraria.domain.Telefone;
import br.gov.sp.fatec.livraria.negocio.usuario.RegraNegocioUsuario;
import br.gov.sp.fatec.livraria.negocio.usuario.VerificarCamposObrigatoriosUsuario;
import br.gov.sp.fatec.livraria.service.UsuarioService;

@Service
@Transactional(readOnly = false)
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao daoUsuario;

	private Map<String, Map<String, List<RegraNegocioUsuario>>> regrasDeNegocio = new HashMap<String, Map<String, List<RegraNegocioUsuario>>>();

	public UsuarioServiceImpl() {
		VerificarCamposObrigatoriosUsuario verificaObrigatoriosUsuario = new VerificarCamposObrigatoriosUsuario();

		List<RegraNegocioUsuario> rnsSalvarUsuario = new ArrayList<RegraNegocioUsuario>();
		rnsSalvarUsuario.add(verificaObrigatoriosUsuario);

		List<RegraNegocioUsuario> rnsAlterarUsuario = new ArrayList<RegraNegocioUsuario>();
		rnsAlterarUsuario.add(verificaObrigatoriosUsuario);

		Map<String, List<RegraNegocioUsuario>> rnsUsuario = new HashMap<String, List<RegraNegocioUsuario>>();
		rnsUsuario.put("SALVAR", rnsSalvarUsuario);
		rnsUsuario.put("ALTERAR", rnsAlterarUsuario);

		regrasDeNegocio.put(Usuario.class.getName(), rnsUsuario);

	}

	@Override
	public ResultadoUsuario salvar(Usuario usuario) {

		ResultadoUsuario resultado = new ResultadoUsuario();

		String msg = executarRegras(usuario, "SALVAR");
		
		if (usuario.getCliente() != null) {
			for (Endereco end : usuario.getCliente().getEnderecos()) {
				end.setCliente(usuario.getCliente());
			}
			
			for (Telefone tel : usuario.getCliente().getTelefones()) {
				tel.setCliente(usuario.getCliente());
			}
		}

		if (msg == null) {
			daoUsuario.save(usuario);
			resultado.setMensagem("Usuario inserido com sucesso.");
			resultado.addUsuario(usuario);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoUsuario editar(Usuario usuario) {
		ResultadoUsuario resultado = new ResultadoUsuario();

		String msg = executarRegras(usuario, "ALTERAR");

		if (msg == null) {
			daoUsuario.update(usuario);
			resultado.setMensagem("Usuario editado com sucesso.");
			resultado.addUsuario(usuario);
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	public ResultadoUsuario excluir(Usuario usuario) {
		ResultadoUsuario resultado = new ResultadoUsuario();

		String msg = executarRegras(usuario, "EXCLUIR");

		if (msg == null) {
			daoUsuario.delete(usuario.getId());
			resultado.setMensagem("Usuario removido com sucesso!");
			resultado.setSuccessOrFailed("success");
		} else {
			resultado.setMensagem(msg);
			resultado.setSuccessOrFailed("fail");
		}
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoUsuario buscarPorId(Usuario usuario) {
		ResultadoUsuario resultado = new ResultadoUsuario();
		resultado.addUsuario(daoUsuario.findById(usuario.getId()));
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoUsuario buscarTodos() {
		ResultadoUsuario resultado = new ResultadoUsuario();
		List<Usuario> listaUsuario = daoUsuario.findAll();
		for (Usuario c : listaUsuario) {
			resultado.addUsuario(c);
		}
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultadoUsuario buscarPorNomeUsuario(String usuario) {
		ResultadoUsuario resultado = new ResultadoUsuario();
		List<Usuario> listaUsuario = daoUsuario.findByNomeUsuario(usuario);
		resultado.addUsuario(listaUsuario.get(0));
		return resultado;
	}

	private String executarRegras(Usuario usuario, String operacao) {
		String nmClasse = usuario.getClass().getName();
		StringBuilder msg = new StringBuilder();

		Map<String, List<RegraNegocioUsuario>> regrasOperacao = regrasDeNegocio.get(nmClasse);

		if (regrasOperacao != null) {
			List<RegraNegocioUsuario> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				for (RegraNegocioUsuario strategies : regras) {
					String mensagemValidacao = strategies.processar(usuario);

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
