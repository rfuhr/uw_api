package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.LancamentoTituloRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SelecaoBaixaTituloRequest;
import br.com.ultraworks.erp.api.financeiro.service.TituloService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/financeiro/titulo")
public class TituloController {

	private TituloService tituloService;

	public TituloController(TituloService tituloService) {
		this.tituloService = tituloService;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> create(@RequestBody LancamentoTituloRequest lancamentoTituloRequest) {
		tituloService.inserirTitulo(lancamentoTituloRequest);
		return ResponseEntity.accepted().build();
	}
	
	@PostMapping("/baixa/selecao")
	@Transactional
	public ResponseEntity<?> baixa(@RequestBody SelecaoBaixaTituloRequest selecaoBaixaTituloRequest) {
		tituloService.selecaoBaixa(selecaoBaixaTituloRequest);
		return ResponseEntity.accepted().build();
	}
}
