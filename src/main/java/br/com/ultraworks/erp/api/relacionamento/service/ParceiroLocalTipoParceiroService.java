package br.com.ultraworks.erp.api.relacionamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTipoParceiro.ParceiroLocalTipoParceiro;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTipoParceiro.ParceiroLocalTipoParceiroDTO;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroLocalTipoParceiroMapper;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalTipoParceiroRepository;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class ParceiroLocalTipoParceiroService extends GenericService<ParceiroLocalTipoParceiro, Long, ParceiroLocalTipoParceiroDTO> {
	
	ParceiroLocalTipoParceiroRepository repository;
	ParceiroLocalTipoParceiroMapper mapper;
	
	@Autowired
	public ParceiroLocalTipoParceiroService(ParceiroLocalTipoParceiroRepository repository, ParceiroLocalTipoParceiroMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<ParceiroLocalTipoParceiro> getAllByParceiroLocal(Long id) {
		return repository.findByParceiroLocalId(id);
	}

}
