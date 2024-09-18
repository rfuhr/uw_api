package br.com.ultraworks.erp.api.financeiro.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.planoclassificacaofinanceira.PlanoClassificacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.planoclassificacaofinanceira.PlanoClassificacaoFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.PlanoClassificacaoFinanceiraMapper;
import br.com.ultraworks.erp.api.financeiro.service.PlanoClassificacaoFinanceiraService;
import br.com.ultraworks.erp.core.dto.EstruturaContaResponse;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/plano-classificacao-financeira")
public class PlanoClassificacaoFinanceiraController
		extends GenericController<PlanoClassificacaoFinanceira, Long, PlanoClassificacaoFinanceiraDTO> {

	public PlanoClassificacaoFinanceiraController(PlanoClassificacaoFinanceiraService service,
			PlanoClassificacaoFinanceiraMapper mapper) {
		super(service, mapper);
	}
	
	@GetMapping("/estrutura")
	public ResponseEntity<?> getJsonEstrutura() {
		 List<EstruturaContaResponse> estruturaConta = ((PlanoClassificacaoFinanceiraService) service).getEstruturaConta();
		return ResponseEntity.ok(estruturaConta);
	}	

}