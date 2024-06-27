package br.com.ultraworks.erp.api.comercial.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.comercial.domain.calculoprecos.CalculoPrecosRequest;
import br.com.ultraworks.erp.api.comercial.service.CalcularPrecoBaseService;

@RestController
@RequestMapping("/comercial/calcular-preco-base")
public class CalcularPrecoBaseController {
	
	private CalcularPrecoBaseService calcularPrecoBaseService;
	
	public CalcularPrecoBaseController(CalcularPrecoBaseService calcularPrecoBaseService) {
		this.calcularPrecoBaseService = calcularPrecoBaseService;
	}
	
	@GetMapping("/item")
	public ResponseEntity<?> getListaNFe(@RequestBody CalculoPrecosRequest calculoPrecosRequest) {
		if (calculoPrecosRequest.getTipoPrecoId() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Identificador do Tipo de Preço deve ser informado");
		}
		if (calculoPrecosRequest.getItens() == null && calculoPrecosRequest.getItens().size() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Identificador do Item deve ser informado");
		}

		return ResponseEntity.ok(calcularPrecoBaseService.calcularPrecoBaseItem(calculoPrecosRequest.getTipoPrecoId(), calculoPrecosRequest.getItens().get(0)));
	}
}
