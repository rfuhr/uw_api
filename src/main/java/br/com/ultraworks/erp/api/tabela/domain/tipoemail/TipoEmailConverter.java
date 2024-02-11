package br.com.ultraworks.erp.api.tabela.domain.tipoemail;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoEmailConverter implements AttributeConverter<TipoEmail, String> {

    @Override
    public String convertToDatabaseColumn(TipoEmail tipoEmail) {
        if (tipoEmail == null) {
            return null;
        }
        return tipoEmail.getCodigo();
    }

    @Override
    public TipoEmail convertToEntityAttribute(String codigo) {
        if (codigo == null) {
            return null;
        }
        return TipoEmail.fromCodigo(codigo);
    }
}
