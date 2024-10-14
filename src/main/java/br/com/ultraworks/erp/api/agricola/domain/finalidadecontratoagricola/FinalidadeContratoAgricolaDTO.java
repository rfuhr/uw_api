package br.com.ultraworks.erp.api.agricola.domain.finalidadecontratoagricola;

import java.time.LocalDate;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FinalidadeContratoAgricolaDTO {

	private Long id;
	@NotNull
	private int codigo;
	@NotNull
	@NotEmpty
	@Size(max = 250)
	@FriendlyName("Nome")
	private String nome;
	@NotEmpty
	private String sigla;
	@NotNull
	private LocalDate dataInicioVigencia;
	@NotNull
	private LocalDate dataFinalVigencia;
}
