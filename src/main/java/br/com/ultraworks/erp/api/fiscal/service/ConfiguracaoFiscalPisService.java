package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalpis.ConfiguracaoFiscalPis;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalpis.ConfiguracaoFiscalPisDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfiguracaoFiscalPisMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalPisRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfiguracaoFiscalPisService extends GenericService<ConfiguracaoFiscalPis, Long, ConfiguracaoFiscalPisDTO> {
	
	ConfiguracaoFiscalPisRepository repository;

	@Autowired
	public ConfiguracaoFiscalPisService(ConfiguracaoFiscalPisRepository repository, ConfiguracaoFiscalPisMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}
	
	public ConfiguracaoFiscalPis findByConfiguracaoFiscalId(Long configuracaoFiscalId) {
		return repository.findByConfiguracaoFiscalId(configuracaoFiscalId);
	}

}