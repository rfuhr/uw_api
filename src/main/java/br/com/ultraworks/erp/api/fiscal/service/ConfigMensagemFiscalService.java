package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.ConfigMensagemFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.ConfigMensagemFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalconfigfiscal.ConfigMensagemFiscalConfigFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalsituactrib.ConfigMensagemFiscalSituacTrib;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigMensagemFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMensagemFiscalService extends GenericService<ConfigMensagemFiscal, Long, ConfigMensagemFiscalDTO> {

	private ConfigMensagemFiscalConfigFiscalService configMensagemFiscalConfigFiscalService;
	private ConfigMensagemFiscalSituacTribService configMensagemFiscalSituacTribService;
	
	@Autowired
	public ConfigMensagemFiscalService(ConfigMensagemFiscalRepository repository, ConfigMensagemFiscalMapper mapper,
			ConfigMensagemFiscalConfigFiscalService configMensagemFiscalConfigFiscalService,
			ConfigMensagemFiscalSituacTribService configMensagemFiscalSituacTribService) {
		super(repository, mapper);
		this.configMensagemFiscalConfigFiscalService = configMensagemFiscalConfigFiscalService;
		this.configMensagemFiscalSituacTribService = configMensagemFiscalSituacTribService;
	}
	
	@Override
	public ConfigMensagemFiscal save(ConfigMensagemFiscal entity) {
		
		List<ConfigMensagemFiscalConfigFiscal> dadosSalvosConfigFiscais = new ArrayList<>();
		if (entity.getId() != null) {
			dadosSalvosConfigFiscais = configMensagemFiscalConfigFiscalService.getAllByConfigMensagemFiscal(entity.getId());
		}
		
		List<ConfigMensagemFiscalSituacTrib> dadosSalvosSituacTribs = new ArrayList<>();
		if (entity.getId() != null) {
			dadosSalvosSituacTribs = configMensagemFiscalSituacTribService.getAllByConfigMensagemFiscal(entity.getId());
		}
		
		repository.save(entity);
		
		if (entity.getConfigMensagemFiscalConfigFiscals() != null && entity.getConfigMensagemFiscalConfigFiscals().size() > 0) {
			entity.getConfigMensagemFiscalConfigFiscals().forEach(configFiscal -> {
				configFiscal.setConfigMensagemFiscal(entity);
				configFiscal = configMensagemFiscalConfigFiscalService.save(configFiscal);
			});
		}
		
		if (entity.getConfigMensagemFiscalSituacTribs() != null && entity.getConfigMensagemFiscalSituacTribs().size() > 0) {
			entity.getConfigMensagemFiscalSituacTribs().forEach(situacTrib -> {
				situacTrib.setConfigMensagemFiscal(entity);
				situacTrib = configMensagemFiscalSituacTribService.save(situacTrib);
			});
		}
		
		List<ConfigMensagemFiscalConfigFiscal> dadosConfigFiscaisExcluir = (List<ConfigMensagemFiscalConfigFiscal>) ListUtils
				.compararListasERetornaDiferenca(dadosSalvosConfigFiscais, entity.getConfigMensagemFiscalConfigFiscals());
		if (dadosConfigFiscaisExcluir.size() > 0) {
			dadosConfigFiscaisExcluir.forEach(local -> {
				configMensagemFiscalConfigFiscalService.delete(local.getId());
			});
		}
		
		List<ConfigMensagemFiscalSituacTrib> dadosSituacTribsExcluir = (List<ConfigMensagemFiscalSituacTrib>) ListUtils
				.compararListasERetornaDiferenca(dadosSalvosSituacTribs, entity.getConfigMensagemFiscalSituacTribs());
		if (dadosSituacTribsExcluir.size() > 0) {
			dadosSituacTribsExcluir.forEach(local -> {
				configMensagemFiscalSituacTribService.delete(local.getId());
			});
		}
		
		return entity;
	}

}