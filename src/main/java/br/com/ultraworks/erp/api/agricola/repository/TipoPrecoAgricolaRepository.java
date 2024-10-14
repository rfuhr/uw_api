package br.com.ultraworks.erp.api.agricola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.tipoprecoagricola.TipoPrecoAgricola;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface TipoPrecoAgricolaRepository extends UWRepository<TipoPrecoAgricola, Long> {

	@Query(value = "SELECT proximo_codigo('tipo_preco_agricola', 'codigo')", nativeQuery = true)
	int getProximoCodigo();
	
	@Query(value = "select distinct fg.id 																	"
			+ " from   tipo_preco_agricola fg 																"
			+ "	   	   join valida_operacao_interna_agricola voia on voia.tipo_preco_agricola_id = fg.id 	"
			+ " where  current_timestamp between voia.data_inicio_vigencia and voia.data_final_vigencia 	"
			+ " and    voia.item_id = :itemId																"
			+ " and    voia.operacao_interna_id = :operacaoInternaId										"
			+ " and    voia.grupo_operacao_agricola_id = :grupoOperacaoAgricolaId							", nativeQuery = true)
	List<Long> buscarIdsTipoPrecoValidadoParaOperacaoAgricola(Long itemId, Long grupoOperacaoAgricolaId,
			Long operacaoInternaId);
}
