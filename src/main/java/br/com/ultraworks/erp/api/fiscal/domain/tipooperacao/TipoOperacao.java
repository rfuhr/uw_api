package br.com.ultraworks.erp.api.fiscal.domain.tipooperacao;

public enum TipoOperacao {
    ESTADUAL("Estadual"),
    INTERESTADUAL("Interestadual"),
    EXTERIOR("Exterior");

    private String codigo;

    TipoOperacao(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static TipoOperacao fromCodigo(String codigo) {
        for (TipoOperacao tipo : TipoOperacao.values()) {
            if (tipo.getCodigo().equalsIgnoreCase(codigo)) {
                return tipo;
            }
        }
        return null; // Ou lançar uma exceção se preferir
    }
}
