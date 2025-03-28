package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.indicefinanceiro.IndiceFinanceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface IndiceFinanceiroRepository extends UWRepository<IndiceFinanceiro, Long> {

	@Query(value = "SELECT proximo_codigo('indice_financeiro', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
}
