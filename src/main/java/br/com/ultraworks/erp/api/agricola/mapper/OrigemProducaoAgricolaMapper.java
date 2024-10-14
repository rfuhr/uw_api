package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.origemproducaoagricola.OrigemProducaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.origemproducaoagricola.OrigemProducaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.OrigemProducaoAgricolaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OrigemProducaoAgricolaMapper extends GenericMapper<OrigemProducaoAgricola, OrigemProducaoAgricolaDTO> {

	public OrigemProducaoAgricolaMapper(OrigemProducaoAgricolaRepository repository) {
		super(repository, OrigemProducaoAgricola::new, OrigemProducaoAgricolaDTO::new);
	}

	@Override
	protected void setValuesToEntity(OrigemProducaoAgricolaDTO dto, OrigemProducaoAgricola entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(OrigemProducaoAgricola entity, OrigemProducaoAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
	}
}
