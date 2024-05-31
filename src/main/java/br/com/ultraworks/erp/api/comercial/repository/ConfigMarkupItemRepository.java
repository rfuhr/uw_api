package br.com.ultraworks.erp.api.comercial.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupitem.ConfigMarkupItem;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigMarkupItemRepository extends UWRepository<ConfigMarkupItem, Long> {
	
	@Query(value = "SELECT id from config_markup_item where item_id = :itemId and current_date between data_inicio_vigencia and data_final_vigencia ", nativeQuery = true)
	Long buscaConfigMarkupItemVigente(Long itemId); 

}
