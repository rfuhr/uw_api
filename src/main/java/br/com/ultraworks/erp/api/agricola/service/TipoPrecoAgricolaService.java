package br.com.ultraworks.erp.api.agricola.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.tipoprecoagricola.TipoPrecoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipoprecoagricola.TipoPrecoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.TipoPrecoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.TipoPrecoAgricolaRepository;
import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TipoPrecoAgricolaService extends GenericService<TipoPrecoAgricola, Long, TipoPrecoAgricolaDTO> {

	@Autowired
	public TipoPrecoAgricolaService(TipoPrecoAgricolaRepository repository, TipoPrecoAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((TipoPrecoAgricolaRepository) repository).getProximoCodigo();
	}

	public Page<TipoPrecoAgricola> buscarTipoPrecoValidadoParaOperacaoAgricola(Long itemId, Long grupoOperacaoAgricolaId,
			Long operacaoInternaId, LazyParams params) {
		List<Long> ids = ((TipoPrecoAgricolaRepository) repository).buscarIdsTipoPrecoValidadoParaOperacaoAgricola(itemId,
				grupoOperacaoAgricolaId, operacaoInternaId);
		if (!ids.isEmpty()) {
			return getAllPaginadaFilterIds(params, ids);
		} else {
			return Page.empty();
		}
	}
}