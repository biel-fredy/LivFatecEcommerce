package br.gov.sp.fatec.livraria.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.domain.Cliente;

@Repository
public interface ClienteDao {

	void save(Cliente cliente);

	void update(Cliente cliente);

	void delete(Long id);

	Cliente findById(Long id);

	List<Cliente> findAll();
	
}
