package br.com.ultraworks.erp.api.agricola.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.tipoclassificacaoagricola.TipoClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipoclassificacaoagricola.TipoClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.TipoClassificacaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.TipoClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TipoClassificacaoAgricolaService
		extends GenericService<TipoClassificacaoAgricola, Long, TipoClassificacaoAgricolaDTO> {

	@Autowired
	public TipoClassificacaoAgricolaService(TipoClassificacaoAgricolaRepository repository,
			TipoClassificacaoAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public List<TipoClassificacaoAgricola> getBuscaTiposConfigurados(Long produtoId, Long safraId) {
		return ((TipoClassificacaoAgricolaRepository) repository).findByTiposConfigurados(produtoId, safraId);
	}
}