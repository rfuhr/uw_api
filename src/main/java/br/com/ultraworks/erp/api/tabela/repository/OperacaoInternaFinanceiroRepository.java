package br.com.ultraworks.erp.api.tabela.repository;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.operacaointernafinanceiro.OperacaoInternaFinanceiro;
import br.com.ultraworks.erp.core.UWRepository;
import jakarta.persistence.QueryHint;

@Repository
public interface OperacaoInternaFinanceiroRepository extends UWRepository<OperacaoInternaFinanceiro, Long> {

	@QueryHints(value = { @QueryHint(name = "jakarta.persistence.cache.retrieveMode", value = "BYPASS") })
	OperacaoInternaFinanceiro findByOperacaoInternaId(Long operacaoInternaId);

}
