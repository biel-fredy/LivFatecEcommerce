package br.gov.sp.fatec.livraria.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.domain.Estoque;

@Repository
public interface EstoqueDao {

	void save(Estoque estoque);

	void update(Estoque estoque);

	void delete(Long id);

	Estoque findById(Long id);

	List<Estoque> findAll();

}
