package br.gov.sp.fatec.livraria.dao.impl;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.dao.AbstractDao;
import br.gov.sp.fatec.livraria.dao.EnderecoDao;
import br.gov.sp.fatec.livraria.domain.Endereco;

@Repository
public class EnderecoDaoImpl extends AbstractDao<Endereco, Long> implements EnderecoDao {

}
