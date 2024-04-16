package br.com.ultraworks.erp.api.tabela.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.cidade.Cidade;
import br.com.ultraworks.erp.api.tabela.domain.cidade.CidadeDTO;
import br.com.ultraworks.erp.api.tabela.mapper.CidadeMapper;
import br.com.ultraworks.erp.api.tabela.repository.CidadeRepository;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class CidadeService extends GenericService<Cidade, Long, CidadeDTO> {
	
	CidadeRepository repository;
	CidadeMapper mapper;
	
	@Autowired
	public CidadeService(CidadeRepository repository, CidadeMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
	}
	
	public Optional<Cidade> getByIbge(Long ibge) {
		return this.repository.findByCodigoIBGE(ibge);
	}
}
