package br.com.ultraworks.erp.api.agricola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.ordemcalculoagricola.OrdemCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.ordemcalculoagricola.OrdemCalculoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.OrdemCalculoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.OrdemCalculoAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class OrdemCalculoAgricolaService extends GenericService<OrdemCalculoAgricola, Long, OrdemCalculoAgricolaDTO> {

	@Autowired
	public OrdemCalculoAgricolaService(OrdemCalculoAgricolaRepository repository, OrdemCalculoAgricolaMapper mapper) {
		super(repository, mapper);
	}

}