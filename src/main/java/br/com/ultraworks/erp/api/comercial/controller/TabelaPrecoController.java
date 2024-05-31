package br.com.ultraworks.erp.api.comercial.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.comercial.domain.tabelapreco.TabelaPreco;
import br.com.ultraworks.erp.api.comercial.domain.tabelapreco.TabelaPrecoDTO;
import br.com.ultraworks.erp.api.comercial.mapper.TabelaPrecoMapper;
import br.com.ultraworks.erp.api.comercial.service.TabelaPrecoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/comercial/tabela-preco")
public class TabelaPrecoController extends GenericController<TabelaPreco, Long, TabelaPrecoDTO> {

	public TabelaPrecoController(TabelaPrecoService service, TabelaPrecoMapper mapper) {
		super(service, mapper);
	}

}
