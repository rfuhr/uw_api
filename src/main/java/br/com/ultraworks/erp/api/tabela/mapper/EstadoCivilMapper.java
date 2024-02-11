package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.estadocivil.EstadoCivil;
import br.com.ultraworks.erp.api.tabela.domain.estadocivil.EstadoCivilDTO;
import br.com.ultraworks.erp.api.tabela.domain.uf.Uf;
import br.com.ultraworks.erp.api.tabela.domain.uf.UfDTO;
import br.com.ultraworks.erp.api.tabela.repository.EstadoCivilRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class EstadoCivilMapper extends GenericMapper<EstadoCivil, EstadoCivilDTO> {

	public EstadoCivilMapper(EstadoCivilRepository repository) {
		super(repository, EstadoCivil::new, EstadoCivilDTO::new);
    }

	@Override
	protected void setValuesToEntity(EstadoCivilDTO dto, EstadoCivil entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setCodigo(dto.getCodigo());
	}

	@Override
	protected void setValuesToDto(EstadoCivil entity, EstadoCivilDTO dto) {
		dto.setId(entity.getId());
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setCodigo(entity.getCodigo());
	}
}
