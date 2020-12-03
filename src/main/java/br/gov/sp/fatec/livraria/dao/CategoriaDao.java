package br.gov.sp.fatec.livraria.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.domain.Categoria;

@Repository
public interface CategoriaDao {

	void save(Categoria categoria);

	void update(Categoria categoria);

	void delete(Long id);

	Categoria findById(Long id);

	List<Categoria> findAll();

}
