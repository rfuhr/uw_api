package br.com.ultraworks.erp.api.tabela.domain.tipotelefone;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

//@Converter(autoApply = true)
public class TipoTelefoneConverter implements AttributeConverter<TipoTelefone, String> {

	@Override
	public String convertToDatabaseColumn(TipoTelefone tipoTelefone) {
		if (tipoTelefone == null) {
			return null;
		}
		return tipoTelefone.getCodigo();
	}

	@Override
	public TipoTelefone convertToEntityAttribute(String codigo) {
		if (codigo == null) {
			return null;
		}
		return TipoTelefone.fromCodigo(codigo);
	}
}
