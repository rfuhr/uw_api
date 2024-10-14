package br.com.ultraworks.erp.api.agricola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.predefinicaoprecoagricola.PredefinicaoPrecoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface PredefinicaoPrecoAgricolaRepository extends UWRepository<PredefinicaoPrecoAgricola, Long> {

	@Query(value = "SELECT proximo_codigo('predefinicao_preco_agricola', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
	
	@Query(value = "select distinct p.id 																	"
			+ " from   predefinicao_preco_agricola p 														"
			+ "	   	   join valida_preco_agricola_item vpai on vpai.predefinicao_preco_agricola_id = p.id 	"
			+ " where  current_timestamp between vpai.data_inicio_vigencia and vpai.data_final_vigencia 	"
			+ " and    vpai.item_id = :itemId																"
			+ " and    vpai.tipo_preco_agricola_id = :tipoPrecoAgricolaId									", nativeQuery = true)
	List<Long> buscarIdsPredefinicaoValidadoParaOperacaoAgricola(Long itemId, Long tipoPrecoAgricolaId);
}
