package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.grupotributacao.GrupoTributacao;
import br.com.ultraworks.erp.api.fiscal.domain.grupotributacao.GrupoTributacaoDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.GrupoTributacaoMapper;
import br.com.ultraworks.erp.api.fiscal.service.GrupoTributacaoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/grupo-tributacao")
public class GrupoTributacaoController extends GenericController<GrupoTributacao, Long, GrupoTributacaoDTO> {

	public GrupoTributacaoController(GrupoTributacaoService service, GrupoTributacaoMapper mapper) {
		super(service, mapper);
	}

}