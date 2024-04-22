package br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos;

import lombok.Data;

@Data
public class CalculoImpostoResponse {

	private Long itemId;
	
	private Imposto imposto;

}
