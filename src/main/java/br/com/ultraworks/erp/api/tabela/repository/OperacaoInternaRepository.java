package br.com.ultraworks.erp.api.tabela.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface OperacaoInternaRepository extends UWRepository<OperacaoInterna, Long> {

	@Query(value = "select distinct oi.id 																"
			+ " from   operacao_interna oi 																"
			+ "	   join operacao_interna_agricola oia on oia.operacao_interna_id = oi.id 				"
			+ "	   join valida_operacao_interna_agricola voia on voia.operacao_interna_id = oi.id 		"
			+ " where  oi.caracteristica_agricola = true 												"
			+ " and    oia.identificacao_documento_agricola in ('0', '1') 								"
			+ " and    current_timestamp between voia.data_inicio_vigencia and voia.data_final_vigencia "
			+ " and    voia.item_id = :itemId															"
			+ " and    voia.grupo_operacao_agricola_id = :grupoOperacaoAgricolaId						", nativeQuery = true)
	List<Long> buscarIdsOperacaoInterValidadoParaRomaneio(Long itemId, Long grupoOperacaoAgricolaId);
	
	@Query(value = "select distinct oi.id 																"
			+ " from   operacao_interna oi 																"
			+ "	   join operacao_interna_agricola oia on oia.operacao_interna_id = oi.id 				"
			+ " where  oi.caracteristica_agricola = true 												"
			+ " and    oia.identificacao_documento_agricola in ('0', '2') 								", nativeQuery = true)
	List<Long> buscarIdsOperacaoInterParaFixacao();
	
	@Query(value = "select distinct oi.id 																"
			+ " from   operacao_interna oi 																"
			+ "	   join operacao_interna_agricola oia on oia.operacao_interna_id = oi.id 				"
			+ "	   join valida_operacao_interna_agricola voia on voia.operacao_interna_id = oi.id 		"
			+ " where  oi.caracteristica_agricola = true 												"
			+ " and    oia.identificacao_documento_agricola in ('0', '3') 								"
			+ " and    current_timestamp between voia.data_inicio_vigencia and voia.data_final_vigencia "
			+ " and    voia.item_id = :itemId															"
			+ " and    voia.grupo_operacao_agricola_id = :grupoOperacaoAgricolaId						", nativeQuery = true)
	List<Long> buscarIdsOperacaoInterValidadoParaContrato(Long itemId, Long grupoOperacaoAgricolaId);
}
