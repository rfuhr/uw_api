package br.com.ultraworks.erp.api.fiscal.domain.tipotributo;

public enum TipoTributo {
    ICMS("ICMS"),
    PIS("PIS"),
    COFINS("COFINS"),
    IPI("IPI");

    private String codigo;

    TipoTributo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static TipoTributo fromCodigo(String codigo) {
        for (TipoTributo tipo : TipoTributo.values()) {
            if (tipo.getCodigo().equalsIgnoreCase(codigo)) {
                return tipo;
            }
        }
        return null; // Ou lançar uma exceção se preferir
    }
}
