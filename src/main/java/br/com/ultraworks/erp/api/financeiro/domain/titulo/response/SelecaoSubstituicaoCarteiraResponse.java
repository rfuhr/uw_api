package br.com.ultraworks.erp.api.financeiro.domain.titulo.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelecaoSubstituicaoCarteiraResponse {

	private Long tituloId;
	private Long parcelaFinanceiraId;
	private Long movimentoFinanceiroId;
	private Long nossoNumero;
	private Long departamentoId;
	private String departamentoSigla;
	private String departamentoNome;
	private String parceiroNome;
	private String parceiroLocalCpfCnpj;
	private String parceiroLocalNome;
	private String carteiraFinanceiraSigla;
	private String carteiraFinanceiraNome;
	private String documento;
	private int numeroParcela;
	private LocalDate dataVencimento;
	private BigDecimal saldoParcela;
	private Long caracteristicaMovimentoId;
	private String caracteristicaMovimentoSigla;
	private String caracteristicaMovimentoNome;
	private String empresaFilialSigla;
	private String empresaFilialNome;

}
