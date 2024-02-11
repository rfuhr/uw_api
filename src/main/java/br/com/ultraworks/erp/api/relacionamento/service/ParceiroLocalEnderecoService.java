package br.com.ultraworks.erp.api.relacionamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEndereco;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEnderecoDTO;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroLocalEnderecoMapper;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalEnderecoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class ParceiroLocalEnderecoService extends GenericService<ParceiroLocalEndereco, Long, ParceiroLocalEnderecoDTO> {
	
	ParceiroLocalEnderecoRepository repository;
	ParceiroLocalEnderecoMapper mapper;
	
	@Autowired
	public ParceiroLocalEnderecoService(ParceiroLocalEnderecoRepository repository, ParceiroLocalEnderecoMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<ParceiroLocalEndereco> getAllByParceiroLocal(Long id) {
		return repository.findByParceiroLocalId(id);
	}

}
