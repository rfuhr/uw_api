package br.com.ultraworks.erp.api.comercial.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupitem.ConfigMarkupItem;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigMarkupItemRepository extends UWRepository<ConfigMarkupItem, Long> {

}
