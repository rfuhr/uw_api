package br.com.ultraworks.erp.api.agricola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.complordemcalculoagricola.ComplOrdemCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.complordemcalculoagricola.ComplOrdemCalculoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.ComplOrdemCalculoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.ComplOrdemCalculoAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ComplOrdemCalculoAgricolaService
		extends GenericService<ComplOrdemCalculoAgricola, Long, ComplOrdemCalculoAgricolaDTO> {

	@Autowired
	public ComplOrdemCalculoAgricolaService(ComplOrdemCalculoAgricolaRepository repository,
			ComplOrdemCalculoAgricolaMapper mapper) {
		super(repository, mapper);
	}

}