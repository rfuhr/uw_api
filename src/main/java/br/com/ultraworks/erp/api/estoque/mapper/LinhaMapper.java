package br.com.ultraworks.erp.api.estoque.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.estoque.domain.linha.Linha;
import br.com.ultraworks.erp.api.estoque.domain.linha.LinhaDTO;
import br.com.ultraworks.erp.api.estoque.repository.LinhaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class LinhaMapper extends GenericMapper<Linha, LinhaDTO> {

	public LinhaMapper(LinhaRepository repository) {
		super(repository, Linha::new, LinhaDTO::new);
	}

	@Override
	protected void setValuesToEntity(LinhaDTO dto, Linha entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(Linha entity, LinhaDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
	}
}
