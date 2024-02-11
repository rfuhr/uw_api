package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.OperacaoMovimentoFinanceiroMapper;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoMovimentoFinanceiroRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class OperacaoMovimentoFinanceiroService
		extends GenericService<OperacaoMovimentoFinanceiro, Long, OperacaoMovimentoFinanceiroDTO> {

	@Autowired
	public OperacaoMovimentoFinanceiroService(OperacaoMovimentoFinanceiroRepository repository,
			OperacaoMovimentoFinanceiroMapper mapper) {
		super(repository, mapper);
	}

}