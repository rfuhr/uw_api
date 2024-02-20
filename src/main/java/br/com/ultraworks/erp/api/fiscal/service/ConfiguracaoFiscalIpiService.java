package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalipi.ConfiguracaoFiscalIpi;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalipi.ConfiguracaoFiscalIpiDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfiguracaoFiscalIpiMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalIpiRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfiguracaoFiscalIpiService extends GenericService<ConfiguracaoFiscalIpi, Long, ConfiguracaoFiscalIpiDTO> {
	
	ConfiguracaoFiscalIpiRepository repository;

	@Autowired
	public ConfiguracaoFiscalIpiService(ConfiguracaoFiscalIpiRepository repository, ConfiguracaoFiscalIpiMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}
	
	public ConfiguracaoFiscalIpi findByConfiguracaoFiscalId(Long configuracaoFiscalId) {
		return repository.findByConfiguracaoFiscalId(configuracaoFiscalId);
	}

}