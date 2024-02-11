package br.com.ultraworks.erp.api.seguranca.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.seguranca.domain.funcionalidade.Funcionalidade;
import br.com.ultraworks.erp.api.seguranca.domain.funcionalidade.FuncionalidadeDTO;
import br.com.ultraworks.erp.api.seguranca.repository.FuncionalidadeRepository;
import br.com.ultraworks.erp.api.seguranca.service.ModuloService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class FuncionalidadeMapper extends GenericMapper<Funcionalidade, FuncionalidadeDTO> {

	

	public FuncionalidadeMapper(FuncionalidadeRepository funcionalidadeRepository) {
		super(funcionalidadeRepository, Funcionalidade::new, FuncionalidadeDTO::new);
    }

	@Override
	protected void setValuesToEntity(FuncionalidadeDTO dto, Funcionalidade entity) {
		entity.setId(dto.getId() < 0 ? null : dto.getId());
		entity.setNome(dto.getNome());
		entity.setTag(dto.getTag());
		entity.setCrud(dto.isCrud());
	}

	@Override
	protected void setValuesToDto(Funcionalidade entity, FuncionalidadeDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setTag(entity.getTag());
		dto.setModuloNome(entity.getModulo().getNome());
		dto.setCrud(entity.isCrud());
	}	
}
