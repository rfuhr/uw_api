package br.com.ultraworks.erp.api.tabela.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.uf.Uf;
import br.com.ultraworks.erp.api.tabela.domain.uf.UfDTO;
import br.com.ultraworks.erp.api.tabela.mapper.UfMapper;
import br.com.ultraworks.erp.api.tabela.service.UfService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/uf")
public class UfController extends GenericController<Uf, Long, UfDTO> {

	private UfService service;
	private UfMapper mapper;

	public UfController(UfService service, UfMapper mapper) {
		super(service, mapper);
		this.service = service;
		this.mapper = mapper;
	}
	
	@GetMapping("/sigla/{sigla}")
	public ResponseEntity<UfDTO> getBySigla(@PathVariable String sigla) {
		Optional<Uf> uf = service.getBySigla(sigla);
		if (uf.isPresent()) {
			return ResponseEntity.ok(mapper.toDto(uf.get()));
		} else {
			throw  new RegisterNotFoundException("Não encontrado uf");
		}
	};
	

}
