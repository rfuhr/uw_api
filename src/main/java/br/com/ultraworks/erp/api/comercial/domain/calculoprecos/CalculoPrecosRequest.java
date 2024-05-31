package br.com.ultraworks.erp.api.comercial.domain.calculoprecos;

import java.util.List;

import lombok.Data;

@Data
public class CalculoPrecosRequest {
	
	private Long tipoPrecoId;
	private List<Long> itens;

}
