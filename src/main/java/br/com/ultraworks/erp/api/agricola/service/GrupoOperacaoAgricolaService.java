package br.com.ultraworks.erp.api.agricola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.grupooperacaoagricola.GrupoOperacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.grupooperacaoagricola.GrupoOperacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.GrupoOperacaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.GrupoOperacaoAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class GrupoOperacaoAgricolaService
		extends GenericService<GrupoOperacaoAgricola, Long, GrupoOperacaoAgricolaDTO> {

	@Autowired
	public GrupoOperacaoAgricolaService(GrupoOperacaoAgricolaRepository repository,
			GrupoOperacaoAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((GrupoOperacaoAgricolaRepository) repository).getProximoCodigo();
	}

}