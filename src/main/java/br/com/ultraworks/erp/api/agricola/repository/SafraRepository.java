package br.com.ultraworks.erp.api.agricola.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.agricola.domain.safra.Safra;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface SafraRepository extends UWRepository<Safra, Long> {

}
