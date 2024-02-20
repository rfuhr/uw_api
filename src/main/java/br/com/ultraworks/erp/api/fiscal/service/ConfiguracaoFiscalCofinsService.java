package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalcofins.ConfiguracaoFiscalCofins;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalcofins.ConfiguracaoFiscalCofinsDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfiguracaoFiscalCofinsMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalCofinsRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfiguracaoFiscalCofinsService extends GenericService<ConfiguracaoFiscalCofins, Long, ConfiguracaoFiscalCofinsDTO> {

	ConfiguracaoFiscalCofinsRepository repository;
	
	@Autowired
	public ConfiguracaoFiscalCofinsService(ConfiguracaoFiscalCofinsRepository repository, ConfiguracaoFiscalCofinsMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}
	
	public ConfiguracaoFiscalCofins findByConfiguracaoFiscalId(Long configuracaoFiscalId) {
		return repository.findByConfiguracaoFiscalId(configuracaoFiscalId);
	}

}