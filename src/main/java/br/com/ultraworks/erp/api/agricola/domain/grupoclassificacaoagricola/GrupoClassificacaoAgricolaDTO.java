package br.com.ultraworks.erp.api.agricola.domain.grupoclassificacaoagricola;

import java.time.LocalDate;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GrupoClassificacaoAgricolaDTO {

	private Long id;
	@NotNull
	private Long codigo;
	
	@NotNull
	@NotEmpty
	@Size(max = 80)
	@FriendlyName("Nome")
	private String nome;
	
	@NotNull
	private LocalDate dataInicioVigencia;
	@NotNull
	private LocalDate dataFinalVigencia;
}
