package br.com.ultraworks.erp.api.agricola.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.validaoperacaointernaagricola.ValidaOperacaoInternaAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ValidaOperacaoInternaAgricolaRepository extends UWRepository<ValidaOperacaoInternaAgricola, Long> {

	@Query(value = "select vica.*			 															"
			+ " from   valida_operacao_interna_agricola voia											"
			+ " where  voia.item_id = :itemId 															"
			+ " and    voia.operacao_interna_id = :operacaoInternaId									"
			+ " and    voia.grupo_operacao_agricola_id = :grupoOperacaoAgricolaId						"
			+ " and    :dataBase between voia.data_inicio_vigencia and voia.data_final_vigencia			"
			+ "	order by voia.data_inicio_vigencia desc	limit 1											", nativeQuery = true)
	ValidaOperacaoInternaAgricola getValidacaoVigente(Long itemId, Long operacaoInternaId, Long grupoOperacaoAgricolaId, LocalDate dataBase);
}
