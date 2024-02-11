package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.operacaofinanceira.OperacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaofinanceira.OperacaoFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.OperacaoFinanceiraMapper;
import br.com.ultraworks.erp.api.financeiro.service.OperacaoFinanceiraService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/operacao-financeira")
public class OperacaoFinanceiraController extends GenericController<OperacaoFinanceira, Long, OperacaoFinanceiraDTO> {

	public OperacaoFinanceiraController(OperacaoFinanceiraService service, OperacaoFinanceiraMapper mapper) {
		super(service, mapper);
	}

}