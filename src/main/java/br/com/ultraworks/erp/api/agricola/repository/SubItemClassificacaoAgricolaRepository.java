package br.com.ultraworks.erp.api.agricola.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.subitemclassificacaoagricola.SubItemClassificacaoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface SubItemClassificacaoAgricolaRepository extends UWRepository<SubItemClassificacaoAgricola, Long> {

	@Query(value = "select	distinct sica.id		 																"
			+ " from	sub_item_classificacao_agricola sica											"
			+ " where	sica.item_id = :itemId 															"
			+ " and     sica.item_classificacao_agricola_id = :itemClassificacaoAgricolaId				"
			+ " and     :dataRomaneio  between sica.data_inicio_vigencia and sica.data_final_vigencia	", nativeQuery = true)
	List<Long> buscarIdsItensParaRomaneio(Long itemId, Long itemClassificacaoAgricolaId,
			LocalDate dataRomaneio);
	
}
