package br.com.ultraworks.erp.api.fiscal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.configuracao.domain.tipoemissao.TipoEmissao;
import br.com.ultraworks.erp.api.fiscal.domain.finalidadenfe.FinalidadeNfe;
import br.com.ultraworks.erp.api.fiscal.domain.seloipi.SeloIpi;
import br.com.ultraworks.erp.api.fiscal.domain.tipoconsumidor.TipoConsumidor;
import br.com.ultraworks.erp.api.fiscal.domain.tipodocumentoreferenciado.TipoDocumentoReferenciado;

@RestController
@RequestMapping("/fiscal/selo-ipi")
public class SeloIpiController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(SeloIpi.valuesResponse());
	}

}