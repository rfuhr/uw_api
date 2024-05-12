package br.com.ultraworks.erp.api.estoque.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.tipodocumentoestoque.TipoDocumentoEstoque;

@RestController
@RequestMapping("/fiscal/tipo-documento-estoque")
public class TipoDocumentoEstoqueController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(TipoDocumentoEstoque.valuesResponse());
	}

}