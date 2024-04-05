package br.com.ultraworks.erp.api.configuracao.domain.configempresa;

import java.util.List;

import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFeDTO;
import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConfigEmpresaDTO {

	private Long id;

	@NotNull
	@FriendlyName("Empresa")
	private Long empresaId;
	private String empresaNome;
	private Long regimeTributarioId;
	
	@Valid
	private List<ConfigEmpresaNFeDTO> configuracoesNFe;
}
