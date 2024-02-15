package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.cfop.Cfop;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface CfopRepository extends UWRepository<Cfop, Long> {

}
