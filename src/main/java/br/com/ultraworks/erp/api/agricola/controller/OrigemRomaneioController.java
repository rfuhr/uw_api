package br.com.ultraworks.erp.api.agricola.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.origemromaneio.OrigemRomaneio;

@RestController
@RequestMapping("/agricola/origem-romaneio")
public class OrigemRomaneioController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(OrigemRomaneio.valuesResponse());
	}

}
