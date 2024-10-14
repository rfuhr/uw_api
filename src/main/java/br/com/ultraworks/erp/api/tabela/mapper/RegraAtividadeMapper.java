package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.regraatividade.RegraAtividade;
import br.com.ultraworks.erp.api.tabela.domain.regraatividade.RegraAtividadeDTO;
import br.com.ultraworks.erp.api.tabela.repository.RegraAtividadeRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class RegraAtividadeMapper extends GenericMapper<RegraAtividade, RegraAtividadeDTO> {

	public RegraAtividadeMapper(RegraAtividadeRepository repository) {
		super(repository, RegraAtividade::new, RegraAtividadeDTO::new);
	}

	@Override
	protected void setValuesToEntity(RegraAtividadeDTO dto, RegraAtividade entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setCodigo(dto.getCodigo());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(RegraAtividade entity, RegraAtividadeDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setCodigo(entity.getCodigo());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
	}
}
