package br.gov.sp.fatec.livraria.domain.enums.converters;

import javax.persistence.AttributeConverter;

import br.gov.sp.fatec.livraria.domain.enums.FormaPagamento;

public class FormaPagementoConverter implements AttributeConverter<FormaPagamento, Integer> {

	@Override
	public Integer convertToDatabaseColumn(FormaPagamento attribute) {
		return attribute.getValorNumerico();
	}

	@Override
	public FormaPagamento convertToEntityAttribute(Integer dbData) {
		return FormaPagamento.valorToEnum(dbData);
	}

}
