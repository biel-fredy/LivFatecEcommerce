package br.gov.sp.fatec.livraria.aplicacao.rotina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoLivro;
import br.gov.sp.fatec.livraria.domain.Livro;
import br.gov.sp.fatec.livraria.service.LivroService;

@Component
@EnableScheduling
public class RotinaInativarLivro {

	private final long QUINZE_MINUTOS = 1000 * 60 * 15;

	@Autowired
	private LivroService livroService;
	
	@Scheduled(fixedDelay = QUINZE_MINUTOS)
	public void inativaLivro() {
		ResultadoLivro resultado = livroService.buscarTodos();
		System.out.println("Executou Rotina");
		
		for (Livro livro : resultado.getResultadoLista()) {
			livro.carregaQuantidadeEstoque();
			if (livro.getQuantidadeEstoque() <= 0) {
				livro.setStatus(false);
				livroService.editar(livro);
			}
		}
	}
}
