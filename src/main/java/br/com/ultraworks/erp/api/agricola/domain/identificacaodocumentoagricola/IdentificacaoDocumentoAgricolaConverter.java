package br.com.ultraworks.erp.api.agricola.domain.identificacaodocumentoagricola;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class IdentificacaoDocumentoAgricolaConverter
		implements AttributeConverter<IdentificacaoDocumentoAgricola, String> {

	@Override
	public String convertToDatabaseColumn(IdentificacaoDocumentoAgricola situacao) {
		if (situacao == null) {
			return null;
		}
		return situacao.getValue();
	}

	@Override
	public IdentificacaoDocumentoAgricola convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}
		return IdentificacaoDocumentoAgricola.fromValue(value);
	}
}
