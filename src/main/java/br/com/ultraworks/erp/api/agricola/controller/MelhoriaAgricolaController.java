package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.melhoriaagricola.MelhoriaAgricola;
import br.com.ultraworks.erp.api.agricola.domain.melhoriaagricola.MelhoriaAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.MelhoriaAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.MelhoriaAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/melhoria-agricola")
public class MelhoriaAgricolaController extends GenericController<MelhoriaAgricola, Long, MelhoriaAgricolaDTO> {

	public MelhoriaAgricolaController(MelhoriaAgricolaService service, MelhoriaAgricolaMapper mapper) {
		super(service, mapper);
	}

}