package br.com.ultraworks.erp.api.financeiro.domain.tipocondicaopagamento;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.api.agricola.domain.situacaoromaneio.SituacaoRomaneio;
import br.com.ultraworks.erp.core.dto.EnumResponse;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoCondicaoPagamentoConverter implements AttributeConverter<TipoCondicaoPagamento, String> {

    @Override
    public String convertToDatabaseColumn(TipoCondicaoPagamento tipo) {
        if (tipo == null) {
            return null;
        }
        return tipo.getValue();
    }

    @Override
    public TipoCondicaoPagamento convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return TipoCondicaoPagamento.fromValue(value);
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < TipoCondicaoPagamento.values().length; i++) {
			TipoCondicaoPagamento tipo = TipoCondicaoPagamento.values()[i];

			list.add(EnumResponse.builder().name(tipo.getName()).value(tipo.getValue()).build());
		}

		return list;
	}
}
