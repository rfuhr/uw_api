package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.tipoproduto.TipoProduto;
import br.com.ultraworks.erp.api.fiscal.domain.tipoproduto.TipoProdutoDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.TipoProdutoMapper;
import br.com.ultraworks.erp.api.fiscal.service.TipoProdutoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/tipo-produto")
public class TipoProdutoController extends GenericController<TipoProduto, Long, TipoProdutoDTO> {

	public TipoProdutoController(TipoProdutoService service, TipoProdutoMapper mapper) {
		super(service, mapper);
	}

}