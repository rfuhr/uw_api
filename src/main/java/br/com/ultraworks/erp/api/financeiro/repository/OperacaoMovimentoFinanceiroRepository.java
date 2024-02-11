package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface OperacaoMovimentoFinanceiroRepository extends UWRepository<OperacaoMovimentoFinanceiro, Long> {

}
