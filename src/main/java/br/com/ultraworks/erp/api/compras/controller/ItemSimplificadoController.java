package br.com.ultraworks.erp.api.compras.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.service.TipoPrecoAgricolaService;
import br.com.ultraworks.erp.api.compras.domain.itemsimplificado.ItemSimplificado;
import br.com.ultraworks.erp.api.compras.domain.itemsimplificado.ItemSimplificadoDTO;
import br.com.ultraworks.erp.api.compras.mapper.ItemSimplificadoMapper;
import br.com.ultraworks.erp.api.compras.service.ItemSimplificadoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/compra/item-simplificado")
public class ItemSimplificadoController
		extends GenericController<ItemSimplificado, Long, ItemSimplificadoDTO> {

	public ItemSimplificadoController(ItemSimplificadoService service, ItemSimplificadoMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((ItemSimplificadoService) service).getProximoCodigo());
	}
}