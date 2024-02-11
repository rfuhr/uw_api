package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.operacaofinanceira.OperacaoFinanceira;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface OperacaoFinanceiraRepository extends UWRepository<OperacaoFinanceira, Long> {

}
