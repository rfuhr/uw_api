package br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculost;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ModalidadeBaseCalculoStConverter implements AttributeConverter<ModalidadeBaseCalculoSt, String> {

    @Override
    public String convertToDatabaseColumn(ModalidadeBaseCalculoSt ModalidadeBaseCalculo) {
        if (ModalidadeBaseCalculo == null) {
            return null;
        }
        return ModalidadeBaseCalculo.getValue();
    }

    @Override
    public ModalidadeBaseCalculoSt convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return ModalidadeBaseCalculoSt.fromValue(value);
    }
}
