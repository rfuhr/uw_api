package br.com.ultraworks.erp.api.agricola.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricolamovimento.ContratoAgricolaMovimento;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ContratoAgricolaMovimentoRepository extends UWRepository<ContratoAgricolaMovimento, Long> {

	List<ContratoAgricolaMovimento> findByContratoAgricolaId(Long contratoAgricolaId);
}
