package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.fixacaoagricola.FixacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.fixacaoagricola.FixacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.FixacaoAgricolaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class FixacaoAgricolaMapper extends GenericMapper<FixacaoAgricola, FixacaoAgricolaDTO> {

	public FixacaoAgricolaMapper(FixacaoAgricolaRepository repository) {
		super(repository, FixacaoAgricola::new, FixacaoAgricolaDTO::new);
	}

	@Override
	protected void setValuesToEntity(FixacaoAgricolaDTO dto, FixacaoAgricola entity) {
		entity.setId(dto.getId());
	}

	@Override
	protected void setValuesToDto(FixacaoAgricola entity, FixacaoAgricolaDTO dto) {
		dto.setId(entity.getId());
	}
}
