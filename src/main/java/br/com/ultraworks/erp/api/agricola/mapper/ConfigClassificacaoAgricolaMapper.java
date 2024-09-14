package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.configclassificacaoagricola.ConfigClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.configclassificacaoagricola.ConfigClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.ConfigClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.SafraRepository;
import br.com.ultraworks.erp.api.agricola.repository.TipoClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigClassificacaoAgricolaMapper
		extends GenericMapper<ConfigClassificacaoAgricola, ConfigClassificacaoAgricolaDTO> {

	ConfigClassificacaoAgricolaRepository configClassificacaoAgricolaRepository;
	ItemRepository itemRepository;
	TipoClassificacaoAgricolaRepository tipoClassificacaoAgricolaRepository;
	SafraRepository safraRepository;

	public ConfigClassificacaoAgricolaMapper(
			ConfigClassificacaoAgricolaRepository configClassificacaoAgricolaRepository, ItemRepository itemRepository,
			TipoClassificacaoAgricolaRepository tipoClassificacaoAgricolaRepository, SafraRepository safraRepository) {
		super(configClassificacaoAgricolaRepository, ConfigClassificacaoAgricola::new,
				ConfigClassificacaoAgricolaDTO::new);
		this.itemRepository = itemRepository;
		this.tipoClassificacaoAgricolaRepository = tipoClassificacaoAgricolaRepository;
		this.safraRepository = safraRepository;
	}

	@Override
	protected void setValuesToEntity(ConfigClassificacaoAgricolaDTO dto, ConfigClassificacaoAgricola entity) {
		entity.setId(dto.getId());
		entity.setItem(itemRepository.findById(dto.getItemId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado item com id " + dto.getItemId())));
		entity.setTipoClassificacaoAgricola(
				tipoClassificacaoAgricolaRepository.findById(dto.getTipoClassificacaoAgricolaId()).orElseThrow(
						() -> new RegisterNotFoundException("Não encontrado tipo de classificação agrícola com id "
								+ dto.getTipoClassificacaoAgricolaId())));
		entity.setSafra(safraRepository.findById(dto.getSafraId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado safra com id " + dto.getSafraId())));
		entity.setFaixaInicial(dto.getFaixaInicial());
		entity.setFaixaFinal(dto.getFaixaFinal());
		entity.setDesconto(dto.isDesconto());
		entity.setPercentualDesconto(dto.getPercentualDesconto());
	}

	@Override
	protected void setValuesToDto(ConfigClassificacaoAgricola entity, ConfigClassificacaoAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setItemId(entity.getItem().getId());
		dto.setTipoClassificacaoAgricolaId(entity.getTipoClassificacaoAgricola().getId());
		dto.setSafraId(entity.getSafra().getId());
		dto.setFaixaInicial(entity.getFaixaInicial());
		dto.setFaixaFinal(entity.getFaixaFinal());
		dto.setDesconto(entity.isDesconto());
		dto.setPercentualDesconto(entity.getPercentualDesconto());

		dto.setItemNome(entity.getItem().getNome());
		dto.setTipoClassificacaoAgricolaNome(entity.getTipoClassificacaoAgricola().getNome());
		dto.setSafraNome(entity.getSafra().getNome());
	}
}
