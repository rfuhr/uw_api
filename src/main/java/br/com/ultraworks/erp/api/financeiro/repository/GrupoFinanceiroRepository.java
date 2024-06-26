package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface GrupoFinanceiroRepository extends UWRepository<GrupoFinanceiro, Long> {

	@Query(value = "SELECT proximo_codigo('grupo_financeiro', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
}
