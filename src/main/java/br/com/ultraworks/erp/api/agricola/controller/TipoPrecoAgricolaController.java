package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.tipoprecoagricola.TipoPrecoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipoprecoagricola.TipoPrecoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.TipoPrecoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.TipoPrecoAgricolaService;
import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.dto.ResultPage;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/tipo-preco-agricola")
public class TipoPrecoAgricolaController extends GenericController<TipoPrecoAgricola, Long, TipoPrecoAgricolaDTO> {

	public TipoPrecoAgricolaController(TipoPrecoAgricolaService service, TipoPrecoAgricolaMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((TipoPrecoAgricolaService) service).getProximoCodigo());
	}

	@PostMapping("/seletores/valido/operacao-agricola")
	public ResponseEntity<ResultPage<TipoPrecoAgricolaDTO>> buscarTipoPrecoValidadoParaOperacaoAgricola(
			@RequestParam(value = "item") Long itemId,
			@RequestParam(value = "grupooperacaoagricola") Long grupoOperacaoAgricolaId,
			@RequestParam(value = "operacaointerna") Long operacaoInternaId, @RequestBody LazyParams params) {
		Page<TipoPrecoAgricola> resultados = ((TipoPrecoAgricolaService) service).buscarTipoPrecoValidadoParaOperacaoAgricola(itemId,
				grupoOperacaoAgricolaId, operacaoInternaId, params);

		if (!resultados.isEmpty()) {
			ResultPage<TipoPrecoAgricolaDTO> resultado = new ResultPage<TipoPrecoAgricolaDTO>(mapper.toDto(resultados.getContent()),
					resultados.getTotalElements(), resultados.getPageable().getPageNumber(),
					resultados.getPageable().getPageSize(), resultados.getTotalPages());
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.ok(ResultPage.empty());
		}
	}
}