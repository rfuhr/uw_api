package br.com.ultraworks.erp.api.organograma.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface EmpresaRepository extends UWRepository<Empresa, Long> {
	
}
