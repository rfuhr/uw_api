package br.com.ultraworks.erp.api.tabela.domain.tipoobrigatoriedade;

public enum TipoObrigatoriedade {
    OBRIGATORIO("S"),
    OPCIONAL("O"),
    NAO_INFORMA("N");

    private String codigo;

    TipoObrigatoriedade(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static TipoObrigatoriedade fromCodigo(String codigo) {
        for (TipoObrigatoriedade tipo : TipoObrigatoriedade.values()) {
            if (tipo.getCodigo().equalsIgnoreCase(codigo)) {
                return tipo;
            }
        }
        return null; // Ou lançar uma exceção se preferir
    }
}

