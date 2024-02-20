package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.situacaotributaria.SituacaoTributaria;
import br.com.ultraworks.erp.api.fiscal.domain.situacaotributaria.SituacaoTributariaDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.SituacaoTributariaMapper;
import br.com.ultraworks.erp.api.fiscal.service.SituacaoTributariaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/situacao-tributaria")
public class SituacaoTributariaController extends GenericController<SituacaoTributaria, Long, SituacaoTributariaDTO> {

	public SituacaoTributariaController(SituacaoTributariaService service, SituacaoTributariaMapper mapper) {
		super(service, mapper);
	}

}