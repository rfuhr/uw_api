package br.com.ultraworks.erp.api.configuracao.domain.tipoambiente;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoAmbienteConverter implements AttributeConverter<TipoAmbiente, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoAmbiente tipoCertificado) {
        if (tipoCertificado == null) {
            return null;
        }
        return Integer.parseInt(tipoCertificado.getValue());
    }

    @Override
    public TipoAmbiente convertToEntityAttribute(Integer codigo) {
        if (codigo == null) {
            return null;
        }
        return TipoAmbiente.fromValue(Integer.toString(codigo));
    }
}
