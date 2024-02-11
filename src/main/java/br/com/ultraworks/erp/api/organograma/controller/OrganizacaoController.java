package br.com.ultraworks.erp.api.organograma.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.organograma.domain.organizacao.Organizacao;
import br.com.ultraworks.erp.api.organograma.domain.organizacao.OrganizacaoDTO;
import br.com.ultraworks.erp.api.organograma.mapper.OrganizacaoMapper;
import br.com.ultraworks.erp.api.organograma.service.OrganizacaoService;
import br.com.ultraworks.erp.core.generics.GenericController;
import br.com.ultraworks.erp.core.util.ResponseUtil;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/organograma/organizacao")
public class OrganizacaoController extends GenericController<Organizacao, Long, OrganizacaoDTO> {

	private OrganizacaoService service;

	public OrganizacaoController(OrganizacaoService service, OrganizacaoMapper mapper) {
		super(service, mapper);
		this.service = service;
	}
	
	@Override
	@PostMapping
	@Transactional
	public ResponseEntity<OrganizacaoDTO> create(@RequestBody OrganizacaoDTO dto) {
		Organizacao entity = mapper.toNewEntity(dto);
		entity = service.save(entity);
		return ResponseUtil.wrapOrNotFound(Optional.of(mapper.toDto(entity)));
	}
}