package br.com.ultraworks.erp.api.tabela.domain.tipoobrigatoriedade;

public enum TipoObrigatoriedade {
    OBRIGATORIO("S", "Obrigatório"),
    OPCIONAL("O", "Opcional"),
    NAO_INFORMA("N", "Não informa");

    private String value;
    private String name;

    TipoObrigatoriedade(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
    	return name;
    }
    
    public static TipoObrigatoriedade fromValue(String codigo) {
        for (TipoObrigatoriedade tipo : TipoObrigatoriedade.values()) {
            if (tipo.getValue().equalsIgnoreCase(codigo)) {
                return tipo;
            }
        }
        return null; // Ou lançar uma exceção se preferir
    }
}

