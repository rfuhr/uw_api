package br.com.ultraworks.erp.api.relacionamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalJuridica.ParceiroLocalJuridica;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalJuridica.ParceiroLocalJuridicaDTO;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroLocalJuridicaMapper;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalJuridicaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class ParceiroLocalJuridicaService extends GenericService<ParceiroLocalJuridica, Long, ParceiroLocalJuridicaDTO> {
	
	ParceiroLocalJuridicaRepository repository;
	ParceiroLocalJuridicaMapper mapper;
	
	@Autowired
	public ParceiroLocalJuridicaService(ParceiroLocalJuridicaRepository repository, ParceiroLocalJuridicaMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<ParceiroLocalJuridica> getAllByParceiroLocal(Long id) {
		return repository.findByParceiroLocalId(id);
	}

}
