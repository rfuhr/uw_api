package br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo;

public enum ModalidadeBaseCalculo {
    MVA("Margem Valor Agregado"),
    PAUTA("Pauta (Valor)"),
    PRECO("Preço Tabelado"),
    VALOR("Valor da Operação");

    private String codigo;

    ModalidadeBaseCalculo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static ModalidadeBaseCalculo fromCodigo(String codigo) {
        for (ModalidadeBaseCalculo tipo : ModalidadeBaseCalculo.values()) {
            if (tipo.getCodigo().equalsIgnoreCase(codigo)) {
                return tipo;
            }
        }
        return null; // Ou lançar uma exceção se preferir
    }
}
