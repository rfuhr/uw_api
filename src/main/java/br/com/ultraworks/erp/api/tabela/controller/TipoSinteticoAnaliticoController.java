package br.com.ultraworks.erp.api.tabela.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.tiposinteticoanalitico.TipoSinteticoAnalitico;

@RestController
@RequestMapping("/tabela/tipo-sintetico-analitico")
public class TipoSinteticoAnaliticoController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(TipoSinteticoAnalitico.valuesResponse());
	}

}