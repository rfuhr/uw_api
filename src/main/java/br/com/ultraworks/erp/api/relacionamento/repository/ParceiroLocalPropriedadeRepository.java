package br.com.ultraworks.erp.api.relacionamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalPropriedade.ParceiroLocalPropriedade;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ParceiroLocalPropriedadeRepository extends UWRepository<ParceiroLocalPropriedade, Long> {

	List<ParceiroLocalPropriedade> findByParceiroLocalId(Long parceiroLocalId);
	
	@Query(value = "SELECT DISTINCT plp.id " +
            "FROM parceiro_local_propriedade plp " +
            "WHERE plp.parceiro_local_id = ?1", nativeQuery = true)
	List<Long> buscarIdsPropriedadesPeloParceiroLocal(Long parceiroLocalId);	
}
