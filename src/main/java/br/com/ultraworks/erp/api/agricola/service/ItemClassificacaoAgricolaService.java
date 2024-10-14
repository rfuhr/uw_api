package br.com.ultraworks.erp.api.agricola.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola.ItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola.ItemClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola.ItemClassificacaoAgricolaRomaneioTelaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.ItemClassificacaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.ItemClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.query.SelecionaItensClassificacaoParaRomaneioQuery;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ItemClassificacaoAgricolaService
		extends GenericService<ItemClassificacaoAgricola, Long, ItemClassificacaoAgricolaDTO> {

	private SelecionaItensClassificacaoParaRomaneioQuery selecionaItensClassificacaoParaRomaneioQuery;

	@Autowired
	public ItemClassificacaoAgricolaService(ItemClassificacaoAgricolaRepository repository,
			ItemClassificacaoAgricolaMapper mapper,
			SelecionaItensClassificacaoParaRomaneioQuery selecionaItensClassificacaoParaRomaneioQuery) {
		super(repository, mapper);
		this.selecionaItensClassificacaoParaRomaneioQuery = selecionaItensClassificacaoParaRomaneioQuery;
	}

	public int getProximoCodigo() {
		return ((ItemClassificacaoAgricolaRepository) repository).getProximoCodigo();
	}

	public List<ItemClassificacaoAgricolaRomaneioTelaDTO> buscarItensParaRomaneio(Long itemId, LocalDate dataRomaneio) {
		return selecionaItensClassificacaoParaRomaneioQuery.executeSQL(dataRomaneio, itemId);
	}
}