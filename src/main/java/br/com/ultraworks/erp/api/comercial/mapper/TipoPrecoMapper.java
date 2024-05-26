package br.com.ultraworks.erp.api.comercial.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.comercial.domain.tipopreco.TipoPreco;
import br.com.ultraworks.erp.api.comercial.domain.tipopreco.TipoPrecoDTO;
import br.com.ultraworks.erp.api.comercial.repository.TipoPrecoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TipoPrecoMapper extends GenericMapper<TipoPreco, TipoPrecoDTO> {

	public TipoPrecoMapper(TipoPrecoRepository repository) {
		super(repository, TipoPreco::new, TipoPrecoDTO::new);
	}

	@Override
	protected void setValuesToEntity(TipoPrecoDTO dto, TipoPreco entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setTransferencia(dto.isTransferencia());
	}

	@Override
	protected void setValuesToDto(TipoPreco entity, TipoPrecoDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setTransferencia(entity.isTransferencia());
	}
}
