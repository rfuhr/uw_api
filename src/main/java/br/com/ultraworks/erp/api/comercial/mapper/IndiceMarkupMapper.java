package br.com.ultraworks.erp.api.comercial.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.comercial.domain.indicemarkup.IndiceMarkup;
import br.com.ultraworks.erp.api.comercial.domain.indicemarkup.IndiceMarkupDTO;
import br.com.ultraworks.erp.api.comercial.repository.IndiceMarkupRepository;
import br.com.ultraworks.erp.api.fiscal.domain.tipotributo.TipoTributo;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class IndiceMarkupMapper extends GenericMapper<IndiceMarkup, IndiceMarkupDTO> {

	public IndiceMarkupMapper(IndiceMarkupRepository repository) {
		super(repository, IndiceMarkup::new, IndiceMarkupDTO::new);
	}

	@Override
	protected void setValuesToEntity(IndiceMarkupDTO dto, IndiceMarkup entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setTributo(dto.isTributo());
		if (dto.getTipoTributo() != null)
			entity.setTipoTributo(TipoTributo.fromValue(dto.getTipoTributo()));
	}

	@Override
	protected void setValuesToDto(IndiceMarkup entity, IndiceMarkupDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setTributo(entity.isTributo());
		if (entity.getTipoTributo() != null)
			dto.setTipoTributo(entity.getTipoTributo().getValue());
	}
}
