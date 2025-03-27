package br.com.ultraworks.erp.api.fiscal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.tiponfe.TipoNfe;

@RestController
@RequestMapping("/fiscal/tipo-nfe")
public class TipoNfeController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(TipoNfe.valuesResponse());
	}

}