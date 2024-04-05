package br.com.ultraworks.erp.api.configuracao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.configuracao.domain.configempresa.ConfigEmpresa;
import br.com.ultraworks.erp.api.configuracao.domain.configempresa.ConfigEmpresaDTO;
import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFe;
import br.com.ultraworks.erp.api.configuracao.mapper.ConfigEmpresaMapper;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigEmpresaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigEmpresaService extends GenericService<ConfigEmpresa, Long, ConfigEmpresaDTO> {

	ConfigEmpresaRepository repository;
	ConfigEmpresaMapper mapper;
	private ConfigEmpresaNFeService configEmpresaNFeService;

	@Autowired
	public ConfigEmpresaService(ConfigEmpresaRepository repository, ConfigEmpresaMapper mapper,
			ConfigEmpresaNFeService configEmpresaNFeService) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
		this.configEmpresaNFeService = configEmpresaNFeService;
	}

	@Override
	public Optional<ConfigEmpresa> getById(Long id) {
		Optional<ConfigEmpresa> configEmpresa = repository.findById(id);
		if (configEmpresa.isPresent()) {
			configEmpresa.get().setConfiguracoesNFe(new ArrayList<>());
			configEmpresa.get().getConfiguracoesNFe()
					.addAll(configEmpresaNFeService.getAllByConfigEmpresa(configEmpresa.get().getId()));
		}
		return configEmpresa;
	}

	@Override
	public ConfigEmpresa save(ConfigEmpresa entity) {
		List<ConfigEmpresaNFe> configuracoesNFeSalvos = new ArrayList<ConfigEmpresaNFe>();
		if (entity.getId() != null) {
			configuracoesNFeSalvos = configEmpresaNFeService.getAllByConfigEmpresa(entity.getId());
		}
		repository.save(entity);

		if (entity.getConfiguracoesNFe() != null && entity.getConfiguracoesNFe().size() > 0) {
			entity.getConfiguracoesNFe().forEach(config -> {
				config.setConfigEmpresa(entity);
				config = configEmpresaNFeService.save(config);
			});
		}

		List<ConfigEmpresaNFe> configuracoesNFeExcluir = (List<ConfigEmpresaNFe>) ListUtils
				.compararListasERetornaDiferenca(configuracoesNFeSalvos, entity.getConfiguracoesNFe());
		if (configuracoesNFeExcluir.size() > 0) {
			configuracoesNFeExcluir.forEach(config -> {
				configEmpresaNFeService.delete(config.getId());
			});
		}

		return entity;
	}

	@Override
	public void delete(Long id) {
		Optional<ConfigEmpresa> configEmpresa = this.getById(id);
		if (configEmpresa.isPresent()) {
			configEmpresa.get().getConfiguracoesNFe().forEach(config -> {
				configEmpresaNFeService.delete(config.getId());
			});
			repository.deleteById(id);
		}
	}
	
	public Optional<ConfigEmpresa> getByEmpresaId(Long empresaId) {
		return repository.findByEmpresaId(empresaId);
	}

}