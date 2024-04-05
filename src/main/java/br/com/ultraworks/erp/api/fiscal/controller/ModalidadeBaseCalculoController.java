package br.com.ultraworks.erp.api.fiscal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.ModalidadeBaseCalculo;

@RestController
@RequestMapping("/fiscal/modalidade-base-calculo")
public class ModalidadeBaseCalculoController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(ModalidadeBaseCalculo.valuesResponse());
	}

}