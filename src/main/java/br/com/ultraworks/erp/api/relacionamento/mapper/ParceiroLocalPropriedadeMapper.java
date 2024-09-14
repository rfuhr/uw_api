package br.com.ultraworks.erp.api.relacionamento.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalPropriedade.ParceiroLocalPropriedade;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalPropriedade.ParceiroLocalPropriedadeDTO;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalPropriedadeRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ParceiroLocalPropriedadeMapper
		extends GenericMapper<ParceiroLocalPropriedade, ParceiroLocalPropriedadeDTO> {

	private ParceiroLocalRepository parceiroLocalRepository;

	public ParceiroLocalPropriedadeMapper(ParceiroLocalPropriedadeRepository parceiroPropriedadeRepository,
			ParceiroLocalRepository parceiroLocalRepository) {
		super(parceiroPropriedadeRepository, ParceiroLocalPropriedade::new, ParceiroLocalPropriedadeDTO::new);
		this.parceiroLocalRepository = parceiroLocalRepository;
	}

	@Override
	protected void setValuesToEntity(ParceiroLocalPropriedadeDTO dto, ParceiroLocalPropriedade entity) {
		entity.setId(dto.getId());
		if (dto.getParceiroLocalId() != null) {
			entity.setParceiroLocal(parceiroLocalRepository.findById(dto.getParceiroLocalId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado local de parceiro com id " + dto.getParceiroLocalId())));
		}
		entity.setIdentificacao(dto.getIdentificacao());
	}

	@Override
	protected void setValuesToDto(ParceiroLocalPropriedade entity, ParceiroLocalPropriedadeDTO dto) {
		dto.setId(entity.getId());
		dto.setParceiroLocalId(entity.getParceiroLocal().getId());
		dto.setIdentificacao(entity.getIdentificacao());
	}
}
