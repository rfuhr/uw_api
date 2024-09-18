package br.com.ultraworks.erp.api.comercial.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.comercial.service.CalcularPrecoBaseService;

@RestController
@RequestMapping("/comercial/calcular-preco-base")
public class CalcularPrecoBaseController {
	
	private CalcularPrecoBaseService calcularPrecoBaseService;
	
	public CalcularPrecoBaseController(CalcularPrecoBaseService calcularPrecoBaseService) {
		this.calcularPrecoBaseService = calcularPrecoBaseService;
	}
	
	@GetMapping("/{tipoPrecoId}/item/{itemId}")
	public ResponseEntity<?> getCalcularPrecoBaseItem(@PathVariable Long tipoPrecoId, @PathVariable Long itemId) {
		if (tipoPrecoId == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Identificador do Tipo de Preço deve ser informado");
		}
		if (itemId == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Identificador do Item deve ser informado");
		}

		return ResponseEntity.ok(calcularPrecoBaseService.calcularPrecoBaseItem(tipoPrecoId, itemId));
	}
}
