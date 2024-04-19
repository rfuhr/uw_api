package br.com.ultraworks.erp.api.tabela.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.indicadorformapagamento.IndicadorFormaPagamento;

@RestController
@RequestMapping("/tabela/indicador-forma-pagamento")
public class IndicadorFormaPagamentoController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(IndicadorFormaPagamento.valuesResponse());
	}

}