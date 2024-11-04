package br.com.ultraworks.erp.api.tabela.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.autorizacao.Autorizacao;
import br.com.ultraworks.erp.api.tabela.domain.autorizacao.AutorizacaoDTO;
import br.com.ultraworks.erp.api.tabela.mapper.AutorizacaoMapper;
import br.com.ultraworks.erp.api.tabela.service.AutorizacaoService;
import br.com.ultraworks.erp.core.generics.GenericController;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/tabela/autorizacao")
public class AutorizacaoController extends GenericController<Autorizacao, Long, AutorizacaoDTO> {

	public AutorizacaoController(AutorizacaoService service, AutorizacaoMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("/servicos/get-autorizacao-by-origem")
	public ResponseEntity<List<AutorizacaoDTO>> getAutorizacaoByOrigem(
			@RequestParam(value = "documentoorigem") Long documentoOrigemId,
			@RequestParam(value = "tipo") String tipo) {
		List<Autorizacao> autorizacoes = ((AutorizacaoService) service).getAutorizacaoByOrigem(documentoOrigemId, tipo);

		if (!autorizacoes.isEmpty()) {
			return ResponseEntity.ok(autorizacoes.stream().map(mapper::toDto).collect(Collectors.toList()));
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}
	
	@PatchMapping("{id}/autorizar")
	@Transactional
	public ResponseEntity<?> autorizar(
			@PathVariable(value = "id") Long id) {
		((AutorizacaoService) service).autorizar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("{id}/negar")
	@Transactional
	public ResponseEntity<?> negar(
			@PathVariable(value = "id") Long id) {
		((AutorizacaoService) service).negar(id);
		return ResponseEntity.noContent().build();
	}
}