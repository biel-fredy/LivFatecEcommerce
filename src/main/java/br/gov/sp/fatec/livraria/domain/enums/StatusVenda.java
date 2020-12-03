package br.gov.sp.fatec.livraria.domain.enums;

public enum StatusVenda {
	
	EM_PROCESSAMENTO(1, "Em processamento"),
	APROVADA(2, "Aprovada"),
	REPROVADA(3, "Reprovada"),
	EM_TRANSITO(4, "Em Trânsito"),
	ENTREGUE(5, "Entregue"),
	EM_TROCA(6, "Em troca"),
	TROCA_AUTORIZADA(7, "Troca autorizada"),
	TROCADO(8, "Trocado");
	
	private Integer valorNumerico;
	private String descricao;
	
	private StatusVenda(Integer valorNumerico, String descricao) {
		this.valorNumerico = valorNumerico;
		this.descricao = descricao;
	}

	public Integer getValorNumerico() {
		return valorNumerico;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static StatusVenda valorToEnum(Integer valorNumerico) {
		if (valorNumerico == null) {
			return null;
		}
		for (StatusVenda status : StatusVenda.values()) {
			if (valorNumerico == status.getValorNumerico()) {
				return status;
			}
		}		
		throw new IllegalArgumentException("Valor inválido: " + valorNumerico);
	}


}
