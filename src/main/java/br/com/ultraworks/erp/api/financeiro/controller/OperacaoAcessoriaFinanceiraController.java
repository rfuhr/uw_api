package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.OperacaoAcessoriaFinanceiraMapper;
import br.com.ultraworks.erp.api.financeiro.service.OperacaoAcessoriaFinanceiraService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/operacao-acessoria-financeira")
public class OperacaoAcessoriaFinanceiraController
		extends GenericController<OperacaoAcessoriaFinanceira, Long, OperacaoAcessoriaFinanceiraDTO> {

	public OperacaoAcessoriaFinanceiraController(OperacaoAcessoriaFinanceiraService service,
			OperacaoAcessoriaFinanceiraMapper mapper) {
		super(service, mapper);
	}

}