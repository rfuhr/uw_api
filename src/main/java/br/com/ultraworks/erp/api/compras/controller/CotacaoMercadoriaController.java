package br.com.ultraworks.erp.api.compras.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoria.CotacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoria.CotacaoMercadoriaDTO;
import br.com.ultraworks.erp.api.compras.mapper.CotacaoMercadoriaMapper;
import br.com.ultraworks.erp.api.compras.service.cotacaomercadoria.CotacaoMercadoriaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/compras/cotacao-mercadoria")
public class CotacaoMercadoriaController
		extends GenericController<CotacaoMercadoria, Long, CotacaoMercadoriaDTO> {

	public CotacaoMercadoriaController(CotacaoMercadoriaService service, CotacaoMercadoriaMapper mapper) {
		super(service, mapper);
	}

}