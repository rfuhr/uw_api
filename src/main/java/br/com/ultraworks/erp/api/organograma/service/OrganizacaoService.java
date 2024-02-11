package br.com.ultraworks.erp.api.organograma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.organograma.domain.organizacao.Organizacao;
import br.com.ultraworks.erp.api.organograma.domain.organizacao.OrganizacaoDTO;
import br.com.ultraworks.erp.api.organograma.mapper.OrganizacaoMapper;
import br.com.ultraworks.erp.api.organograma.repository.OrganizacaoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;

@Service
public class OrganizacaoService  extends GenericService<Organizacao, Long, OrganizacaoDTO> {
	
	OrganizacaoRepository repository;
	OrganizacaoMapper mapper;
	
	@Autowired
	public OrganizacaoService(OrganizacaoRepository repository, OrganizacaoMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
		this.mapper = mapper;
	}
}
