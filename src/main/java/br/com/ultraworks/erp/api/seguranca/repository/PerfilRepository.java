package br.com.ultraworks.erp.api.seguranca.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.seguranca.domain.perfil.Perfil;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface PerfilRepository extends UWRepository<Perfil, Long> {
	
}
