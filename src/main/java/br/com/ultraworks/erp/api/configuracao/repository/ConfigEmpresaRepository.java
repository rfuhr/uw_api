package br.com.ultraworks.erp.api.configuracao.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.configuracao.domain.configempresa.ConfigEmpresa;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigEmpresaRepository extends UWRepository<ConfigEmpresa, Long> {

	Optional<ConfigEmpresa> findByEmpresaId(Long empresaId);
}
