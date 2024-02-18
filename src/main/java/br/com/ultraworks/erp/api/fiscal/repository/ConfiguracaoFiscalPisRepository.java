package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalpis.ConfiguracaoFiscalPis;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfiguracaoFiscalPisRepository extends UWRepository<ConfiguracaoFiscalPis, Long> {
	
	ConfiguracaoFiscalPis findByConfiguracaoFiscalId(Long configuracaoFiscalId);

}
