package br.com.ultraworks.erp.api.financeiro.domain.titulo.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LancamentoTituloRequest {

	@NotNull
	private LocalDate dataMovimento;
	@NotNull
	private Long empresaFilialId;	
	@NotNull
	private Long departamentoId;
	@NotNull
	private Long tipoTituloId;
	@NotNull
	private Long caracteristicaMovimentoFinanceiroId;
	@NotNull
	private Long grupoFinanceiroId;
	@NotNull
	private Long fatoGeradorId;
	@NotNull
	private Long carteiraFinanceiraId;
	@NotNull
	private Long parceiroLocalId;
	@NotNull
	private Long historicoPadraoId;
	private String documento;
	private LocalDate dataDocumento;
	private String complemento;
	@NotNull
	private BigDecimal valorTitulo;
	@NotNull
	private BigDecimal valorLiquido;
	private String observacao;
	private Long contaId;
	
	private List<LancamentoParcelaRequest> parcelas;
	
}
