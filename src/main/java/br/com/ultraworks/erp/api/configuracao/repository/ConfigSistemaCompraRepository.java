package br.com.ultraworks.erp.api.configuracao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.configuracao.domain.configsistemacompra.ConfigSistemaCompra;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigSistemaCompraRepository extends UWRepository<ConfigSistemaCompra, Long> {

	List<ConfigSistemaCompra> findByConfigSistemaId(Long configSistemaId);
}
