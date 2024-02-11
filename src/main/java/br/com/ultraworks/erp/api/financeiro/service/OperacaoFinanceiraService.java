package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.operacaofinanceira.OperacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaofinanceira.OperacaoFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.OperacaoFinanceiraMapper;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoFinanceiraRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class OperacaoFinanceiraService extends GenericService<OperacaoFinanceira, Long, OperacaoFinanceiraDTO> {

	@Autowired
	public OperacaoFinanceiraService(OperacaoFinanceiraRepository repository, OperacaoFinanceiraMapper mapper) {
		super(repository, mapper);
	}

}