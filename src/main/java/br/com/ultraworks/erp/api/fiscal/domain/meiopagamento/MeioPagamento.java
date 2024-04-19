package br.com.ultraworks.erp.api.fiscal.domain.meiopagamento;

import java.util.ArrayList;
import java.util.List;

import br.com.ultraworks.erp.core.dto.EnumResponse;

public enum MeioPagamento {
    DINHEIRO("01", "Dinheiro"),
    CHEQUE("02", "Cheque"),
    CARTAOCREDITO("03", "Cartão de Crédito"),
    CARTAODEBITO("04", "Cartão de Débito"),
    CREDITOLOJA("5", "Crédito Loja"),
    VALEALIMENTACAO("10", "Vale Alimentação"),
    VALEREFEICAO("11", "Vale Refeição"),
    VALEPRESENTE("12", "Vale Presente"),
    VALECOMBUSTIVEL("13", "Vale Combustível"),
    BOLETOBANCARIO("15", "Boleto Bancário"),
    DEPOSITOBANCARIO("16", "Depósito Bancário"),
    PIX("17", "Pix"),
    TRANSFBANCARIA_CARTDIGITAL("18", "Transferência Bancário, Carteira Digital"),
    FIDELIDADE_CASHBACK_CREDVIRTUAL("19", "Programa de Fidelidade, Cashback, Crédito Virtual"),
    SEMPAGAMENTO("90", "Sem Pagamento"),
    OUTROS("99", "Outros");
    
    private String value;
    private String name;

    MeioPagamento(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
        return name;
    }
    

    public static MeioPagamento fromValue(String value) {
        for (MeioPagamento tipo : MeioPagamento.values()) {
            if (tipo.getValue().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        return null;
    }
    
	public static List<EnumResponse> valuesResponse() {
		List<EnumResponse> list = new ArrayList<>();

		for (int i = 0; i < MeioPagamento.values().length; i++) {
			MeioPagamento meioPagamento = MeioPagamento.values()[i];

			list.add(EnumResponse.builder().name(meioPagamento.getName()).value(meioPagamento.getValue()).build());
		}

		return list;
	}
}
