package br.com.ultraworks.erp.api.estoque.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.estoque.domain.grupocontabil.GrupoContabil;
import br.com.ultraworks.erp.api.estoque.domain.grupocontabil.GrupoContabilDTO;
import br.com.ultraworks.erp.api.estoque.repository.GrupoContabilRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class GrupoContabilMapper extends GenericMapper<GrupoContabil, GrupoContabilDTO> {

	public GrupoContabilMapper(GrupoContabilRepository repository) {
		super(repository, GrupoContabil::new, GrupoContabilDTO::new);
	}

	@Override
	protected void setValuesToEntity(GrupoContabilDTO dto, GrupoContabil entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(GrupoContabil entity, GrupoContabilDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
	}
}
