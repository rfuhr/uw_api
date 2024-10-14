package br.com.ultraworks.erp.api.agricola.domain.validacalculoagricola;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ValidaCalculoAgricolaDTO {

	private Long id;

	@NotNull
	private Long itemId;
	@NotNull
	private Long tipoCalculoAgricolaId;
	@NotNull
	private Long operacaoInternaId;
	@NotNull
	private Long grupoOperacaoAgricolaId;
	@NotNull
	private Long regraAtividadeId;
	
	private String itemNome;
	private String tipoCalculoAgricolaNome;
	private String operacaoInternaNome;
	private String grupoOperacaoAgricolaNome;
	private String regraAtividadeNome;
	
	@NotNull
	private LocalDate dataInicioVigencia;
	@NotNull
	private LocalDate dataFinalVigencia;
}
