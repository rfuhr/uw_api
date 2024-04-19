package br.com.ultraworks.erp.api.fiscal.domain.modalidadefrete;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ModalidadeFreteConverter implements AttributeConverter<ModalidadeFrete, String> {

    @Override
    public String convertToDatabaseColumn(ModalidadeFrete modalidadeFreteCalculo) {
        if (modalidadeFreteCalculo == null) {
            return null;
        }
        return modalidadeFreteCalculo.getValue();
    }

    @Override
    public ModalidadeFrete convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return ModalidadeFrete.fromValue(value);
    }
}
