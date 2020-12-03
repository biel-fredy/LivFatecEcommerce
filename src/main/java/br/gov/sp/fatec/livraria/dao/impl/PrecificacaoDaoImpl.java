package br.gov.sp.fatec.livraria.dao.impl;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.dao.AbstractDao;
import br.gov.sp.fatec.livraria.dao.PrecificacaoDao;
import br.gov.sp.fatec.livraria.domain.Precificacao;

@Repository
public class PrecificacaoDaoImpl extends AbstractDao<Precificacao, Long> implements PrecificacaoDao {

}
