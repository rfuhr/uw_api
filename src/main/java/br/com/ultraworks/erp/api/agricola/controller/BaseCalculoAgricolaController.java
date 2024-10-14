package br.com.ultraworks.erp.api.agricola.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.basecalculoagricola.BaseCalculoAgricola;

@RestController
@RequestMapping("/agricola/base-calculo-agricola")
public class BaseCalculoAgricolaController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(BaseCalculoAgricola.valuesResponse());
	}

}
