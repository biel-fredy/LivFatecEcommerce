package br.gov.sp.fatec.livraria.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import br.gov.sp.fatec.livraria.domain.Endereco;

@Repository
public interface EnderecoDao {

	void save(Endereco endereco);

	void update(Endereco endereco);

	void delete(Long id);

	Endereco findById(Long id);

	List<Endereco> findAll();
	
}
