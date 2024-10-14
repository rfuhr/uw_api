package br.com.ultraworks.erp.api.agricola.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricoladesconto.ContratoAgricolaDesconto;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ContratoAgricolaDescontoRepository extends UWRepository<ContratoAgricolaDesconto, Long> {

	List<ContratoAgricolaDesconto> findByContratoAgricolaId(Long contratoAgricolaId);
}
