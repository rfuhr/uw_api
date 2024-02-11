package br.com.ultraworks.erp.api.financeiro.integrator;

public enum TipoOperacaoIntegracaoFinanceira {
    INCLUSAOPORLANCAMENTO,
    BAIXA,
    ESTORNO;
	
	public boolean isInclusaoLancamento() {
        return this == INCLUSAOPORLANCAMENTO;
    }
	
	public boolean isBaixa() {
        return this == BAIXA;
    }
	
	public boolean isEstorno() {
        return this == ESTORNO;
    }
}
