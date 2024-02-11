package br.com.ultraworks.erp.api.seguranca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.seguranca.domain.funcionalidade.Funcionalidade;
import br.com.ultraworks.erp.api.seguranca.domain.funcionalidade.FuncionalidadeDTO;
import br.com.ultraworks.erp.api.seguranca.mapper.FuncionalidadeMapper;
import br.com.ultraworks.erp.api.seguranca.repository.FuncionalidadeRepository;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class FuncionalidadeService extends GenericService<Funcionalidade, Long, FuncionalidadeDTO> {
	
	FuncionalidadeRepository repository;
	FuncionalidadeMapper mapper;
	
	@Autowired
	public FuncionalidadeService(FuncionalidadeRepository repository, FuncionalidadeMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
	}
}
