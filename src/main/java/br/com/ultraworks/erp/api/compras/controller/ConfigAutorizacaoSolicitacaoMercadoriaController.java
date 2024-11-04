package br.com.ultraworks.erp.api.compras.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.compras.domain.configautorizacaosolicitacaomercadoria.ConfigAutorizacaoSolicitacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.configautorizacaosolicitacaomercadoria.ConfigAutorizacaoSolicitacaoMercadoriaDTO;
import br.com.ultraworks.erp.api.compras.mapper.ConfigAutorizacaoSolicitacaoMercadoriaMapper;
import br.com.ultraworks.erp.api.compras.service.ConfigAutorizacaoSolicitacaoMercadoriaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/compra/config-autorizacao-solicitacao-mercadoria")
public class ConfigAutorizacaoSolicitacaoMercadoriaController extends
		GenericController<ConfigAutorizacaoSolicitacaoMercadoria, Long, ConfigAutorizacaoSolicitacaoMercadoriaDTO> {

	public ConfigAutorizacaoSolicitacaoMercadoriaController(ConfigAutorizacaoSolicitacaoMercadoriaService service,
			ConfigAutorizacaoSolicitacaoMercadoriaMapper mapper) {
		super(service, mapper);
	}

}