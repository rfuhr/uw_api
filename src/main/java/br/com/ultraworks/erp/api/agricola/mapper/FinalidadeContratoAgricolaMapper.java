package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.finalidadecontratoagricola.FinalidadeContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.finalidadecontratoagricola.FinalidadeContratoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.FinalidadeContratoAgricolaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class FinalidadeContratoAgricolaMapper
		extends GenericMapper<FinalidadeContratoAgricola, FinalidadeContratoAgricolaDTO> {

	public FinalidadeContratoAgricolaMapper(FinalidadeContratoAgricolaRepository repository) {
		super(repository, FinalidadeContratoAgricola::new, FinalidadeContratoAgricolaDTO::new);
	}

	@Override
	protected void setValuesToEntity(FinalidadeContratoAgricolaDTO dto, FinalidadeContratoAgricola entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(FinalidadeContratoAgricola entity, FinalidadeContratoAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
	}
}
