package br.com.ultraworks.erp.api.compras.domain.statussolicitacaomercadoriaitem;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusSolicitacaoMercadoriaItemConverter
		implements AttributeConverter<StatusSolicitacaoMercadoriaItem, String> {

	@Override
	public String convertToDatabaseColumn(StatusSolicitacaoMercadoriaItem status) {
		if (status == null) {
			return null;
		}
		return status.getValue();
	}

	@Override
	public StatusSolicitacaoMercadoriaItem convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}
		return StatusSolicitacaoMercadoriaItem.fromValue(value);
	}
}
