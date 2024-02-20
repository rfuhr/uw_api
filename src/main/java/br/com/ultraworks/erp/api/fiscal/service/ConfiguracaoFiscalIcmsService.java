package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalicms.ConfiguracaoFiscalIcms;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalicms.ConfiguracaoFiscalIcmsDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfiguracaoFiscalIcmsMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalIcmsRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfiguracaoFiscalIcmsService extends GenericService<ConfiguracaoFiscalIcms, Long, ConfiguracaoFiscalIcmsDTO> {
	
	ConfiguracaoFiscalIcmsRepository repository;

	@Autowired
	public ConfiguracaoFiscalIcmsService(ConfiguracaoFiscalIcmsRepository repository, ConfiguracaoFiscalIcmsMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}
	
	public ConfiguracaoFiscalIcms findByConfiguracaoFiscalId(Long configuracaoFiscalId) {
		return repository.findByConfiguracaoFiscalId(configuracaoFiscalId);
	}

}