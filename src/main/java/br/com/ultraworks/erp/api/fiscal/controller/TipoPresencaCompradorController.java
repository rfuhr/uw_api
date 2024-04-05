package br.com.ultraworks.erp.api.fiscal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.configuracao.domain.tipoemissao.TipoEmissao;
import br.com.ultraworks.erp.api.fiscal.domain.finalidadenfe.FinalidadeNfe;
import br.com.ultraworks.erp.api.fiscal.domain.tipopresencacomprador.TipoPresencaComprador;

@RestController
@RequestMapping("/fiscal/tipo-presenca-comprador")
public class TipoPresencaCompradorController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(TipoPresencaComprador.valuesResponse());
	}

}