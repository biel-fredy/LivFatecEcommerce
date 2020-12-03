package br.gov.sp.fatec.livraria.domain.enums;

public enum FormaPagamento {
	
	BOLETO(1, "Boleto"),
	CARTAO_CREDITO(2, "Cartão de crédito"),
	CARTAO_DEBITO(3, "Cartão de débito");
	
	private Integer valorNumerico;
	private String descricao;
	
	private FormaPagamento(Integer valorNumerico, String descricao) {
		this.valorNumerico = valorNumerico;
		this.descricao = descricao;
	}

	public Integer getValorNumerico() {
		return valorNumerico;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static FormaPagamento valorToEnum(Integer valorNumerico) {
		if (valorNumerico == null) {
			return null;
		}
		for (FormaPagamento formaPagamento : FormaPagamento.values()) {
			if (valorNumerico == formaPagamento.getValorNumerico()) {
				return formaPagamento;
			}
		}		
		throw new IllegalArgumentException("Valor inválido: " + valorNumerico);
	}


}
