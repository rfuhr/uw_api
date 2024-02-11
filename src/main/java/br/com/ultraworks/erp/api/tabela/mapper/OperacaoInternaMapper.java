package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInternaDTO;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OperacaoInternaMapper extends GenericMapper<OperacaoInterna, OperacaoInternaDTO> {

	public OperacaoInternaMapper(OperacaoInternaRepository repository) {
		super(repository, OperacaoInterna::new, OperacaoInternaDTO::new);
    }

	@Override
	protected void setValuesToEntity(OperacaoInternaDTO dto, OperacaoInterna entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
	}

	@Override
	protected void setValuesToDto(OperacaoInterna entity, OperacaoInternaDTO dto) {
		dto.setId(entity.getId());
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
	}
}
