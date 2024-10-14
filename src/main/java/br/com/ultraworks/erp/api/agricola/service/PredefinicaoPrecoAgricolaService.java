package br.com.ultraworks.erp.api.agricola.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.predefinicaoprecoagricola.PredefinicaoPrecoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.predefinicaoprecoagricola.PredefinicaoPrecoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.PredefinicaoPrecoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.PredefinicaoPrecoAgricolaRepository;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.repository.FatoGeradorRepository;
import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class PredefinicaoPrecoAgricolaService
		extends GenericService<PredefinicaoPrecoAgricola, Long, PredefinicaoPrecoAgricolaDTO> {

	@Autowired
	public PredefinicaoPrecoAgricolaService(PredefinicaoPrecoAgricolaRepository repository,
			PredefinicaoPrecoAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((PredefinicaoPrecoAgricolaRepository) repository).getProximoCodigo();
	}
	
	public Page<PredefinicaoPrecoAgricola> buscarPredefinicaoValidadoParaOperacaoAgricola(Long itemId, Long tipoPrecoAgricolaId,
			LazyParams params) {
		List<Long> ids = ((PredefinicaoPrecoAgricolaRepository) repository).buscarIdsPredefinicaoValidadoParaOperacaoAgricola(itemId,
				tipoPrecoAgricolaId);
		if (!ids.isEmpty()) {
			return getAllPaginadaFilterIds(params, ids);
		} else {
			return Page.empty();
		}
	}

}