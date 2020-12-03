package br.gov.sp.fatec.livraria.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.domain.Dimensao;

@Repository
public interface DimensaoDao {

	void save(Dimensao dimensao);

	void update(Dimensao dimensao);

	void delete(Long id);

	Dimensao findById(Long id);

	List<Dimensao> findAll();

}
