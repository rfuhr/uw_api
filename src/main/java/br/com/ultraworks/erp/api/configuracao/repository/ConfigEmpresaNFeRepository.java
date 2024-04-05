package br.com.ultraworks.erp.api.configuracao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFe;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigEmpresaNFeRepository extends UWRepository<ConfigEmpresaNFe, Long> {

	List<ConfigEmpresaNFe> findByConfigEmpresaId(Long configEmpresaId);
	
	ConfigEmpresaNFe findByEmpresaFilialId(Long empresaFilialId);
}
