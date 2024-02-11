package br.com.ultraworks.erp.api.relacionamento.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiro.Parceiro;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ParceiroRepository extends UWRepository<Parceiro, Long> {
	
}
