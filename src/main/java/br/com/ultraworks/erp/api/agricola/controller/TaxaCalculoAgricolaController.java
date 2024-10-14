package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.taxacalculoagricola.TaxaCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.taxacalculoagricola.TaxaCalculoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.TaxaCalculoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.TaxaCalculoAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/taxa-calculo-agricola")
public class TaxaCalculoAgricolaController
		extends GenericController<TaxaCalculoAgricola, Long, TaxaCalculoAgricolaDTO> {

	public TaxaCalculoAgricolaController(TaxaCalculoAgricolaService service, TaxaCalculoAgricolaMapper mapper) {
		super(service, mapper);
	}

}