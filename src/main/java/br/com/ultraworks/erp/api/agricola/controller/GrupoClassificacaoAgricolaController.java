package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.grupoclassificacaoagricola.GrupoClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.grupoclassificacaoagricola.GrupoClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.GrupoClassificacaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.GrupoClassificacaoAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/grupo-classificacao-agricola")
public class GrupoClassificacaoAgricolaController
		extends GenericController<GrupoClassificacaoAgricola, Long, GrupoClassificacaoAgricolaDTO> {

	public GrupoClassificacaoAgricolaController(GrupoClassificacaoAgricolaService service,
			GrupoClassificacaoAgricolaMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((GrupoClassificacaoAgricolaService) service).getProximoCodigo());
	}

}