package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalcofins.ConfiguracaoFiscalCofins;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfiguracaoFiscalCofinsRepository extends UWRepository<ConfiguracaoFiscalCofins, Long> {
	
	ConfiguracaoFiscalCofins findByConfiguracaoFiscalId(Long configuracaoFiscalId);

}
