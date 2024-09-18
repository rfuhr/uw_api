package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.safra.Safra;
import br.com.ultraworks.erp.api.agricola.domain.safra.SafraDTO;
import br.com.ultraworks.erp.api.agricola.repository.SafraRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class SafraMapper extends GenericMapper<Safra, SafraDTO> {

	public SafraMapper(SafraRepository repository) {
		super(repository, Safra::new, SafraDTO::new);
	}

	@Override
	protected void setValuesToEntity(SafraDTO dto, Safra entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(Safra entity, SafraDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
	}
}
