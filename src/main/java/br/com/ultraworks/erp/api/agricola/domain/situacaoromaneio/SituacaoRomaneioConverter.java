package br.com.ultraworks.erp.api.agricola.domain.situacaoromaneio;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SituacaoRomaneioConverter implements AttributeConverter<SituacaoRomaneio, String> {

	@Override
	public String convertToDatabaseColumn(SituacaoRomaneio situacao) {
		if (situacao == null) {
			return null;
		}
		return situacao.getValue();
	}

	@Override
	public SituacaoRomaneio convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}
		return SituacaoRomaneio.fromValue(value);
	}
}
