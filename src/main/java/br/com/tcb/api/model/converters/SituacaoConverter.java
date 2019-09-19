package br.com.intelector.api.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.intelector.api.model.enums.SituacaoRegistroEnum;

@Converter(autoApply = true)
public class SituacaoConverter implements AttributeConverter<SituacaoRegistroEnum, String> {

	@Override
	public String convertToDatabaseColumn(SituacaoRegistroEnum attribute) {
		return attribute.getName();
	}

	@Override
	public SituacaoRegistroEnum convertToEntityAttribute(String dbData) {
		return SituacaoRegistroEnum.convertStringToEnum(dbData);
	}

}
