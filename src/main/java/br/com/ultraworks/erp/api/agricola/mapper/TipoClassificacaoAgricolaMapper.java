package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.tipoclassificacaoagricola.TipoClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipoclassificacaoagricola.TipoClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.TipoClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TipoClassificacaoAgricolaMapper extends GenericMapper<TipoClassificacaoAgricola, TipoClassificacaoAgricolaDTO> {

	public TipoClassificacaoAgricolaMapper(TipoClassificacaoAgricolaRepository repository) {
		super(repository, TipoClassificacaoAgricola::new, TipoClassificacaoAgricolaDTO::new);
	}

	@Override
	protected void setValuesToEntity(TipoClassificacaoAgricolaDTO dto, TipoClassificacaoAgricola entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(TipoClassificacaoAgricola entity, TipoClassificacaoAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
	}
}
