package br.gov.sp.fatec.livraria.domain.enums;

public enum TipoTelefone {

	RESIDENCIAL(1, "Residencial"),
	CELULAR(2, "Celular"),
	COMERCIAL(3, "Comercial");

	private Integer valorNumerico;
	private String descricao;

	private TipoTelefone(Integer valorNumerico, String descricao) {
		this.valorNumerico = valorNumerico;
		this.descricao = descricao;
	}

	public Integer getValorNumerico() {
		return valorNumerico;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoTelefone valorToEnum(Integer valorNumerico) {
		if (valorNumerico == null) {
			return null;
		}
		for (TipoTelefone tipo : TipoTelefone.values()) {
			if (valorNumerico == tipo.getValorNumerico()) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Valor inválido: " + valorNumerico);
	}
}
