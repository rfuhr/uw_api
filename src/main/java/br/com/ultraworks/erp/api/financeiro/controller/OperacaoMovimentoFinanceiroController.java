package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.OperacaoMovimentoFinanceiroMapper;
import br.com.ultraworks.erp.api.financeiro.service.OperacaoMovimentoFinanceiroService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/operacao-movimento-financeiro")
public class OperacaoMovimentoFinanceiroController
		extends GenericController<OperacaoMovimentoFinanceiro, Long, OperacaoMovimentoFinanceiroDTO> {

	public OperacaoMovimentoFinanceiroController(OperacaoMovimentoFinanceiroService service,
			OperacaoMovimentoFinanceiroMapper mapper) {
		super(service, mapper);
	}

}