package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.motivoestornofinanceiro.MotivoEstornoFinanceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface MotivoEstornoFinanceiroRepository extends UWRepository<MotivoEstornoFinanceiro, Long> {

}
