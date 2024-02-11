package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.pais.Pais;
import br.com.ultraworks.erp.api.tabela.domain.pais.PaisDTO;
import br.com.ultraworks.erp.api.tabela.repository.PaisRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class PaisMapper extends GenericMapper<Pais, PaisDTO> {

	public PaisMapper(PaisRepository paisRepository) {
		super(paisRepository, Pais::new, PaisDTO::new);
    }

	@Override
	protected void setValuesToEntity(PaisDTO dto, Pais entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
		entity.setCodigoIBGE(dto.getCodigoIBGE());
		entity.setCodigoSiscomex(dto.getCodigoSiscomex());
	}

	@Override
	protected void setValuesToDto(Pais entity, PaisDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
		dto.setCodigoIBGE(entity.getCodigoIBGE());
		dto.setCodigoSiscomex(entity.getCodigoSiscomex());
		
	}	
}
