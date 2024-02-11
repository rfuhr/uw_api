package br.com.ultraworks.erp.api.seguranca.mapper;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.seguranca.domain.usuarioFuncionalidade.UsuarioFuncionalidade;
import br.com.ultraworks.erp.api.seguranca.domain.usuarioFuncionalidade.UsuarioFuncionalidadeDTO;
import br.com.ultraworks.erp.api.seguranca.repository.UsuarioFuncionalidadeRepository;
import br.com.ultraworks.erp.api.seguranca.service.UsuarioService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class UsuarioFuncionalidadeMapper extends GenericMapper<UsuarioFuncionalidade, UsuarioFuncionalidadeDTO> {

	UsuarioService usuarioService;
	
	public UsuarioFuncionalidadeMapper(UsuarioFuncionalidadeRepository repository, UsuarioService usuarioService) {
		super(repository, UsuarioFuncionalidade::new, UsuarioFuncionalidadeDTO::new);
		this.usuarioService = usuarioService;
		
    }

	@Override
	protected void setValuesToEntity(UsuarioFuncionalidadeDTO dto, UsuarioFuncionalidade entity) {
		entity.setId(dto.getId() < 0 ? null : dto.getId());
		entity.setEmpresaId((int) dto.getEmpresaId());
		if (dto.getFiliaisId() != null)
			entity.setFiliaisId(dto.getFiliaisId().stream().map(Object::toString).collect(Collectors.joining(",")));
		entity.setFuncionalidadeId((int) dto.getFuncionalidadeId());
		entity.setUsuario(usuarioService.getById(dto.getUsuarioId()).orElseThrow(() -> new RegisterNotFoundException("Não encontrado usuário com id " + dto.getUsuarioId())));
		entity.setConsultar(dto.isConsultar());
		entity.setInserir(dto.isInserir());
		entity.setAlterar(dto.isAlterar());
		entity.setExcluir(dto.isExcluir());
		entity.setLiberado(dto.isLiberado());
	}

	@Override
	protected void setValuesToDto(UsuarioFuncionalidade entity, UsuarioFuncionalidadeDTO dto) {
		dto.setId(entity.getId());
		dto.setUsuarioId(entity.getUsuario().getId());
		dto.setEmpresaId(entity.getEmpresaId());
		if (StringUtils.isNotBlank(entity.getFiliaisId()))
			dto.setFiliaisId(Arrays.stream(entity.getFiliaisId().split(",")).map(Long::parseLong).collect(Collectors.toList()));
		dto.setFuncionalidadeId(entity.getFuncionalidadeId());
		dto.setConsultar(entity.isConsultar());
		dto.setInserir(entity.isInserir());
		dto.setAlterar(entity.isAlterar());
		dto.setExcluir(entity.isExcluir());
		dto.setLiberado(entity.isLiberado());

	}
}
