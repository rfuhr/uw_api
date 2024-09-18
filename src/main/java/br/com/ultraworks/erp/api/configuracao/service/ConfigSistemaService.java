package br.com.ultraworks.erp.api.configuracao.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistema;
import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistemaDTO;
import br.com.ultraworks.erp.api.configuracao.mapper.ConfigSistemaMapper;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigSistemaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigSistemaService extends GenericService<ConfigSistema, Long, ConfigSistemaDTO> {

	ConfigSistemaRepository repository;
	ConfigSistemaMapper mapper;
	private ConfigSistemaFinanceiroService configSistemaFinanceiroService;

	@Autowired
	public ConfigSistemaService(ConfigSistemaRepository repository, ConfigSistemaMapper mapper,
			ConfigSistemaFinanceiroService configSistemaFinanceiroService) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
		this.configSistemaFinanceiroService = configSistemaFinanceiroService;
	}

	@Override
	public Optional<ConfigSistema> getById(Long id) {
		Optional<ConfigSistema> configSistema = repository.findById(id);
		if (configSistema.isPresent()) {
			configSistema.get().setConfiguracoesFinanceiro(new ArrayList<>());
			configSistema.get().getConfiguracoesFinanceiro()
					.addAll(configSistemaFinanceiroService.getAllByConfigSistema(configSistema.get().getId()));
		}
		return configSistema;
	}

	@Override
	public ConfigSistema save(ConfigSistema entity) {
		ConfigSistema entitySaved = repository.save(entity);
		if (entity.getConfiguracoesFinanceiro() != null)
			entity.getConfiguracoesFinanceiro().forEach(csfin -> csfin.setConfigSistema(entitySaved));
		configSistemaFinanceiroService.persistList(entitySaved.getId(), entitySaved.getConfiguracoesFinanceiro());
		return entitySaved;
	}

	@Override
	public void delete(Long id) {
		Optional<ConfigSistema> optional = this.getById(id);
		if (optional.isPresent()) {
			configSistemaFinanceiroService.deleteList(optional.get().getConfiguracoesFinanceiro());
			repository.deleteById(id);
		}
	}
	
}