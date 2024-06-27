package br.com.ultraworks.erp.api.comercial.domain.tipopreco;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoPrecoDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(max = 80)
	@FriendlyName("Nome")
	private String nome;
	private int codigo;
	private boolean transferencia;
	private boolean promocional;
}
