package br.com.ultraworks.erp.api.estoque.domain.linha;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LinhaDTO {

	private Long id;

	@NotNull
	@NotEmpty
	@Size(max = 120)
	@FriendlyName("Nome")
	private String nome;

}
