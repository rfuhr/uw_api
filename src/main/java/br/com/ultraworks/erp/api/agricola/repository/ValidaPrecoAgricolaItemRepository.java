package br.com.ultraworks.erp.api.agricola.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.validacalculoagricola.ValidaCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validaprecoagricolaitem.ValidaPrecoAgricolaItem;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ValidaPrecoAgricolaItemRepository extends UWRepository<ValidaPrecoAgricolaItem, Long> {

	@Query(value = "select vpai.*			 															"
			+ " from   valida_preco_agricola_item vpai													"
			+ " where	vpai.item_id = :itemId 															"
			+ " and     vpai.tipo_preco_agricola_id = :tipoPrecoAgricolaId								"
			+ " and     :dataBase between vpai.data_inicio_vigencia and vca.data_final_vigencia			"
			+ " order by vpai.data_final_vigencia desc limit 1							", nativeQuery = true)
	ValidaPrecoAgricolaItem getValidacaoVigente(Long itemId, Long tipoPrecoAgricolaId, LocalDate dataBase);
}
