package br.com.ultraworks.erp.api.tabela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.tipooperacao.TipoOperacao;
import br.com.ultraworks.erp.api.tabela.domain.tipooperacao.TipoOperacaoDTO;
import br.com.ultraworks.erp.api.tabela.mapper.TipoOperacaoMapper;
import br.com.ultraworks.erp.api.tabela.service.TipoOperacaoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/tipo-operacao")
public class TipoOperacaoController extends GenericController<TipoOperacao, Long, TipoOperacaoDTO> {

	public TipoOperacaoController(TipoOperacaoService service, TipoOperacaoMapper mapper) {
		super(service, mapper);
	}

}