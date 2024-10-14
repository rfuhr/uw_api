package br.com.ultraworks.erp.api.agricola.domain.taxacalculoagricola;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.ultraworks.erp.api.agricola.domain.tipotaxaagricola.validator.ValidaTipoTaxaAgricola;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaxaCalculoAgricolaDTO {

	private Long id;

	@NotNull
	private Long itemId;
	@NotNull
	private Long tipoCalculoAgricolaId;
	@NotNull
	private Long regraAtividadeId;

	@NotNull
	private BigDecimal faixaLimite;
	@NotNull
	@ValidaTipoTaxaAgricola
	private String tipoTaxaAgricola;
	private BigDecimal fatorCalculo;
	private String itemNome;
	private String tipoCalculoAgricolaNome;
	private String operacaoInternaNome;
	private String grupoOperacaoAgricolaNome;
	private String regraAtividadeNome;
	private String tipoTaxaAgricolaNome;

	@NotNull
	private LocalDate dataInicioVigencia;
	@NotNull
	private LocalDate dataFinalVigencia;
}
