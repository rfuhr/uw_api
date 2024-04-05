package br.com.ultraworks.erp.api.tabela.domain.tipoendereco;

public enum TipoEndereco {
    COMERCIAL("COM", "Comercial"),
    ENTREGA("ENT", "Entrada"),
    FISCAL("FIS", "Físcal"),
    RESIDENCIAL("RES", "Residencial");

    private String value;
    private String name;

    TipoEndereco(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
    	return name;
    }

    public static TipoEndereco fromValue(String value) {
        for (TipoEndereco tipo : TipoEndereco.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null; // Ou lançar uma exceção se preferir
    }
}

