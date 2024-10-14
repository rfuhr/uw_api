package br.com.ultraworks.erp.api.agricola.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.taxacalculoagricola.TaxaCalculoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TaxaCalculoAgricolaRepository extends UWRepository<TaxaCalculoAgricola, Long> {

	@Query(value = "select tca.*		 																"
			+ " from	taxa_calculo_agricola tca														"
			+ " where   tca.item_id = :itemId															"
			+ " and     tca.tipo_calculo_agricola_id = :tipoCalculoAgricolaId							"
			+ " and     tca.regra_atividade_id in (0, :regraAtividadeId)								"
			+ " and     tca.faixa_limite >= :indiceReferencia											"
			+ " and     :dataBase between tca.data_inicio_vigencia and tca.data_final_vigencia			"
			+ " order by tca.faixa_limite, tca.data_final_vigencia desc limit 1							", nativeQuery = true)
	TaxaCalculoAgricola getValidacaoVigente(Long itemId, Long tipoCalculoAgricolaId, Long regraAtividadeId, BigDecimal indiceReferencia,
			LocalDate dataBase);
}
