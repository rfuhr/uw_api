package br.com.ultraworks.erp.api.tabela.domain.tipopessoa;

public enum TipoPessoa {
    PESSOA_FISICA("F"),
    PESSOA_JURIDICA("J"),
    EXTERIOR("EX");

    private String codigo;

    TipoPessoa(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static TipoPessoa fromCodigo(String codigo) {
        for (TipoPessoa tipo : TipoPessoa.values()) {
            if (tipo.getCodigo().equalsIgnoreCase(codigo)) {
                return tipo;
            }
        }
        return null; // Ou lançar uma exceção se preferir
    }
}

