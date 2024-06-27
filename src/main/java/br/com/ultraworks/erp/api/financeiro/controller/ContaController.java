package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.conta.Conta;
import br.com.ultraworks.erp.api.financeiro.domain.conta.ContaDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.ContaMapper;
import br.com.ultraworks.erp.api.financeiro.service.ContaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/conta")
public class ContaController extends GenericController<Conta, Long, ContaDTO> {

	public ContaController(ContaService service, ContaMapper mapper) {
		super(service, mapper);
	}

}