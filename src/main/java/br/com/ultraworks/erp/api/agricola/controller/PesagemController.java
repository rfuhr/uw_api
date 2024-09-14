package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.pesagem.Pesagem;
import br.com.ultraworks.erp.api.agricola.domain.pesagem.PesagemDTO;
import br.com.ultraworks.erp.api.agricola.mapper.PesagemMapper;
import br.com.ultraworks.erp.api.agricola.service.PesagemService;
import br.com.ultraworks.erp.core.generics.GenericController;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/agricola/pesagem")
public class PesagemController extends GenericController<Pesagem, Long, PesagemDTO> {

	public PesagemController(PesagemService service, PesagemMapper mapper) {
		super(service, mapper);
	}

	@PutMapping("{id}/finalizar")
	@Transactional
	public ResponseEntity<PesagemDTO> finalizar(@PathVariable Long id) {
		((PesagemService)service).finalizarPesagem(id);
		return ResponseEntity.noContent().build();
	}	
}