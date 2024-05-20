package br.com.ultraworks.erp.api.estoque.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.operacaoestoque.OperacaoEstoque;

@RestController
@RequestMapping("/estoque/operacao-estoque")
public class OperacaoEstoqueController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(OperacaoEstoque.valuesResponse());
	}

}