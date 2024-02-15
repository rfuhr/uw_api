package br.com.ultraworks.erp.api.seguranca.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.seguranca.domain.grupoautonomia.GrupoAutonomia;
import br.com.ultraworks.erp.api.seguranca.domain.grupoautonomia.GrupoAutonomiaDTO;
import br.com.ultraworks.erp.api.seguranca.repository.GrupoAutonomiaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class GrupoAutonomiaMapper extends GenericMapper<GrupoAutonomia, GrupoAutonomiaDTO> {

	public GrupoAutonomiaMapper(GrupoAutonomiaRepository grupoAutonomiaRepository) {
		super(grupoAutonomiaRepository, GrupoAutonomia::new, GrupoAutonomiaDTO::new);
	}

	@Override
	protected void setValuesToEntity(GrupoAutonomiaDTO dto, GrupoAutonomia entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(GrupoAutonomia entity, GrupoAutonomiaDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());

	}
}
