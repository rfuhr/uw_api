package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.motivoestornofinanceiro.MotivoEstornoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.motivoestornofinanceiro.MotivoEstornoFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.MotivoEstornoFinanceiroMapper;
import br.com.ultraworks.erp.api.financeiro.repository.MotivoEstornoFinanceiroRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class MotivoEstornoFinanceiroService
		extends GenericService<MotivoEstornoFinanceiro, Long, MotivoEstornoFinanceiroDTO> {

	@Autowired
	public MotivoEstornoFinanceiroService(MotivoEstornoFinanceiroRepository repository,
			MotivoEstornoFinanceiroMapper mapper) {
		super(repository, mapper);
	}

}