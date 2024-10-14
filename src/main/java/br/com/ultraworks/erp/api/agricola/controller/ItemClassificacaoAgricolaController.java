package br.com.ultraworks.erp.api.agricola.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola.ItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola.ItemClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola.ItemClassificacaoAgricolaRomaneioTelaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.ItemClassificacaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.ItemClassificacaoAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/item-classificacao-agricola")
public class ItemClassificacaoAgricolaController
		extends GenericController<ItemClassificacaoAgricola, Long, ItemClassificacaoAgricolaDTO> {

	public ItemClassificacaoAgricolaController(ItemClassificacaoAgricolaService service,
			ItemClassificacaoAgricolaMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((ItemClassificacaoAgricolaService) service).getProximoCodigo());
	}

	@GetMapping("/servicos/get-itens-to-romaneio")
	public ResponseEntity<List<ItemClassificacaoAgricolaRomaneioTelaDTO>> buscarParaRomaneio(
			@RequestParam(value = "item") Long itemId, @RequestParam(value = "dataromaneio") LocalDate dataRomaneio) {
		List<ItemClassificacaoAgricolaRomaneioTelaDTO> itens = ((ItemClassificacaoAgricolaService) service)
				.buscarItensParaRomaneio(itemId, dataRomaneio);

		if (!itens.isEmpty()) {
			return ResponseEntity.ok(itens);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}
}