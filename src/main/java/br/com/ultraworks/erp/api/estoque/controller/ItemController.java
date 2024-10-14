package br.com.ultraworks.erp.api.estoque.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.estoque.domain.item.ItemDTO;
import br.com.ultraworks.erp.api.estoque.mapper.ItemMapper;
import br.com.ultraworks.erp.api.estoque.service.ItemService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/estoque/item")
public class ItemController extends GenericController<Item, Long, ItemDTO> {

	public ItemController(ItemService service, ItemMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((ItemService) service).getProximoCodigo());
	}

	
	@GetMapping("/seletor/{empresaFilialId}/{grupoContabilId}")
	public ResponseEntity<?> buscarEstoquePrecoItensParaSeletorQuery(@PathVariable Long empresaFilialId, @PathVariable Long grupoContabilId) {
		if (empresaFilialId == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Identificador da Filial da Empresa deve ser informado");
		}
		if (grupoContabilId == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Identificador o Grupo Contábil deve ser informado");
		}
		return ResponseEntity.ok(((ItemService) service).buscaEstoquePrecoItensParaSeletor(empresaFilialId, grupoContabilId));
	}
}
