package br.com.ultraworks.erp.api.relacionamento.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiro.Parceiro;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiro.ParceiroDTO;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroMapper;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/relacionamento/parceiro")
public class ParceiroController extends GenericController<Parceiro, Long, ParceiroDTO> {

	public ParceiroController(ParceiroService service, ParceiroMapper mapper) {
		super(service, mapper);
	}
}