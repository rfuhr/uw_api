package br.com.ultraworks.erp.api.relacionamento.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTelefone.ParceiroLocalTelefone;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ParceiroLocalTelefoneRepository extends UWRepository<ParceiroLocalTelefone, Long> {
	
	List<ParceiroLocalTelefone> findByParceiroLocalId(Long parceiroLocalId);
}
