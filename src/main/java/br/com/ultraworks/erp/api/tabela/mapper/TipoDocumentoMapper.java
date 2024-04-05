package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumento;
import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumentoDTO;
import br.com.ultraworks.erp.api.tabela.repository.TipoDocumentoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TipoDocumentoMapper extends GenericMapper<TipoDocumento, TipoDocumentoDTO> {

	public TipoDocumentoMapper(TipoDocumentoRepository repository) {
		super(repository, TipoDocumento::new, TipoDocumentoDTO::new);
	}

	@Override
	protected void setValuesToEntity(TipoDocumentoDTO dto, TipoDocumento entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
		entity.setCodigoReceita(dto.getCodigoReceita());
	}

	@Override
	protected void setValuesToDto(TipoDocumento entity, TipoDocumentoDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
		dto.setCodigoReceita(entity.getCodigoReceita());
	}
}
