package br.com.ultraworks.erp.api.relacionamento.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalFisica.ParceiroLocalFisica;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ParceiroLocalFisicaRepository extends UWRepository<ParceiroLocalFisica, Long> {
	
	List<ParceiroLocalFisica> findByParceiroLocalId(Long parceiroLocalId);
}
