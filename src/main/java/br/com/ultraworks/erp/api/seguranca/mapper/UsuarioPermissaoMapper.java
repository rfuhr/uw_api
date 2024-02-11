package br.com.ultraworks.erp.api.seguranca.mapper;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.seguranca.domain.usuarioPermissao.UsuarioPermissao;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioPermissao.UsuarioPermissaoDTO;
import br.com.ultraworks.erp.api.seguranca.repository.UsuarioPermissaoRepository;
import br.com.ultraworks.erp.api.seguranca.service.UsuarioService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class UsuarioPermissaoMapper extends GenericMapper<UsuarioPermissao, UsuarioPermissaoDTO> {

	UsuarioService usuarioService;
	
	
	public UsuarioPermissaoMapper(UsuarioPermissaoRepository repository, UsuarioService usuarioService) {
		super(repository, UsuarioPermissao::new, UsuarioPermissaoDTO::new);
		this.usuarioService = usuarioService;
		
    }

	@Override
	protected void setValuesToEntity(UsuarioPermissaoDTO dto, UsuarioPermissao entity) {
		entity.setId(dto.getId() < 0 ? null : dto.getId());
		entity.setEmpresaId((int) dto.getEmpresaId());
		if (dto.getFiliaisId() != null)
			entity.setFiliaisId(dto.getFiliaisId().stream().map(Object::toString).collect(Collectors.joining(",")));
		if (dto.getPerfisId() != null && !dto.getPerfisId().isEmpty())
			entity.setPerfisId(dto.getPerfisId().stream().map(Object::toString).collect(Collectors.joining(",")));
		entity.setUsuario(usuarioService.getById(dto.getUsuarioId()).orElseThrow(() -> new RegisterNotFoundException("Não encontrado usuário com id " + dto.getUsuarioId())));
	}

	@Override
	protected void setValuesToDto(UsuarioPermissao entity, UsuarioPermissaoDTO dto) {
		dto.setId(entity.getId());
		dto.setUsuarioId(entity.getUsuario().getId());
		dto.setEmpresaId(entity.getEmpresaId());
		if (StringUtils.isNotBlank(entity.getFiliaisId()))
			dto.setFiliaisId(Arrays.stream(entity.getFiliaisId().split(",")).map(Long::parseLong).collect(Collectors.toList()));
		if (StringUtils.isNotBlank(entity.getPerfisId()))
			dto.setPerfisId(Arrays.stream(entity.getPerfisId().split(",")).map(Long::parseLong).collect(Collectors.toList()));

	}
}
