package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.negociacao.NegociacaoFinanceira;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface NegociacaoFinanceiraRepository extends UWRepository<NegociacaoFinanceira, Long> {

}
