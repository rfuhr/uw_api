package br.com.ultraworks.erp.api.agricola.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.tipocalculoagricola.TipoCalculoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TipoCalculoAgricolaRepository extends UWRepository<TipoCalculoAgricola, Long> {

	@Query(value = "SELECT proximo_codigo('tipo_calculo_agricola', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
}
