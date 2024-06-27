package br.com.ultraworks.erp.api.comercial.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitem.ConfigMarkupPlanoItem;
import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitem.ConfigMarkupPlanoItemDTO;
import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitemindice.ConfigMarkupPlanoItemIndice;
import br.com.ultraworks.erp.api.comercial.mapper.ConfigMarkupPlanoItemMapper;
import br.com.ultraworks.erp.api.comercial.repository.ConfigMarkupPlanoItemRepository;
import br.com.ultraworks.erp.api.comercial.repository.query.VerificaDuplicidadeConfigMarkupPlanoItemQuery;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMarkupPlanoItemService extends GenericService<ConfigMarkupPlanoItem, Long, ConfigMarkupPlanoItemDTO> {

	ConfigMarkupPlanoItemRepository repository;
	ConfigMarkupPlanoItemIndiceService configConfigMarkupPlanoItemIndiceService;
	VerificaDuplicidadeConfigMarkupPlanoItemQuery verificaDuplicidadeConfigMarkupPlanoItemQuery;
	
	@Autowired
	public ConfigMarkupPlanoItemService(ConfigMarkupPlanoItemRepository repository, ConfigMarkupPlanoItemMapper mapper,
			ConfigMarkupPlanoItemIndiceService configConfigMarkupPlanoItemIndiceService,
			VerificaDuplicidadeConfigMarkupPlanoItemQuery verificaDuplicidadeConfigMarkupPlanoItemQuery) {
		super(repository, mapper);
		this.repository = repository;
		this.configConfigMarkupPlanoItemIndiceService = configConfigMarkupPlanoItemIndiceService;
		this.verificaDuplicidadeConfigMarkupPlanoItemQuery = verificaDuplicidadeConfigMarkupPlanoItemQuery;
	}
	
	@Override
	public Optional<ConfigMarkupPlanoItem> getById(Long id) {
		Optional<ConfigMarkupPlanoItem> registro = super.getById(id);
		if (registro.isPresent()) {
			registro.get().setConfigMarkupPlanoItemIndices(configConfigMarkupPlanoItemIndiceService.getAllByConfigMarkupPlanoItem(id));
		}
		return registro;
	}
	
	public ConfigMarkupPlanoItem buscaConfigMarkupPlanoItemVigente(Long planoClassificacaoItemId) {
		Long configMarkupPlanoItemId = repository.buscaConfigMarkupPlanoItemVigente(planoClassificacaoItemId);
		if (configMarkupPlanoItemId == null) {
			return null;
		}
		Optional<ConfigMarkupPlanoItem> confOptional = getById(configMarkupPlanoItemId);
		return confOptional.isPresent() ? confOptional.get() : null;
	}
	
	@Override
	public ConfigMarkupPlanoItem save(ConfigMarkupPlanoItem entity) {
		
		verificaDuplicidadeConfigMarkupPlanoItem(entity);
		
		List<ConfigMarkupPlanoItemIndice> dadosSalvosIndices = new ArrayList<>();
		if (entity.getId() != null) {
			dadosSalvosIndices = configConfigMarkupPlanoItemIndiceService.getAllByConfigMarkupPlanoItem(entity.getId());
		}
		
		repository.save(entity);
		
		if (entity.getConfigMarkupPlanoItemIndices() != null && entity.getConfigMarkupPlanoItemIndices().size() > 0) {
			entity.getConfigMarkupPlanoItemIndices().forEach(configParceiro -> {
				configParceiro.setConfigMarkupPlanoItem(entity);
				configParceiro = configConfigMarkupPlanoItemIndiceService.save(configParceiro);
			});
		}
		
		List<ConfigMarkupPlanoItemIndice> dadosParceirosExcluir = (List<ConfigMarkupPlanoItemIndice>) ListUtils
				.compararListasERetornaDiferenca(dadosSalvosIndices, entity.getConfigMarkupPlanoItemIndices());
		if (dadosParceirosExcluir.size() > 0) {
			dadosParceirosExcluir.forEach(local -> {
				configConfigMarkupPlanoItemIndiceService.delete(local.getId());
			});
		}
		
		return entity;
	}
	
	@Override
	public void delete(Long id) {
		Optional<ConfigMarkupPlanoItem> config = this.getById(id);
		if (config.isPresent()) {
			config.get().getConfigMarkupPlanoItemIndices().forEach(indices -> {
				configConfigMarkupPlanoItemIndiceService.delete(indices.getId());
			});
			repository.deleteById(id);
		}
	}

	private void verificaDuplicidadeConfigMarkupPlanoItem(ConfigMarkupPlanoItem entity) {
		this.verificaDuplicidadeConfigMarkupPlanoItemQuery.executeSQL(entity);
	}

}