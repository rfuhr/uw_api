package br.com.ultraworks.erp.api.configuracao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.configuracao.domain.configsistemaagricola.ConfigSistemaAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigSistemaAgricolaRepository extends UWRepository<ConfigSistemaAgricola, Long> {

	List<ConfigSistemaAgricola> findByConfigSistemaId(Long configSistemaId);
}
