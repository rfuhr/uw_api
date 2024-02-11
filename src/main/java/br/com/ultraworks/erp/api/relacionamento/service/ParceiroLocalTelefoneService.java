package br.com.ultraworks.erp.api.relacionamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTelefone.ParceiroLocalTelefone;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTelefone.ParceiroLocalTelefoneDTO;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroLocalTelefoneMapper;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalTelefoneRepository;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class ParceiroLocalTelefoneService extends GenericService<ParceiroLocalTelefone, Long, ParceiroLocalTelefoneDTO> {
	
	ParceiroLocalTelefoneRepository repository;
	ParceiroLocalTelefoneMapper mapper;
	
	@Autowired
	public ParceiroLocalTelefoneService(ParceiroLocalTelefoneRepository repository, ParceiroLocalTelefoneMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<ParceiroLocalTelefone> getAllByParceiroLocal(Long id) {
		return repository.findByParceiroLocalId(id);
	}

}
