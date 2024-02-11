package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.tipooperacao.TipoOperacao;
import br.com.ultraworks.erp.api.tabela.domain.tipooperacao.TipoOperacaoDTO;
import br.com.ultraworks.erp.api.tabela.repository.TipoOperacaoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TipoOperacaoMapper extends GenericMapper<TipoOperacao, TipoOperacaoDTO> {

	public TipoOperacaoMapper(TipoOperacaoRepository repository) {
		super(repository, TipoOperacao::new, TipoOperacaoDTO::new);
    }

	@Override
	protected void setValuesToEntity(TipoOperacaoDTO dto, TipoOperacao entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(TipoOperacao entity, TipoOperacaoDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
	}
}
