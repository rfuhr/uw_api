package br.com.ultraworks.erp.api.fiscal.domain.nfe.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VolumeNFeRequest {
	
	private String quantidade;
	private String especie;
	private String marca;
	private String numeracao;
	private String pesoLiquido;
	private String pesoBruto;
}
