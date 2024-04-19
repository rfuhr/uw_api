package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.ConfiguracaoFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.TributacaoRequest;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.TributacaoResponse;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfiguracaoFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.service.ConfiguracaoFiscalService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/configuracao-fiscal")
public class ConfiguracaoFiscalController extends GenericController<ConfiguracaoFiscal, Long, ConfiguracaoFiscalDTO> {

	ConfiguracaoFiscalService service;
	
	public ConfiguracaoFiscalController(ConfiguracaoFiscalService service, ConfiguracaoFiscalMapper mapper) {
		super(service, mapper);
		this.service = service;
	}

	@PostMapping("/services/tributacao")
	public ResponseEntity<TributacaoResponse> getTributacao(@RequestBody TributacaoRequest request) {
		
		return ResponseEntity.ok(this.service.buscaConfiguracaoFiscalParaTributacao(request));

//		TributacaoResponse response = new TributacaoResponse();
//		
//		response.setTemIcms(true);
//		ConfiguracaoFiscalIcmsDTO configuracaoFiscalIcmsDTO = new ConfiguracaoFiscalIcmsDTO();
//		configuracaoFiscalIcmsDTO.setId(6L);
//		configuracaoFiscalIcmsDTO.setSituacaoTributariaId(6L);
//		configuracaoFiscalIcmsDTO.setSituacaoTributariaCodigo(10L);
//		configuracaoFiscalIcmsDTO.setSituacaoTributariaNome("Com redução de base de cálculo e cobrança do ICMS por Substituição Tributária");
//		configuracaoFiscalIcmsDTO.setModalidadeBaseCalculo("3");
//		configuracaoFiscalIcmsDTO.setAliquota(BigDecimal.valueOf(12));
//		configuracaoFiscalIcmsDTO.setReducaoBaseCalculo(BigDecimal.valueOf(2));
//		configuracaoFiscalIcmsDTO.setMotivoDesoneracaoId(1L);
//		configuracaoFiscalIcmsDTO.setDiferencialAliquota(BigDecimal.valueOf(11));
		
//		configuracaoFiscalIcmsDTO.setModalidadeBaseCalculoST("5");
//		configuracaoFiscalIcmsDTO.setMargemValorAgregadoST(BigDecimal.valueOf(1.5));
//		configuracaoFiscalIcmsDTO.setReducaoBaseCalculoST(BigDecimal.valueOf(1));
//		configuracaoFiscalIcmsDTO.setAliquotaST(BigDecimal.valueOf(12));
		
//		configuracaoFiscalIcmsDTO.setMotivoDesoneracaoSTCodigo(1L);
		
//		configuracaoFiscalIcmsDTO.setAliquotaCredito(BigDecimal.valueOf(12));
		
//		response.setConfiguracaoFiscalIcms(configuracaoFiscalIcmsDTO);
		
//		response.setTemIpi(true);
//		ConfiguracaoFiscalIpiDTO configuracaoFiscalIpiDTO = new ConfiguracaoFiscalIpiDTO();
//		configuracaoFiscalIpiDTO.setCodigoSelo("9710-01");
//		configuracaoFiscalIpiDTO.setEnquadramentoId(1L);
//		configuracaoFiscalIpiDTO.setSituacaoTributariaId(0L);
//		configuracaoFiscalIpiDTO.setSituacaoTributariaNome("Entrada com recuperação de crédito");
//		configuracaoFiscalIpiDTO.setAliquota(BigDecimal.valueOf(12));
//		configuracaoFiscalIpiDTO.setTipoCalculo(TipoCalculo.VALOR.getValue());
//		configuracaoFiscalIpiDTO.setTipoCalculoName(TipoCalculo.VALOR.getName());
//		
//		response.setConfiguracaoFiscalIpi(configuracaoFiscalIpiDTO);
		
//		response.setTemCofins(true);
//		ConfiguracaoFiscalCofinsDTO configuracaoFiscalCofinsDTO = new ConfiguracaoFiscalCofinsDTO();
//		configuracaoFiscalCofinsDTO.setSituacaoTributariaId(1L);
//		configuracaoFiscalCofinsDTO.setSituacaoTributariaCodigo(54L);
//		configuracaoFiscalCofinsDTO.setSituacaoTributariaNome("Operação Tributável com Alíquota Básica");
//		configuracaoFiscalCofinsDTO.setTipoCalculo(TipoCalculo.PERCENTUAL.getValue());
//		configuracaoFiscalCofinsDTO.setTipoCalculoNome(TipoCalculo.PERCENTUAL.getName());
//		configuracaoFiscalCofinsDTO.setAliquota(BigDecimal.valueOf(12));
//		configuracaoFiscalPisDTO.setTipoCalculoST(TipoCalculo.PERCENTUAL.getValue());
//		configuracaoFiscalPisDTO.setTipoCalculoSTNome(TipoCalculo.PERCENTUAL.getName());
		
		
//		response.setConfiguracaoFiscalCofins(configuracaoFiscalCofinsDTO);
//		return ResponseEntity.ok(response);
	}
}