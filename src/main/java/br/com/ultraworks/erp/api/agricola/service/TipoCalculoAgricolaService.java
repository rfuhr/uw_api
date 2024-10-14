package br.com.ultraworks.erp.api.agricola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.tipocalculoagricola.TipoCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipocalculoagricola.TipoCalculoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.TipoCalculoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.TipoCalculoAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TipoCalculoAgricolaService extends GenericService<TipoCalculoAgricola, Long, TipoCalculoAgricolaDTO> {

	@Autowired
	public TipoCalculoAgricolaService(TipoCalculoAgricolaRepository repository, TipoCalculoAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((TipoCalculoAgricolaRepository) repository).getProximoCodigo();
	}
}