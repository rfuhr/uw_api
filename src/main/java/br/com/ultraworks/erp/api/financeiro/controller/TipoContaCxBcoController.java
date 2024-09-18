package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.tipocontacxbco.TipoContaCxBco;
import br.com.ultraworks.erp.api.financeiro.domain.tipocontacxbco.TipoContaCxBcoDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.TipoContaCxBcoMapper;
import br.com.ultraworks.erp.api.financeiro.service.TipoContaCxBcoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/tipo-conta-cxbco")
public class TipoContaCxBcoController extends GenericController<TipoContaCxBco, Long, TipoContaCxBcoDTO> {

	public TipoContaCxBcoController(TipoContaCxBcoService service, TipoContaCxBcoMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((TipoContaCxBcoService) service).getProximoCodigo());
	}	
}