package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.condicaopagamento.CondicaoPagamento;
import br.com.ultraworks.erp.api.financeiro.domain.condicaopagamento.CondicaoPagamentoDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.CondicaoPagamentoMapper;
import br.com.ultraworks.erp.api.financeiro.service.CondicaoPagamentoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/condicao-pagamento")
public class CondicaoPagamentoController extends GenericController<CondicaoPagamento, Long, CondicaoPagamentoDTO> {

	public CondicaoPagamentoController(CondicaoPagamentoService service, CondicaoPagamentoMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((CondicaoPagamentoService) service).getProximoCodigo());
	}

}