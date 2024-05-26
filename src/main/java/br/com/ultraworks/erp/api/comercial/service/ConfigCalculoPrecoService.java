package br.com.ultraworks.erp.api.comercial.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.configcalculopreco.ConfigCalculoPreco;
import br.com.ultraworks.erp.api.comercial.domain.configcalculopreco.ConfigCalculoPrecoDTO;
import br.com.ultraworks.erp.api.comercial.domain.configcalculoprecooperinterna.ConfigCalculoPrecoOperInterna;
import br.com.ultraworks.erp.api.comercial.mapper.ConfigCalculoPrecoMapper;
import br.com.ultraworks.erp.api.comercial.repository.ConfigCalculoPrecoRepository;
import br.com.ultraworks.erp.api.comercial.repository.query.VerificaDuplicidadeConfigCalculoPrecoQuery;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigCalculoPrecoService extends GenericService<ConfigCalculoPreco, Long, ConfigCalculoPrecoDTO> {

	ConfigCalculoPrecoOperInternaService configCalculoPrecoOperInternaService;
	VerificaDuplicidadeConfigCalculoPrecoQuery verificaDuplicidadeConfigCalculoPrecoQuery;
	
	@Autowired
	public ConfigCalculoPrecoService(ConfigCalculoPrecoRepository repository, ConfigCalculoPrecoMapper mapper,
			ConfigCalculoPrecoOperInternaService configCalculoPrecoOperInternaService,
			VerificaDuplicidadeConfigCalculoPrecoQuery verificaDuplicidadeConfigCalculoPrecoQuery) {
		super(repository, mapper);
		this.configCalculoPrecoOperInternaService = configCalculoPrecoOperInternaService;
		this.verificaDuplicidadeConfigCalculoPrecoQuery = verificaDuplicidadeConfigCalculoPrecoQuery;
	}
	
	@Override
	public Optional<ConfigCalculoPreco> getById(Long id) {
		Optional<ConfigCalculoPreco> registro = super.getById(id);
		if (registro.isPresent()) {
			registro.get().setConfigCalculoPrecoOperInternas(configCalculoPrecoOperInternaService.getAllByConfigCalculoPreco(id));
		}
		return registro;
	}
	
	@Override
	public ConfigCalculoPreco save(ConfigCalculoPreco entity) {
		
		verificaDuplicidadeConfigCalculoPreco(entity);
		
		List<ConfigCalculoPrecoOperInterna> dadosSalvosIndices = new ArrayList<>();
		if (entity.getId() != null) {
			dadosSalvosIndices = configCalculoPrecoOperInternaService.getAllByConfigCalculoPreco(entity.getId());
		}
		
		repository.save(entity);
		
		if (entity.getConfigCalculoPrecoOperInternas() != null && entity.getConfigCalculoPrecoOperInternas().size() > 0) {
			entity.getConfigCalculoPrecoOperInternas().forEach(configParceiro -> {
				configParceiro.setConfigCalculoPreco(entity);
				configParceiro = configCalculoPrecoOperInternaService.save(configParceiro);
			});
		}
		
		List<ConfigCalculoPrecoOperInterna> dadosParceirosExcluir = (List<ConfigCalculoPrecoOperInterna>) ListUtils
				.compararListasERetornaDiferenca(dadosSalvosIndices, entity.getConfigCalculoPrecoOperInternas());
		if (dadosParceirosExcluir.size() > 0) {
			dadosParceirosExcluir.forEach(local -> {
				configCalculoPrecoOperInternaService.delete(local.getId());
			});
		}
		
		return entity;
	}
	
	@Override
	public void delete(Long id) {
		Optional<ConfigCalculoPreco> config = this.getById(id);
		if (config.isPresent()) {
			config.get().getConfigCalculoPrecoOperInternas().forEach(indices -> {
				configCalculoPrecoOperInternaService.delete(indices.getId());
			});
			repository.deleteById(id);
		}
	}

	private void verificaDuplicidadeConfigCalculoPreco(ConfigCalculoPreco entity) {
		this.verificaDuplicidadeConfigCalculoPrecoQuery.executeSQL(entity);
	}

}