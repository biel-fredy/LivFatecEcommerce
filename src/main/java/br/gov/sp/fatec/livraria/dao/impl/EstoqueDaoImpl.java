package br.gov.sp.fatec.livraria.dao.impl;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.dao.AbstractDao;
import br.gov.sp.fatec.livraria.dao.EstoqueDao;
import br.gov.sp.fatec.livraria.domain.Estoque;

@Repository
public class EstoqueDaoImpl extends AbstractDao<Estoque, Long> implements EstoqueDao {

}
