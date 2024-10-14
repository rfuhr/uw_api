package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.validaprecoagricolaitem.ValidaPrecoAgricolaItem;
import br.com.ultraworks.erp.api.agricola.domain.validaprecoagricolaitem.ValidaPrecoAgricolaItemDTO;
import br.com.ultraworks.erp.api.agricola.repository.PredefinicaoPrecoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.TipoPrecoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.ValidaPrecoAgricolaItemRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ValidaPrecoAgricolaItemMapper extends GenericMapper<ValidaPrecoAgricolaItem, ValidaPrecoAgricolaItemDTO> {

	private ItemRepository itemRepository;
	private TipoPrecoAgricolaRepository tipoPrecoAgricolaRepository;
	private PredefinicaoPrecoAgricolaRepository predefinicaoPrecoAgricolaRepository;
	
	public ValidaPrecoAgricolaItemMapper(ValidaPrecoAgricolaItemRepository repository, ItemRepository itemRepository,
			TipoPrecoAgricolaRepository tipoPrecoAgricolaRepository,
			PredefinicaoPrecoAgricolaRepository predefinicaoPrecoAgricolaRepository) {
		super(repository, ValidaPrecoAgricolaItem::new, ValidaPrecoAgricolaItemDTO::new);
		this.itemRepository = itemRepository;
		this.tipoPrecoAgricolaRepository = tipoPrecoAgricolaRepository;
		this.predefinicaoPrecoAgricolaRepository = predefinicaoPrecoAgricolaRepository;
	}

	@Override
	protected void setValuesToEntity(ValidaPrecoAgricolaItemDTO dto, ValidaPrecoAgricolaItem entity) {
		entity.setId(dto.getId());
		if (dto.getItemId() != null) {
			entity.setItem(itemRepository.findById(dto.getItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado item com id " + dto.getItemId())));
		}
		if (dto.getTipoPrecoAgricolaId() != null) {
			entity.setTipoPrecoAgricola(tipoPrecoAgricolaRepository.findById(dto.getTipoPrecoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado tipo de preço agrícola com id " + dto.getTipoPrecoAgricolaId())));
		}
		if (dto.getPredefinicaoPrecoAgricolaId() != null) {
			entity.setPredefinicaoPrecoAgricola(predefinicaoPrecoAgricolaRepository.findById(dto.getPredefinicaoPrecoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado predefinição de preço agrícola com id " + dto.getPredefinicaoPrecoAgricolaId())));
		}
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(ValidaPrecoAgricolaItem entity, ValidaPrecoAgricolaItemDTO dto) {
		dto.setId(entity.getId());
		dto.setItemId(entity.getItem().getId());
		dto.setTipoPrecoAgricolaId(entity.getTipoPrecoAgricola().getId());
		dto.setPredefinicaoPrecoAgricolaId(entity.getPredefinicaoPrecoAgricola().getId());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());

		dto.setItemNome(entity.getItem().getNome());
		dto.setTipoPrecoAgricolaNome(entity.getTipoPrecoAgricola().getNome());
		dto.setPredefinicaoPrecoAgricolaNome(entity.getPredefinicaoPrecoAgricola().getNome());
	}
}
