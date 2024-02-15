package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.enquadramento.Enquadramento;
import br.com.ultraworks.erp.api.fiscal.domain.enquadramento.EnquadramentoDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.EnquadramentoMapper;
import br.com.ultraworks.erp.api.fiscal.service.EnquadramentoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/enquadramento")
public class EnquadramentoController extends GenericController<Enquadramento, Long, EnquadramentoDTO> {

	public EnquadramentoController(EnquadramentoService service, EnquadramentoMapper mapper) {
		super(service, mapper);
	}

}