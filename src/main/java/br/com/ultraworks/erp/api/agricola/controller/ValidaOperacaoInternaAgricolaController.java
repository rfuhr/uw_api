package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.validaoperacaointernaagricola.ValidaOperacaoInternaAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validaoperacaointernaagricola.ValidaOperacaoInternaAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.ValidaOperacaoInternaAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.ValidaOperacaoInternaAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/valida-operacao-interna-agricola")
public class ValidaOperacaoInternaAgricolaController
		extends GenericController<ValidaOperacaoInternaAgricola, Long, ValidaOperacaoInternaAgricolaDTO> {

	public ValidaOperacaoInternaAgricolaController(ValidaOperacaoInternaAgricolaService service,
			ValidaOperacaoInternaAgricolaMapper mapper) {
		super(service, mapper);
	}

}