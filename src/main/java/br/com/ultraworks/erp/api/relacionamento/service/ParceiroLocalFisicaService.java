package br.com.ultraworks.erp.api.relacionamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalFisica.ParceiroLocalFisica;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalFisica.ParceiroLocalFisicaDTO;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroLocalFisicaMapper;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalFisicaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class ParceiroLocalFisicaService extends GenericService<ParceiroLocalFisica, Long, ParceiroLocalFisicaDTO> {
	
	ParceiroLocalFisicaRepository repository;
	ParceiroLocalFisicaMapper mapper;
	
	@Autowired
	public ParceiroLocalFisicaService(ParceiroLocalFisicaRepository repository, ParceiroLocalFisicaMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<ParceiroLocalFisica> getAllByParceiroLocal(Long id) {
		return repository.findByParceiroLocalId(id);
	}

}
