package br.com.ultraworks.erp.api.tabela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.pais.Pais;
import br.com.ultraworks.erp.api.tabela.domain.pais.PaisDTO;
import br.com.ultraworks.erp.api.tabela.mapper.PaisMapper;
import br.com.ultraworks.erp.api.tabela.repository.PaisRepository;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class PaisService extends GenericService<Pais, Long, PaisDTO> {
	
	PaisRepository paisRepository;
	PaisMapper paisMapper;
	
	@Autowired
	public PaisService(PaisRepository paisRepository, PaisMapper paisMapper) {
		super(paisRepository, paisMapper);
		this.paisRepository = paisRepository;
		this.paisMapper = paisMapper;
	}
}
