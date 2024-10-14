package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.predefinicaoprecoagricola.PredefinicaoPrecoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.predefinicaoprecoagricola.PredefinicaoPrecoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.PredefinicaoPrecoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.PredefinicaoPrecoAgricolaService;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGeradorDTO;
import br.com.ultraworks.erp.api.financeiro.service.FatoGeradorService;
import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.dto.ResultPage;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/predefinicao-preco-agricola")
public class PredefinicaoPrecoAgricolaController
		extends GenericController<PredefinicaoPrecoAgricola, Long, PredefinicaoPrecoAgricolaDTO> {

	public PredefinicaoPrecoAgricolaController(PredefinicaoPrecoAgricolaService service,
			PredefinicaoPrecoAgricolaMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((PredefinicaoPrecoAgricolaService) service).getProximoCodigo());
	}
	
	@PostMapping("/seletores/valido/operacao-agricola")
	public ResponseEntity<ResultPage<PredefinicaoPrecoAgricolaDTO>> buscarPredefinicaoValidadoParaOperacaoAgricola(
			@RequestParam(value = "item") Long itemId,
			@RequestParam(value = "tipoprecoagricola") Long tipoPrecoAgricolaId, @RequestBody LazyParams params) {
		Page<PredefinicaoPrecoAgricola> resultados = ((PredefinicaoPrecoAgricolaService) service).buscarPredefinicaoValidadoParaOperacaoAgricola(itemId,
				tipoPrecoAgricolaId, params);

		if (!resultados.isEmpty()) {
			ResultPage<PredefinicaoPrecoAgricolaDTO> resultado = new ResultPage<PredefinicaoPrecoAgricolaDTO>(mapper.toDto(resultados.getContent()),
					resultados.getTotalElements(), resultados.getPageable().getPageNumber(),
					resultados.getPageable().getPageSize(), resultados.getTotalPages());
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.ok(ResultPage.empty());
		}
	}

}