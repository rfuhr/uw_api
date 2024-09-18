package br.com.ultraworks.erp.api.financeiro.integrator;

public enum TipoOperacaoIntegracaoFinanceira {
    INCLUSAOPORLANCAMENTO,
    BAIXA,
    ESTORNO,
    SUBSTITUICAOCARTEIRA,
    NEGOCIACAO;
	
	public boolean isInclusaoLancamento() {
        return this == INCLUSAOPORLANCAMENTO;
    }
	
	public boolean isBaixa() {
        return this == BAIXA;
    }
	
	public boolean isEstorno() {
        return this == ESTORNO;
    }
	
	public boolean isSubstituicaoCarteira() {
		return this == SUBSTITUICAOCARTEIRA;
	}
	
	public boolean isNegociacaoFinanceira() {
		return this == NEGOCIACAO;
	}
}
