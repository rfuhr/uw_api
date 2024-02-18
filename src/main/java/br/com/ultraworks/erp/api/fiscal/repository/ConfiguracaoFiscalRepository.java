package br.com.ultraworks.erp.api.fiscal.repository;

import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscal;
import br.com.ultraworks.erp.core.UWRepository;

@Repository
public interface ConfiguracaoFiscalRepository extends UWRepository<ConfiguracaoFiscal, Long> {

}
