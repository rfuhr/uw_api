package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.profissao.Profissao;
import br.com.ultraworks.erp.api.tabela.domain.profissao.ProfissaoDTO;
import br.com.ultraworks.erp.api.tabela.repository.ProfissaoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ProfissaoMapper extends GenericMapper<Profissao, ProfissaoDTO> {

	public ProfissaoMapper(ProfissaoRepository repository) {
		super(repository, Profissao::new, ProfissaoDTO::new);
    }

	@Override
	protected void setValuesToEntity(ProfissaoDTO dto, Profissao entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setCodigo(dto.getCodigo());
	}

	@Override
	protected void setValuesToDto(Profissao entity, ProfissaoDTO dto) {
		dto.setId(entity.getId());
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setCodigo(entity.getCodigo());
	}
}


