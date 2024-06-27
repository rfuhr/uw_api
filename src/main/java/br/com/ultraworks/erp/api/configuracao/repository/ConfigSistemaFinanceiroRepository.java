package br.com.ultraworks.erp.api.configuracao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.configuracao.domain.configsistemafinanceiro.ConfigSistemaFinanceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigSistemaFinanceiroRepository extends UWRepository<ConfigSistemaFinanceiro, Long> {

	List<ConfigSistemaFinanceiro> findByConfigSistemaId(Long configSistemaId);
}
