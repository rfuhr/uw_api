package br.com.ultraworks.erp.api.financeiro.domain.negociacao;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NegociacaoFinanceiraDTO {

	private Long id;
	private Long departamentoId;
	private String departamentoSigla;
	private String departamentoNome;

	private Long parceiroLocalId;
	private String parceiroLocalNome;
	private Long parceiroId;
	private String parceiroNomeRazaoSocial;

	private BigDecimal valorTotalAtraso;
	private BigDecimal valorJurosNegociado;
	private BigDecimal valorDescontoNegociado;
	private BigDecimal valorNegociadoPagar;
	private LocalDate dataNegociacao;
	private Long nossoNumero;
	private String observacao;
}
