package br.com.ultraworks.erp.api.financeiro.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.tipocondicaopagamento.TipoCondicaoPagamento;

@RestController
@RequestMapping("/financeiro/tipo-condicao-pagamento")
public class TipoCondicaoPagamentoController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(TipoCondicaoPagamento.valuesResponse());
	}

}
