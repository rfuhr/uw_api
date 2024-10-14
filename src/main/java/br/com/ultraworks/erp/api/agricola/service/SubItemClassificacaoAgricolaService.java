package br.com.ultraworks.erp.api.agricola.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.subitemclassificacaoagricola.SubItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.subitemclassificacaoagricola.SubItemClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.SubItemClassificacaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.SubItemClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class SubItemClassificacaoAgricolaService
		extends GenericService<SubItemClassificacaoAgricola, Long, SubItemClassificacaoAgricolaDTO> {

	@Autowired
	public SubItemClassificacaoAgricolaService(SubItemClassificacaoAgricolaRepository repository,
			SubItemClassificacaoAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public Page<SubItemClassificacaoAgricola> buscarItensParaRomaneio(Long itemId, Long itemClassificacaoAgricolaId,
			LocalDate dataRomaneio, LazyParams params) {
		List<Long> ids = ((SubItemClassificacaoAgricolaRepository) repository).buscarIdsItensParaRomaneio(itemId,
				itemClassificacaoAgricolaId, dataRomaneio);

		if (!ids.isEmpty()) {
			return getAllPaginadaFilterIds(params, ids);
		} else {
			return Page.empty();
		}
	}
}