package br.com.ultraworks.erp.api.financeiro.domain.titulo.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelecaoBaixaResponse {

	private boolean baixar;
	private boolean parcial;
	private BigDecimal valorBaixa;
	private BigDecimal valorJuros;
	private BigDecimal valorDesconto;
	private BigDecimal valorMovimento;
	private Long carteiraFinanceiraId;
	private String siglaCarteira;
	private Long tituloId;
	private String documento;
	private LocalDate dataVencimento;
	private Long parceiroLocalId;
	private String parceiroLocalCpfCnpj;
	private Long historicoPadraoId;
	private Long caracteristicaMovimentoId;
	private String caracteristicaMovimentoSigla;
	private LocalDate dataDocumento;
	private Long movimentoId;
	private Long tipoTituloId;
	private String tipoTituloSigla;
	private int numeroParcela;
	private Long parcelaFinanceiraId;
	private Long movimentoFinanceiroId;
	private int sequenciaMovimento;
	private Long nossoNumero;
	private Long empresaFilialId;
	private String empresaFilialSigla;
	private Long departamentoId;
	private String departamentoSigla;
}
