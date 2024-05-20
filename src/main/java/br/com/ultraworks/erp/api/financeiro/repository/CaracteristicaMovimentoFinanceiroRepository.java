package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface CaracteristicaMovimentoFinanceiroRepository extends UWRepository<CaracteristicaMovimentoFinanceiro, Long> {

	@Query(value = "SELECT proximo_codigo('caracteristica_movimento_financeiro', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
}
