package br.com.ultraworks.erp.api.agricola.domain.validaitemclassificacaoagricola;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ValidaItemClassificacaoAgricolaDTO {

	private Long id;

	@NotNull
	private Long itemId;
	@NotNull
	private Long itemClassificacaoAgricolaId;
	@NotNull
	private String tipoUsoRomaneio;
	private boolean obrigatorio;
	private int ordemTela;
	private String itemNome;
	private String itemClassificacaoAgricolaNome;
	
	@NotNull
	private LocalDate dataInicioVigencia;
	@NotNull
	private LocalDate dataFinalVigencia;
}
