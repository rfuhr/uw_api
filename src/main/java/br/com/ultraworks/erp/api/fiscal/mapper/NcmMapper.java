package br.com.ultraworks.erp.api.fiscal.mapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.ncm.Ncm;
import br.com.ultraworks.erp.api.fiscal.domain.ncm.NcmDTO;
import br.com.ultraworks.erp.api.fiscal.repository.NcmRepository;
import br.com.ultraworks.erp.api.tabela.domain.tiposinteticoanalitico.TipoSinteticoAnalitico;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class NcmMapper extends GenericMapper<Ncm, NcmDTO> {

	public NcmMapper(NcmRepository repository) {
		super(repository, Ncm::new, NcmDTO::new);
	}

	@Override
	protected void setValuesToEntity(NcmDTO dto, Ncm entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		if (StringUtils.isNotBlank(dto.getTipoSinteticoAnalitico()))
			entity.setTipoSinteticoAnalitico(TipoSinteticoAnalitico.fromValue(dto.getTipoSinteticoAnalitico()));
	}

	@Override
	protected void setValuesToDto(Ncm entity, NcmDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		if (entity.getTipoSinteticoAnalitico() != null) {
			dto.setTipoSinteticoAnalitico(entity.getTipoSinteticoAnalitico().getValue());
			dto.setTipoSinteticoAnaliticoName(entity.getTipoSinteticoAnalitico().getName());
		}
	}
}
