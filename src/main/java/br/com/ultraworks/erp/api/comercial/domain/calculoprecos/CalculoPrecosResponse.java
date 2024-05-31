package br.com.ultraworks.erp.api.comercial.domain.calculoprecos;

import java.util.List;

import lombok.Data;

@Data
public class CalculoPrecosResponse {
	
	private Long tipoPrecoId;
	private List<Precos> itens;

}
