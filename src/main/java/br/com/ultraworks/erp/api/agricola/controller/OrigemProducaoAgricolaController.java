package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.origemproducaoagricola.OrigemProducaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.origemproducaoagricola.OrigemProducaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.OrigemProducaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.OrigemProducaoAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/origem-producao-agricola")
public class OrigemProducaoAgricolaController
		extends GenericController<OrigemProducaoAgricola, Long, OrigemProducaoAgricolaDTO> {

	public OrigemProducaoAgricolaController(OrigemProducaoAgricolaService service,
			OrigemProducaoAgricolaMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((OrigemProducaoAgricolaService) service).getProximoCodigo());
	}

}