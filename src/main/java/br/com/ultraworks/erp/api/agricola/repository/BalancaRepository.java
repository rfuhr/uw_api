package br.com.ultraworks.erp.api.agricola.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.balanca.Balanca;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface BalancaRepository extends UWRepository<Balanca, Long> {
	
	Balanca findByEmpresaFilialId(Long empresaFilialId);
}
