package br.com.ultraworks.erp.api.seguranca.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.seguranca.domain.modulo.Modulo;
import br.com.ultraworks.erp.api.seguranca.domain.modulo.ModuloDTO;
import br.com.ultraworks.erp.api.seguranca.repository.ModuloRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ModuloMapper extends GenericMapper<Modulo, ModuloDTO> {

	public ModuloMapper(ModuloRepository moduloRepository) {
		super(moduloRepository, Modulo::new, ModuloDTO::new);
    }

	@Override
	protected void setValuesToEntity(ModuloDTO dto, Modulo entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
		entity.setPathBase(dto.getPathBase());
		entity.setIcone(dto.getIcone());
	}

	@Override
	protected void setValuesToDto(Modulo entity, ModuloDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
		dto.setPathBase(entity.getPathBase());
		dto.setIcone(entity.getIcone());
		
	}	
}

