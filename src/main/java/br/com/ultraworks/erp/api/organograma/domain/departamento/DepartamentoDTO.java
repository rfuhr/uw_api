package br.com.ultraworks.erp.api.organograma.domain.departamento;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartamentoDTO {

	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 250)
	private String nome;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 30)
	private String sigla;
	
	private boolean general;

	private Long empresaFilialId;
	private String empresaFilialNome;
	
	private Long empresaId;
	private String empresaNome;
	
	private Long parceiroLocalFilialId;
}
