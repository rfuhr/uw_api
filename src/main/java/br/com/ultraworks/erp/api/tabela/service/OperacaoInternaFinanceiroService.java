package br.com.ultraworks.erp.api.tabela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.operacaointernafinanceiro.OperacaoInternaFinanceiro;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafinanceiro.OperacaoInternaFinanceiroDTO;
import br.com.ultraworks.erp.api.tabela.mapper.OperacaoInternaFinanceiroMapper;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaFinanceiroRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class OperacaoInternaFinanceiroService
		extends GenericService<OperacaoInternaFinanceiro, Long, OperacaoInternaFinanceiroDTO> {

	@Autowired
	public OperacaoInternaFinanceiroService(OperacaoInternaFinanceiroRepository repository,
			OperacaoInternaFinanceiroMapper mapper) {
		super(repository, mapper);
	}

	public OperacaoInternaFinanceiro getByOperacaoInterna(Long id) {
		return ((OperacaoInternaFinanceiroRepository) repository).findByOperacaoInternaId(id);
	}

}