package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.service.ItemService;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTituloDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.TipoTituloMapper;
import br.com.ultraworks.erp.api.financeiro.service.TipoTituloService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/tipo-titulo")
public class TipoTituloController extends GenericController<TipoTitulo, Long, TipoTituloDTO> {

	public TipoTituloController(TipoTituloService service, TipoTituloMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((TipoTituloService) service).getProximoCodigo());
	}
}