package br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerIntegracaoNFeAutorizacao {

	private String cnpj;
	private String cpf;
}
