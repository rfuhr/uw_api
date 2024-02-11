package br.com.ultraworks.erp.api.seguranca.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.seguranca.domain.perfil.Perfil;
import br.com.ultraworks.erp.api.seguranca.domain.perfil.PerfilDTO;
import br.com.ultraworks.erp.api.seguranca.repository.PerfilRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class PerfilMapper extends GenericMapper<Perfil, PerfilDTO> {

	public PerfilMapper(PerfilRepository perfilRepository) {
		super(perfilRepository, Perfil::new, PerfilDTO::new);
    }

	@Override
	protected void setValuesToEntity(PerfilDTO dto, Perfil entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
	}

	@Override
	protected void setValuesToDto(Perfil entity, PerfilDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setDescricao(entity.getDescricao());
	}	
}