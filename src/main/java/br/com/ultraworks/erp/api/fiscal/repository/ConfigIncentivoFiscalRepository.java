package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscal.ConfigIncentivoFiscal;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfigIncentivoFiscalRepository extends UWRepository<ConfigIncentivoFiscal, Long> {

}
