package br.com.ultraworks.erp.api.tabela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadrao;
import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadraoDTO;
import br.com.ultraworks.erp.api.tabela.mapper.HistoricoPadraoMapper;
import br.com.ultraworks.erp.api.tabela.service.HistoricoPadraoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/historico-padrao")
public class HistoricoPadraoController extends GenericController<HistoricoPadrao, Long, HistoricoPadraoDTO> {

	public HistoricoPadraoController(HistoricoPadraoService service, HistoricoPadraoMapper mapper) {
		super(service, mapper);
	}

}