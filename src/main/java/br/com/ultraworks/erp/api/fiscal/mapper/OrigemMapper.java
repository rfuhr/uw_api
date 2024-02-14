package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.origem.Origem;
import br.com.ultraworks.erp.api.fiscal.domain.origem.OrigemDTO;
import br.com.ultraworks.erp.api.fiscal.repository.OrigemRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OrigemMapper extends GenericMapper<Origem, OrigemDTO> {

	public OrigemMapper(OrigemRepository repository) {
		super(repository, Origem::new, OrigemDTO::new);
	}

	@Override
	protected void setValuesToEntity(OrigemDTO dto, Origem entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(Origem entity, OrigemDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
	}
}
