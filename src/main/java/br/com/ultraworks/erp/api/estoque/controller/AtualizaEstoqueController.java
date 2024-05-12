package br.com.ultraworks.erp.api.estoque.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.AtualizaEstoqueRequest;
import br.com.ultraworks.erp.api.estoque.service.EstoqueService;

@RestController
public class AtualizaEstoqueController{

	EstoqueService service;
	
	public AtualizaEstoqueController(EstoqueService service) {
		this.service = service;
	}
	
	@PostMapping("/estoque/atualizar")
	public void atualizarEstoque(@RequestBody AtualizaEstoqueRequest atualizaEstoqueRequest) {
		service.atualizar(atualizaEstoqueRequest);
	}

}