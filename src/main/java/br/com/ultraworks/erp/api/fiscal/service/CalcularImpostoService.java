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

@Service
public class CalcularImpostoService {
	
	ConfiguracaoFiscalIpiRepository configuracaoFiscalIpiRepository;
	ConfiguracaoFiscalIcmsRepository configuracaoFiscalIcmsRepository;
	ConfiguracaoFiscalPisRepository configuracaoFiscalPisRepository;
	ConfiguracaoFiscalCofinsRepository configuracaoFiscalCofinsRepository;
	
	CalcularIpiHelper calcularIpiHelper = new CalcularIpiHelper();
	CalcularIcmsHelper calcularIcmsHelper = new CalcularIcmsHelper();
	CalcularPisHelper calcularPisHelper = new CalcularPisHelper();
	CalcularCofinsHelper calcularCofinsHelper = new CalcularCofinsHelper();
	
	public CalcularImpostoService(ConfiguracaoFiscalIpiRepository configuracaoFiscalIpiRepository, 
			ConfiguracaoFiscalIcmsRepository configuracaoFiscalIcmsRepository,
			ConfiguracaoFiscalPisRepository configuracaoFiscalPisRepository,
			ConfiguracaoFiscalCofinsRepository configuracaoFiscalCofinsRepository
			) {
				this.configuracaoFiscalIpiRepository = configuracaoFiscalIpiRepository;
				this.configuracaoFiscalIcmsRepository = configuracaoFiscalIcmsRepository;
				this.configuracaoFiscalPisRepository = configuracaoFiscalPisRepository;
				this.configuracaoFiscalCofinsRepository = configuracaoFiscalCofinsRepository;

	}
	
	public Imposto calcularImpostos(CalculoImpostoRequest calculoImpostoRequest) {
		Imposto imposto = new Imposto();
		
		ConfiguracaoFiscalIpi configuracaoFiscalIpi = null;
		if (calculoImpostoRequest.getConfiguracaoFiscalIpiId() != null) {
			configuracaoFiscalIpi = configuracaoFiscalIpiRepository.findById(calculoImpostoRequest.getConfiguracaoFiscalIpiId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrada a Configuração Fiscal do IPI com id " + calculoImpostoRequest.getConfiguracaoFiscalIpiId()));
			imposto = calcularIpiHelper.calcularIPI(imposto, configuracaoFiscalIpi, calculoImpostoRequest);
		}
		
		ConfiguracaoFiscalIcms configuracaoFiscalIcms = null;
		if (calculoImpostoRequest.getConfiguracaoFiscalIcmsId() != null) {
			configuracaoFiscalIcms = configuracaoFiscalIcmsRepository.findById(calculoImpostoRequest.getConfiguracaoFiscalIcmsId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrada a Configuração Fiscal do ICMS com id " + calculoImpostoRequest.getConfiguracaoFiscalIcmsId()));
			imposto = calcularIcmsHelper.calcularICMS(imposto, configuracaoFiscalIcms, calculoImpostoRequest);
		}
		
		ConfiguracaoFiscalPis configuracaoFiscalPis = null;
		if (calculoImpostoRequest.getConfiguracaoFiscalPisId() != null) {
			configuracaoFiscalPis = configuracaoFiscalPisRepository.findById(calculoImpostoRequest.getConfiguracaoFiscalPisId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrada a Configuração Fiscal do PIS com id " + calculoImpostoRequest.getConfiguracaoFiscalPisId()));
			imposto = calcularPisHelper.calcularPIS(imposto, configuracaoFiscalPis, calculoImpostoRequest);
		}
		
		ConfiguracaoFiscalCofins configuracaoFiscalCofins = null;
		if (calculoImpostoRequest.getConfiguracaoFiscalCofinsId() != null) {
			configuracaoFiscalCofins = configuracaoFiscalCofinsRepository.findById(calculoImpostoRequest.getConfiguracaoFiscalCofinsId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrada a Configuração Fiscal do COFINS com id " + calculoImpostoRequest.getConfiguracaoFiscalCofinsId()));
			imposto = calcularCofinsHelper.calcularCOFINS(imposto, configuracaoFiscalCofins, calculoImpostoRequest);
		}
		
		return imposto;
	}

}
