package br.com.ultraworks.erp.api.comercial.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitem.ConfigMarkupPlanoItem;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigMarkupPlanoItemRepository extends UWRepository<ConfigMarkupPlanoItem, Long> {

	@Query(value = "SELECT id from config_markup_plano_item where plano_classificacao_item_id = :planoClassificacaoItemId and current_date between data_inicio_vigencia and data_final_vigencia ", nativeQuery = true)
	Long buscaConfigMarkupPlanoItemVigente(Long planoClassificacaoItemId); 
	
}
