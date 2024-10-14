package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.grupoclassificacaoagricola.GrupoClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.grupoclassificacaoagricola.GrupoClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.GrupoClassificacaoAgricolaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class GrupoClassificacaoAgricolaMapper
		extends GenericMapper<GrupoClassificacaoAgricola, GrupoClassificacaoAgricolaDTO> {

	public GrupoClassificacaoAgricolaMapper(GrupoClassificacaoAgricolaRepository repository) {
		super(repository, GrupoClassificacaoAgricola::new, GrupoClassificacaoAgricolaDTO::new);
	}

	@Override
	protected void setValuesToEntity(GrupoClassificacaoAgricolaDTO dto, GrupoClassificacaoAgricola entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(GrupoClassificacaoAgricola entity, GrupoClassificacaoAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
	}
}
