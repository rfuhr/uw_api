package br.com.ultraworks.erp.api.estoque.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.localestoque.LocalEstoque;
import br.com.ultraworks.erp.api.estoque.domain.localestoque.LocalEstoqueDTO;
import br.com.ultraworks.erp.api.estoque.mapper.LocalEstoqueMapper;
import br.com.ultraworks.erp.api.estoque.service.LocalEstoqueService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/estoque/local-estoque")
public class LocalEstoqueController extends GenericController<LocalEstoque, Long, LocalEstoqueDTO> {

	public LocalEstoqueController(LocalEstoqueService service, LocalEstoqueMapper mapper) {
		super(service, mapper);
	}

}