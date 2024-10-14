package br.com.ultraworks.erp.api.agricola.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.validacalculoagricola.ValidaCalculoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ValidaCalculoAgricolaRepository extends UWRepository<ValidaCalculoAgricola, Long> {

	@Query(value = "select vca.*			 																"
			+ " from   valida_calculo_agricola vca														"
			+ " join regra_atividade ra on ra.id = vca.regra_atividade_id 								"
			+ " where	vca.item_id = :itemId 															"
			+ " and     vca.tipo_calculo_agricola_id = :tipoCalculoAgricolaId							"
			+ " and     vca.operacao_interna_id = :operacaoInternaId									"
			+ " and     vca.grupo_operacao_agricola_id = :grupoOperacaoAgricolaId						"
			+ " and     vca.regra_atividade_id in (0, :regraAtividadeId)								"
			+ " and     :dataBase between vca.data_inicio_vigencia and vca.data_final_vigencia			"
			+ " and     :dataBase between ra.data_inicio_vigencia and ra.data_final_vigencia			"
			+ " order by ra.codigo desc, vca.data_final_vigencia desc limit 1							", nativeQuery = true)
	ValidaCalculoAgricola getValidacaoVigente(Long itemId, Long tipoCalculoAgricolaId, Long operacaoInternaId,
			Long grupoOperacaoAgricolaId, Long regraAtividadeId, LocalDate dataBase);
	
	@Query(value = "select vca.*			 																"
			+ " from   valida_calculo_agricola vca														"
			+ " join regra_atividade ra on ra.id = vca.regra_atividade_id 								"
			+ " where	vca.item_id = :itemId 															"
			+ " and     vca.operacao_interna_id = :operacaoInternaId									"
			+ " and     vca.grupo_operacao_agricola_id = :grupoOperacaoAgricolaId						"
			+ " and     vca.regra_atividade_id in (0, :regraAtividadeId)								"
			+ " and     :dataBase between vca.data_inicio_vigencia and vca.data_final_vigencia			"
			+ " and     :dataBase between ra.data_inicio_vigencia and ra.data_final_vigencia			"
			+ " order by ra.codigo desc, vca.data_final_vigencia desc limit 1							", nativeQuery = true)
	List<ValidaCalculoAgricola> getValidacoesTipoCalculoVigente(Long itemId, Long operacaoInternaId,
			Long grupoOperacaoAgricolaId, Long regraAtividadeId, LocalDate dataBase);
}
