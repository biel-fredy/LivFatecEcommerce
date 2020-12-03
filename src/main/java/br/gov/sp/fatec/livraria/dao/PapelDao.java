package br.gov.sp.fatec.livraria.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.aplicacao.Papel;

@Repository
public interface PapelDao {

	void save(Papel papel);

	void update(Papel papel);

	void delete(Long id);

	Papel findById(Long id);

	List<Papel> findAll();

}
