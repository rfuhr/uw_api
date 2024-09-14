package br.com.ultraworks.erp.api.agricola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.balanca.Balanca;
import br.com.ultraworks.erp.api.agricola.domain.balanca.BalancaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.BalancaMapper;
import br.com.ultraworks.erp.api.agricola.repository.BalancaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class BalancaService extends GenericService<Balanca, Long, BalancaDTO> {

	@Autowired
	public BalancaService(BalancaRepository repository, BalancaMapper mapper) {
		super(repository, mapper);
	}

	public Balanca getByEmpresaFilialId(Long empresaFilialId) {
		return ((BalancaRepository) repository).findByEmpresaFilialId(empresaFilialId);
	}

	

}