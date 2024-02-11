package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.TipoOperacaoFinanceiraMapper;
import br.com.ultraworks.erp.api.financeiro.repository.TipoOperacaoFinanceiraRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TipoOperacaoFinanceiraService
		extends GenericService<TipoOperacaoFinanceira, Long, TipoOperacaoFinanceiraDTO> {

	@Autowired
	public TipoOperacaoFinanceiraService(TipoOperacaoFinanceiraRepository repository,
			TipoOperacaoFinanceiraMapper mapper) {
		super(repository, mapper);
	}

}