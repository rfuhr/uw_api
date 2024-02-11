package br.com.ultraworks.erp.api.tabela.domain.uf;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UfDTO {

	private Long id;
	private Long codigo;
	@NotNull
	@NotEmpty
	@Size(max = 250)
	private String nome;
	@NotNull
	@NotEmpty
	@Size(min = 2, max = 2)
	private String sigla;
}
