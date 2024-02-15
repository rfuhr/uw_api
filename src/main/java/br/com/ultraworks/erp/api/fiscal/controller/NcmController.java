package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.ncm.Ncm;
import br.com.ultraworks.erp.api.fiscal.domain.ncm.NcmDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.NcmMapper;
import br.com.ultraworks.erp.api.fiscal.service.NcmService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/ncm")
public class NcmController extends GenericController<Ncm, Long, NcmDTO> {

	public NcmController(NcmService service, NcmMapper mapper) {
		super(service, mapper);
	}

}