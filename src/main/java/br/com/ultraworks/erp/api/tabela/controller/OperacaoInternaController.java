package br.com.ultraworks.erp.api.tabela.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.operacaointerna.OperacaoInternaDTO;
import br.com.ultraworks.erp.api.tabela.mapper.OperacaoInternaMapper;
import br.com.ultraworks.erp.api.tabela.service.OperacaoInternaService;
import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.dto.ResultPage;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/operacao-interna")
public class OperacaoInternaController extends GenericController<OperacaoInterna, Long, OperacaoInternaDTO> {

	public OperacaoInternaController(OperacaoInternaService service, OperacaoInternaMapper mapper) {
		super(service, mapper);
	}

	@PostMapping("/seletores/operacao-interna-agricola/valido/romaneio")
	public ResponseEntity<ResultPage<OperacaoInternaDTO>> buscarOperacaoInterValidadoParaRomaneio(
			@RequestParam (value = "item") Long itemId, @RequestParam (value = "grupooperacaoagricola") Long grupoOperacaoAgricolaId, @RequestBody LazyParams params) {
		Page<OperacaoInterna> resultados = ((OperacaoInternaService) service)
				.buscarOperacaoInterValidadoParaRomaneio(itemId, grupoOperacaoAgricolaId, params);

		if (!resultados.isEmpty()) {
			ResultPage<OperacaoInternaDTO> resultado = new ResultPage<OperacaoInternaDTO>(
					mapper.toDto(resultados.getContent()), resultados.getTotalElements(),
					resultados.getPageable().getPageNumber(), resultados.getPageable().getPageSize(),
					resultados.getTotalPages());
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.ok(ResultPage.empty());
		}
	}
	
	@PostMapping("/seletores/operacao-interna-agricola/fixacao")
	public ResponseEntity<ResultPage<OperacaoInternaDTO>> buscarOperacaoInterParaFixacao(
			@RequestParam (value = "item") Long itemId, @RequestParam (value = "grupooperacaoagricola") Long grupoOperacaoAgricolaId, @RequestBody LazyParams params) {
		Page<OperacaoInterna> resultados = ((OperacaoInternaService) service)
				.buscarIdsOperacaoInterParaFixacao( params);

		if (!resultados.isEmpty()) {
			ResultPage<OperacaoInternaDTO> resultado = new ResultPage<OperacaoInternaDTO>(
					mapper.toDto(resultados.getContent()), resultados.getTotalElements(),
					resultados.getPageable().getPageNumber(), resultados.getPageable().getPageSize(),
					resultados.getTotalPages());
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.ok(ResultPage.empty());
		}
	}
	
	@PostMapping("/seletores/operacao-interna-agricola/valido/contrato")
	public ResponseEntity<ResultPage<OperacaoInternaDTO>> buscarOperacaoInterValidadoParaContrato(
			@RequestParam (value = "item") Long itemId, @RequestParam (value = "grupooperacaoagricola") Long grupoOperacaoAgricolaId, @RequestBody LazyParams params) {
		Page<OperacaoInterna> resultados = ((OperacaoInternaService) service)
				.buscarOperacaoInterValidadoParaContrato(itemId, grupoOperacaoAgricolaId, params);

		if (!resultados.isEmpty()) {
			ResultPage<OperacaoInternaDTO> resultado = new ResultPage<OperacaoInternaDTO>(
					mapper.toDto(resultados.getContent()), resultados.getTotalElements(),
					resultados.getPageable().getPageNumber(), resultados.getPageable().getPageSize(),
					resultados.getTotalPages());
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.ok(ResultPage.empty());
		}
	}
	
	

}