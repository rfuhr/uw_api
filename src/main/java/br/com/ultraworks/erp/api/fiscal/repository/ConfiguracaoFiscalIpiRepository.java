package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalipi.ConfiguracaoFiscalIpi;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfiguracaoFiscalIpiRepository extends UWRepository<ConfiguracaoFiscalIpi, Long> {

}
