package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfiguracaoFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfiguracaoFiscalService extends GenericService<ConfiguracaoFiscal, Long, ConfiguracaoFiscalDTO> {

	@Autowired
	public ConfiguracaoFiscalService(ConfiguracaoFiscalRepository repository, ConfiguracaoFiscalMapper mapper) {
		super(repository, mapper);
	}

}