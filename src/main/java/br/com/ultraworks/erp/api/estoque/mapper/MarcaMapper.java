package br.com.ultraworks.erp.api.estoque.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.estoque.domain.marca.Marca;
import br.com.ultraworks.erp.api.estoque.domain.marca.MarcaDTO;
import br.com.ultraworks.erp.api.estoque.repository.MarcaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class MarcaMapper extends GenericMapper<Marca, MarcaDTO> {

	public MarcaMapper(MarcaRepository repository) {
		super(repository, Marca::new, MarcaDTO::new);
	}

	@Override
	protected void setValuesToEntity(MarcaDTO dto, Marca entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(Marca entity, MarcaDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
	}
}
