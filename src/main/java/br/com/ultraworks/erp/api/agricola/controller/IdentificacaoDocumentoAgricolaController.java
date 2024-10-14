package br.com.ultraworks.erp.api.agricola.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.identificacaodocumentoagricola.IdentificacaoDocumentoAgricola;

@RestController
@RequestMapping("/agricola/identificacao-documento")
public class IdentificacaoDocumentoAgricolaController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(IdentificacaoDocumentoAgricola.valuesResponse());
	}

}
