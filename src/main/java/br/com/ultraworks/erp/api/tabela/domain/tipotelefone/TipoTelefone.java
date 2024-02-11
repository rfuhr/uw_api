package br.com.ultraworks.erp.api.tabela.domain.tipotelefone;

public enum TipoTelefone {
    COMERCIAL("COM"),
    RECADO("REC"),
    RESIDENCIAL("RES"),
    WHATSAPP("ZAP");

    private String codigo;

    TipoTelefone(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static TipoTelefone fromCodigo(String codigo) {
        for (TipoTelefone tipo : TipoTelefone.values()) {
            if (tipo.getCodigo().equalsIgnoreCase(codigo)) {
                return tipo;
            }
        }
        return null; // Ou lançar uma exceção se preferir
    }
}

