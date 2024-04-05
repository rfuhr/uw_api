package br.com.ultraworks.erp.api.tabela.domain.tipopessoa;

public enum TipoPessoa {
    PESSOA_FISICA("F", "Pessoa Física"),
    PESSOA_JURIDICA("J", "Pessoa Jurídica"),
    EXTERIOR("EX", "Pessoa Exterior");

    private String value;
    private String name;

    TipoPessoa(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
    	return name;
    }

    public static TipoPessoa fromValue(String value) {
        for (TipoPessoa tipo : TipoPessoa.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null; // Ou lançar uma exceção se preferir
    }
}

