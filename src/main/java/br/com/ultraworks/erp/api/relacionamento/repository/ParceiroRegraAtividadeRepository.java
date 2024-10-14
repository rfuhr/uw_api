package br.com.ultraworks.erp.api.relacionamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroRegraAtividade.ParceiroRegraAtividade;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ParceiroRegraAtividadeRepository extends UWRepository<ParceiroRegraAtividade, Long> {

	List<ParceiroRegraAtividade> findByParceiroId(Long parceiroId);
	
	@Query(value = 	"SELECT DISTINCT p.regra_atividade_id as id" +
            		"	FROM  parceiro_regra_atividade p  " +
            		" 	where p.parceiro_id = :parceiroId " +
            		"	and current_timestamp between data_inicio_vigencia and data_final_vigencia " +
            		"   union all " +
            		"SELECT distinct r.id from regra_atividade r where r.codigo = 0 ", nativeQuery = true)
	List<Long> buscarIdsRegrasByParceiro(Long parceiroId);
}
