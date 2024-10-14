package br.com.ultraworks.erp.api.agricola.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.finalidadecontratoagricola.FinalidadeContratoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface FinalidadeContratoAgricolaRepository extends UWRepository<FinalidadeContratoAgricola, Long> {

	@Query(value = "SELECT proximo_codigo('finalidade_contrato_agricola', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
}
