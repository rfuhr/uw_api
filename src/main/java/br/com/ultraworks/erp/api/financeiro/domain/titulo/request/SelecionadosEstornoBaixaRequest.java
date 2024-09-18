package br.com.ultraworks.erp.api.financeiro.domain.titulo.request;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SelecionadosEstornoBaixaRequest {

	private Long tituloId;
	private Long parcelaFinanceiraId;
	private Long movimentoFinanceiroId;
	private BigDecimal valorMovimento;
}
