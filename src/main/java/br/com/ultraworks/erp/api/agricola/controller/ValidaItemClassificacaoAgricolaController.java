package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.validaitemclassificacaoagricola.ValidaItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validaitemclassificacaoagricola.ValidaItemClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.ValidaItemClassificacaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.ValidaItemClassificacaoAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/valida-item-classificacao-agricola")
public class ValidaItemClassificacaoAgricolaController
		extends GenericController<ValidaItemClassificacaoAgricola, Long, ValidaItemClassificacaoAgricolaDTO> {

	public ValidaItemClassificacaoAgricolaController(ValidaItemClassificacaoAgricolaService service, ValidaItemClassificacaoAgricolaMapper mapper) {
		super(service, mapper);
	}

}