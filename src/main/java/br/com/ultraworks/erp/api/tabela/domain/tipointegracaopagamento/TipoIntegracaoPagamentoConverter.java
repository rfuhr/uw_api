package br.com.ultraworks.erp.api.tabela.domain.tipointegracaopagamento;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

//@Converter(autoApply = true)
public class TipoIntegracaoPagamentoConverter implements AttributeConverter<TipoIntegracaoPagamento, String> {

	@Override
	public String convertToDatabaseColumn(TipoIntegracaoPagamento tipoTelefone) {
		if (tipoTelefone == null) {
			return null;
		}
		return tipoTelefone.getValue();
	}

	@Override
	public TipoIntegracaoPagamento convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}
		return TipoIntegracaoPagamento.fromValue(value);
	}
}
