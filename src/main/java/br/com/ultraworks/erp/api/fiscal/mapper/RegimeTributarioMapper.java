package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.regimetributario.RegimeTributario;
import br.com.ultraworks.erp.api.fiscal.domain.regimetributario.RegimeTributarioDTO;
import br.com.ultraworks.erp.api.fiscal.repository.RegimeTributarioRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class RegimeTributarioMapper extends GenericMapper<RegimeTributario, RegimeTributarioDTO> {

	public RegimeTributarioMapper(RegimeTributarioRepository repository) {
		super(repository, RegimeTributario::new, RegimeTributarioDTO::new);
	}

	@Override
	protected void setValuesToEntity(RegimeTributarioDTO dto, RegimeTributario entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(RegimeTributario entity, RegimeTributarioDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
	}
}
