package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.TipoOperacaoFinanceiraMapper;
import br.com.ultraworks.erp.api.financeiro.service.TipoOperacaoFinanceiraService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/tipo-operacao-financeira")
public class TipoOperacaoFinanceiraController
		extends GenericController<TipoOperacaoFinanceira, Long, TipoOperacaoFinanceiraDTO> {

	public TipoOperacaoFinanceiraController(TipoOperacaoFinanceiraService service,
			TipoOperacaoFinanceiraMapper mapper) {
		super(service, mapper);
	}

}