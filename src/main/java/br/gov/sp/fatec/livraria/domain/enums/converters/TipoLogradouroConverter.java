package br.gov.sp.fatec.livraria.domain.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.gov.sp.fatec.livraria.domain.enums.TipoLogradouro;

@Converter
public class TipoLogradouroConverter implements AttributeConverter<TipoLogradouro, Integer> {

	@Override
	public Integer convertToDatabaseColumn(TipoLogradouro attribute) {
		return attribute.getValorNumerico();
	}

	@Override
	public TipoLogradouro convertToEntityAttribute(Integer dbData) {
		return TipoLogradouro.valorToEnum(dbData);
	}

}
