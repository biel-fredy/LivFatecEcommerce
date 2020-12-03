package br.gov.sp.fatec.livraria.domain;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

	private List<ItemVenda> itensVenda = new ArrayList<ItemVenda>();

	public Carrinho() {
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public void addItem(ItemVenda itemVenda) {
		this.itensVenda.add(itemVenda);
	}

	public void removeItem(Long id) {
		for (int i = 0; i < this.itensVenda.size(); i++) {
			if (this.itensVenda.get(i).getLivro().getId() == id) {
				this.getItensVenda().remove(i);
			}
		}
	}
	
	public void removeItem(ItemVenda item) {
		this.itensVenda.remove(item);
	}
	
	public Double totalCarrinho(){
		Double total = 0d;
		for (ItemVenda item : this.itensVenda) {
			total += item.getValorVenda() * item.getQuantidade();
		}
		return total;
	}
}
