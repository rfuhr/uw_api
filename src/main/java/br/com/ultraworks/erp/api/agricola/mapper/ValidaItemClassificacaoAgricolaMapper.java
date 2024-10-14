package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.validaitemclassificacaoagricola.ValidaItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validaitemclassificacaoagricola.ValidaItemClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.ItemClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.ValidaItemClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ValidaItemClassificacaoAgricolaMapper
		extends GenericMapper<ValidaItemClassificacaoAgricola, ValidaItemClassificacaoAgricolaDTO> {

	private ItemRepository itemRepository;
	private ItemClassificacaoAgricolaRepository itemClassificacaoAgricolaRepository;

	public ValidaItemClassificacaoAgricolaMapper(ValidaItemClassificacaoAgricolaRepository repository,
			ItemRepository itemRepository, ItemClassificacaoAgricolaRepository itemClassificacaoAgricolaRepository) {
		super(repository, ValidaItemClassificacaoAgricola::new, ValidaItemClassificacaoAgricolaDTO::new);
		this.itemRepository = itemRepository;
		this.itemClassificacaoAgricolaRepository = itemClassificacaoAgricolaRepository;
	}

	@Override
	protected void setValuesToEntity(ValidaItemClassificacaoAgricolaDTO dto, ValidaItemClassificacaoAgricola entity) {
		entity.setId(dto.getId());
		if (dto.getItemId() != null) {
			entity.setItem(itemRepository.findById(dto.getItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado item com id " + dto.getItemId())));
		}
		if (dto.getItemClassificacaoAgricolaId() != null) {
			entity.setItemClassificacaoAgricola(
					itemClassificacaoAgricolaRepository.findById(dto.getItemClassificacaoAgricolaId()).orElseThrow(
							() -> new RegisterNotFoundException("Não encontrado item classificação agrícola com id "
									+ dto.getItemClassificacaoAgricolaId())));
		}
		entity.setTipoUsoRomaneio(dto.getTipoUsoRomaneio());
		entity.setObrigatorio(dto.isObrigatorio());
		entity.setOrdemTela(dto.getOrdemTela());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(ValidaItemClassificacaoAgricola entity, ValidaItemClassificacaoAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setItemId(entity.getItem().getId());
		dto.setItemClassificacaoAgricolaId(entity.getItemClassificacaoAgricola().getId());
		dto.setTipoUsoRomaneio(entity.getTipoUsoRomaneio());
		dto.setObrigatorio(entity.isObrigatorio());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		dto.setOrdemTela(entity.getOrdemTela());
		dto.setItemNome(entity.getItem().getNome());
		dto.setItemClassificacaoAgricolaNome(entity.getItemClassificacaoAgricola().getNome());
	}
}
