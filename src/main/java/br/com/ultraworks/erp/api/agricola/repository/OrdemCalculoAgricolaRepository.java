package br.com.ultraworks.erp.api.agricola.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.ordemcalculoagricola.OrdemCalculoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface OrdemCalculoAgricolaRepository extends UWRepository<OrdemCalculoAgricola, Long> {

}
