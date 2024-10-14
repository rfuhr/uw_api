package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.complordemcalculoagricola.ComplOrdemCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.complordemcalculoagricola.ComplOrdemCalculoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.ComplOrdemCalculoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.ComplOrdemCalculoAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/compl-ordem-calculo-agricola")
public class ComplOrdemCalculoAgricolaController
		extends GenericController<ComplOrdemCalculoAgricola, Long, ComplOrdemCalculoAgricolaDTO> {

	public ComplOrdemCalculoAgricolaController(ComplOrdemCalculoAgricolaService service,
			ComplOrdemCalculoAgricolaMapper mapper) {
		super(service, mapper);
	}

}