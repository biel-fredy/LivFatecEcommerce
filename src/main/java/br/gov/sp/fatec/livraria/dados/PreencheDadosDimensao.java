package br.gov.sp.fatec.livraria.dados;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.gov.sp.fatec.livraria.domain.Dimensao;
import br.gov.sp.fatec.livraria.service.DimensaoService;

public class PreencheDadosDimensao {
		
	@Autowired
	private DimensaoService dimensaoService;
	
	public void preencheDados() {
		System.out.println("chega aqui");
		List<Dimensao> lista = new ArrayList<Dimensao>();
		Dimensao dimensao = new Dimensao(2L, "Comercial 13x20 Capa Dura", 20.96, 13.34);
		Dimensao dimensao1 = new Dimensao("Comercial 13x20 Capa Mole", 20.32, 12.70);
		Dimensao dimensao2 = new Dimensao("Comercial 15x23 Capa Dura", 23.50, 15.88);
		Dimensao dimensao3 = new Dimensao("Comercial 15x23 Capa Mole", 22.86, 15.24);
		Dimensao dimensao4 = new Dimensao("Comercial 20x25 Capa Dura", 26.04, 20.96);
		Dimensao dimensao5 = new Dimensao("Comercial 20x25 Capa Mole", 25.40, 20.32);
		Dimensao dimensao6 = new Dimensao("Mini Quadrado 13x13 Capa Mole", 12.70, 12.70);
		Dimensao dimensao7 = new Dimensao("Quadrado Pequeno 18x18 Capa Dura", 17.78, 17.78);
		Dimensao dimensao8 = new Dimensao("Quadrado Pequeno 18x18 Capa Mole", 17.15, 16.83);
		dimensaoService.salvar(dimensao);
		
		lista.add(dimensao);
		lista.add(dimensao1);
		lista.add(dimensao2);
		lista.add(dimensao3);
		lista.add(dimensao4);
		lista.add(dimensao5);
		lista.add(dimensao6);
		lista.add(dimensao7);
		lista.add(dimensao8);
	}
	
	public void salvaDados(List<Dimensao> lista) {
		for(Dimensao d : lista) {
			System.out.println(d.getNome());
			dimensaoService.salvar(d);
		}
	}

}








