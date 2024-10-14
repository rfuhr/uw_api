package br.com.ultraworks.erp.api.agricola.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ContratoAgricolaRepository extends UWRepository<ContratoAgricola, Long> {
	
}
