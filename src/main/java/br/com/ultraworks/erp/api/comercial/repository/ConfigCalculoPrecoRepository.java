package br.com.ultraworks.erp.api.comercial.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.comercial.domain.configcalculopreco.ConfigCalculoPreco;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigCalculoPrecoRepository extends UWRepository<ConfigCalculoPreco, Long> {

}
