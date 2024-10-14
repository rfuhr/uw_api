package br.com.ultraworks.erp.api.configuracao.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistema;
import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistemaDTO;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigSistemaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigSistemaMapper extends GenericMapper<ConfigSistema, ConfigSistemaDTO> {

	private ConfigSistemaFinanceiroMapper configSistemaFinanceiroMapper;
	private ConfigSistemaAgricolaMapper configSistemaAgricolaMapper;

	public ConfigSistemaMapper(ConfigSistemaRepository repository,
			ConfigSistemaFinanceiroMapper configSistemaFinanceiroMapper,
			ConfigSistemaAgricolaMapper configSistemaAgricolaMapper) {
		super(repository, ConfigSistema::new, ConfigSistemaDTO::new);
		this.configSistemaFinanceiroMapper = configSistemaFinanceiroMapper;
		this.configSistemaAgricolaMapper = configSistemaAgricolaMapper;
	}

	@Override
	protected void setValuesToEntity(ConfigSistemaDTO dto, ConfigSistema entity) {
		entity.setId(dto.getId());
		if (dto.getConfiguracoesFinanceiro() != null && dto.getConfiguracoesFinanceiro().size() > 0) {
			entity.setConfiguracoesFinanceiro(new ArrayList<>());
			entity.getConfiguracoesFinanceiro()
					.addAll(configSistemaFinanceiroMapper.toEntity(dto.getConfiguracoesFinanceiro()));
		}
		if (dto.getConfiguracoesAgricola() != null && dto.getConfiguracoesAgricola().size() > 0) {
			entity.setConfiguracoesAgricola(new ArrayList<>());
			entity.getConfiguracoesAgricola()
					.addAll(configSistemaAgricolaMapper.toEntity(dto.getConfiguracoesAgricola()));
		}

	}

	@Override
	protected void setValuesToDto(ConfigSistema entity, ConfigSistemaDTO dto) {
		dto.setId(entity.getId());
		dto.setConfiguracoesFinanceiro(new ArrayList<>());
		if (entity.getConfiguracoesFinanceiro() != null) {
			dto.getConfiguracoesFinanceiro()
					.addAll(configSistemaFinanceiroMapper.toDto(entity.getConfiguracoesFinanceiro()));
		}
		dto.setConfiguracoesAgricola(new ArrayList<>());
		if (entity.getConfiguracoesAgricola() != null) {
			dto.getConfiguracoesAgricola().addAll(configSistemaAgricolaMapper.toDto(entity.getConfiguracoesAgricola()));
		}
	}
}
