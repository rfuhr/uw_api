package br.com.ultraworks.erp.api.fiscal.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.ConfigMensagemFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.ConfigMensagemFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.MensagemFiscalRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigMensagemFiscalMapper extends GenericMapper<ConfigMensagemFiscal, ConfigMensagemFiscalDTO> {

	MensagemFiscalRepository mensagemFiscalRepository;
	ConfigMensagemFiscalConfigFiscalMapper configMensagemFiscalConfigFiscalMapper;
	ConfigMensagemFiscalSituacTribMapper configMensagemFiscalSituacTribMapper;
	ConfigMensagemFiscalGrupoTribMapper configMensagemFiscalGrupoTribMapper;
	ConfigMensagemFiscalItemMapper configMensagemFiscalItemMapper;
	ConfigMensagemFiscalOperInternaMapper configMensagemFiscalOperInternaMapper;
	ConfigMensagemFiscalTipoIncentFiscalMapper configMensagemFiscalTipoIncentFiscalMapper;
	
	public ConfigMensagemFiscalMapper(ConfigMensagemFiscalRepository ConfigMensagemFiscalRepository, 
			MensagemFiscalRepository mensagemFiscalRepository,
			ConfigMensagemFiscalConfigFiscalMapper configMensagemFiscalConfigFiscalMapper,
			ConfigMensagemFiscalSituacTribMapper configMensagemFiscalSituacTribMapper,
			ConfigMensagemFiscalGrupoTribMapper configMensagemFiscalGrupoTribMapper,
			ConfigMensagemFiscalItemMapper configMensagemFiscalItemMapper,
			ConfigMensagemFiscalOperInternaMapper configMensagemFiscalOperInternaMapper,
			ConfigMensagemFiscalTipoIncentFiscalMapper configMensagemFiscalTipoIncentFiscalMapper) {
		super(ConfigMensagemFiscalRepository, ConfigMensagemFiscal::new, ConfigMensagemFiscalDTO::new);
		this.mensagemFiscalRepository = mensagemFiscalRepository;
		this.configMensagemFiscalConfigFiscalMapper = configMensagemFiscalConfigFiscalMapper;
		this.configMensagemFiscalSituacTribMapper = configMensagemFiscalSituacTribMapper;
		this.configMensagemFiscalGrupoTribMapper = configMensagemFiscalGrupoTribMapper;
		this.configMensagemFiscalItemMapper = configMensagemFiscalItemMapper;
		this.configMensagemFiscalOperInternaMapper = configMensagemFiscalOperInternaMapper;
		this.configMensagemFiscalTipoIncentFiscalMapper = configMensagemFiscalTipoIncentFiscalMapper;
    }

	@Override
	protected void setValuesToEntity(ConfigMensagemFiscalDTO dto, ConfigMensagemFiscal entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		entity.setMensagemFiscal(mensagemFiscalRepository.findById(dto.getMensagemFiscalId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrada Mensagem Fiscal com id " + dto.getMensagemFiscalId())));
		if (dto.getConfigMensagemFiscalConfigFiscals() != null && dto.getConfigMensagemFiscalConfigFiscals().size() > 0) {
			entity.setConfigMensagemFiscalConfigFiscals(new ArrayList<>());
			entity.getConfigMensagemFiscalConfigFiscals().addAll(configMensagemFiscalConfigFiscalMapper.toEntity(dto.getConfigMensagemFiscalConfigFiscals()));
		}
		if (dto.getConfigMensagemFiscalSituacTribs() != null && dto.getConfigMensagemFiscalSituacTribs().size() > 0) {
			entity.setConfigMensagemFiscalSituacTribs(new ArrayList<>());
			entity.getConfigMensagemFiscalSituacTribs().addAll(configMensagemFiscalSituacTribMapper.toEntity(dto.getConfigMensagemFiscalSituacTribs()));
		}
		if (dto.getConfigMensagemFiscalGrupoTribs() != null && dto.getConfigMensagemFiscalGrupoTribs().size() > 0) {
			entity.setConfigMensagemFiscalGrupoTribs(new ArrayList<>());
			entity.getConfigMensagemFiscalGrupoTribs().addAll(configMensagemFiscalGrupoTribMapper.toEntity(dto.getConfigMensagemFiscalGrupoTribs()));
		}
		if (dto.getConfigMensagemFiscalItems() != null && dto.getConfigMensagemFiscalItems().size() > 0) {
			entity.setConfigMensagemFiscalItems(new ArrayList<>());
			entity.getConfigMensagemFiscalItems().addAll(configMensagemFiscalItemMapper.toEntity(dto.getConfigMensagemFiscalItems()));
		}
		if (dto.getConfigMensagemFiscalOperInternas() != null && dto.getConfigMensagemFiscalOperInternas().size() > 0) {
			entity.setConfigMensagemFiscalOperInternas(new ArrayList<>());
			entity.getConfigMensagemFiscalOperInternas().addAll(configMensagemFiscalOperInternaMapper.toEntity(dto.getConfigMensagemFiscalOperInternas()));
		}
		if (dto.getConfigMensagemFiscalTipoIncentFiscals() != null && dto.getConfigMensagemFiscalTipoIncentFiscals().size() > 0) {
			entity.setConfigMensagemFiscalTipoIncentFiscals(new ArrayList<>());
			entity.getConfigMensagemFiscalTipoIncentFiscals().addAll(configMensagemFiscalTipoIncentFiscalMapper.toEntity(dto.getConfigMensagemFiscalTipoIncentFiscals()));
		}
	}

	@Override
	protected void setValuesToDto(ConfigMensagemFiscal entity, ConfigMensagemFiscalDTO dto) {
		dto.setId(entity.getId());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		if (entity.getMensagemFiscal() != null) {
			dto.setMensagemFiscalId(entity.getMensagemFiscal().getId());
			dto.setMensagemFiscalNome(entity.getMensagemFiscal().getNome());
			dto.setMensagemFiscalCodigo(entity.getMensagemFiscal().getCodigo());
		}
		dto.setConfigMensagemFiscalConfigFiscals(new ArrayList<>());
		if (entity.getConfigMensagemFiscalConfigFiscals() != null) {
			dto.getConfigMensagemFiscalConfigFiscals().addAll(configMensagemFiscalConfigFiscalMapper.toDto(entity.getConfigMensagemFiscalConfigFiscals()));
		}
		dto.setConfigMensagemFiscalSituacTribs(new ArrayList<>());
		if (entity.getConfigMensagemFiscalSituacTribs() != null) {
			dto.getConfigMensagemFiscalSituacTribs().addAll(configMensagemFiscalSituacTribMapper.toDto(entity.getConfigMensagemFiscalSituacTribs()));
		}
		dto.setConfigMensagemFiscalGrupoTribs(new ArrayList<>());
		if (entity.getConfigMensagemFiscalGrupoTribs() != null) {
			dto.getConfigMensagemFiscalGrupoTribs().addAll(configMensagemFiscalGrupoTribMapper.toDto(entity.getConfigMensagemFiscalGrupoTribs()));
		}
		dto.setConfigMensagemFiscalItems(new ArrayList<>());
		if (entity.getConfigMensagemFiscalItems() != null) {
			dto.getConfigMensagemFiscalItems().addAll(configMensagemFiscalItemMapper.toDto(entity.getConfigMensagemFiscalItems()));
		}
		dto.setConfigMensagemFiscalOperInternas(new ArrayList<>());
		if (entity.getConfigMensagemFiscalOperInternas() != null) {
			dto.getConfigMensagemFiscalOperInternas().addAll(configMensagemFiscalOperInternaMapper.toDto(entity.getConfigMensagemFiscalOperInternas()));
		}
		dto.setConfigMensagemFiscalTipoIncentFiscals(new ArrayList<>());
		if (entity.getConfigMensagemFiscalTipoIncentFiscals() != null) {
			dto.getConfigMensagemFiscalTipoIncentFiscals().addAll(configMensagemFiscalTipoIncentFiscalMapper.toDto(entity.getConfigMensagemFiscalTipoIncentFiscals()));
		}
	}	
}
