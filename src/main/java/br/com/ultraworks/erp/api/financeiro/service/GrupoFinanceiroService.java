package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.GrupoFinanceiroMapper;
import br.com.ultraworks.erp.api.financeiro.repository.CarteiraFinanceiraRepository;
import br.com.ultraworks.erp.api.financeiro.repository.GrupoFinanceiroRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class GrupoFinanceiroService extends GenericService<GrupoFinanceiro, Long, GrupoFinanceiroDTO> {

	@Autowired
	public GrupoFinanceiroService(GrupoFinanceiroRepository repository, GrupoFinanceiroMapper mapper) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((GrupoFinanceiroRepository) repository).getProximoCodigo();
	}
}