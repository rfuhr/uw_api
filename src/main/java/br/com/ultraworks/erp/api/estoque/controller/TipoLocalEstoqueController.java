package br.com.ultraworks.erp.api.estoque.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.tipolocalestoque.TipoLocalEstoque;
import br.com.ultraworks.erp.api.estoque.domain.tipolocalestoque.TipoLocalEstoqueDTO;
import br.com.ultraworks.erp.api.estoque.mapper.TipoLocalEstoqueMapper;
import br.com.ultraworks.erp.api.estoque.service.TipoLocalEstoqueService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/estoque/tipo-local-estoque")
public class TipoLocalEstoqueController extends GenericController<TipoLocalEstoque, Long, TipoLocalEstoqueDTO> {

	public TipoLocalEstoqueController(TipoLocalEstoqueService service, TipoLocalEstoqueMapper mapper) {
		super(service, mapper);
	}

}