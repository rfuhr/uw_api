package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.conta.Conta;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ContaRepository extends UWRepository<Conta, Long> {

}
