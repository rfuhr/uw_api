package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola.ItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola.ItemClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.GrupoClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.ItemClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.TipoCalculoAgricolaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ItemClassificacaoAgricolaMapper
		extends GenericMapper<ItemClassificacaoAgricola, ItemClassificacaoAgricolaDTO> {

	private GrupoClassificacaoAgricolaRepository grupoClassificacaoAgricolaRepository;
	private TipoCalculoAgricolaRepository tipoCalculoAgricolaRepository;

	public ItemClassificacaoAgricolaMapper(ItemClassificacaoAgricolaRepository repository,
			GrupoClassificacaoAgricolaRepository grupoClassificacaoAgricolaRepository,
			TipoCalculoAgricolaRepository tipoCalculoAgricolaRepository) {
		super(repository, ItemClassificacaoAgricola::new, ItemClassificacaoAgricolaDTO::new);
		this.grupoClassificacaoAgricolaRepository = grupoClassificacaoAgricolaRepository;
		this.tipoCalculoAgricolaRepository = tipoCalculoAgricolaRepository;
	}

	@Override
	protected void setValuesToEntity(ItemClassificacaoAgricolaDTO dto, ItemClassificacaoAgricola entity) {
		entity.setId(dto.getId());
		if (dto.getGrupoClassificacaoAgricolaId() != null) {
			entity.setGrupoClassificacaoAgricola(grupoClassificacaoAgricolaRepository
					.findById(dto.getGrupoClassificacaoAgricolaId()).orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado grupo de classificação agrícola com id " + dto.getGrupoClassificacaoAgricolaId())));
		}
		if (dto.getTipoCalculoAgricolaIdRomaneio() != null) {
			entity.setTipoCalculoAgricolaRomaneio(tipoCalculoAgricolaRepository
					.findById(dto.getTipoCalculoAgricolaIdRomaneio()).orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado tipo de cálculo com id " + dto.getTipoCalculoAgricolaIdRomaneio())));
		}
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(ItemClassificacaoAgricola entity, ItemClassificacaoAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		if (entity.getGrupoClassificacaoAgricola() != null) {
			dto.setGrupoClassificacaoAgricolaId(entity.getGrupoClassificacaoAgricola().getId());
			dto.setGrupoClassificacaoAgricolaNome(entity.getGrupoClassificacaoAgricola().getNome());
		}
		if (entity.getTipoCalculoAgricolaRomaneio() != null) {
			dto.setTipoCalculoAgricolaIdRomaneio(entity.getTipoCalculoAgricolaRomaneio().getId());
			dto.setTipoCalculoAgricolaNomeRomaneio(entity.getTipoCalculoAgricolaRomaneio().getNome());
		}
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());

	}
}
