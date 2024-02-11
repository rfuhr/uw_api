package br.com.ultraworks.erp.api.relacionamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEmail.ParceiroLocalEmail;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEmail.ParceiroLocalEmailDTO;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroLocalEmailMapper;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalEmailRepository;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class ParceiroLocalEmailService extends GenericService<ParceiroLocalEmail, Long, ParceiroLocalEmailDTO> {

	ParceiroLocalEmailRepository repository;
	ParceiroLocalEmailMapper mapper;

	@Autowired
	public ParceiroLocalEmailService(ParceiroLocalEmailRepository repository, ParceiroLocalEmailMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<ParceiroLocalEmail> getAllByParceiroLocal(Long id) {
		return repository.findByParceiroLocalId(id);
	}

}
