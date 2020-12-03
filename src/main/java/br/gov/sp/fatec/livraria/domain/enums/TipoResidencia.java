package br.gov.sp.fatec.livraria.domain.enums;

public enum TipoResidencia {
	
	CASA(1, "Casa"),
	APARTAMENTO(2, "Apartamento"),
	QUITINETE(3, "Quitinete"),
	FLAT(4, "Flat"),
	DUPLEX(5, "Duplex");
	
	private Integer valorNumerico;
	private String descricao;
	
	private TipoResidencia(Integer valorNumerico, String descricao) {
		this.valorNumerico = valorNumerico;
		this.descricao = descricao;
	}

	public Integer getValorNumerico() {
		return valorNumerico;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoResidencia valorToEnum(Integer valorNumerico) {
		if (valorNumerico == null) {
			return null;
		}
		for (TipoResidencia tipo : TipoResidencia.values()) {
			if (valorNumerico == tipo.getValorNumerico()) {
				return tipo;
			}
		}		
		throw new IllegalArgumentException("Valor inválido: " + valorNumerico);
	}

}
