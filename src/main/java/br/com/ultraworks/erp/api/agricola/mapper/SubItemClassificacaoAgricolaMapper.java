package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.subitemclassificacaoagricola.SubItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.subitemclassificacaoagricola.SubItemClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.ItemClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.SubItemClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class SubItemClassificacaoAgricolaMapper
		extends GenericMapper<SubItemClassificacaoAgricola, SubItemClassificacaoAgricolaDTO> {

	private ItemRepository itemRepository;
	private ItemClassificacaoAgricolaRepository itemClassificacaoAgricolaRepository;

	public SubItemClassificacaoAgricolaMapper(SubItemClassificacaoAgricolaRepository repository,
			ItemRepository itemRepository, ItemClassificacaoAgricolaRepository itemClassificacaoAgricolaRepository) {
		super(repository, SubItemClassificacaoAgricola::new, SubItemClassificacaoAgricolaDTO::new);
		this.itemRepository = itemRepository;
		this.itemClassificacaoAgricolaRepository = itemClassificacaoAgricolaRepository;
	}

	@Override
	protected void setValuesToEntity(SubItemClassificacaoAgricolaDTO dto, SubItemClassificacaoAgricola entity) {
		entity.setId(dto.getId());
		if (dto.getItemId() != null) {
			entity.setItem(itemRepository.findById(dto.getItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado item com id " + dto.getItemId())));
		}
		if (dto.getItemClassificacaoAgricolaId() != null) {
			entity.setItemClassificacaoAgricola(
					itemClassificacaoAgricolaRepository.findById(dto.getItemClassificacaoAgricolaId()).orElseThrow(
							() -> new RegisterNotFoundException("Não encontrado item de classificação agrícola com id "
									+ dto.getItemClassificacaoAgricolaId())));
		}
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setIndiceReferencia(dto.getIndiceReferencia());
		if (dto.getItemClassificacaoAgricolaGeradoId() != null) {
			entity.setItemClassificacaoAgricolaGerado(
					itemClassificacaoAgricolaRepository.findById(dto.getItemClassificacaoAgricolaGeradoId()).orElseThrow(
							() -> new RegisterNotFoundException("Não encontrado item de classificação agrícola gerado com id "
									+ dto.getItemClassificacaoAgricolaGeradoId())));
		}
		if (dto.getSubItemClassificacaoAgricolaGeradoId() != null) {
			entity.setSubItemClassificacaoAgricolaGerado(
					repository.findById(dto.getSubItemClassificacaoAgricolaGeradoId()).orElseThrow(
							() -> new RegisterNotFoundException("Não encontrado sub. item de classificação agrícola gerado com id "
									+ dto.getSubItemClassificacaoAgricolaGeradoId())));
		}
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(SubItemClassificacaoAgricola entity, SubItemClassificacaoAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setIndiceReferencia(entity.getIndiceReferencia());
		if (entity.getItem() != null) {
			dto.setItemId(entity.getItem().getId());
			dto.setItemNome(entity.getItem().getNome());
		}
		if (entity.getItemClassificacaoAgricola() != null) {
			dto.setItemClassificacaoAgricolaId(entity.getItemClassificacaoAgricola().getId());
			dto.setItemClassificacaoAgricolaNome(entity.getItemClassificacaoAgricola().getNome());
		}
		if (entity.getItemClassificacaoAgricolaGerado() != null) {
			dto.setItemClassificacaoAgricolaGeradoId(entity.getItemClassificacaoAgricolaGerado().getId());
			dto.setItemClassificacaoAgricolaGeradoNome(entity.getItemClassificacaoAgricolaGerado().getNome());
		}
		if (entity.getSubItemClassificacaoAgricolaGerado() != null) {
			dto.setSubItemClassificacaoAgricolaGeradoId(entity.getSubItemClassificacaoAgricolaGerado().getId());
			dto.setSubItemClassificacaoAgricolaGeradoNome(entity.getSubItemClassificacaoAgricolaGerado().getNome());
		}
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());

	}
}
