package br.com.ultraworks.erp.api.seguranca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.seguranca.domain.grupoautonomia.GrupoAutonomia;
import br.com.ultraworks.erp.api.seguranca.domain.grupoautonomia.GrupoAutonomiaDTO;
import br.com.ultraworks.erp.api.seguranca.mapper.GrupoAutonomiaMapper;
import br.com.ultraworks.erp.api.seguranca.repository.GrupoAutonomiaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class GrupoAutonomiaService extends GenericService<GrupoAutonomia, Long, GrupoAutonomiaDTO> {
	
	GrupoAutonomiaRepository repository;
	GrupoAutonomiaMapper mapper;
	
	@Autowired
	public GrupoAutonomiaService(GrupoAutonomiaRepository repository, GrupoAutonomiaMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
	}
}
