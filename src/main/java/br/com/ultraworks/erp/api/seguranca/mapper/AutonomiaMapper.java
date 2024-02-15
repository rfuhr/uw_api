package br.com.ultraworks.erp.api.seguranca.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.seguranca.domain.autonomia.Autonomia;
import br.com.ultraworks.erp.api.seguranca.domain.autonomia.AutonomiaDTO;
import br.com.ultraworks.erp.api.seguranca.repository.AutonomiaRepository;
import br.com.ultraworks.erp.api.seguranca.service.GrupoAutonomiaService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class AutonomiaMapper extends GenericMapper<Autonomia, AutonomiaDTO> {

	private GrupoAutonomiaService grupoAutonomiaService;

	public AutonomiaMapper(AutonomiaRepository autonomiaRepository, GrupoAutonomiaService grupoAutonomiaService) {
		super(autonomiaRepository, Autonomia::new, AutonomiaDTO::new);
		this.grupoAutonomiaService = grupoAutonomiaService;
	}

	@Override
	protected void setValuesToEntity(AutonomiaDTO dto, Autonomia entity) {
		entity.setId(dto.getId() < 0 ? null : dto.getId());
		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
		entity.setTag(dto.getTag());
		entity.setGrupoAutonomia(grupoAutonomiaService.getById(dto.getGrupoAutonomiaId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado grupo de autonomia com id " + dto.getGrupoAutonomiaId())));
	}

	@Override
	protected void setValuesToDto(Autonomia entity, AutonomiaDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setDescricao(entity.getDescricao());
		dto.setGrupoAutonomiaNome(entity.getGrupoAutonomia().getNome());
		dto.setGrupoAutonomiaId(entity.getGrupoAutonomia().getId());
	}
}
