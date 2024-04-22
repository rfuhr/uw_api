package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.CalculoImpostoRequest;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.Imposto;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalcofins.ConfiguracaoFiscalCofins;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalicms.ConfiguracaoFiscalIcms;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalipi.ConfiguracaoFiscalIpi;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalpis.ConfiguracaoFiscalPis;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalCofinsRepository;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalIcmsRepository;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalIpiRepository;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalPisRepository;
import br.com.ultraworks.erp.api.fiscal.service.helper.CalcularCofinsHelper;
import br.com.ultraworks.erp.api.fiscal.service.helper.CalcularIcmsHelper;
import br.com.ultraworks.erp.api.fiscal.service.helper.CalcularIpiHelper;
import br.com.ultraworks.erp.api.fiscal.service.helper.CalcularPisHelper;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CalcularImpostoService {
	
	ConfiguracaoFiscalIpiRepository configuracaoFiscalIpiRepository;
	ConfiguracaoFiscalIcmsRepository configuracaoFiscalIcmsRepository;
	ConfiguracaoFiscalPisRepository configuracaoFiscalPisRepository;
	ConfiguracaoFiscalCofinsRepository configuracaoFiscalCofinsRepository;
	
	CalcularIpiHelper calcularIpiHelper = new CalcularIpiHelper();
	CalcularIcmsHelper calcularIcmsHelper = new CalcularIcmsHelper();
	CalcularPisHelper calcularPisHelper = new CalcularPisHelper();
	CalcularCofinsHelper calcularCofinsHelper = new CalcularCofinsHelper();
	
	public Imposto calcularImpostos(CalculoImpostoRequest calculoImpostoRequest) {
		Imposto imposto = new Imposto();
		
		ConfiguracaoFiscalIpi configuracaoFiscalIpi = configuracaoFiscalIpiRepository.findById(calculoImpostoRequest.getConfiguracaoFiscalIpiId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrada a Configuração Fiscal do IPI com id " + calculoImpostoRequest.getConfiguracaoFiscalIpiId()));
		
		ConfiguracaoFiscalIcms configuracaoFiscalIcms = configuracaoFiscalIcmsRepository.findById(calculoImpostoRequest.getConfiguracaoFiscalIcmsId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrada a Configuração Fiscal do ICMS com id " + calculoImpostoRequest.getConfiguracaoFiscalIcmsId()));
		
		ConfiguracaoFiscalPis configuracaoFiscalPis = configuracaoFiscalPisRepository.findById(calculoImpostoRequest.getConfiguracaoFiscalPisId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrada a Configuração Fiscal do PIS com id " + calculoImpostoRequest.getConfiguracaoFiscalPisId()));
		
		ConfiguracaoFiscalCofins configuracaoFiscalCofins = configuracaoFiscalCofinsRepository.findById(calculoImpostoRequest.getConfiguracaoFiscalCofinsId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrada a Configuração Fiscal do COFINS com id " + calculoImpostoRequest.getConfiguracaoFiscalCofinsId()));
		
		imposto = calcularIpiHelper.calcularIPI(imposto, configuracaoFiscalIpi, calculoImpostoRequest);
		imposto = calcularIcmsHelper.calcularICMS(imposto, configuracaoFiscalIcms, calculoImpostoRequest);
		imposto = calcularPisHelper.calcularPIS(imposto, configuracaoFiscalPis, calculoImpostoRequest);
		imposto = calcularCofinsHelper.calcularCOFINS(imposto, configuracaoFiscalCofins, calculoImpostoRequest);
		
		return imposto;
	}

}
