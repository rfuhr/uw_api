package br.com.ultraworks.erp.api.relacionamento.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocalDTO;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroLocalMapper;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/relacionamento/parceiro-local")
public class ParceiroLocalController extends GenericController<ParceiroLocal, Long, ParceiroLocalDTO> {

	public ParceiroLocalController(ParceiroLocalService service, ParceiroLocalMapper mapper) {
		super(service, mapper);
	}
}