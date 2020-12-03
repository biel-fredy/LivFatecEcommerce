package br.gov.sp.fatec.livraria.domain.enums;

public enum TipoLogradouro {
	
	AEROPORTO(1, "Aeroporto"),
	ALAMEDA(2, "Alameda"),
	AREA(3, "Área"),
	AVENIDA(4, "Avenida"),
	CAMPO(5, "Campo"),
	CHACARA(6, "Chácara"),
	COLONIA(7, "Colônia"),
	CONDOMINIO(8, "Condomínio"),
	CONJUNTO(9, "Conjunto"),
	DISTRITO(10, "Distrito"),
	ESPLANADA(11, "Esplanada"),
	ESTACAO(12, "Estação"),
	ESTRADA(13, "Estrada"),
	FAVELA(14, "Favela"),
	FAZENDA(15, "Fazenda"),
	FEIRA(16, "Feira"),
	JARDIM(17, "Jardim"),
	LADEIRA(18, "Ladeira"),
	LAGO(19, "Lago"),
	LAGOA(20, "Lagoa"),
	LARGO(21, "Largo"),
	LOTEAMENTO(1, "Loteamento"),
	MORRO(22, "Morro"),
	NUCLEO(23, "Núcleo"),
	PARQUE(24, "Parque"),
	PASSARELA(25, "Passarela"),
	PATIO(26, "Pátio"),
	PRACA(27, "Praça"),
	QUADRA(28, "Quadra"),
	RECANTO(29, "Recanto"),
	RESIDENCIAL(30, "Residencial"),
	RODOVIA(31, "Rodovia"),
	RUA(32, "Rua"),
	SETOR(33, "Setor"),
	SITIO(34, "Sítio"),
	TRAVESSA(35, "Travessa"),
	TRECHO(36, "Trecho"),
	TREVO(37, "Trevo"),
	VALE(38, "Vale"),
	VEREDA(39, "Vereda"),
	VIA(40, "Via"),
	VIADUTO(41, "Viaduto"),
	VIELA(42, "Viela"),
	VILA(43, "Vila");
	
	private Integer valorNumerico;
	private String descricao;

	private TipoLogradouro(Integer valorNumerico, String descricao) {
		this.valorNumerico = valorNumerico;
		this.descricao = descricao;
	}

	public Integer getValorNumerico() {
		return valorNumerico;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoLogradouro valorToEnum(Integer valorNumerico) {
		if (valorNumerico == null) {
			return null;
		}
		for (TipoLogradouro tipo : TipoLogradouro.values()) {
			if (valorNumerico == tipo.getValorNumerico()) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Valor inválido: " + valorNumerico);
	}

}
