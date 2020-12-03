package br.gov.sp.fatec.livraria.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.domain.Autor;

@Repository
public interface AutorDao {

	void save(Autor autor);

	void update(Autor autor);

	void delete(Long id);

	Autor findById(Long id);

	List<Autor> findAll();
	
	List<Autor> findByNome(String nome);

}
