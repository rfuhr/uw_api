package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.validacalculoagricola.ValidaCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validacalculoagricola.ValidaCalculoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.ValidaCalculoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.ValidaCalculoAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/valida-calculo-agricola")
public class ValidaCalculoAgricolaController
		extends GenericController<ValidaCalculoAgricola, Long, ValidaCalculoAgricolaDTO> {

	public ValidaCalculoAgricolaController(ValidaCalculoAgricolaService service, ValidaCalculoAgricolaMapper mapper) {
		super(service, mapper);
	}

}