package br.com.ultraworks.erp.api.agricola.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.complordemcalculoagricola.ComplOrdemCalculoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ComplOrdemCalculoAgricolaRepository extends UWRepository<ComplOrdemCalculoAgricola, Long> {

}
