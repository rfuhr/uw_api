package br.com.ultraworks.erp.api.financeiro.domain.titulo.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelecaoEstornoResponse {

	private Long tituloId;
	private Long parceiroLocalId;
	private String documento;
	private Long nossoNumero;
	private Long parcelaFinanceiraId;
	private int numeroParcela;
	private LocalDate dataVencimento;
	private Long movimentoFinanceiroId;
	private Long operacaoFinanceiraId;
	private LocalDate dataMovimento;
	private int sequenciaMovimento;
	private BigDecimal valorMovimento;
	private Long tipoOperacaoFinanceiraId;
	private String tipoOperacaoFinanceiraNome;
	private Long operacaoMovimentoFinanceiroId;
	private Long operacaoAcessoriaFinanceiraId;
	private String departamentoSigla;
	private String parceiroLocalCpfCnpj;
}
