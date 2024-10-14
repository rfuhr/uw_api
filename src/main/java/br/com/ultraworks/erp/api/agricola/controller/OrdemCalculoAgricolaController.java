package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.ordemcalculoagricola.OrdemCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.ordemcalculoagricola.OrdemCalculoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.OrdemCalculoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.OrdemCalculoAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/ordem-calculo-agricola")
public class OrdemCalculoAgricolaController
		extends GenericController<OrdemCalculoAgricola, Long, OrdemCalculoAgricolaDTO> {

	public OrdemCalculoAgricolaController(OrdemCalculoAgricolaService service, OrdemCalculoAgricolaMapper mapper) {
		super(service, mapper);
	}

}