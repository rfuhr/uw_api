package br.com.ultraworks.erp.api.tabela.domain.indicadorDC;

public enum IndicadorDC {
    DEBITO("D"),
    CRÉDITO("C");

    private String codigo;

    IndicadorDC(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static IndicadorDC fromCodigo(String codigo) {
        for (IndicadorDC tipo : IndicadorDC.values()) {
            if (tipo.getCodigo().equalsIgnoreCase(codigo)) {
                return tipo;
            }
        }
        return null; // Ou lançar uma exceção se preferir
    }
}

