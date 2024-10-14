package br.com.ultraworks.erp.api.agricola.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.grupoclassificacaoagricola.GrupoClassificacaoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface GrupoClassificacaoAgricolaRepository extends UWRepository<GrupoClassificacaoAgricola, Long> {

	@Query(value = "SELECT proximo_codigo('grupo_classificacao_agricola', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
}
