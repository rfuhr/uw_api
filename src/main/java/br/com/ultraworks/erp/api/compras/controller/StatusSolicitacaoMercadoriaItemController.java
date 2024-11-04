package br.com.ultraworks.erp.api.compras.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.compras.domain.statussolicitacaomercadoriaitem.StatusSolicitacaoMercadoriaItem;

@RestController
@RequestMapping("/compras/status-solicitacao-mercadoria-item")
public class StatusSolicitacaoMercadoriaItemController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(StatusSolicitacaoMercadoriaItem.valuesResponse());
	}

}