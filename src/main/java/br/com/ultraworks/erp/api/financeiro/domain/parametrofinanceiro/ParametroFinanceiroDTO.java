package br.com.ultraworks.erp.api.financeiro.domain.parametrofinanceiro;

import lombok.Data;

@Data
public class ParametroFinanceiroDTO {

	private Long id;
	private Long empresaId;
	private Long tipoTituloId;
	private Long caracteristicaMovimentoFinanceiroId;
	private Long carteiraFinanceiraId;
	private Long fatoGeradorId;
	private String indicadorDC;
	private Long operacaoFinanceiraId;
}
