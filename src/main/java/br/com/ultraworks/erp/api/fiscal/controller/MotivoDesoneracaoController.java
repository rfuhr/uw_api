package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.motivodesoneracao.MotivoDesoneracao;
import br.com.ultraworks.erp.api.fiscal.domain.motivodesoneracao.MotivoDesoneracaoDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.MotivoDesoneracaoMapper;
import br.com.ultraworks.erp.api.fiscal.service.MotivoDesoneracaoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/motivo-desoneracao")
public class MotivoDesoneracaoController extends GenericController<MotivoDesoneracao, Long, MotivoDesoneracaoDTO> {

	public MotivoDesoneracaoController(MotivoDesoneracaoService service, MotivoDesoneracaoMapper mapper) {
		super(service, mapper);
	}

}