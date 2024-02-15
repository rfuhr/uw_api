package br.com.ultraworks.erp.api.seguranca.mapper;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.seguranca.domain.usuarioAutonomia.UsuarioAutonomia;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioAutonomia.UsuarioAutonomiaDTO;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioFuncionalidade.UsuarioFuncionalidade;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioFuncionalidade.UsuarioFuncionalidadeDTO;
import br.com.ultraworks.erp.api.seguranca.repository.UsuarioAutonomiaRepository;
import br.com.ultraworks.erp.api.seguranca.service.UsuarioService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class UsuarioAutonomiaMapper extends GenericMapper<UsuarioAutonomia, UsuarioAutonomiaDTO> {

	UsuarioService usuarioService;

	public UsuarioAutonomiaMapper(UsuarioAutonomiaRepository repository, UsuarioService usuarioService) {
		super(repository, UsuarioAutonomia::new, UsuarioAutonomiaDTO::new);
		this.usuarioService = usuarioService;

	}

	@Override
	protected void setValuesToEntity(UsuarioAutonomiaDTO dto, UsuarioAutonomia entity) {
		entity.setId(dto.getId() < 0 ? null : dto.getId());
		entity.setEmpresaId((int) dto.getEmpresaId());
		if (dto.getFiliaisId() != null)
			entity.setFiliaisId(dto.getFiliaisId().stream().map(Object::toString).collect(Collectors.joining(",")));
		entity.setAutonomiaId((int) dto.getAutonomiaId());
		entity.setUsuario(usuarioService.getById(dto.getUsuarioId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado usuário com id " + dto.getUsuarioId())));
	}

	@Override
	protected void setValuesToDto(UsuarioAutonomia entity, UsuarioAutonomiaDTO dto) {
		dto.setId(entity.getId());
		dto.setUsuarioId(entity.getUsuario().getId());
		dto.setEmpresaId(entity.getEmpresaId());
		if (StringUtils.isNotBlank(entity.getFiliaisId()))
			dto.setFiliaisId(
					Arrays.stream(entity.getFiliaisId().split(",")).map(Long::parseLong).collect(Collectors.toList()));
		dto.setAutonomiaId(entity.getAutonomiaId());
	}
}
