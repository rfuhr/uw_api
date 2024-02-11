package br.com.ultraworks.erp.api.tabela.domain.tipoendereco;

public enum TipoEndereco {
    COMERCIAL("COM"),
    ENTREGA("ENT"),
    FISCAL("FIS"),
    RESIDENCIAL("RES");

    private String codigo;

    TipoEndereco(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static TipoEndereco fromCodigo(String codigo) {
        for (TipoEndereco tipo : TipoEndereco.values()) {
            if (tipo.getCodigo().equalsIgnoreCase(codigo)) {
                return tipo;
            }
        }
        return null; // Ou lançar uma exceção se preferir
    }
}

