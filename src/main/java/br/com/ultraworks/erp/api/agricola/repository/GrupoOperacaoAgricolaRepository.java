package br.com.ultraworks.erp.api.agricola.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.grupooperacaoagricola.GrupoOperacaoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface GrupoOperacaoAgricolaRepository extends UWRepository<GrupoOperacaoAgricola, Long> {

	@Query(value = "SELECT proximo_codigo('grupo_operacao_agricola', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
}
