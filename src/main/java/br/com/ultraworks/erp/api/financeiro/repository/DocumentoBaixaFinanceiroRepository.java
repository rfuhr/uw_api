package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.documentobaixafinanceiro.DocumentoBaixaFinanceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface DocumentoBaixaFinanceiroRepository extends UWRepository<DocumentoBaixaFinanceiro, Long> {

}
