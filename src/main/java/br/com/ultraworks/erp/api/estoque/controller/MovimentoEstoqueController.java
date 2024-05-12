package br.com.ultraworks.erp.api.estoque.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.MovimentoEstoqueDTO;
import br.com.ultraworks.erp.api.estoque.mapper.MovimentoEstoqueMapper;
import br.com.ultraworks.erp.api.estoque.service.MovimentoEstoqueService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/estoque/movimento-estoque")
public class MovimentoEstoqueController extends GenericController<MovimentoEstoque, Long, MovimentoEstoqueDTO> {
	
	public MovimentoEstoqueController(MovimentoEstoqueService service, MovimentoEstoqueMapper mapper) {
		super(service, mapper);
	}

}