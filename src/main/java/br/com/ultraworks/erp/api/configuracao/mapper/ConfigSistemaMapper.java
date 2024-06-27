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

	public ConfigSistemaMapper(ConfigSistemaRepository repository, 
			ConfigSistemaFinanceiroMapper configSistemaFinanceiroMapper) {
		super(repository, ConfigSistema::new, ConfigSistemaDTO::new);
		this.configSistemaFinanceiroMapper = configSistemaFinanceiroMapper;
	}

	@Override
	protected void setValuesToEntity(ConfigSistemaDTO dto, ConfigSistema entity) {
		entity.setId(dto.getId());
		if (dto.getConfiguracoesFinanceiro() != null && dto.getConfiguracoesFinanceiro().size() > 0) {
			entity.setConfiguracoesFinanceiro(new ArrayList<>());
			entity.getConfiguracoesFinanceiro().addAll(configSistemaFinanceiroMapper.toEntity(dto.getConfiguracoesFinanceiro()));
		}

	}

	@Override
	protected void setValuesToDto(ConfigSistema entity, ConfigSistemaDTO dto) {
		dto.setId(entity.getId());
		dto.setConfiguracoesFinanceiro(new ArrayList<>());
		if (entity.getConfiguracoesFinanceiro() != null) {
			dto.getConfiguracoesFinanceiro().addAll(configSistemaFinanceiroMapper.toDto(entity.getConfiguracoesFinanceiro()));
		}
	}
}
