package br.com.ultraworks.erp.api.agricola.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.classificacaoagricola.RecalcularClassificacaoAgricolaRequest;
import br.com.ultraworks.erp.api.agricola.service.ClassificacaoAgricolaService;

@RestController
@RequestMapping("/agricola/classificacao-agricola")
public class ClassificacaoAgricolaController {

	private ClassificacaoAgricolaService classificacaoAgricolaService;

	public ClassificacaoAgricolaController(ClassificacaoAgricolaService classificacaoAgricolaService) {
		this.classificacaoAgricolaService = classificacaoAgricolaService;

	}

	@PostMapping("/servicos/recalcular")
	public ResponseEntity<?> recalcularClassificacaoAgricola(
			@RequestBody RecalcularClassificacaoAgricolaRequest request) {
		return ResponseEntity.ok(classificacaoAgricolaService.recalcular(request));
	}
	
	@PostMapping("/servicos/recalcular/all")
	public ResponseEntity<?> recalcularClassificacaoAgricola(
			@RequestBody List<RecalcularClassificacaoAgricolaRequest> request) {
		return ResponseEntity.ok(classificacaoAgricolaService.recalcular(request));
	}
}
