package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.banco.Banco;
import br.com.ultraworks.erp.api.financeiro.domain.banco.BancoDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.BancoMapper;
import br.com.ultraworks.erp.api.financeiro.service.BancoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/banco")
public class BancoController extends GenericController<Banco, Long, BancoDTO> {

	public BancoController(BancoService service, BancoMapper mapper) {
		super(service, mapper);
	}

}