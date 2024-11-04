package br.com.ultraworks.erp.api.compras.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.compras.domain.urgenciasolicitacaomercadoria.UrgenciaSolicitacaoMercadoria;

@RestController
@RequestMapping("/compras/urgencia-solicitacao-mercadoria")
public class UrgenciaSolicitacaoMercadoriaController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(UrgenciaSolicitacaoMercadoria.valuesResponse());
	}

}