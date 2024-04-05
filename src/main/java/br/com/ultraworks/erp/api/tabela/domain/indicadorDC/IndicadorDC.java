package br.com.ultraworks.erp.api.tabela.domain.indicadorDC;

public enum IndicadorDC {
    DEBITO("D", "Débito"),
    CRÉDITO("C", "Crédito");

    private String value;
    private String name;

    IndicadorDC(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
    	return name;
    }

    public static IndicadorDC fromValue(String value) {
        for (IndicadorDC tipo : IndicadorDC.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null; 
    }
}

