package br.gov.sp.fatec.livraria.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.aplicacao.Usuario;
import br.gov.sp.fatec.livraria.dao.AbstractDao;
import br.gov.sp.fatec.livraria.dao.UsuarioDao;

@Repository
public class UsuarioDaoImpl extends AbstractDao<Usuario, Long> implements UsuarioDao {

	@Override
	public List<Usuario> findByNomeUsuario(String usuario) {
		return createQuery("select u from Usuario u where u.nomeUsuario = ?1", usuario);
	}

}
