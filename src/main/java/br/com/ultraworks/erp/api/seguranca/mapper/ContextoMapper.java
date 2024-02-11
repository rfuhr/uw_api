package br.com.ultraworks.erp.api.seguranca.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.seguranca.domain.contexto.Contexto;
import br.com.ultraworks.erp.api.seguranca.domain.contexto.ContextoDTO;
import br.com.ultraworks.erp.api.seguranca.repository.ContextoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ContextoMapper extends GenericMapper<Contexto, ContextoDTO> {

	public ContextoMapper(ContextoRepository contextoRepository) {
		super(contextoRepository, Contexto::new, ContextoDTO::new);
    }

	@Override
	protected void setValuesToEntity(ContextoDTO dto, Contexto entity) {
		entity.setId(dto.getId());
		entity.setUsuarioId(dto.getUsuarioId());
		entity.setEmpresaId(dto.getEmpresaId());
		entity.setEmpresaFilialId(dto.getEmpresaFilialId());
		entity.setModuloId(dto.getModuloId());
	}

	@Override
	protected void setValuesToDto(Contexto entity, ContextoDTO dto) {
		dto.setId(entity.getId());
		dto.setUsuarioId(entity.getUsuarioId());
		dto.setEmpresaId(entity.getEmpresaId());
		dto.setEmpresaFilialId(entity.getEmpresaFilialId());
		dto.setModuloId(entity.getModuloId());
	}

}