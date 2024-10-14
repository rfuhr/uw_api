package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGeradorDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.FatoGeradorMapper;
import br.com.ultraworks.erp.api.financeiro.service.FatoGeradorService;
import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.dto.ResultPage;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/fato-gerador")
public class FatoGeradorController extends GenericController<FatoGerador, Long, FatoGeradorDTO> {

	public FatoGeradorController(FatoGeradorService service, FatoGeradorMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((FatoGeradorService) service).getProximoCodigo());
	}

	@PostMapping("/seletores/valido/contrato-agricola")
	public ResponseEntity<ResultPage<FatoGeradorDTO>> buscarFatoGeradorValidadoParaContrato(
			@RequestParam(value = "item") Long itemId,
			@RequestParam(value = "grupooperacaoagricola") Long grupoOperacaoAgricolaId,
			@RequestParam(value = "operacaointerna") Long operacaoInternaId, @RequestBody LazyParams params) {
		Page<FatoGerador> resultados = ((FatoGeradorService) service).buscarFatoGeradorValidadoParaContrato(itemId,
				grupoOperacaoAgricolaId, operacaoInternaId, params);

		if (!resultados.isEmpty()) {
			ResultPage<FatoGeradorDTO> resultado = new ResultPage<FatoGeradorDTO>(mapper.toDto(resultados.getContent()),
					resultados.getTotalElements(), resultados.getPageable().getPageNumber(),
					resultados.getPageable().getPageSize(), resultados.getTotalPages());
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.ok(ResultPage.empty());
		}
	}
}