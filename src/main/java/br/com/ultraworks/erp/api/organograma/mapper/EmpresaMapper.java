package br.com.ultraworks.erp.api.organograma.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.api.organograma.domain.empresa.EmpresaDTO;
import br.com.ultraworks.erp.api.organograma.repository.EmpresaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class EmpresaMapper extends GenericMapper<Empresa, EmpresaDTO> {

	public EmpresaMapper(EmpresaRepository empresaRepository) {
		super(empresaRepository, Empresa::new, EmpresaDTO::new);
    }

	@Override
	protected void setValuesToEntity(EmpresaDTO dto, Empresa entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
	}

	@Override
	protected void setValuesToDto(Empresa entity, EmpresaDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
		
	}	
}
