package br.com.ultraworks.erp.api.organograma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.api.organograma.domain.empresa.EmpresaDTO;
import br.com.ultraworks.erp.api.organograma.mapper.EmpresaMapper;
import br.com.ultraworks.erp.api.organograma.repository.EmpresaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class EmpresaService extends GenericService<Empresa, Long, EmpresaDTO> {
	
	EmpresaRepository repository;
	EmpresaMapper mapper;
	
	@Autowired
	public EmpresaService(EmpresaRepository repository, EmpresaMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
	}
}
