package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.regimetributario.RegimeTributario;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface RegimeTributarioRepository extends UWRepository<RegimeTributario, Long> {

}
