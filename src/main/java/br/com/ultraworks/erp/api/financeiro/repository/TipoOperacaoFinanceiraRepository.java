package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TipoOperacaoFinanceiraRepository extends UWRepository<TipoOperacaoFinanceira, Long> {

}
