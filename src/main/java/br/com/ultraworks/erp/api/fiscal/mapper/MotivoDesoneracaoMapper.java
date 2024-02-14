package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.motivodesoneracao.MotivoDesoneracao;
import br.com.ultraworks.erp.api.fiscal.domain.motivodesoneracao.MotivoDesoneracaoDTO;
import br.com.ultraworks.erp.api.fiscal.repository.MotivoDesoneracaoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class MotivoDesoneracaoMapper extends GenericMapper<MotivoDesoneracao, MotivoDesoneracaoDTO> {

	public MotivoDesoneracaoMapper(MotivoDesoneracaoRepository repository) {
		super(repository, MotivoDesoneracao::new, MotivoDesoneracaoDTO::new);
	}

	@Override
	protected void setValuesToEntity(MotivoDesoneracaoDTO dto, MotivoDesoneracao entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(MotivoDesoneracao entity, MotivoDesoneracaoDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
	}
}
