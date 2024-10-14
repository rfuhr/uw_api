package br.com.ultraworks.erp.api.agricola.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.validaitemclassificacaoagricola.ValidaItemClassificacaoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ValidaItemClassificacaoAgricolaRepository extends UWRepository<ValidaItemClassificacaoAgricola, Long> {

	@Query(value = "select vica.*			 															"
			+ " from   valida_item_classificacao_agricola vica											"
			+ " where  vica.item_id = :itemId 															"
			+ " and    vica.item_classificacao_agricola_id = :itemClassificacaoAgricolaId				"
			+ " and    vica.tipo_uso_romaneio in ('DD', 'DC') 											"
			+ " and    :dataBase between vica.data_inicio_vigencia and vica.data_final_vigencia			", nativeQuery = true)
	List<ValidaItemClassificacaoAgricola> getValidacaoVigenteUsoRomaneio(Long itemId, Long itemClassificacaoAgricolaId, LocalDate dataBase);
	
	@Query(value = "select vica.*			 															"
			+ " from   valida_item_classificacao_agricola vica											"
			+ " where  vica.item_id = :itemId 															"
			+ " and    vica.tipo_uso_romaneio in ('DD', 'DC') 											"
			+ " and    :dataBase between vica.data_inicio_vigencia and vica.data_final_vigencia			", nativeQuery = true)
	List<ValidaItemClassificacaoAgricola> getItensValidacaoVigenteUsoRomaneio(Long itemId, LocalDate dataBase);

}
