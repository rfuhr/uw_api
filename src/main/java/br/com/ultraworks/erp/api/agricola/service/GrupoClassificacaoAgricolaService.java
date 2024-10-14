package br.com.ultraworks.erp.api.agricola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.grupoclassificacaoagricola.GrupoClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.grupoclassificacaoagricola.GrupoClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.GrupoClassificacaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.GrupoClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class GrupoClassificacaoAgricolaService
		extends GenericService<GrupoClassificacaoAgricola, Long, GrupoClassificacaoAgricolaDTO> {

	@Autowired
	public GrupoClassificacaoAgricolaService(GrupoClassificacaoAgricolaRepository repository,
			GrupoClassificacaoAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((GrupoClassificacaoAgricolaRepository) repository).getProximoCodigo();
	}

}