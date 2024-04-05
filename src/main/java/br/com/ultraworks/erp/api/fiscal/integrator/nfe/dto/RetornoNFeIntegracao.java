package br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto;

import lombok.Data;

@Data
public class RetornoNFeIntegracao {
	
	private String xml;
	private String status;
	private String protocolo;
	private String recibo;
	private String erroValidarRetorno;
}
