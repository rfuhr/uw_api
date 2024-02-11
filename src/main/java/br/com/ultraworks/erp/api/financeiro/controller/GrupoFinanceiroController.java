package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.GrupoFinanceiroMapper;
import br.com.ultraworks.erp.api.financeiro.service.GrupoFinanceiroService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/grupo-financeiro")
public class GrupoFinanceiroController extends GenericController<GrupoFinanceiro, Long, GrupoFinanceiroDTO> {

	public GrupoFinanceiroController(GrupoFinanceiroService service, GrupoFinanceiroMapper mapper) {
		super(service, mapper);
	}

}