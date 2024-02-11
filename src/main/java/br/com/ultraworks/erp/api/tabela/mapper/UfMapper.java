package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.uf.Uf;
import br.com.ultraworks.erp.api.tabela.domain.uf.UfDTO;
import br.com.ultraworks.erp.api.tabela.repository.UfRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class UfMapper extends GenericMapper<Uf, UfDTO> {

	public UfMapper(UfRepository ufRepository) {
		super(ufRepository, Uf::new, UfDTO::new);
    }

	@Override
	protected void setValuesToEntity(UfDTO dto, Uf entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
		entity.setCodigo(dto.getCodigo());
	}

	@Override
	protected void setValuesToDto(Uf entity, UfDTO dto) {
		dto.setId(entity.getId());
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
		dto.setCodigo(entity.getCodigo());
		
	}	
}
