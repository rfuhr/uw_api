package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.grupooperacaointerna.GrupoOperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.grupooperacaointerna.GrupoOperacaoInternaDTO;
import br.com.ultraworks.erp.api.tabela.repository.GrupoOperacaoInternaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class GrupoOperacaoInternaMapper extends GenericMapper<GrupoOperacaoInterna, GrupoOperacaoInternaDTO> {

	public GrupoOperacaoInternaMapper(GrupoOperacaoInternaRepository repository) {
		super(repository, GrupoOperacaoInterna::new, GrupoOperacaoInternaDTO::new);
    }

	@Override
	protected void setValuesToEntity(GrupoOperacaoInternaDTO dto, GrupoOperacaoInterna entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
	}

	@Override
	protected void setValuesToDto(GrupoOperacaoInterna entity, GrupoOperacaoInternaDTO dto) {
		dto.setId(entity.getId());
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
	}
}
