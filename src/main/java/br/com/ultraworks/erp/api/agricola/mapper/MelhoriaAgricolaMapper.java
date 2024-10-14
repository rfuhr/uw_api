package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.melhoriaagricola.MelhoriaAgricola;
import br.com.ultraworks.erp.api.agricola.domain.melhoriaagricola.MelhoriaAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.ItemClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.MelhoriaAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.SubItemClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class MelhoriaAgricolaMapper extends GenericMapper<MelhoriaAgricola, MelhoriaAgricolaDTO> {

	private ItemRepository itemRepository;
	private ItemClassificacaoAgricolaRepository itemClassificacaoAgricolaRepository;
	private SubItemClassificacaoAgricolaRepository subItemClassificacaoAgricolaRepository;

	public MelhoriaAgricolaMapper(MelhoriaAgricolaRepository repository, ItemRepository itemRepository,
			ItemClassificacaoAgricolaRepository itemClassificacaoAgricolaRepository,
			SubItemClassificacaoAgricolaRepository subItemClassificacaoAgricolaRepository) {
		super(repository, MelhoriaAgricola::new, MelhoriaAgricolaDTO::new);
		this.itemRepository = itemRepository;
		this.itemClassificacaoAgricolaRepository = itemClassificacaoAgricolaRepository;
		this.subItemClassificacaoAgricolaRepository = subItemClassificacaoAgricolaRepository;
	}

	@Override
	protected void setValuesToEntity(MelhoriaAgricolaDTO dto, MelhoriaAgricola entity) {
		entity.setId(dto.getId());
		if (dto.getItemId() != null) {
			entity.setItem(itemRepository.findById(dto.getItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado item com id " + dto.getItemId())));
		}
		if (dto.getItemClassificacaoAgricolaPrincipalId() != null) {
			entity.setItemClassificacaoAgricolaPrincipal(
					itemClassificacaoAgricolaRepository.findById(dto.getItemClassificacaoAgricolaPrincipalId())
							.orElseThrow(() -> new RegisterNotFoundException(
									"Não encontrado item de classificação agrícola principal com id "
											+ dto.getItemClassificacaoAgricolaPrincipalId())));
		}
		if (dto.getSubItemClassificacaoAgricolaPrincipalId() != null) {
			entity.setSubItemClassificacaoAgricolaPrincipal(
					subItemClassificacaoAgricolaRepository.findById(dto.getSubItemClassificacaoAgricolaPrincipalId())
							.orElseThrow(() -> new RegisterNotFoundException(
									"Não encontrado sub. item de classificação agrícola principal com id "
											+ dto.getSubItemClassificacaoAgricolaPrincipalId())));
		}
		if (dto.getItemClassificacaoAgricolaSecundarioId() != null) {
			entity.setItemClassificacaoAgricolaSecundario(
					itemClassificacaoAgricolaRepository.findById(dto.getItemClassificacaoAgricolaSecundarioId())
							.orElseThrow(() -> new RegisterNotFoundException(
									"Não encontrado item de classificação agrícola secundário com id "
											+ dto.getItemClassificacaoAgricolaSecundarioId())));
		}
		if (dto.getSubItemClassificacaoAgricolaSecundarioId() != null) {
			entity.setSubItemClassificacaoAgricolaSecundario(
					subItemClassificacaoAgricolaRepository.findById(dto.getSubItemClassificacaoAgricolaSecundarioId())
							.orElseThrow(() -> new RegisterNotFoundException(
									"Não encontrado sub. item de classificação agrícola secundário com id "
											+ dto.getSubItemClassificacaoAgricolaSecundarioId())));
		}
		entity.setValorAdicionaPrincipal(dto.getValorAdicionaPrincipal());
		entity.setValorAdicionaSecundario(dto.getValorAdicionaSecundario());
		if (dto.getItemClassificacaoAgricolaGeradoId() != null) {
			entity.setItemClassificacaoAgricolaGerado(
					itemClassificacaoAgricolaRepository.findById(dto.getItemClassificacaoAgricolaGeradoId())
							.orElseThrow(() -> new RegisterNotFoundException(
									"Não encontrado item de classificação agrícola gerado com id "
											+ dto.getItemClassificacaoAgricolaGeradoId())));
		}
		if (dto.getSubItemClassificacaoAgricolaGeradoId() != null) {
			entity.setSubItemClassificacaoAgricolaGerado(
					subItemClassificacaoAgricolaRepository.findById(dto.getSubItemClassificacaoAgricolaGeradoId())
							.orElseThrow(() -> new RegisterNotFoundException(
									"Não encontrado sub. item de classificação agrícola gerado com id "
											+ dto.getSubItemClassificacaoAgricolaGeradoId())));
		}
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(MelhoriaAgricola entity, MelhoriaAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setItemId(entity.getItem().getId());
		dto.setItemNome(entity.getItem().getNome());
		dto.setItemClassificacaoAgricolaPrincipalId(entity.getItemClassificacaoAgricolaPrincipal().getId());
		dto.setItemClassificacaoAgricolaPrincipalNome(entity.getItemClassificacaoAgricolaPrincipal().getNome());
		dto.setItemClassificacaoAgricolaSecundarioId(entity.getItemClassificacaoAgricolaSecundario().getId());
		dto.setItemClassificacaoAgricolaSecundarioNome(entity.getItemClassificacaoAgricolaSecundario().getNome());
		dto.setValorAdicionaPrincipal(entity.getValorAdicionaPrincipal());
		dto.setValorAdicionaSecundario(entity.getValorAdicionaSecundario());
		dto.setItemClassificacaoAgricolaGeradoId(entity.getItemClassificacaoAgricolaGerado().getId());
		dto.setItemClassificacaoAgricolaGeradoNome(entity.getItemClassificacaoAgricolaGerado().getNome());

		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());

	}
}
