package br.gov.sp.fatec.livraria.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.aplicacao.Usuario;

@Repository
public interface UsuarioDao {

	void save(Usuario usuario);

	void update(Usuario usuario);

	void delete(Long id);

	Usuario findById(Long id);

	List<Usuario> findByNomeUsuario(String usuario);

	List<Usuario> findAll();

}
