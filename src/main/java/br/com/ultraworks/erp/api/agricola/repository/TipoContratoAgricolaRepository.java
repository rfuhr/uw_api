package br.com.ultraworks.erp.api.agricola.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.tipocontratoagricola.TipoContratoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TipoContratoAgricolaRepository extends UWRepository<TipoContratoAgricola, Long> {

	@Query(value = "SELECT proximo_codigo('tipo_contrato_agricola', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
}
