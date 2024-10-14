package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.safra.Safra;
import br.com.ultraworks.erp.api.agricola.domain.safra.SafraDTO;
import br.com.ultraworks.erp.api.agricola.repository.SafraRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class SafraMapper extends GenericMapper<Safra, SafraDTO> {

	private ItemRepository itemRepository;
	
	public SafraMapper(SafraRepository repository, ItemRepository itemRepository) {
		super(repository, Safra::new, SafraDTO::new);
		this.itemRepository = itemRepository;
	}

	@Override
	protected void setValuesToEntity(SafraDTO dto, Safra entity) {
		entity.setId(dto.getId());
		if (dto.getItemId() != null) {
			entity.setItem(itemRepository.findById(dto.getItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado item com id " + dto.getItemId())));
		}
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(Safra entity, SafraDTO dto) {
		dto.setId(entity.getId());
		dto.setItemId(entity.getItem().getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		
		dto.setItemNome(entity.getItem().getNome());
	}
}
