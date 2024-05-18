package br.com.ultraworks.erp.api.estoque.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.saldoestoque.SaldoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.saldoestoque.SaldoEstoqueDTO;
import br.com.ultraworks.erp.api.estoque.domain.saldoestoque.SaldoEstoqueRequest;
import br.com.ultraworks.erp.api.estoque.domain.saldoestoque.SaldoEstoqueResponse;
import br.com.ultraworks.erp.api.estoque.mapper.SaldoEstoqueMapper;
import br.com.ultraworks.erp.api.estoque.service.RelatoriosEstoqueService;
import br.com.ultraworks.erp.api.estoque.service.SaldoEstoqueService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/estoque/saldo-estoque")
public class SaldoEstoqueController extends GenericController<SaldoEstoque, Long, SaldoEstoqueDTO> {

	SaldoEstoqueService service;
	RelatoriosEstoqueService relatoriosEstoqueService;
	
	public SaldoEstoqueController(SaldoEstoqueService service, SaldoEstoqueMapper mapper,
			RelatoriosEstoqueService relatoriosEstoqueService) {
		super(service, mapper);
		this.service = service;
		this.relatoriosEstoqueService = relatoriosEstoqueService;
	}
	
	@PostMapping("/consultar")
	public List<SaldoEstoqueResponse> buscaSaldoEstoque(@RequestBody SaldoEstoqueRequest saldoEstoqueRequest) {
		return service.buscaSaldoEstoque(saldoEstoqueRequest);
	}
	
	@PostMapping("/imprimir")
    public ResponseEntity<byte[]> imprimirPosicaoFisicoFinanceiro(@RequestBody SaldoEstoqueRequest saldoEstoqueRequest) {
    	return relatoriosEstoqueService.imprimirPosicaoFisicoFinanceiro(saldoEstoqueRequest);
    }

}