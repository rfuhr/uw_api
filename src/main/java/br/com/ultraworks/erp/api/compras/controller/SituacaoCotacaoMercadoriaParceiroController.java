package br.com.ultraworks.erp.api.compras.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.compras.domain.situacaocotacaomercadoriaparceiro.SituacaoCotacaoMercadoriaParceiro;

@RestController
@RequestMapping("/compras/situacao-cotacao-mercadoria-parceiro")
public class SituacaoCotacaoMercadoriaParceiroController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(SituacaoCotacaoMercadoriaParceiro.valuesResponse());
	}

}