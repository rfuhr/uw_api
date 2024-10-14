package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.indicefinanceiro.IndiceFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.indicefinanceiro.IndiceFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.IndiceFinanceiroMapper;
import br.com.ultraworks.erp.api.financeiro.service.IndiceFinanceiroService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/indice-financeiro")
public class IndiceFinanceiroController extends GenericController<IndiceFinanceiro, Long, IndiceFinanceiroDTO> {

	public IndiceFinanceiroController(IndiceFinanceiroService service, IndiceFinanceiroMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((IndiceFinanceiroService) service).getProximoCodigo());
	}
}