package br.com.ultraworks.erp.api.tabela.domain.unidademedida;

import br.com.ultraworks.erp.api.tabela.domain.grandezaMedida.validator.ValidaGrandezaMedida;
import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UnidadeMedidaDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(max = 250)
	@FriendlyName("Nome")
	private String nome;
	@NotNull
	@NotEmpty
	@Size(max = 20)
	@FriendlyName("Sigla")
	private String sigla;

	@ValidaGrandezaMedida
	private String grandeza;
	
	private String grandezaNome;
}
