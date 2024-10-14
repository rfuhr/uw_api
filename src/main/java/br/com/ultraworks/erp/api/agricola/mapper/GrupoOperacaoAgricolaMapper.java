package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.grupooperacaoagricola.GrupoOperacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.grupooperacaoagricola.GrupoOperacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.GrupoOperacaoAgricolaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class GrupoOperacaoAgricolaMapper extends GenericMapper<GrupoOperacaoAgricola, GrupoOperacaoAgricolaDTO> {

	public GrupoOperacaoAgricolaMapper(GrupoOperacaoAgricolaRepository repository) {
		super(repository, GrupoOperacaoAgricola::new, GrupoOperacaoAgricolaDTO::new);
	}

	@Override
	protected void setValuesToEntity(GrupoOperacaoAgricolaDTO dto, GrupoOperacaoAgricola entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(GrupoOperacaoAgricola entity, GrupoOperacaoAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
	}
}
