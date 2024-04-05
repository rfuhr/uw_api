package br.com.ultraworks.erp.api.tabela.domain.tipotelefone;

public enum TipoTelefone {
	COMERCIAL("COM", "Comercial"), RECADO("REC", "Recado"), RESIDENCIAL("RES", "Residencial"),
	WHATSAPP("ZAP", "WhatsApp");

	private String value;
	private String name;

	TipoTelefone(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public static TipoTelefone fromValue(String value) {
		for (TipoTelefone tipo : TipoTelefone.values()) {
			if (tipo.getValue().equalsIgnoreCase(value)) {
				return tipo;
			}
		}
		return null; // Ou lançar uma exceção se preferir
	}
}
