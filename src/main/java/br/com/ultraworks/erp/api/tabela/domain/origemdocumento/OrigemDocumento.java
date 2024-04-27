package br.com.ultraworks.erp.api.tabela.domain.origemdocumento;

public enum OrigemDocumento {
    EMISSOR_NFE("1", "Emissor de NFe");

    private String value;
    private String name;

    OrigemDocumento(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
    	return name;
    }

    public static OrigemDocumento fromValue(String value) {
        for (OrigemDocumento tipo : OrigemDocumento.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null; 
    }
}

