package br.com.ultraworks.erp.api.relacionamento.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalFisica.ParceiroLocalFisica;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalJuridica.ParceiroLocalJuridica;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ParceiroLocalJuridicaRepository extends UWRepository<ParceiroLocalJuridica, Long> {
	
	List<ParceiroLocalJuridica> findByParceiroLocalId(Long parceiroLocalId);
}
