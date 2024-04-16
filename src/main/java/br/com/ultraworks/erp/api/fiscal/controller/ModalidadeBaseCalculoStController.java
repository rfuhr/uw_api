package br.com.ultraworks.erp.api.fiscal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculost.ModalidadeBaseCalculoSt;

@RestController
@RequestMapping("/fiscal/modalidade-base-calculo-st")
public class ModalidadeBaseCalculoStController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(ModalidadeBaseCalculoSt.valuesResponse());
	}

}