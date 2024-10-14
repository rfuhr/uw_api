package br.com.ultraworks.erp.api.agricola.domain.safra;

import java.time.LocalDate;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SafraDTO {

	private Long id;
	@NotNull
	private Long itemId;
	@NotNull
	private String codigo;
	@NotNull
	@NotEmpty
	@Size(max = 80)
	@FriendlyName("Nome")
	private String nome;

	@NotNull
	private LocalDate dataInicioVigencia;
	@NotNull
	private LocalDate dataFinalVigencia;
	
	private String itemNome;
}
