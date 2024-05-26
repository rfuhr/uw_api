package br.com.ultraworks.erp.api.comercial.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.comercial.domain.indicemarkup.IndiceMarkup;
import br.com.ultraworks.erp.api.comercial.domain.indicemarkup.IndiceMarkupDTO;
import br.com.ultraworks.erp.api.comercial.mapper.IndiceMarkupMapper;
import br.com.ultraworks.erp.api.comercial.service.IndiceMarkupService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/comercial/indice-markup")
public class IndiceMarkupController extends GenericController<IndiceMarkup, Long, IndiceMarkupDTO> {

	public IndiceMarkupController(IndiceMarkupService service, IndiceMarkupMapper mapper) {
		super(service, mapper);
	}

}