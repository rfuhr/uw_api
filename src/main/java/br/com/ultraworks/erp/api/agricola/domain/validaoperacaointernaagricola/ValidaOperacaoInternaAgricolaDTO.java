package br.com.ultraworks.erp.api.agricola.domain.validaoperacaointernaagricola;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ValidaOperacaoInternaAgricolaDTO {

	private Long id;

	@NotNull
	private Long itemId;
	@NotNull
	private Long operacaoInternaId;
	@NotNull
	private Long grupoOperacaoAgricolaId;
	@NotNull
	private Long tipoPrecoAgricolaId;
	private Long fatoGeradorContratoId;

	private String itemNome;
	private String operacaoInternaNome;
	private String grupoOperacaoAgricolaNome;
	private String tipoPrecoAgricolaNome;
	private String fatoGeradorContratoNome;
	
	@NotNull
	private LocalDate dataInicioVigencia;
	@NotNull
	private LocalDate dataFinalVigencia;
}
