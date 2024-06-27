package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.motivoestornofinanceiro.MotivoEstornoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.motivoestornofinanceiro.MotivoEstornoFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.MotivoEstornoFinanceiroMapper;
import br.com.ultraworks.erp.api.financeiro.service.MotivoEstornoFinanceiroService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/motivo-estorno-financeiro")
public class MotivoEstornoFinanceiroController extends GenericController<MotivoEstornoFinanceiro, Long, MotivoEstornoFinanceiroDTO> {

	public MotivoEstornoFinanceiroController(MotivoEstornoFinanceiroService service, MotivoEstornoFinanceiroMapper mapper) {
		super(service, mapper);
	}

}