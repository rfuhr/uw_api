package br.com.ultraworks.erp.api.financeiro.domain.titulo.request;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SelecionadosNegociacaoRequest {

	private Long tituloId;
	private Long parcelaFinanceiroId;
	private Long movimentoFinanceiroId;
	private BigDecimal valorBaixa;
}
