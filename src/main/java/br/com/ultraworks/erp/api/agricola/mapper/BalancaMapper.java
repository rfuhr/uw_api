package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.balanca.Balanca;
import br.com.ultraworks.erp.api.agricola.domain.balanca.BalancaDTO;
import br.com.ultraworks.erp.api.agricola.repository.BalancaRepository;
import br.com.ultraworks.erp.api.organograma.repository.EmpresaFilialRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class BalancaMapper extends GenericMapper<Balanca, BalancaDTO> {

	BalancaRepository balancaRepository;
	EmpresaFilialRepository empresaFilialRepository;
	
	public BalancaMapper(BalancaRepository balancaRepository, 
			EmpresaFilialRepository empresaFilialRepository) {
		super(balancaRepository, Balanca::new, BalancaDTO::new);
		this.empresaFilialRepository = empresaFilialRepository;
	}

	@Override
	protected void setValuesToEntity(BalancaDTO dto, Balanca entity) {
		entity.setId(dto.getId());
		entity.setEmpresaFilial(empresaFilialRepository.findById(dto.getEmpresaFilialId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado empresa filial com id " + dto.getEmpresaFilialId())));
		entity.setNome(dto.getNome());
		entity.setPorta(dto.getPorta());
		entity.setVelocidade(dto.getVelocidade());
	}

	@Override
	protected void setValuesToDto(Balanca entity, BalancaDTO dto) {
		dto.setId(entity.getId());
		dto.setEmpresaFilialId(entity.getEmpresaFilial().getId());
		dto.setNome(entity.getNome());
		dto.setPorta(entity.getPorta());
		dto.setVelocidade(entity.getVelocidade());
		
		dto.setEmpresaFilialNome(entity.getEmpresaFilial().getNome());
	}
}
