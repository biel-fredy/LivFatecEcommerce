package br.gov.sp.fatec.livraria.dao.impl;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.aplicacao.Papel;
import br.gov.sp.fatec.livraria.dao.AbstractDao;
import br.gov.sp.fatec.livraria.dao.PapelDao;

@Repository
public class PapelDaoImpl extends AbstractDao<Papel, Long> implements PapelDao {

}
