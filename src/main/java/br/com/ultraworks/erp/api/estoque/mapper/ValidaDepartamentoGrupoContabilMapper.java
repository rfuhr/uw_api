package br.com.ultraworks.erp.api.estoque.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.estoque.domain.validadepartamentogrupocontabil.ValidaDepartamentoGrupoContabil;
import br.com.ultraworks.erp.api.estoque.domain.validadepartamentogrupocontabil.ValidaDepartamentoGrupoContabilDTO;
import br.com.ultraworks.erp.api.estoque.repository.ValidaDepartamentoGrupoContabilRepository;
import br.com.ultraworks.erp.api.estoque.service.GrupoContabilService;
import br.com.ultraworks.erp.api.organograma.service.DepartamentoService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ValidaDepartamentoGrupoContabilMapper
		extends GenericMapper<ValidaDepartamentoGrupoContabil, ValidaDepartamentoGrupoContabilDTO> {

	DepartamentoService departamentoService;
	GrupoContabilService grupoContabilService;

	public ValidaDepartamentoGrupoContabilMapper(ValidaDepartamentoGrupoContabilRepository repository,
			DepartamentoService departamentoService, GrupoContabilService grupoContabilService) {
		super(repository, ValidaDepartamentoGrupoContabil::new, ValidaDepartamentoGrupoContabilDTO::new);
		this.departamentoService = departamentoService;
		this.grupoContabilService = grupoContabilService;
	}

	@Override
	protected void setValuesToEntity(ValidaDepartamentoGrupoContabilDTO dto, ValidaDepartamentoGrupoContabil entity) {
		entity.setId(dto.getId());
		if (dto.getDepartamentoId() != null) {
			entity.setDepartamento(departamentoService.getById(dto.getDepartamentoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrada o departamento com id " + dto.getDepartamentoId())));
		}
		if (dto.getGrupoContabilId() != null) {
			entity.setGrupoContabil(grupoContabilService.getById(dto.getGrupoContabilId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrada o grupo contábil com id " + dto.getGrupoContabilId())));
		}
		entity.setControlaEstoque(dto.isControlaEstoque());
	}

	@Override
	protected void setValuesToDto(ValidaDepartamentoGrupoContabil entity, ValidaDepartamentoGrupoContabilDTO dto) {
		dto.setId(entity.getId());
		if (entity.getDepartamento() != null) {
			dto.setDepartamentoId(entity.getDepartamento().getId());
			dto.setDepartamentoNome(entity.getDepartamento().getNome());
		}
		if (entity.getGrupoContabil() != null) {
			dto.setGrupoContabilId(entity.getGrupoContabil().getId());
			dto.setGrupoContabilNome(entity.getGrupoContabil().getNome());
		}
		dto.setControlaEstoque(entity.isControlaEstoque());
	}
}
