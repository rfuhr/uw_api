package br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ModalidadeBaseCalculoConverter implements AttributeConverter<ModalidadeBaseCalculo, String> {

    @Override
    public String convertToDatabaseColumn(ModalidadeBaseCalculo ModalidadeBaseCalculo) {
        if (ModalidadeBaseCalculo == null) {
            return null;
        }
        return ModalidadeBaseCalculo.getValue();
    }

    @Override
    public ModalidadeBaseCalculo convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return ModalidadeBaseCalculo.fromValue(value);
    }
}
