package br.gov.sp.fatec.livraria.dao.impl;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.dao.AbstractDao;
import br.gov.sp.fatec.livraria.dao.ClienteDao;
import br.gov.sp.fatec.livraria.domain.Cliente;

@Repository
public class ClienteDaoImpl extends AbstractDao<Cliente, Long> implements ClienteDao {

}
