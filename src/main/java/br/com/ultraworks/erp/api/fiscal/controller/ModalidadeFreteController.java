package br.com.ultraworks.erp.api.fiscal.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.modalidadefrete.ModalidadeFrete;

@RestController
@RequestMapping("/fiscal/modalidade-frete")
public class ModalidadeFreteController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(ModalidadeFrete.valuesResponse());
	}

}