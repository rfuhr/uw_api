package br.com.ultraworks.erp.api.tabela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.unidademedida.UnidadeMedida;
import br.com.ultraworks.erp.api.tabela.domain.unidademedida.UnidadeMedidaDTO;
import br.com.ultraworks.erp.api.tabela.mapper.UnidadeMedidaMapper;
import br.com.ultraworks.erp.api.tabela.service.UnidadeMedidaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/unidade-medida")
public class UnidadeMedidaController extends GenericController<UnidadeMedida, Long, UnidadeMedidaDTO> {

	public UnidadeMedidaController(UnidadeMedidaService service, UnidadeMedidaMapper mapper) {
		super(service, mapper);
	}

}
