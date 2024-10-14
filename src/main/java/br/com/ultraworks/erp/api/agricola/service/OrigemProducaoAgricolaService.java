package br.com.ultraworks.erp.api.agricola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.origemproducaoagricola.OrigemProducaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.origemproducaoagricola.OrigemProducaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.OrigemProducaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.OrigemProducaoAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class OrigemProducaoAgricolaService
		extends GenericService<OrigemProducaoAgricola, Long, OrigemProducaoAgricolaDTO> {

	@Autowired
	public OrigemProducaoAgricolaService(OrigemProducaoAgricolaRepository repository,
			OrigemProducaoAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((OrigemProducaoAgricolaRepository) repository).getProximoCodigo();
	}

}