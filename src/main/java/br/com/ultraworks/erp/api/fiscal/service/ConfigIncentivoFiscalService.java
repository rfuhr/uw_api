package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscal.ConfigIncentivoFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscal.ConfigIncentivoFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscalparceiro.ConfigIncentivoFiscalParceiro;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigIncentivoFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigIncentivoFiscalRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigIncentivoFiscalService extends GenericService<ConfigIncentivoFiscal, Long, ConfigIncentivoFiscalDTO> {

	private ConfigIncentivoFiscalParceiroService configIncentivoFiscalParceiroService;
	
	@Autowired
	public ConfigIncentivoFiscalService(ConfigIncentivoFiscalRepository repository, ConfigIncentivoFiscalMapper mapper,
			ConfigIncentivoFiscalParceiroService configIncentivoFiscalParceiroService) {
		super(repository, mapper);
		this.configIncentivoFiscalParceiroService = configIncentivoFiscalParceiroService;
	}
	
	@Override
	public ConfigIncentivoFiscal save(ConfigIncentivoFiscal entity) {
		
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

}