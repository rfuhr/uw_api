package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.estornobaixafinanceiro.EstornoBaixaFinanceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface EstornoBaixaFinanceiroRepository extends UWRepository<EstornoBaixaFinanceiro, Long> {


}
