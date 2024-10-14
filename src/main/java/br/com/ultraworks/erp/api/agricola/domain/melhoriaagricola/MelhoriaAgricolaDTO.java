package br.com.ultraworks.erp.api.agricola.domain.melhoriaagricola;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MelhoriaAgricolaDTO {

	private Long id;

	@NotNull
	private Long itemId;
	@NotNull
	private Long itemClassificacaoAgricolaPrincipalId;
	@NotNull
	private Long subItemClassificacaoAgricolaPrincipalId;
	@NotNull
	private Long itemClassificacaoAgricolaSecundarioId;
	@NotNull
	private Long subItemClassificacaoAgricolaSecundarioId;

	private BigDecimal valorAdicionaPrincipal;
	private BigDecimal valorAdicionaSecundario;

	@NotNull
	private Long itemClassificacaoAgricolaGeradoId;
	@NotNull
	private Long subItemClassificacaoAgricolaGeradoId;

	private String itemNome;
	private String itemClassificacaoAgricolaPrincipalNome;
	private String subItemClassificacaoAgricolaPrincipalNome;
	private String itemClassificacaoAgricolaSecundarioNome;
	private String subItemClassificacaoAgricolaSecundarioNome;
	private String itemClassificacaoAgricolaGeradoNome;
	private String subItemClassificacaoAgricolaGeradoNome;

	@NotNull
	private LocalDate dataInicioVigencia;
	@NotNull
	private LocalDate dataFinalVigencia;
}
