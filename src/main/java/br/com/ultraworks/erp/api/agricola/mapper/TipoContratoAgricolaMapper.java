package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.caracteristicacontratoagricola.CaracteristicaContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipocontratoagricola.TipoContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipocontratoagricola.TipoContratoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.TipoContratoAgricolaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TipoContratoAgricolaMapper extends GenericMapper<TipoContratoAgricola, TipoContratoAgricolaDTO> {

	public TipoContratoAgricolaMapper(TipoContratoAgricolaRepository repository) {
		super(repository, TipoContratoAgricola::new, TipoContratoAgricolaDTO::new);
	}

	@Override
	protected void setValuesToEntity(TipoContratoAgricolaDTO dto, TipoContratoAgricola entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
		entity.setCaracteristicaContratoAgricola(
				CaracteristicaContratoAgricola.fromValue(dto.getCaracteristicaContratoAgricola()));
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(TipoContratoAgricola entity, TipoContratoAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
		dto.setCaracteristicaContratoAgricola(entity.getCaracteristicaContratoAgricola().getValue());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		dto.setCaracteristicaContratoAgricolaNome(entity.getCaracteristicaContratoAgricola().getName());
	}
}
