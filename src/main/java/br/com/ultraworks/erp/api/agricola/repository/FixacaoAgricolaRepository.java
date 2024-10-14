package br.com.ultraworks.erp.api.agricola.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.fixacaoagricola.FixacaoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface FixacaoAgricolaRepository extends UWRepository<FixacaoAgricola, Long> {

}
