package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.CalculoImpostoRequest;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.Imposto;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.TributacaoRequest;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.TributacaoResponse;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfiguracaoFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.service.CalcularImpostoService;
import br.com.ultraworks.erp.api.fiscal.service.ConfiguracaoFiscalService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/configuracao-fiscal")
public class ConfiguracaoFiscalController extends GenericController<ConfiguracaoFiscal, Long, ConfiguracaoFiscalDTO> {

	ConfiguracaoFiscalService service;
	CalcularImpostoService calcularImpostoService;
	
	public ConfiguracaoFiscalController(ConfiguracaoFiscalService service, ConfiguracaoFiscalMapper mapper, CalcularImpostoService calcularImpostoService) {
		super(service, mapper);
		this.service = service;
		this.calcularImpostoService = calcularImpostoService;
	}

	@PostMapping("/services/tributacao")
	public ResponseEntity<TributacaoResponse> getTributacao(@RequestBody TributacaoRequest request) {
		
		return ResponseEntity.ok(this.service.buscaConfiguracaoFiscalParaTributacao(request));
	}
	
	@PostMapping("/services/calculo")
	public ResponseEntity<Imposto> getCalculo(@RequestBody CalculoImpostoRequest request) {
		
		return ResponseEntity.ok(calcularImpostoService.calcularImpostos(request));
		
	}
}