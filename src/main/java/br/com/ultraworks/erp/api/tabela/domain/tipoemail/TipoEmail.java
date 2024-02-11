package br.com.ultraworks.erp.api.tabela.domain.tipoemail;

public enum TipoEmail {
    NOTA_FISCAL_ELETRONICA("NFE"),
    DIVERSOS("DIV");

    private String codigo;

    TipoEmail(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static TipoEmail fromCodigo(String codigo) {
        for (TipoEmail tipo : TipoEmail.values()) {
            if (tipo.getCodigo().equalsIgnoreCase(codigo)) {
                return tipo;
            }
        }
        return null; // Ou lançar uma exceção se preferir
    }
}

