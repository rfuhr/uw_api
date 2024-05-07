package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscal.ConfigIncentivoFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscal.ConfigIncentivoFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscalparceiro.ConfigIncentivoFiscalParceiro;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigIncentivoFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigIncentivoFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.query.VerificaDuplicidadeConfigIncentivoFiscalQuery;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigIncentivoFiscalService extends GenericService<ConfigIncentivoFiscal, Long, ConfigIncentivoFiscalDTO> {

	ConfigIncentivoFiscalParceiroService configIncentivoFiscalParceiroService;
	VerificaDuplicidadeConfigIncentivoFiscalQuery verificaDuplicidadeConfigIncentivoFiscalQuery;
	
	@Autowired
	public ConfigIncentivoFiscalService(ConfigIncentivoFiscalRepository repository, ConfigIncentivoFiscalMapper mapper,
			ConfigIncentivoFiscalParceiroService configIncentivoFiscalParceiroService,
			VerificaDuplicidadeConfigIncentivoFiscalQuery verificaDuplicidadeConfigIncentivoFiscalQuery) {
		super(repository, mapper);
		this.configIncentivoFiscalParceiroService = configIncentivoFiscalParceiroService;
		this.verificaDuplicidadeConfigIncentivoFiscalQuery = verificaDuplicidadeConfigIncentivoFiscalQuery;
	}
	
	@Override
	public Optional<ConfigIncentivoFiscal> getById(Long id) {
		Optional<ConfigIncentivoFiscal> registro = super.getById(id);
		if (registro.isPresent()) {
			registro.get().setConfigIncentivoFiscalParceiros(configIncentivoFiscalParceiroService.getAllByConfigIncentivoFiscal(id));
		}
		return registro;
	}
	
	@Override
	public ConfigIncentivoFiscal save(ConfigIncentivoFiscal entity) {
		
		verificaDuplicidadeConfigIncentivoFiscal(entity);
		
		List<ConfigIncentivoFiscalParceiro> dadosSalvosParceiros = new ArrayList<>();
		if (entity.getId() != null) {
			dadosSalvosParceiros = configIncentivoFiscalParceiroService.getAllByConfigIncentivoFiscal(entity.getId());
		}
		
		repository.save(entity);
		
		if (entity.getConfigIncentivoFiscalParceiros() != null && entity.getConfigIncentivoFiscalParceiros().size() > 0) {
			entity.getConfigIncentivoFiscalParceiros().forEach(configParceiro -> {
				configParceiro.setConfigIncentivoFiscal(entity);
				configParceiro = configIncentivoFiscalParceiroService.save(configParceiro);
			});
		}
		
		List<ConfigIncentivoFiscalParceiro> dadosParceirosExcluir = (List<ConfigIncentivoFiscalParceiro>) ListUtils
				.compararListasERetornaDiferenca(dadosSalvosParceiros, entity.getConfigIncentivoFiscalParceiros());
		if (dadosParceirosExcluir.size() > 0) {
			dadosParceirosExcluir.forEach(local -> {
				configIncentivoFiscalParceiroService.delete(local.getId());
			});
		}
		
		return entity;
	}
	
	@Override
	public void delete(Long id) {
		Optional<ConfigIncentivoFiscal> configIncentivoFiscal = this.getById(id);
		if (configIncentivoFiscal.isPresent()) {
			configIncentivoFiscal.get().getConfigIncentivoFiscalParceiros().forEach(parceiros -> {
				configIncentivoFiscalParceiroService.delete(parceiros.getId());
			});
			repository.deleteById(id);
		}
	}

	private void verificaDuplicidadeConfigIncentivoFiscal(ConfigIncentivoFiscal entity) {
		this.verificaDuplicidadeConfigIncentivoFiscalQuery.executeSQL(entity);
	}

}