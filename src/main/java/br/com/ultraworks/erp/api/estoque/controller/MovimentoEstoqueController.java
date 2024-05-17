package br.com.ultraworks.erp.api.estoque.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoqueDTO;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoqueRequest;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoqueResponse;
import br.com.ultraworks.erp.api.estoque.mapper.MovimentoEstoqueMapper;
import br.com.ultraworks.erp.api.estoque.service.MovimentoEstoqueService;
import br.com.ultraworks.erp.api.estoque.service.RelatoriosEstoqueService;
import br.com.ultraworks.erp.core.generics.GenericController;


@RestController
@RequestMapping("/estoque/movimento-estoque")
public class MovimentoEstoqueController extends GenericController<MovimentoEstoque, Long, MovimentoEstoqueDTO> {
	
	MovimentoEstoqueService service;
	RelatoriosEstoqueService relatoriosEstoqueService;

	public MovimentoEstoqueController(MovimentoEstoqueService service, MovimentoEstoqueMapper mapper,
			RelatoriosEstoqueService relatoriosEstoqueService) {
		super(service, mapper);
		this.service = service;
		this.relatoriosEstoqueService = relatoriosEstoqueService;
	}
	
	@PostMapping("/consultar")
	public List<MovimentoEstoqueResponse> buscaMovimentoEstoque(@RequestBody MovimentoEstoqueRequest movimentoEstoqueRequest) {
		return service.buscaMovimentoEstoque(movimentoEstoqueRequest);
	}
	
	@PostMapping("/imprimir")
    public ResponseEntity<byte[]> generateReport(@RequestBody MovimentoEstoqueRequest movimentoEstoqueRequest) {
    	return relatoriosEstoqueService.imprimirRazaoEstoque(movimentoEstoqueRequest);
    }

}