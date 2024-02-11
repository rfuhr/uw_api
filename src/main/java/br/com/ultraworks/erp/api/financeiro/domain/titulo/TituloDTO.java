package br.com.ultraworks.erp.api.financeiro.domain.titulo;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TituloDTO {

	private Long id;
	@NotNull
	private Long departamentoId;
	@NotNull
	private Long parceiroLocalId;
	@NotNull
	private Long tipoTituloId;
	@NotNull
	private Long grupoFinanceiroId;
	@NotNull
	private Long fatoGeradorId;
	@NotNull
	private Long caracteristicaMovimentoFinanceiroId;
	@NotNull
	private Long historicoPadraoId;
	private String documento;
	private String dataDocumento;
	private String observacao;
	@NotNull
	private BigDecimal valorTotal;
	private String historico;
}
