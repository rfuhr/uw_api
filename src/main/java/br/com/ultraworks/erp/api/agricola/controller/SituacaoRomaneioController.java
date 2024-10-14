package br.com.ultraworks.erp.api.agricola.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.situacaoromaneio.SituacaoRomaneio;

@RestController
@RequestMapping("/agricola/situacao-romaneio")
public class SituacaoRomaneioController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(SituacaoRomaneio.valuesResponse());
	}

}
