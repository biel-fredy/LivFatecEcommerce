package br.gov.sp.fatec.livraria.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.dao.AbstractDao;
import br.gov.sp.fatec.livraria.dao.AutorDao;
import br.gov.sp.fatec.livraria.domain.Autor;

@Repository
public class AutorDaoImpl extends AbstractDao<Autor, Long> implements AutorDao {

	@Override
	public List<Autor> findByNome(String nome) {
		return createQuery("select a from autores a where a.nome like concat('%', ?1, '%')", nome);
	}

}
