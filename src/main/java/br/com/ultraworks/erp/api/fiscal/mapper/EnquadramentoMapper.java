package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.enquadramento.Enquadramento;
import br.com.ultraworks.erp.api.fiscal.domain.enquadramento.EnquadramentoDTO;
import br.com.ultraworks.erp.api.fiscal.repository.EnquadramentoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class EnquadramentoMapper extends GenericMapper<Enquadramento, EnquadramentoDTO> {

	public EnquadramentoMapper(EnquadramentoRepository repository) {
		super(repository, Enquadramento::new, EnquadramentoDTO::new);
	}

	@Override
	protected void setValuesToEntity(EnquadramentoDTO dto, Enquadramento entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(Enquadramento entity, EnquadramentoDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
	}
}
