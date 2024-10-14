package br.com.ultraworks.erp.api.agricola.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola.ItemClassificacaoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ItemClassificacaoAgricolaRepository extends UWRepository<ItemClassificacaoAgricola, Long> {

	@Query(value = "SELECT proximo_codigo('item_classificacao_agricola', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
	
	@Query(value = "select	ica.*		 																"
			+ " from	valida_item_classificacao_agricola vica											"
			+ "	   join item_classificacao_agricola ica on ica.id = vica.item_classificacao_agricola_id	"
			+ "	   join grupo_classificacao_agricola gca on gca.id = ica.grupo_classificacao_agricola_id"
			+ " where	:dataRomaneio  between vica.data_inicio_vigencia and vica.data_final_vigencia 	"
			+ " and     :dataRomaneio  between ica.data_inicio_vigencia and ica.data_final_vigencia		"
			+ " and     vica.tipo_uso_romaneio in ('DD', 'DC') 											"
			+ " and     vica.item_id = :itemId															", nativeQuery = true)	
	List<ItemClassificacaoAgricola> buscarItensParaRomaneio(Long itemId, LocalDate dataRomaneio);
}
