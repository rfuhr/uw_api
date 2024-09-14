package br.com.ultraworks.erp.api.agricola.domain.situacaopesagem;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SituacaoPesagemConverter implements AttributeConverter<SituacaoPesagem, String> {

	@Override
	public String convertToDatabaseColumn(SituacaoPesagem situacao) {
		if (situacao == null) {
			return null;
		}
		return situacao.getValue();
	}

	@Override
	public SituacaoPesagem convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}
		return SituacaoPesagem.fromValue(value);
	}
}
