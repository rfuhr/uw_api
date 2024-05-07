package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalitem.ConfigMensagemFiscalItem;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalitem.ConfigMensagemFiscalItemDTO;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalItemRepository;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigMensagemFiscalItemMapper extends GenericMapper<ConfigMensagemFiscalItem, ConfigMensagemFiscalItemDTO> {

	ConfigMensagemFiscalRepository configMensagemFiscalRepository;
	ItemRepository itemRepository;
	
	public ConfigMensagemFiscalItemMapper(ConfigMensagemFiscalItemRepository ConfigMensagemFiscalItemRepository, ConfigMensagemFiscalRepository configMensagemFiscalRepository,
			ItemRepository itemRepository) {
		super(ConfigMensagemFiscalItemRepository, ConfigMensagemFiscalItem::new, ConfigMensagemFiscalItemDTO::new);
		this.configMensagemFiscalRepository = configMensagemFiscalRepository;
		this.itemRepository = itemRepository;
    }

	@Override
	protected void setValuesToEntity(ConfigMensagemFiscalItemDTO dto, ConfigMensagemFiscalItem entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		if (dto.getConfigMensagemFiscalId() != null) {
			entity.setConfigMensagemFiscal(configMensagemFiscalRepository.findById(dto.getConfigMensagemFiscalId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Configuração da Mensagem Fiscal com id " + dto.getConfigMensagemFiscalId())));			
		}
		entity.setItem(itemRepository.findById(dto.getItemId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Item com id " + dto.getItemId())));
	}

	@Override
	protected void setValuesToDto(ConfigMensagemFiscalItem entity, ConfigMensagemFiscalItemDTO dto) {
		dto.setId(entity.getId());
		dto.setConfigMensagemFiscalId(entity.getConfigMensagemFiscal().getId());
		dto.setItemId(entity.getItem().getId());
		dto.setItemNome(entity.getItem().getNome());
		dto.setItemCodigo(entity.getItem().getCodigo());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
	}	
}
