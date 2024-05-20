package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface CarteiraFinanceiraRepository extends UWRepository<CarteiraFinanceira, Long> {

	@Query(value = "SELECT proximo_codigo('carteira_financeira', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
}
