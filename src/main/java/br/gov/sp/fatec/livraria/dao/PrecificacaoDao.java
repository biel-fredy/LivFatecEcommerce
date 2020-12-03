package br.gov.sp.fatec.livraria.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.livraria.domain.Precificacao;

@Repository
public interface PrecificacaoDao {
	
	void save(Precificacao precificacao);

	void update(Precificacao precificacao);

	void delete(Long id);

	Precificacao findById(Long id);

	List<Precificacao> findAll();

}
