package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalconfigfiscal.ConfigMensagemFiscalConfigFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalconfigfiscal.ConfigMensagemFiscalConfigFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigMensagemFiscalConfigFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalConfigFiscalRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMensagemFiscalConfigFiscalService extends GenericService<ConfigMensagemFiscalConfigFiscal, Long, ConfigMensagemFiscalConfigFiscalDTO> {

	ConfigMensagemFiscalConfigFiscalRepository repository;
	
	@Autowired
	public ConfigMensagemFiscalConfigFiscalService(ConfigMensagemFiscalConfigFiscalRepository repository, ConfigMensagemFiscalConfigFiscalMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}

	public List<ConfigMensagemFiscalConfigFiscal> getAllByConfigMensagemFiscal(Long id) {
		List<ConfigMensagemFiscalConfigFiscal> listRegistros = new ArrayList<>();

		repository.findByConfigMensagemFiscalId(id).forEach(configFiscal -> {
			listRegistros.add(configFiscal);
		});
		return listRegistros;
	}
	
}