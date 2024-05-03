package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFe;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface NFeRepository extends UWRepository<NFe, Long> {

}
