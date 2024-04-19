package br.com.ultraworks.erp.api.tabela.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.indicadorformapagamento.IndicadorFormaPagamento;
import br.com.ultraworks.erp.api.tabela.domain.tipointegracaopagamento.TipoIntegracaoPagamento;

@RestController
@RequestMapping("/tabela/tipo-integracao-pagamento")
public class TipoIntegracaoPagamentoController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(TipoIntegracaoPagamento.valuesResponse());
	}

}