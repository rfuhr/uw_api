package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.tipocontacxbco.TipoContaCxBco;
import br.com.ultraworks.erp.api.financeiro.domain.tipocontacxbco.TipoContaCxBcoDTO;
import br.com.ultraworks.erp.api.financeiro.repository.TipoContaCxBcoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TipoContaCxBcoMapper extends GenericMapper<TipoContaCxBco, TipoContaCxBcoDTO> {

	public TipoContaCxBcoMapper(TipoContaCxBcoRepository repository) {
		super(repository, TipoContaCxBco::new, TipoContaCxBcoDTO::new);
    }

	@Override
	protected void setValuesToEntity(TipoContaCxBcoDTO dto, TipoContaCxBco entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(TipoContaCxBco entity, TipoContaCxBcoDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
	}
}

