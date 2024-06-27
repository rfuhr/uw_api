package br.com.ultraworks.erp.api.configuracao.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistema;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigSistemaRepository extends UWRepository<ConfigSistema, Long> {

}
