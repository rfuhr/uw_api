package br.com.ultraworks.erp.api.tabela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumento;
import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumentoDTO;
import br.com.ultraworks.erp.api.tabela.mapper.TipoDocumentoMapper;
import br.com.ultraworks.erp.api.tabela.service.TipoDocumentoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/tipo-documento")
public class TipoDocumentoController extends GenericController<TipoDocumento, Long, TipoDocumentoDTO> {

	public TipoDocumentoController(TipoDocumentoService service, TipoDocumentoMapper mapper) {
		super(service, mapper);
	}

}