package br.com.ultraworks.erp.api.comercial.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitem.ConfigMarkupPlanoItem;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigMarkupPlanoItemRepository extends UWRepository<ConfigMarkupPlanoItem, Long> {

}
