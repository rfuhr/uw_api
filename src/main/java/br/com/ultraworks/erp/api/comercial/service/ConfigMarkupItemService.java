package br.com.ultraworks.erp.api.comercial.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupitem.ConfigMarkupItem;
import br.com.ultraworks.erp.api.comercial.domain.configmarkupitem.ConfigMarkupItemDTO;
import br.com.ultraworks.erp.api.comercial.domain.configmarkupitemindice.ConfigMarkupItemIndice;
import br.com.ultraworks.erp.api.comercial.mapper.ConfigMarkupItemMapper;
import br.com.ultraworks.erp.api.comercial.repository.ConfigMarkupItemRepository;
import br.com.ultraworks.erp.api.comercial.repository.query.VerificaDuplicidadeConfigMarkupItemQuery;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMarkupItemService extends GenericService<ConfigMarkupItem, Long, ConfigMarkupItemDTO> {

	ConfigMarkupItemIndiceService configConfigMarkupItemIndiceService;
	VerificaDuplicidadeConfigMarkupItemQuery verificaDuplicidadeConfigMarkupItemQuery;
	ConfigMarkupItemRepository repository;
	
	@Autowired
	public ConfigMarkupItemService(ConfigMarkupItemRepository repository, ConfigMarkupItemMapper mapper,
			ConfigMarkupItemIndiceService configConfigMarkupItemIndiceService,
			VerificaDuplicidadeConfigMarkupItemQuery verificaDuplicidadeConfigMarkupItemQuery) {
		super(repository, mapper);
		this.repository = repository;
		this.configConfigMarkupItemIndiceService = configConfigMarkupItemIndiceService;
		this.verificaDuplicidadeConfigMarkupItemQuery = verificaDuplicidadeConfigMarkupItemQuery;
	}
	
	@Override
	public Optional<ConfigMarkupItem> getById(Long id) {
		Optional<ConfigMarkupItem> registro = super.getById(id);
		if (registro.isPresent()) {
			registro.get().setConfigMarkupItemIndices(configConfigMarkupItemIndiceService.getAllByConfigMarkupItem(id));
		}
		return registro;
	}
	
	public ConfigMarkupItem buscaConfigMarkupItemVigente(Long itemId) {
		Long configMarkupItemId = repository.buscaConfigMarkupItemVigente(itemId);
		if (configMarkupItemId == null) {
			return null;
		}
		Optional<ConfigMarkupItem> confOptional = getById(configMarkupItemId);
		return confOptional.isPresent() ? confOptional.get() : null;
	}
	
	@Override
	public ConfigMarkupItem save(ConfigMarkupItem entity) {
		
		verificaDuplicidadeConfigMarkupItem(entity);
		
		List<ConfigMarkupItemIndice> dadosSalvosIndices = new ArrayList<>();
		if (entity.getId() != null) {
			dadosSalvosIndices = configConfigMarkupItemIndiceService.getAllByConfigMarkupItem(entity.getId());
		}
		
		repository.save(entity);
		
		if (entity.getConfigMarkupItemIndices() != null && entity.getConfigMarkupItemIndices().size() > 0) {
			entity.getConfigMarkupItemIndices().forEach(configParceiro -> {
				configParceiro.setConfigMarkupItem(entity);
				configParceiro = configConfigMarkupItemIndiceService.save(configParceiro);
			});
		}
		
		List<ConfigMarkupItemIndice> dadosParceirosExcluir = (List<ConfigMarkupItemIndice>) ListUtils
				.compararListasERetornaDiferenca(dadosSalvosIndices, entity.getConfigMarkupItemIndices());
		if (dadosParceirosExcluir.size() > 0) {
			dadosParceirosExcluir.forEach(local -> {
				configConfigMarkupItemIndiceService.delete(local.getId());
			});
		}
		
		return entity;
	}
	
	@Override
	public void delete(Long id) {
		Optional<ConfigMarkupItem> config = this.getById(id);
		if (config.isPresent()) {
			config.get().getConfigMarkupItemIndices().forEach(indices -> {
				configConfigMarkupItemIndiceService.delete(indices.getId());
			});
			repository.deleteById(id);
		}
	}

	private void verificaDuplicidadeConfigMarkupItem(ConfigMarkupItem entity) {
		this.verificaDuplicidadeConfigMarkupItemQuery.executeSQL(entity);
	}

}