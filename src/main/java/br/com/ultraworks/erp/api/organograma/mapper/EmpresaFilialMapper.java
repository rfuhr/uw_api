package br.com.ultraworks.erp.api.organograma.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.api.organograma.domain.empresa.EmpresaDTO;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilialDTO;
import br.com.ultraworks.erp.api.organograma.repository.EmpresaFilialRepository;
import br.com.ultraworks.erp.api.organograma.service.EmpresaService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class EmpresaFilialMapper extends GenericMapper<EmpresaFilial, EmpresaFilialDTO> {


	private EmpresaService empresaService;

	public EmpresaFilialMapper(EmpresaFilialRepository empresaFilialRepository, EmpresaService empresaService) {
		super(empresaFilialRepository, EmpresaFilial::new, EmpresaFilialDTO::new);
		this.empresaService = empresaService;
    }

	@Override
	protected void setValuesToEntity(EmpresaFilialDTO dto, EmpresaFilial entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
		entity.setEmpresa(empresaService.getById(dto.getEmpresaId()).orElseThrow(RegisterNotFoundException::new));
		entity.setGeneral(dto.isGeneral());
	}

	@Override
	protected void setValuesToDto(EmpresaFilial entity, EmpresaFilialDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
		dto.setEmpresaId(entity.getEmpresa().getId());
		dto.setGeneral(entity.isGeneral());
		
	}	
}