package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.ConfigMensagemFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.ConfigMensagemFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.MensagemFiscalNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.MensagemFiscalNFeResponse;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalconfigfiscal.ConfigMensagemFiscalConfigFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalgrupotrib.ConfigMensagemFiscalGrupoTrib;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalitem.ConfigMensagemFiscalItem;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaloperinterna.ConfigMensagemFiscalOperInterna;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalsituactrib.ConfigMensagemFiscalSituacTrib;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaltipoincentfiscal.ConfigMensagemFiscalTipoIncentFiscal;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigMensagemFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.query.VerificaDuplicidadeConfigMensagemFiscalQuery;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMensagemFiscalService extends GenericService<ConfigMensagemFiscal, Long, ConfigMensagemFiscalDTO> {

	ConfigMensagemFiscalConfigFiscalService configMensagemFiscalConfigFiscalService;
	ConfigMensagemFiscalSituacTribService configMensagemFiscalSituacTribService;
	ConfigMensagemFiscalItemService configMensagemFiscalItemService;
	ConfigMensagemFiscalGrupoTribService configMensagemFiscalGrupoTribService;
	ConfigMensagemFiscalOperInternaService configMensagemFiscalOperInternaService;
	ConfigMensagemFiscalTipoIncentFiscalService configMensagemFiscalTipoIncentFiscalService;
	VerificaDuplicidadeConfigMensagemFiscalQuery verificaDuplicidadeConfigMensagemFiscalQuery;
	
	@Autowired
	public ConfigMensagemFiscalService(ConfigMensagemFiscalRepository repository, ConfigMensagemFiscalMapper mapper,
			ConfigMensagemFiscalConfigFiscalService configMensagemFiscalConfigFiscalService,
			ConfigMensagemFiscalSituacTribService configMensagemFiscalSituacTribService,
			ConfigMensagemFiscalItemService configMensagemFiscalItemService,
			ConfigMensagemFiscalGrupoTribService configMensagemFiscalGrupoTribService,
			ConfigMensagemFiscalOperInternaService configMensagemFiscalOperInternaService,
			ConfigMensagemFiscalTipoIncentFiscalService configMensagemFiscalTipoIncentFiscalService,
			VerificaDuplicidadeConfigMensagemFiscalQuery verificaDuplicidadeConfigMensagemFiscalQuery) {
		super(repository, mapper);
		this.configMensagemFiscalConfigFiscalService = configMensagemFiscalConfigFiscalService;
		this.configMensagemFiscalSituacTribService = configMensagemFiscalSituacTribService;
		this.configMensagemFiscalItemService = configMensagemFiscalItemService;
		this.configMensagemFiscalGrupoTribService = configMensagemFiscalGrupoTribService;
		this.configMensagemFiscalOperInternaService = configMensagemFiscalOperInternaService;
		this.configMensagemFiscalTipoIncentFiscalService = configMensagemFiscalTipoIncentFiscalService;
		this.verificaDuplicidadeConfigMensagemFiscalQuery = verificaDuplicidadeConfigMensagemFiscalQuery;
	}
	
	@Override
	public Optional<ConfigMensagemFiscal> getById(Long id) {
		Optional<ConfigMensagemFiscal> registro = super.getById(id);
		if (registro.isPresent()) {
			registro.get().setConfigMensagemFiscalConfigFiscals(configMensagemFiscalConfigFiscalService.getAllByConfigMensagemFiscal(id));
			registro.get().setConfigMensagemFiscalSituacTribs(configMensagemFiscalSituacTribService.getAllByConfigMensagemFiscal(id));
			registro.get().setConfigMensagemFiscalItems(configMensagemFiscalItemService.getAllByConfigMensagemFiscal(id));
			registro.get().setConfigMensagemFiscalGrupoTribs(configMensagemFiscalGrupoTribService.getAllByConfigMensagemFiscal(id));
			registro.get().setConfigMensagemFiscalOperInternas(configMensagemFiscalOperInternaService.getAllByConfigMensagemFiscal(id));
			registro.get().setConfigMensagemFiscalTipoIncentFiscals(configMensagemFiscalTipoIncentFiscalService.getAllByConfigMensagemFiscal(id));
		}
		return registro;
	}
	
	@Override
	public ConfigMensagemFiscal save(ConfigMensagemFiscal entity) {
		
		verificaDuplicidadeConfigMensagemFiscal(entity);
		
		List<ConfigMensagemFiscalConfigFiscal> dadosSalvosConfigFiscais = new ArrayList<>();
		if (entity.getId() != null) {
			dadosSalvosConfigFiscais = configMensagemFiscalConfigFiscalService.getAllByConfigMensagemFiscal(entity.getId());
		}
		
		List<ConfigMensagemFiscalSituacTrib> dadosSalvosSituacTribs = new ArrayList<>();
		if (entity.getId() != null) {
			dadosSalvosSituacTribs = configMensagemFiscalSituacTribService.getAllByConfigMensagemFiscal(entity.getId());
		}
		
		List<ConfigMensagemFiscalItem> dadosSalvosItems = new ArrayList<>();
		if (entity.getId() != null) {
			dadosSalvosItems = configMensagemFiscalItemService.getAllByConfigMensagemFiscal(entity.getId());
		}
		
		List<ConfigMensagemFiscalGrupoTrib> dadosSalvosGrupoTribs = new ArrayList<>();
		if (entity.getId() != null) {
			dadosSalvosGrupoTribs = configMensagemFiscalGrupoTribService.getAllByConfigMensagemFiscal(entity.getId());
		}
		
		List<ConfigMensagemFiscalOperInterna> dadosSalvosOperInternas = new ArrayList<>();
		if (entity.getId() != null) {
			dadosSalvosOperInternas = configMensagemFiscalOperInternaService.getAllByConfigMensagemFiscal(entity.getId());
		}
		
		List<ConfigMensagemFiscalTipoIncentFiscal> dadosSalvosTipoIncentFiscals = new ArrayList<>();
		if (entity.getId() != null) {
			dadosSalvosTipoIncentFiscals = configMensagemFiscalTipoIncentFiscalService.getAllByConfigMensagemFiscal(entity.getId());
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
		
		if (entity.getConfigMensagemFiscalItems() != null && entity.getConfigMensagemFiscalItems().size() > 0) {
			entity.getConfigMensagemFiscalItems().forEach(item -> {
				item.setConfigMensagemFiscal(entity);
				item = configMensagemFiscalItemService.save(item);
			});
		}
		
		if (entity.getConfigMensagemFiscalGrupoTribs() != null && entity.getConfigMensagemFiscalGrupoTribs().size() > 0) {
			entity.getConfigMensagemFiscalGrupoTribs().forEach(grupoTrib -> {
				grupoTrib.setConfigMensagemFiscal(entity);
				grupoTrib = configMensagemFiscalGrupoTribService.save(grupoTrib);
			});
		}
		
		if (entity.getConfigMensagemFiscalOperInternas() != null && entity.getConfigMensagemFiscalOperInternas().size() > 0) {
			entity.getConfigMensagemFiscalOperInternas().forEach(operInterna -> {
				operInterna.setConfigMensagemFiscal(entity);
				operInterna = configMensagemFiscalOperInternaService.save(operInterna);
			});
		}
		
		if (entity.getConfigMensagemFiscalTipoIncentFiscals() != null && entity.getConfigMensagemFiscalTipoIncentFiscals().size() > 0) {
			entity.getConfigMensagemFiscalTipoIncentFiscals().forEach(tipoIncentFiscal -> {
				tipoIncentFiscal.setConfigMensagemFiscal(entity);
				tipoIncentFiscal = configMensagemFiscalTipoIncentFiscalService.save(tipoIncentFiscal);
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
		
		List<ConfigMensagemFiscalItem> dadosItemsExcluir = (List<ConfigMensagemFiscalItem>) ListUtils
				.compararListasERetornaDiferenca(dadosSalvosItems, entity.getConfigMensagemFiscalItems());
		if (dadosItemsExcluir.size() > 0) {
			dadosItemsExcluir.forEach(local -> {
				configMensagemFiscalItemService.delete(local.getId());
			});
		}
		
		List<ConfigMensagemFiscalGrupoTrib> dadosGrupoTribsExcluir = (List<ConfigMensagemFiscalGrupoTrib>) ListUtils
				.compararListasERetornaDiferenca(dadosSalvosGrupoTribs, entity.getConfigMensagemFiscalGrupoTribs());
		if (dadosGrupoTribsExcluir.size() > 0) {
			dadosGrupoTribsExcluir.forEach(local -> {
				configMensagemFiscalGrupoTribService.delete(local.getId());
			});
		}
		
		List<ConfigMensagemFiscalOperInterna> dadosOperInternasExcluir = (List<ConfigMensagemFiscalOperInterna>) ListUtils
				.compararListasERetornaDiferenca(dadosSalvosOperInternas, entity.getConfigMensagemFiscalOperInternas());
		if (dadosOperInternasExcluir.size() > 0) {
			dadosOperInternasExcluir.forEach(local -> {
				configMensagemFiscalOperInternaService.delete(local.getId());
			});
		}
		
		List<ConfigMensagemFiscalTipoIncentFiscal> dadosTipoIncentFiscalsExcluir = (List<ConfigMensagemFiscalTipoIncentFiscal>) ListUtils
				.compararListasERetornaDiferenca(dadosSalvosTipoIncentFiscals, entity.getConfigMensagemFiscalTipoIncentFiscals());
		if (dadosTipoIncentFiscalsExcluir.size() > 0) {
			dadosTipoIncentFiscalsExcluir.forEach(local -> {
				configMensagemFiscalTipoIncentFiscalService.delete(local.getId());
			});
		}
		
		return entity;
	}

	private void verificaDuplicidadeConfigMensagemFiscal(ConfigMensagemFiscal entity) {
		verificaDuplicidadeConfigMensagemFiscalQuery.executeSQL(entity);
	}
	
	@Override
	public void delete(Long id) {
		Optional<ConfigMensagemFiscal> configMensagemFiscal = this.getById(id);
		if (configMensagemFiscal.isPresent()) {
			configMensagemFiscal.get().getConfigMensagemFiscalConfigFiscals().forEach(registro -> {
				configMensagemFiscalConfigFiscalService.delete(registro.getId());
			});
			configMensagemFiscal.get().getConfigMensagemFiscalSituacTribs().forEach(registro -> {
				configMensagemFiscalSituacTribService.delete(registro.getId());
			});
			configMensagemFiscal.get().getConfigMensagemFiscalItems().forEach(registro -> {
				configMensagemFiscalItemService.delete(registro.getId());
			});
			configMensagemFiscal.get().getConfigMensagemFiscalGrupoTribs().forEach(registro -> {
				configMensagemFiscalGrupoTribService.delete(registro.getId());
			});
			configMensagemFiscal.get().getConfigMensagemFiscalOperInternas().forEach(registro -> {
				configMensagemFiscalOperInternaService.delete(registro.getId());
			});
			configMensagemFiscal.get().getConfigMensagemFiscalTipoIncentFiscals().forEach(registro -> {
				configMensagemFiscalTipoIncentFiscalService.delete(registro.getId());
			});
			repository.deleteById(id);
		}
	}
	
	public MensagemFiscalNFeResponse buscaMensagensFiscaisParaNFe(MensagemFiscalNFeRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}