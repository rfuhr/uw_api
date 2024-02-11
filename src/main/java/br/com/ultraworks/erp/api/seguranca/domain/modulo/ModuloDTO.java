package br.com.ultraworks.erp.api.seguranca.domain.modulo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModuloDTO {

	private Long id;	
	@NotNull
	@NotEmpty
	@Size(min = 5, max = 255)
	private String nome;
	@NotNull
	@NotEmpty
	@Size(min = 5, max = 30)
	private String sigla;
	@NotNull
	@NotEmpty
	@Size(min = 5, max = 30)
	private String icone;
	@NotNull
	@NotEmpty
	@Size(min = 5, max = 30)	
	private String pathBase;

}
