package br.gov.sp.fatec.livraria.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "0")
public class CupomTroca extends Cupom {

	private static final long serialVersionUID = 1L;
	
	private ItemVenda itemVenda;

	public ItemVenda getItemVenda() {
		return itemVenda;
	}

	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}

}
