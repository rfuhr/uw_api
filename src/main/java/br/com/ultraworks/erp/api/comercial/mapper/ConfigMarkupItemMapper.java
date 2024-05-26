package br.com.ultraworks.erp.api.comercial.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupitem.ConfigMarkupItem;
import br.com.ultraworks.erp.api.comercial.domain.configmarkupitem.ConfigMarkupItemDTO;
import br.com.ultraworks.erp.api.comercial.repository.ConfigMarkupItemRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigMarkupItemMapper extends GenericMapper<ConfigMarkupItem, ConfigMarkupItemDTO> {

	ItemRepository itemRepository;
	ConfigMarkupItemIndiceMapper configMarkupItemIndiceMapper;
	
	public ConfigMarkupItemMapper(ConfigMarkupItemRepository repository,
			ItemRepository itemRepository,
			ConfigMarkupItemIndiceMapper configMarkupItemIndiceMapper) {
		super(repository, ConfigMarkupItem::new, ConfigMarkupItemDTO::new);
		this.itemRepository = itemRepository;
		this.configMarkupItemIndiceMapper = configMarkupItemIndiceMapper;
	}

	@Override
	protected void setValuesToEntity(ConfigMarkupItemDTO dto, ConfigMarkupItem entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		entity.setItem(itemRepository.findById(dto.getItemId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado o Item com id " + dto.getItemId())));	
		if (dto.getConfigMarkupItemIndices() != null && dto.getConfigMarkupItemIndices().size() > 0) {
			entity.setConfigMarkupItemIndices(new ArrayList<>());
			entity.getConfigMarkupItemIndices().addAll(configMarkupItemIndiceMapper.toEntity(dto.getConfigMarkupItemIndices()));
		}
	}

	@Override
	protected void setValuesToDto(ConfigMarkupItem entity, ConfigMarkupItemDTO dto) {
		dto.setId(entity.getId());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		if (entity.getItem() != null) {
			dto.setItemCodigo(entity.getItem().getCodigo());
			dto.setItemNome(entity.getItem().getNome());
			dto.setItemId(entity.getItem().getId());
		}
		dto.setConfigMarkupItemIndices(new ArrayList<>());
		if (entity.getConfigMarkupItemIndices() != null) {
			dto.getConfigMarkupItemIndices().addAll(configMarkupItemIndiceMapper.toDto(entity.getConfigMarkupItemIndices()));
		}
	}
}
