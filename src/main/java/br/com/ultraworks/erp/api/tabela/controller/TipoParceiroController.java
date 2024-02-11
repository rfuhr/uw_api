package br.com.ultraworks.erp.api.tabela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.tipoparceiro.TipoParceiro;
import br.com.ultraworks.erp.api.tabela.domain.tipoparceiro.TipoParceiroDTO;
import br.com.ultraworks.erp.api.tabela.mapper.TipoParceiroMapper;
import br.com.ultraworks.erp.api.tabela.service.TipoParceiroService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/tipo-parceiro")
public class TipoParceiroController extends GenericController<TipoParceiro, Long, TipoParceiroDTO> {

	public TipoParceiroController(TipoParceiroService service, TipoParceiroMapper mapper) {
		super(service, mapper);
	}

}