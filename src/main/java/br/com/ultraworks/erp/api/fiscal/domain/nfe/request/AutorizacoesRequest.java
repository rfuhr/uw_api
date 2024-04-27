package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutorizacoesRequest {

	private String tipoPessoa;
	private String cnpj;
	private String cpf;
	
}
