package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.grupotributacao.GrupoTributacao;
import br.com.ultraworks.erp.api.fiscal.domain.grupotributacao.GrupoTributacaoDTO;
import br.com.ultraworks.erp.api.fiscal.repository.GrupoTributacaoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class GrupoTributacaoMapper extends GenericMapper<GrupoTributacao, GrupoTributacaoDTO> {

	public GrupoTributacaoMapper(GrupoTributacaoRepository repository) {
		super(repository, GrupoTributacao::new, GrupoTributacaoDTO::new);
	}

	@Override
	protected void setValuesToEntity(GrupoTributacaoDTO dto, GrupoTributacao entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(GrupoTributacao entity, GrupoTributacaoDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
	}
}
