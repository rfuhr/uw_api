package br.com.ultraworks.erp.api.comercial.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.comercial.domain.tipopreco.TipoPreco;
import br.com.ultraworks.erp.api.comercial.domain.tipopreco.TipoPrecoDTO;
import br.com.ultraworks.erp.api.comercial.mapper.TipoPrecoMapper;
import br.com.ultraworks.erp.api.comercial.service.TipoPrecoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/comercial/tipo-preco")
public class TipoPrecoController extends GenericController<TipoPreco, Long, TipoPrecoDTO> {

	public TipoPrecoController(TipoPrecoService service, TipoPrecoMapper mapper) {
		super(service, mapper);
	}

}