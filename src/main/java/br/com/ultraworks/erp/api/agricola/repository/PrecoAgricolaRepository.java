package br.com.ultraworks.erp.api.agricola.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.precoagricola.PrecoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface PrecoAgricolaRepository extends UWRepository<PrecoAgricola, Long> {

	@Query(value = "select pa	.*			 															"
			+ " from	preco_agricola pa																"
			+ " 		join departamento d on d.id = pa.departamento_id								"
			+ " where	pa.item_id = :itemId				 											"
			+ " and     pa.tipo_preco_agricola_id = :tipoPrecoAgricolaId								"
			+ " and     pa.empresa_id = :empresaId														"
			+ " and     (pa.empresa_filial_id = :empresaFilialId or :empresaFilialId is null)			"
			+ " and     (pa.departamento_id = :departamentoId or :departamentoId is null)				"
			+ " and     pa.predefinicao_preco_agricola_id = :predefinicaoPrecoAgricolaId				"
			+ " and     pa.cod_nivel_class1 = :codNivelClass1											"
			+ " and     pa.cod_nivel_class2 = :codNivelClass2											"
			+ " and     pa.cod_nivel_class3 = :codNivelClass3											"
			+ " and     pa.cod_nivel_class4 = :codNivelClass4											"
			+ " and     :dataBase between pa.data_inicio_vigencia and pa.data_final_vigencia			"
			+ " order by pa.data_inicio_vigencia desc limit 1											", nativeQuery = true)
	Optional<PrecoAgricola> getPrecoAgricolaVigente(Long itemId, Long tipoPrecoAgricolaId, Long empresaId, Long empresaFilialId,  Long departamentoId, Long predefinicaoPrecoAgricolaId,
			Long codNivelClass1, Long codNivelClass2, Long codNivelClass3, Long codNivelClass4, LocalDate dataBase);
}
