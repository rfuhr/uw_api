package br.com.ultraworks.erp.api.agricola.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricolaparcela.ContratoAgricolaParcela;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ContratoAgricolaParcelaRepository extends UWRepository<ContratoAgricolaParcela, Long> {

	List<ContratoAgricolaParcela> findByContratoAgricolaId(Long contratoAgricolaId);
}
