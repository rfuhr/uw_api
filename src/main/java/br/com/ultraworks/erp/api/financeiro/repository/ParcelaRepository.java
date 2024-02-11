package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.parcela.ParcelaFinanceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ParcelaRepository extends UWRepository<ParcelaFinanceiro, Long> {

}
