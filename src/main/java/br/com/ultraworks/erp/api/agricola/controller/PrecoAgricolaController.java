package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.precoagricola.BuscaPrecoAgricolaRequest;
import br.com.ultraworks.erp.api.agricola.domain.precoagricola.PrecoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.precoagricola.PrecoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.PrecoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.PrecoAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/agricola/preco-agricola")
public class PrecoAgricolaController extends GenericController<PrecoAgricola, Long, PrecoAgricolaDTO> {

	public PrecoAgricolaController(PrecoAgricolaService service, PrecoAgricolaMapper mapper) {
		super(service, mapper);
	}

	@PostMapping("/servico/busca-preco-vigente")
	@Transactional
	public ResponseEntity<?> buscaPrecoVigente(@RequestBody BuscaPrecoAgricolaRequest request) {
		PrecoAgricola precoAgricola = ((PrecoAgricolaService) service).getPrecoAgricolaVigente(request.getItemId(),
				request.getTipoPrecoAgricolaId(), request.getEmpresaId(), request.getEmpresaFilialId(),
				request.getDepartamentoId(), request.getPredefinicaoPrecoAgricolaId(), request.getNivelClass1Id(),
				request.getNivelClass2Id(), request.getNivelClass3Id(), request.getNivelClass4Id(),
				request.getDataBase());
		if (precoAgricola != null)
			return ResponseEntity.ok(precoAgricola);
		else
			return ResponseEntity.notFound().build();
	}
}