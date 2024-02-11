package br.com.ultraworks.erp.api.financeiro.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.titulo.TituloDTO;
import br.com.ultraworks.erp.core.util.ResponseUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/financeiro/titulo")
public class TituloController {

	@PostMapping
	@Transactional
	public ResponseEntity<TituloDTO> create(@Valid @RequestBody TituloDTO dto) {
		return ResponseUtil.wrapOrNotFound(Optional.of(dto));
	}
}
