package br.com.ultraworks.erp.api.tabela.domain.pais;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PaisDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(max = 250)
	@FriendlyName("Nome")
	private String nome;
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 3)
	@FriendlyName("Sigla")
	private String sigla;
	@NotNull
	@NotEmpty
	@Size(min = 4, max = 4)
	@FriendlyName("Cód. Ibge")
	private String codigoIBGE;
	@NotEmpty
	@Size(min = 3, max = 3)
	@FriendlyName("Cód. Siscomex")
	private String codigoSiscomex;

}
