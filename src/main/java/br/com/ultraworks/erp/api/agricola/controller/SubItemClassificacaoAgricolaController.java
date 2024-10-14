package br.com.ultraworks.erp.api.agricola.controller;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.subitemclassificacaoagricola.SubItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.subitemclassificacaoagricola.SubItemClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.SubItemClassificacaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.SubItemClassificacaoAgricolaService;
import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.dto.ResultPage;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/sub-item-classificacao-agricola")
public class SubItemClassificacaoAgricolaController
		extends GenericController<SubItemClassificacaoAgricola, Long, SubItemClassificacaoAgricolaDTO> {

	public SubItemClassificacaoAgricolaController(SubItemClassificacaoAgricolaService service,
			SubItemClassificacaoAgricolaMapper mapper) {
		super(service, mapper);
	}

	@PostMapping("/seletores/get-itens-to-romaneio")
	public ResponseEntity<ResultPage<SubItemClassificacaoAgricolaDTO>> buscarItensParaRomaneio(
			@RequestParam(value = "item") Long itemId,
			@RequestParam(value = "itemclassificacaoagricola") Long itemClassificacaoAgricolaId,
			@RequestParam(value = "dataromaneio") LocalDate dataRomaneio, @RequestBody LazyParams params) {
		Page<SubItemClassificacaoAgricola> resultados = ((SubItemClassificacaoAgricolaService) service)
				.buscarItensParaRomaneio(itemId, itemClassificacaoAgricolaId, dataRomaneio, params);

		if (!resultados.isEmpty()) {
			ResultPage<SubItemClassificacaoAgricolaDTO> resultado = new ResultPage<SubItemClassificacaoAgricolaDTO>(
					mapper.toDto(resultados.getContent()), resultados.getTotalElements(),
					resultados.getPageable().getPageNumber(), resultados.getPageable().getPageSize(),
					resultados.getTotalPages());
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.ok(ResultPage.empty());
		}
	}
}