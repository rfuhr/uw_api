package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.parametrofinanceiro.ParametroFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.parametrofinanceiro.ParametroFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.ParametroFinanceiroMapper;
import br.com.ultraworks.erp.api.financeiro.repository.ParametroFinanceiroRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ParametroFinanceiroService extends GenericService<ParametroFinanceiro, Long, ParametroFinanceiroDTO> {

	private ParametroFinanceiroRepository repository;

	@Autowired
	public ParametroFinanceiroService(ParametroFinanceiroRepository repository, ParametroFinanceiroMapper mapper) {
		super(repository, mapper);
	}

	public ParametroFinanceiro findByParametros(Long empresaId, Long tipoTituloId,
			Long caracteristicaMovimentoFinanceiroId, Long carteiraFinanceiraId, Long fatoGeradorId,
			Long operacaoFinanceiraId) {
		return repository.findByParametros(empresaId, tipoTituloId, caracteristicaMovimentoFinanceiroId,
				carteiraFinanceiraId, fatoGeradorId, operacaoFinanceiraId);
	}
}