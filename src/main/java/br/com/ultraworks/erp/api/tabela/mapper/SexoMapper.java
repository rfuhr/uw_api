package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.sexo.Sexo;
import br.com.ultraworks.erp.api.tabela.domain.sexo.SexoDTO;
import br.com.ultraworks.erp.api.tabela.repository.SexoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class SexoMapper extends GenericMapper<Sexo, SexoDTO> {

	public SexoMapper(SexoRepository repository) {
		super(repository, Sexo::new, SexoDTO::new);
    }

	@Override
	protected void setValuesToEntity(SexoDTO dto, Sexo entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(Sexo entity, SexoDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
	}
}
