package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.tipocontratoagricola.TipoContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipocontratoagricola.TipoContratoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.TipoContratoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.TipoContratoAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/tipo-contrato-agricola")
public class TipoContratoAgricolaController
		extends GenericController<TipoContratoAgricola, Long, TipoContratoAgricolaDTO> {

	public TipoContratoAgricolaController(TipoContratoAgricolaService service, TipoContratoAgricolaMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((TipoContratoAgricolaService) service).getProximoCodigo());
	}
}