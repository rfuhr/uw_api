package br.com.ultraworks.erp.api.agricola.domain.validaprecoagricolaitem;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ValidaPrecoAgricolaItemDTO {

	private Long id;

	@NotNull
	private Long itemId;
	@NotNull
	private Long tipoPrecoAgricolaId;
	@NotNull
	private Long predefinicaoPrecoAgricolaId;
	
	
	private String itemNome;
	private String tipoPrecoAgricolaNome;
	private String predefinicaoPrecoAgricolaNome;
	
	@NotNull
	private LocalDate dataInicioVigencia;
	@NotNull
	private LocalDate dataFinalVigencia;
}
