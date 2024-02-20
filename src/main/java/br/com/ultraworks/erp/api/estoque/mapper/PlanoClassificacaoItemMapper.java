package br.com.ultraworks.erp.api.estoque.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.estoque.domain.planoclassificacaoitem.PlanoClassificacaoItem;
import br.com.ultraworks.erp.api.estoque.domain.planoclassificacaoitem.PlanoClassificacaoItemDTO;
import br.com.ultraworks.erp.api.estoque.repository.PlanoClassificacaoItemRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class PlanoClassificacaoItemMapper extends GenericMapper<PlanoClassificacaoItem, PlanoClassificacaoItemDTO> {

	public PlanoClassificacaoItemMapper(PlanoClassificacaoItemRepository repository) {
		super(repository, PlanoClassificacaoItem::new, PlanoClassificacaoItemDTO::new);
	}

	@Override
	protected void setValuesToEntity(PlanoClassificacaoItemDTO dto, PlanoClassificacaoItem entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setNivel(dto.getNivel());
		entity.setSintetica(dto.isSintetica());

		if (dto.getContaSuperiorId() != null) {
			entity.setContaSuperior(
					repository.findById(dto.getContaSuperiorId()).orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado conta superior com id " + dto.getContaSuperiorId())));
		}
	}

	@Override
	protected void setValuesToDto(PlanoClassificacaoItem entity, PlanoClassificacaoItemDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setNivel(entity.getNivel());
		dto.setSintetica(entity.isSintetica());
		if (entity.getContaSuperior() != null) {
			dto.setContaSuperiorId(entity.getContaSuperior().getId());
			dto.setContaSuperiorCodigo(entity.getContaSuperior().getCodigo());
			dto.setContaSuperiorNome(entity.getContaSuperior().getNome());
		}
	}
}
