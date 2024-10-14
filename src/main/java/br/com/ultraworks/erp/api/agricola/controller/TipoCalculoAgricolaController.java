package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.tipocalculoagricola.TipoCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipocalculoagricola.TipoCalculoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.TipoCalculoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.TipoCalculoAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/tipo-calculo-agricola")
public class TipoCalculoAgricolaController
		extends GenericController<TipoCalculoAgricola, Long, TipoCalculoAgricolaDTO> {

	public TipoCalculoAgricolaController(TipoCalculoAgricolaService service, TipoCalculoAgricolaMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((TipoCalculoAgricolaService) service).getProximoCodigo());
	}
}