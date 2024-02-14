package br.com.ultraworks.erp.api.fiscal.domain.entradasaida;

public enum EntradaSaida {
    ENTRADA("Entrada"),
    SAIDA("Saída)");

    private String codigo;

    EntradaSaida(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static EntradaSaida fromCodigo(String codigo) {
        for (EntradaSaida tipo : EntradaSaida.values()) {
            if (tipo.getCodigo().equalsIgnoreCase(codigo)) {
                return tipo;
            }
        }
        return null; // Ou lançar uma exceção se preferir
    }
}
