package br.com.ultraworks.erp.api.organograma.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface EmpresaFilialRepository extends UWRepository<EmpresaFilial, Long> {
	
}
