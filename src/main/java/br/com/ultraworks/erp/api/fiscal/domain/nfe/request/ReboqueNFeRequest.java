package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReboqueNFeRequest {

	private String placaVeiculo;
	private String siglaUf;
	private String rntc;
}
