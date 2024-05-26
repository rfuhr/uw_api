package br.com.ultraworks.erp.api.comercial.domain.indicemarkup;

import br.com.ultraworks.erp.api.fiscal.domain.tipotributo.validador.ValidaTipoTributo;
import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class IndiceMarkupDTO {

	private Long id;
	@NotNull
	@NotEmpty
	@Size(max = 80)
	@FriendlyName("Nome")
	private String nome;
	private int codigo;
	private boolean tributo;
	
	@ValidaTipoTributo
	private String tipoTributo;
}
