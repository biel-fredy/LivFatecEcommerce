package br.gov.sp.fatec.livraria.dao.impl;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.dao.AbstractDao;
import br.gov.sp.fatec.livraria.dao.LivroDao;
import br.gov.sp.fatec.livraria.domain.Livro;

@Repository
public class LivroDaoImpl extends AbstractDao<Livro, Long> implements LivroDao {

}
