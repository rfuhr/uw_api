package br.com.ultraworks.erp.api.agricola.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricolaSelecaoResponse;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricoladesconto.CalculoDescontoContratoRequest;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricoladesconto.CalculoDescontoContratoResponse;
import br.com.ultraworks.erp.api.agricola.mapper.ContratoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.contratoagricola.ContratoAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/contrato-agricola")
public class ContratoAgricolaController extends GenericController<ContratoAgricola, Long, ContratoAgricolaDTO> {

	public ContratoAgricolaController(ContratoAgricolaService service, ContratoAgricolaMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("/servicos/get-contratos-selecao-romaneio")
	public ResponseEntity<List<ContratoAgricolaSelecaoResponse>> buscarParaRomaneio(
			@RequestParam(value = "parceiro") Long parceiroId, @RequestParam(value = "item") Long itemId,
			@RequestParam(value = "safra") Long safraId) {
		List<ContratoAgricolaSelecaoResponse> contratos = ((ContratoAgricolaService) service)
				.buscarParaRomaneio(parceiroId, itemId, safraId);

		if (!contratos.isEmpty()) {
			return ResponseEntity.ok(contratos);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}

	@GetMapping("/servicos/get-contratos-selecao-fixacao")
	public ResponseEntity<List<ContratoAgricolaSelecaoResponse>> buscarParaFixacao(
			@RequestParam(value = "parceiro") Long parceiroId, @RequestParam(value = "item") Long itemId) {
		List<ContratoAgricolaSelecaoResponse> contratos = ((ContratoAgricolaService) service)
				.buscarParaFixacao(parceiroId, itemId);

		if (!contratos.isEmpty()) {
			return ResponseEntity.ok(contratos);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}

	@PostMapping("/servicos/calcula-desconto")
	public ResponseEntity<List<CalculoDescontoContratoResponse>> calcularDescontos(
			@RequestBody CalculoDescontoContratoRequest request) {
		List<CalculoDescontoContratoResponse> descontos = ((ContratoAgricolaService) service).calcularDescontos(request);

		if (!descontos.isEmpty()) {
			return ResponseEntity.ok(descontos);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}
}