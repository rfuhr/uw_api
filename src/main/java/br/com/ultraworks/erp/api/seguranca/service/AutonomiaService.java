package br.com.ultraworks.erp.api.seguranca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.seguranca.domain.autonomia.Autonomia;
import br.com.ultraworks.erp.api.seguranca.domain.autonomia.AutonomiaDTO;
import br.com.ultraworks.erp.api.seguranca.mapper.AutonomiaMapper;
import br.com.ultraworks.erp.api.seguranca.repository.AutonomiaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class AutonomiaService extends GenericService<Autonomia, Long, AutonomiaDTO> {

	AutonomiaRepository repository;
	AutonomiaMapper mapper;

	@Autowired
	public AutonomiaService(AutonomiaRepository repository, AutonomiaMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
	}
}
