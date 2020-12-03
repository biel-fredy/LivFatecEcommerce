package br.gov.sp.fatec.livraria.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.aplicacao.Permissao;

@Repository
public interface PermissaoDao {
	
	void save(Permissao permissao);

	void update(Permissao permissao);

	void delete(Long id);

	Permissao findById(Long id);

	List<Permissao> findAll();


}
