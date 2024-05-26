package br.com.ultraworks.erp.api.comercial.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.comercial.domain.configcalculoprecooperinterna.ConfigCalculoPrecoOperInterna;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigCalculoPrecoOperInternaRepository extends UWRepository<ConfigCalculoPrecoOperInterna, Long> {
	
	List<ConfigCalculoPrecoOperInterna> findByConfigCalculoPrecoId(Long configCalculoPrecoId);

}
