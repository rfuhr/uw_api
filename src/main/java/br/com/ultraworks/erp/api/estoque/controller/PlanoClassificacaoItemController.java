package br.com.ultraworks.erp.api.estoque.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.planoclassificacaoitem.PlanoClassificacaoItem;
import br.com.ultraworks.erp.api.estoque.domain.planoclassificacaoitem.PlanoClassificacaoItemDTO;
import br.com.ultraworks.erp.api.estoque.mapper.PlanoClassificacaoItemMapper;
import br.com.ultraworks.erp.api.estoque.service.PlanoClassificacaoItemService;
import br.com.ultraworks.erp.core.dto.EstruturaContaResponse;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/estoque/plano-classificacao-item")
public class PlanoClassificacaoItemController
		extends GenericController<PlanoClassificacaoItem, Long, PlanoClassificacaoItemDTO> {

	public PlanoClassificacaoItemController(PlanoClassificacaoItemService service,
			PlanoClassificacaoItemMapper mapper) {
		super(service, mapper);
	}
	
	@GetMapping("/estrutura")
	public ResponseEntity<?> getJsonEstrutura() {
		 List<EstruturaContaResponse> estruturaConta = ((PlanoClassificacaoItemService) service).getEstruturaConta();
		return ResponseEntity.ok(estruturaConta);
	}	

}