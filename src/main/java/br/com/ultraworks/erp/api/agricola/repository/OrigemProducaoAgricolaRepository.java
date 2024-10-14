package br.com.ultraworks.erp.api.agricola.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.origemproducaoagricola.OrigemProducaoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface OrigemProducaoAgricolaRepository extends UWRepository<OrigemProducaoAgricola, Long> {

	@Query(value = "SELECT proximo_codigo('tipo_preco_agricola', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
}
