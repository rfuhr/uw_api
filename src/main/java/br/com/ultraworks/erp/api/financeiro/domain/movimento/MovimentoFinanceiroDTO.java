package br.com.ultraworks.erp.api.financeiro.domain.movimento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MovimentoFinanceiroDTO {

	private Long id;
	private Long parcelaFinanceiroId;
	private Long departamentoId;
	private String departamentoSigla;
	private String departamentoNome;
	private int seqMvto;
	private int subSeqMvto;
	private Long tipoOperacaoFinanceiraId;
	private String tipoOperacaoFinanceiraNome;
	private String tipoOperacaoFinanceiraSigla;
	private Long operacaoMovimentoFinanceiroId;
	private String operacaoMovimentoFinanceiroNome;
	private Long operacaoAcessoriaFinanceiraId;
	private String operacaoAcessoriaFinanceiraNome;
	private Long grupoFinanceiroId;
	private String grupoFinanceiroSigla;
	private String grupoFinanceiroNome;
	private Long carteiraFinanceiraId;
	private String carteiraFinanceiraSigla;
	private String carteiraFinanceiraNome;
	private BigDecimal valorMovimento;
	private LocalDate dataMovimento;
	private BigDecimal saldoParcela;
	private LocalDateTime dataLancamento;
	private String observacao;
	private String responsavel;
	private Long contaId;
	private Long negociacaoFinanceiraId;
}
