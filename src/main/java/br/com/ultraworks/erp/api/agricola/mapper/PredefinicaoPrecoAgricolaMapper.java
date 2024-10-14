package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.predefinicaoprecoagricola.PredefinicaoPrecoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.predefinicaoprecoagricola.PredefinicaoPrecoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.ItemClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.PredefinicaoPrecoAgricolaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class PredefinicaoPrecoAgricolaMapper
		extends GenericMapper<PredefinicaoPrecoAgricola, PredefinicaoPrecoAgricolaDTO> {

	private ItemClassificacaoAgricolaRepository itemClassificacaoAgricolaRepository;

	public PredefinicaoPrecoAgricolaMapper(PredefinicaoPrecoAgricolaRepository repository,
			ItemClassificacaoAgricolaRepository itemClassificacaoAgricolaRepository) {
		super(repository, PredefinicaoPrecoAgricola::new, PredefinicaoPrecoAgricolaDTO::new);
		this.itemClassificacaoAgricolaRepository = itemClassificacaoAgricolaRepository;
	}

	@Override
	protected void setValuesToEntity(PredefinicaoPrecoAgricolaDTO dto, PredefinicaoPrecoAgricola entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		if (dto.getItemClassificacaoAgricola1Id() != null) {
			entity.setItemClassificacaoAgricola1(itemClassificacaoAgricolaRepository
					.findById(dto.getItemClassificacaoAgricola1Id()).orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado item de classificação com id " + dto.getItemClassificacaoAgricola1Id())));
		}
		if (dto.getItemClassificacaoAgricola2Id() != null) {
			entity.setItemClassificacaoAgricola2(itemClassificacaoAgricolaRepository
					.findById(dto.getItemClassificacaoAgricola2Id()).orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado item de classificação com id " + dto.getItemClassificacaoAgricola2Id())));
		}
		if (dto.getItemClassificacaoAgricola3Id() != null) {
			entity.setItemClassificacaoAgricola3(itemClassificacaoAgricolaRepository
					.findById(dto.getItemClassificacaoAgricola3Id()).orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado item de classificação com id " + dto.getItemClassificacaoAgricola3Id())));
		}
		if (dto.getItemClassificacaoAgricola4Id() != null) {
			entity.setItemClassificacaoAgricola4(itemClassificacaoAgricolaRepository
					.findById(dto.getItemClassificacaoAgricola4Id()).orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado item de classificação com id " + dto.getItemClassificacaoAgricola4Id())));
		}
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(PredefinicaoPrecoAgricola entity, PredefinicaoPrecoAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setItemClassificacaoAgricola1Id(entity.getItemClassificacaoAgricola1().getId());
		dto.setItemClassificacaoAgricola1Nome(entity.getItemClassificacaoAgricola1().getNome());
		dto.setItemClassificacaoAgricola1Codigo(entity.getItemClassificacaoAgricola1().getCodigo());
		dto.setItemClassificacaoAgricola2Id(entity.getItemClassificacaoAgricola2().getId());
		dto.setItemClassificacaoAgricola2Nome(entity.getItemClassificacaoAgricola2().getNome());
		dto.setItemClassificacaoAgricola2Codigo(entity.getItemClassificacaoAgricola2().getCodigo());
		dto.setItemClassificacaoAgricola3Id(entity.getItemClassificacaoAgricola3().getId());
		dto.setItemClassificacaoAgricola3Nome(entity.getItemClassificacaoAgricola3().getNome());
		dto.setItemClassificacaoAgricola3Codigo(entity.getItemClassificacaoAgricola3().getCodigo());
		dto.setItemClassificacaoAgricola4Id(entity.getItemClassificacaoAgricola4().getId());
		dto.setItemClassificacaoAgricola4Nome(entity.getItemClassificacaoAgricola4().getNome());
		dto.setItemClassificacaoAgricola4Codigo(entity.getItemClassificacaoAgricola4().getCodigo());

		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
	}
}
