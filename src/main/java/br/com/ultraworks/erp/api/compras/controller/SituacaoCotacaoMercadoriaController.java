package br.com.ultraworks.erp.api.compras.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.compras.domain.situacaocotacaomercadoria.SituacaoCotacaoMercadoria;

@RestController
@RequestMapping("/compras/situacao-cotacao-mercadoria")
public class SituacaoCotacaoMercadoriaController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(SituacaoCotacaoMercadoria.valuesResponse());
	}

}