package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.finalidadecontratoagricola.FinalidadeContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.finalidadecontratoagricola.FinalidadeContratoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.FinalidadeContratoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.FinalidadeContratoAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/finalidade-contrato-agricola")
public class FinalidadeContratoAgricolaController
		extends GenericController<FinalidadeContratoAgricola, Long, FinalidadeContratoAgricolaDTO> {

	public FinalidadeContratoAgricolaController(FinalidadeContratoAgricolaService service,
			FinalidadeContratoAgricolaMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((FinalidadeContratoAgricolaService) service).getProximoCodigo());
	}
}