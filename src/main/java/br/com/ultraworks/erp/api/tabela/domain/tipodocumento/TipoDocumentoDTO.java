package br.com.ultraworks.erp.api.tabela.domain.tipodocumento;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoDocumentoDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(max = 250)
	@FriendlyName("Nome")
	private String nome;
	@NotNull
	@NotEmpty
	@Size(max = 10)
	@FriendlyName("Sigla")
	private String sigla;

	@Size(max = 2)
	@FriendlyName("Código Receita")
	private String codigoReceita;
}
