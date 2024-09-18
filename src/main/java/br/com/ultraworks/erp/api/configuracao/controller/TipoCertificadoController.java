package br.com.ultraworks.erp.api.configuracao.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.configuracao.domain.tipocertificado.TipoCertificado;

@RestController
@RequestMapping("/configuracao/tipo-certificado")
public class TipoCertificadoController {

	@GetMapping
	public ResponseEntity<List<?>> getAll() {
		return ResponseEntity.ok(TipoCertificado.valuesResponse());
	}

}