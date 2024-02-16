package br.com.ultraworks.erp.api.estoque.domain.marca;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MarcaDTO {

	private Long id;

	@NotNull
	@NotEmpty
	@Size(max = 120)
	@FriendlyName("Nome")
	private String nome;

}
