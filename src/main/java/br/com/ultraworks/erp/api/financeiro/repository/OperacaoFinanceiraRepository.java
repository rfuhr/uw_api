package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.operacaofinanceira.OperacaoFinanceira;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface OperacaoFinanceiraRepository extends UWRepository<OperacaoFinanceira, Long> {

	@Query(value = "Select of from OperacaoFinanceira of where of.tipoOperacaoFinanceira.id = :tipoOperacaoFinanceiraId "
			+ " and of.operacaoMovimentoFinanceiro.id = :operacaoMovimentoFinanceiroId"
			+ " and of.operacaoAcessoriaFinanceira.id = :operacaoAcessoriaFinanceiraId")
	OperacaoFinanceira findOperacaoFinanceira(Long tipoOperacaoFinanceiraId, Long operacaoMovimentoFinanceiroId,
			Long operacaoAcessoriaFinanceiraId);
}
