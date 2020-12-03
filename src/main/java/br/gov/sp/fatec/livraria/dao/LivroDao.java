package br.gov.sp.fatec.livraria.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import br.gov.sp.fatec.livraria.domain.Livro;

@Repository
public interface LivroDao {

	void save(Livro livro);

	void update(Livro livro);

	void delete(Long id);

	Livro findById(Long id);

	List<Livro> findAll();

}
