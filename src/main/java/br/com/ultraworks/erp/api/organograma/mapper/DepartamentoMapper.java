package br.com.ultraworks.erp.api.organograma.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.domain.departamento.DepartamentoDTO;
import br.com.ultraworks.erp.api.organograma.repository.DepartamentoRepository;
import br.com.ultraworks.erp.api.organograma.service.EmpresaFilialService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class DepartamentoMapper extends GenericMapper<Departamento, DepartamentoDTO> {


	private EmpresaFilialService empresaFilialService;

	public DepartamentoMapper(DepartamentoRepository DepartamentoRepository, EmpresaFilialService empresaFilialService) {
		super(DepartamentoRepository, Departamento::new, DepartamentoDTO::new);
		this.empresaFilialService = empresaFilialService;
    }

	@Override
	protected void setValuesToEntity(DepartamentoDTO dto, Departamento entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
		entity.setEmpresaFilial(empresaFilialService.getById(dto.getEmpresaFilialId()).orElseThrow(RegisterNotFoundException::new));
	}

	@Override
	protected void setValuesToDto(Departamento entity, DepartamentoDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
		dto.setEmpresaFilialId(entity.getEmpresaFilial().getId());
		dto.setEmpresaFilialNome(entity.getEmpresaFilial().getNome());
		
		dto.setEmpresaId(entity.getEmpresaFilial().getEmpresa().getId());
		dto.setEmpresaNome(entity.getEmpresaFilial().getEmpresa().getNome());
		dto.setParceiroLocalFilialId(entity.getEmpresaFilial().getParceiroLocal().getId());
		
	}	
}