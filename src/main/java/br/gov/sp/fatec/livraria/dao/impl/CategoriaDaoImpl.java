package br.gov.sp.fatec.livraria.dao.impl;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.dao.AbstractDao;
import br.gov.sp.fatec.livraria.dao.CategoriaDao;
import br.gov.sp.fatec.livraria.domain.Categoria;

@Repository
public class CategoriaDaoImpl extends AbstractDao<Categoria, Long> implements CategoriaDao {

}
