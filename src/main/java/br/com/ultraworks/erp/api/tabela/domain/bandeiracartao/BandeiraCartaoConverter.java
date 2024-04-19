package br.com.ultraworks.erp.api.tabela.domain.bandeiracartao;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

//@Converter(autoApply = true)
public class BandeiraCartaoConverter implements AttributeConverter<BandeiraCartao, String> {

	@Override
	public String convertToDatabaseColumn(BandeiraCartao bandeiraCartao) {
		if (bandeiraCartao == null) {
			return null;
		}
		return bandeiraCartao.getValue();
	}

	@Override
	public BandeiraCartao convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}
		return BandeiraCartao.fromValue(value);
	}
}
