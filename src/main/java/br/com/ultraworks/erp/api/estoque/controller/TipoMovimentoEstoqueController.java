package br.com.ultraworks.erp.api.estoque.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.tipomovimentoestoque.TipoMovimentoEstoque;

@RestController
@RequestMapping("/estoque/tipo-movimento-estoque")
public class TipoMovimentoEstoqueController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(TipoMovimentoEstoque.valuesResponse());
	}

}