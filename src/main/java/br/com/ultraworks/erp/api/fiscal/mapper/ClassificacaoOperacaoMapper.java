package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.classificacaooperacao.ClassificacaoOperacao;
import br.com.ultraworks.erp.api.fiscal.domain.classificacaooperacao.ClassificacaoOperacaoDTO;
import br.com.ultraworks.erp.api.fiscal.repository.ClassificacaoOperacaoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ClassificacaoOperacaoMapper extends GenericMapper<ClassificacaoOperacao, ClassificacaoOperacaoDTO> {

	public ClassificacaoOperacaoMapper(ClassificacaoOperacaoRepository repository) {
		super(repository, ClassificacaoOperacao::new, ClassificacaoOperacaoDTO::new);
	}

	@Override
	protected void setValuesToEntity(ClassificacaoOperacaoDTO dto, ClassificacaoOperacao entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setUsoConsumo(dto.isUsoConsumo());
	}

	@Override
	protected void setValuesToDto(ClassificacaoOperacao entity, ClassificacaoOperacaoDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setUsoConsumo(entity.isUsoConsumo());
	}
}
