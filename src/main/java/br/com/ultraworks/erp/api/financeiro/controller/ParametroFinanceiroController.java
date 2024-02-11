package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.parametrofinanceiro.ParametroFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.parametrofinanceiro.ParametroFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.ParametroFinanceiroMapper;
import br.com.ultraworks.erp.api.financeiro.service.ParametroFinanceiroService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/parametro-financeiro")
public class ParametroFinanceiroController extends GenericController<ParametroFinanceiro, Long, ParametroFinanceiroDTO> {

	public ParametroFinanceiroController(ParametroFinanceiroService service, ParametroFinanceiroMapper mapper) {
		super(service, mapper);
	}

}