package br.com.ultraworks.erp.api.estoque.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.estoque.domain.item.ItemDTO;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.api.tabela.service.UnidadeMedidaService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ItemMapper extends GenericMapper<Item, ItemDTO> {

	private UnidadeMedidaService unidadeMedidaService;
	
	public ItemMapper(ItemRepository repository, UnidadeMedidaService unidadeMedidaService) {
		super(repository, Item::new, ItemDTO::new);
		this.unidadeMedidaService = unidadeMedidaService;
	}

	@Override
	protected void setValuesToEntity(ItemDTO dto, Item entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setSku(dto.getSku());
		entity.setDescritivo(dto.getDescritivo());
		entity.setUnidadeMedidaComercial(unidadeMedidaService.getById(dto.getUnidadeMedidaComercialId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado unidade de medida comercial com id " + dto.getUnidadeMedidaComercialId())));
		entity.setProdutoProprio(dto.isProdutoProprio());
	}

	@Override
	protected void setValuesToDto(Item entity, ItemDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setSku(entity.getSku());
		dto.setDescritivo(entity.getDescritivo());
		dto.setUnidadeMedidaComercialId(entity.getUnidadeMedidaComercial().getId());
		dto.setUnidadeMedidaComercialNome(entity.getUnidadeMedidaComercial().getNome());
		dto.setProdutoProprio(entity.isProdutoProprio());
	}
}
