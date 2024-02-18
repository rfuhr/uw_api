package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalicms.ConfiguracaoFiscalIcms;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfiguracaoFiscalIcmsRepository extends UWRepository<ConfiguracaoFiscalIcms, Long> {

	ConfiguracaoFiscalIcms findByConfiguracaoFiscalId(Long configuracaoFiscalId);
	
}
