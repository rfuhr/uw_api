package br.com.ultraworks.erp.api.estoque.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.AtualizaEstoqueRequest;
import br.com.ultraworks.erp.api.estoque.service.EstoqueService;

@RestController
@RequestMapping("/estoque")
public class AtualizaEstoqueController{

	EstoqueService service;
	
	public AtualizaEstoqueController(EstoqueService service) {
		this.service = service;
	}
	
	@PostMapping("/atualizar")
	public void atualizarEstoque(@RequestBody AtualizaEstoqueRequest atualizaEstoqueRequest) {
		service.atualizar(atualizaEstoqueRequest);
	}

}