package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.fixacaoagricola.FixacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.fixacaoagricola.FixacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.domain.fixacaoagricola.SelecaoRomaneioParaFixacaoRequest;
import br.com.ultraworks.erp.api.agricola.mapper.FixacaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.FixacaoAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/agricola/fixacao-agricola")
public class FixacaoAgricolaController extends GenericController<FixacaoAgricola, Long, FixacaoAgricolaDTO> {

	
	public FixacaoAgricolaController(FixacaoAgricolaService service, FixacaoAgricolaMapper mapper) {
		super(service, mapper);
	}
	
	@PostMapping("/servico/pesquisa-romaneios")
	@Transactional
	public ResponseEntity<?> pesquisaRomaneios(@RequestBody SelecaoRomaneioParaFixacaoRequest request) {
		((FixacaoAgricolaService) service).pesquisaRomaneios(request);
		return ResponseEntity.noContent().build();
	}

}