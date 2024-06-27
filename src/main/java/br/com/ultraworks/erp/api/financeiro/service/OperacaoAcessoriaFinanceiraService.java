package br.com.ultraworks.erp.api.financeiro.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.OperacaoAcessoriaFinanceiraMapper;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoAcessoriaFinanceiraRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class OperacaoAcessoriaFinanceiraService
		extends GenericService<OperacaoAcessoriaFinanceira, Long, OperacaoAcessoriaFinanceiraDTO> {

	@Autowired
	public OperacaoAcessoriaFinanceiraService(OperacaoAcessoriaFinanceiraRepository repository,
			OperacaoAcessoriaFinanceiraMapper mapper) {
		super(repository, mapper);
	}

	public List<OperacaoAcessoriaFinanceira> getAllJuros() {
		return ((OperacaoAcessoriaFinanceiraRepository) repository).findByJuroDesconto("J");
	}

	public Collection<OperacaoAcessoriaFinanceira> getAllDescontos() {
		return ((OperacaoAcessoriaFinanceiraRepository) repository).findByJuroDesconto("D");
	}

}