package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.agencia.Agencia;
import br.com.ultraworks.erp.api.financeiro.domain.agencia.AgenciaDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.AgenciaMapper;
import br.com.ultraworks.erp.api.financeiro.service.AgenciaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/agencia")
public class AgenciaController extends GenericController<Agencia, Long, AgenciaDTO> {

	public AgenciaController(AgenciaService service, AgenciaMapper mapper) {
		super(service, mapper);
	}

}