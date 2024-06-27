package br.com.ultraworks.erp.api.financeiro.domain.tipocontacxbco;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoContaCxBcoDTO {

	private Long id;
	private int codigo;
	@NotNull
	@NotEmpty
	@Size(max = 100)
	@FriendlyName("Nome")
	private String nome;
}
