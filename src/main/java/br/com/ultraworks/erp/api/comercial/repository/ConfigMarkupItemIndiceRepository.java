package br.com.ultraworks.erp.api.comercial.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupitemindice.ConfigMarkupItemIndice;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigMarkupItemIndiceRepository extends UWRepository<ConfigMarkupItemIndice, Long> {
	
	List<ConfigMarkupItemIndice> findByConfigMarkupItemId(Long configMarkupItemId);

}
