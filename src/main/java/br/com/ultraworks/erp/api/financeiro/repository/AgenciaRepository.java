package br.com.ultraworks.erp.api.financeiro.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.agencia.Agencia;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface AgenciaRepository extends UWRepository<Agencia, Long> {

}
