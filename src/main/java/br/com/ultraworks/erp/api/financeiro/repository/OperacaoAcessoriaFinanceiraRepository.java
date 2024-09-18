package br.com.ultraworks.erp.api.financeiro.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface OperacaoAcessoriaFinanceiraRepository extends UWRepository<OperacaoAcessoriaFinanceira, Long> {

	List<OperacaoAcessoriaFinanceira> findByJuroDesconto(String juroDesconto);
}
