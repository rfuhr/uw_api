package br.com.ultraworks.erp.api.comercial.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitem.ConfigMarkupPlanoItem;
import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitem.ConfigMarkupPlanoItemDTO;
import br.com.ultraworks.erp.api.comercial.repository.ConfigMarkupPlanoItemRepository;
import br.com.ultraworks.erp.api.estoque.repository.PlanoClassificacaoItemRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigMarkupPlanoItemMapper extends GenericMapper<ConfigMarkupPlanoItem, ConfigMarkupPlanoItemDTO> {

	PlanoClassificacaoItemRepository planoClassificacaoItemRepository;
	ConfigMarkupPlanoItemIndiceMapper configMarkupPlanoItemIndiceMapper;
	
	public ConfigMarkupPlanoItemMapper(ConfigMarkupPlanoItemRepository repository,
			PlanoClassificacaoItemRepository planoClassificacaoItemRepository,
			ConfigMarkupPlanoItemIndiceMapper configMarkupPlanoItemIndiceMapper) {
		super(repository, ConfigMarkupPlanoItem::new, ConfigMarkupPlanoItemDTO::new);
		this.planoClassificacaoItemRepository = planoClassificacaoItemRepository;
		this.configMarkupPlanoItemIndiceMapper = configMarkupPlanoItemIndiceMapper;
	}

	@Override
	protected void setValuesToEntity(ConfigMarkupPlanoItemDTO dto, ConfigMarkupPlanoItem entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		entity.setPlanoClassificacaoItem(planoClassificacaoItemRepository.findById(dto.getPlanoClassificacaoItemId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado o Plano de Classificação do item com id " + dto.getPlanoClassificacaoItemId())));	
		if (dto.getConfigMarkupPlanoItemIndices() != null && dto.getConfigMarkupPlanoItemIndices().size() > 0) {
			entity.setConfigMarkupPlanoItemIndices(new ArrayList<>());
			entity.getConfigMarkupPlanoItemIndices().addAll(configMarkupPlanoItemIndiceMapper.toEntity(dto.getConfigMarkupPlanoItemIndices()));
		}
	}

	@Override
	protected void setValuesToDto(ConfigMarkupPlanoItem entity, ConfigMarkupPlanoItemDTO dto) {
		dto.setId(entity.getId());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		if (entity.getPlanoClassificacaoItem() != null) {
			dto.setPlanoClassificacaoItemCodigo(entity.getPlanoClassificacaoItem().getCodigo());
			dto.setPlanoClassificacaoItemNome(entity.getPlanoClassificacaoItem().getNome());
			dto.setPlanoClassificacaoItemId(entity.getPlanoClassificacaoItem().getId());
		}
		dto.setConfigMarkupPlanoItemIndices(new ArrayList<>());
		if (entity.getConfigMarkupPlanoItemIndices() != null) {
			dto.getConfigMarkupPlanoItemIndices().addAll(configMarkupPlanoItemIndiceMapper.toDto(entity.getConfigMarkupPlanoItemIndices()));
		}
	}
}
