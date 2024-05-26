package br.com.ultraworks.erp.api.comercial.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitemindice.ConfigMarkupPlanoItemIndice;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigMarkupPlanoItemIndiceRepository extends UWRepository<ConfigMarkupPlanoItemIndice, Long> {
	
	List<ConfigMarkupPlanoItemIndice> findByConfigMarkupPlanoItemId(Long configMarkupPlanoItemId);

}
