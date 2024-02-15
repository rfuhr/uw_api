package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.ncm.Ncm;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface NcmRepository extends UWRepository<Ncm, Long> {

}
