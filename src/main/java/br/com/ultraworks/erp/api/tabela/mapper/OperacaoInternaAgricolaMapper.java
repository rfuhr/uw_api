package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.operacaointernaagricola.OperacaoInternaAgricola;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernaagricola.OperacaoInternaAgricolaDTO;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaAgricolaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OperacaoInternaAgricolaMapper extends GenericMapper<OperacaoInternaAgricola, OperacaoInternaAgricolaDTO> {

	public OperacaoInternaAgricolaMapper(OperacaoInternaAgricolaRepository repository) {
		super(repository, OperacaoInternaAgricola::new, OperacaoInternaAgricolaDTO::new);
	}

	@Override
	protected void setValuesToEntity(OperacaoInternaAgricolaDTO dto, OperacaoInternaAgricola entity) {
		entity.setId(dto.getId());
		entity.setSelecionaPesagem(dto.isSelecionaPesagem());
	}

	@Override
	protected void setValuesToDto(OperacaoInternaAgricola entity, OperacaoInternaAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setSelecionaPesagem(entity.isSelecionaPesagem());
	}
}
