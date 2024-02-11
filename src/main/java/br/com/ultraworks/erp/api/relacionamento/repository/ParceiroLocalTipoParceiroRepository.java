package br.com.ultraworks.erp.api.relacionamento.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTipoParceiro.ParceiroLocalTipoParceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ParceiroLocalTipoParceiroRepository extends UWRepository<ParceiroLocalTipoParceiro, Long> {
	
	List<ParceiroLocalTipoParceiro> findByParceiroLocalId(Long parceiroLocalId);
}
