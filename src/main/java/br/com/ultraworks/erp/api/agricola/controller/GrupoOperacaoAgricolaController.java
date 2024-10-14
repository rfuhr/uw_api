package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.grupooperacaoagricola.GrupoOperacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.grupooperacaoagricola.GrupoOperacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.GrupoOperacaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.GrupoOperacaoAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/grupo-operacao-agricola")
public class GrupoOperacaoAgricolaController
		extends GenericController<GrupoOperacaoAgricola, Long, GrupoOperacaoAgricolaDTO> {

	public GrupoOperacaoAgricolaController(GrupoOperacaoAgricolaService service, GrupoOperacaoAgricolaMapper mapper) {
		super(service, mapper);
	}
	
	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((GrupoOperacaoAgricolaService) service).getProximoCodigo());
	}

}