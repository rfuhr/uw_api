package br.com.ultraworks.erp.api.agricola.mapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.basecalculoagricola.BaseCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipocalculoagricola.TipoCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipocalculoagricola.TipoCalculoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.TipoCalculoAgricolaRepository;
import br.com.ultraworks.erp.api.tabela.domain.indicadorDC.IndicadorDC;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TipoCalculoAgricolaMapper extends GenericMapper<TipoCalculoAgricola, TipoCalculoAgricolaDTO> {

	public TipoCalculoAgricolaMapper(TipoCalculoAgricolaRepository repository) {
		super(repository, TipoCalculoAgricola::new, TipoCalculoAgricolaDTO::new);
	}

	@Override
	protected void setValuesToEntity(TipoCalculoAgricolaDTO dto, TipoCalculoAgricola entity) {
		entity.setId(dto.getId());
		entity.setBaseCalculoAgricola(BaseCalculoAgricola.fromValue(dto.getBaseCalculoAgricola()));
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		if (StringUtils.isNotBlank(dto.getIndicadorDC()))
			entity.setIndicadorDC(IndicadorDC.fromValue(dto.getIndicadorDC()));
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(TipoCalculoAgricola entity, TipoCalculoAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setBaseCalculoAgricola(entity.getBaseCalculoAgricola().getValue());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		if (entity.getIndicadorDC() != null)
			dto.setIndicadorDC(entity.getIndicadorDC().getValue());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());

		dto.setBaseCalculoAgricolaNome(entity.getBaseCalculoAgricola().getName());
	}
}
