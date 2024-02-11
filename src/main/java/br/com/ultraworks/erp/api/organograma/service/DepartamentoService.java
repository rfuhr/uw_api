package br.com.ultraworks.erp.api.organograma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.domain.departamento.DepartamentoDTO;
import br.com.ultraworks.erp.api.organograma.mapper.DepartamentoMapper;
import br.com.ultraworks.erp.api.organograma.repository.DepartamentoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class DepartamentoService extends GenericService<Departamento, Long, DepartamentoDTO> {
	
	
	@Autowired
	public DepartamentoService(DepartamentoRepository repository, DepartamentoMapper mapper) {
		super(repository, mapper);
	}
}