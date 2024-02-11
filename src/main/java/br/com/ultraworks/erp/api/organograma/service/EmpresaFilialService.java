package br.com.ultraworks.erp.api.organograma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilialDTO;
import br.com.ultraworks.erp.api.organograma.mapper.EmpresaFilialMapper;
import br.com.ultraworks.erp.api.organograma.repository.EmpresaFilialRepository;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class EmpresaFilialService extends GenericService<EmpresaFilial, Long, EmpresaFilialDTO> {
	
	
	@Autowired
	public EmpresaFilialService(EmpresaFilialRepository repository, EmpresaFilialMapper mapper) {
		super(repository, mapper);
	}
}