package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTituloDTO;
import br.com.ultraworks.erp.api.financeiro.repository.TipoTituloRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TipoTituloMapper extends GenericMapper<TipoTitulo, TipoTituloDTO> {

	public TipoTituloMapper(TipoTituloRepository repository) {
		super(repository, TipoTitulo::new, TipoTituloDTO::new);
    }

	@Override
	protected void setValuesToEntity(TipoTituloDTO dto, TipoTitulo entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
	}

	@Override
	protected void setValuesToDto(TipoTitulo entity, TipoTituloDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
	}
}

