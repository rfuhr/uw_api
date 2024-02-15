package br.com.ultraworks.erp.api.fiscal.domain.entradasaida;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EntradaSaidaConverter implements AttributeConverter<EntradaSaida, String> {

    @Override
    public String convertToDatabaseColumn(EntradaSaida ModalidadeBaseCalculo) {
        if (ModalidadeBaseCalculo == null) {
            return null;
        }
        return ModalidadeBaseCalculo.getCodigo();
    }

    @Override
    public EntradaSaida convertToEntityAttribute(String codigo) {
        if (codigo == null) {
            return null;
        }
        return EntradaSaida.fromCodigo(codigo);
    }
}
