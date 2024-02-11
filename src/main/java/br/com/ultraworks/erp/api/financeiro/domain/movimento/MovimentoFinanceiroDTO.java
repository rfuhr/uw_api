package br.com.ultraworks.erp.api.financeiro.domain.movimento;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

@Data
public class MovimentoFinanceiroDTO {

	private Long id;
	private Long parcelaId;
	private Long departamentoId;
	private int seqMvto;
	private int subSeqMvto;
	private Long operacaoFinanceiraId;
	private Long grupoFinanceiroId;
	private Long carteiraFinanceiraId;
	private BigDecimal valorMovimento;
	private Date dataMovimento;
	private BigDecimal saldoParcela;
	private Date dataLancamento;
	private String observacao;
}
