package br.com.ultraworks.erp.api.tabela.domain.tipoemail;

public enum TipoEmail {
    NOTA_FISCAL_ELETRONICA("NFE", "NFE"),
    DIVERSOS("OUT", "Outros");

    private String codigo;
    private String name;

    TipoEmail(String codigo, String name) {
        this.codigo = codigo;
        this.name = name;
    }

    public String getCodigo() {
        return codigo;
    }
    
    public String getName() {
    	return name;
    }

    public static TipoEmail fromValue(String value) {
        for (TipoEmail tipo : TipoEmail.values()) {
            if (tipo.getCodigo().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null; // Ou lançar uma exceção se preferir
    }
}

