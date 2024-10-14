package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.indicefinanceiro.IndiceFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.indicefinanceiro.IndiceFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.IndiceFinanceiroMapper;
import br.com.ultraworks.erp.api.financeiro.repository.IndiceFinanceiroRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class IndiceFinanceiroService extends GenericService<IndiceFinanceiro, Long, IndiceFinanceiroDTO> {

	@Autowired
	public IndiceFinanceiroService(IndiceFinanceiroRepository repository, IndiceFinanceiroMapper mapper) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((IndiceFinanceiroRepository) repository).getProximoCodigo();
	}

}