package br.com.ultraworks.erp.api.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface FatoGeradorRepository extends UWRepository<FatoGerador, Long> {

	@Query(value = "SELECT proximo_codigo('fato_gerador', 'codigo')", nativeQuery = true)
	int getProximoCodigo();

	@Query(value = "select distinct fg.id 																	"
			+ " from   fato_gerador fg 																		"
			+ "	   	   join valida_operacao_interna_agricola voia on voia.fato_gerador_contrato_id = fg.id 	"
			+ " where  current_timestamp between voia.data_inicio_vigencia and voia.data_final_vigencia 	"
			+ " and    voia.item_id = :itemId																"
			+ " and    voia.operacao_interna_id = :operacaoInternaId										"
			+ " and    voia.grupo_operacao_agricola_id = :grupoOperacaoAgricolaId							", nativeQuery = true)
	List<Long> buscarIdsFatoGeradorValidadoParaContrato(Long itemId, Long grupoOperacaoAgricolaId,
			Long operacaoInternaId);
}
