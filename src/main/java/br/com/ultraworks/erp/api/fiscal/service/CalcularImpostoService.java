package br.com.ultraworks.erp.api.fiscal.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.configuracao.domain.configempresa.ConfigEmpresa;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigEmpresaRepository;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.CalculoImpostoRequest;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.Imposto;
import br.com.ultraworks.erp.api.fiscal.domain.calculoimpostos.estadual.ValoresICMS;
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
	ItemRepository itemRepository;
	ConfigEmpresaRepository configEmpresaRepository;
	
	CalcularIpiHelper calcularIpiHelper = new CalcularIpiHelper();
	CalcularIcmsHelper calcularIcmsHelper = new CalcularIcmsHelper();
	CalcularPisHelper calcularPisHelper = new CalcularPisHelper();
	CalcularCofinsHelper calcularCofinsHelper = new CalcularCofinsHelper();
	
	public CalcularImpostoService(ConfiguracaoFiscalIpiRepository configuracaoFiscalIpiRepository, 
			ConfiguracaoFiscalIcmsRepository configuracaoFiscalIcmsRepository,
			ConfiguracaoFiscalPisRepository configuracaoFiscalPisRepository,
			ConfiguracaoFiscalCofinsRepository configuracaoFiscalCofinsRepository,
			ItemRepository itemRepository, ConfigEmpresaRepository configEmpresaRepository
			) {
				this.configuracaoFiscalIpiRepository = configuracaoFiscalIpiRepository;
				this.configuracaoFiscalIcmsRepository = configuracaoFiscalIcmsRepository;
				this.configuracaoFiscalPisRepository = configuracaoFiscalPisRepository;
				this.configuracaoFiscalCofinsRepository = configuracaoFiscalCofinsRepository;
				this.itemRepository = itemRepository;
				this.configEmpresaRepository = configEmpresaRepository;

	}
	
	public Imposto calcularImpostos(CalculoImpostoRequest calculoImpostoRequest) {
		Imposto imposto = new Imposto();
		
		Optional<ConfigEmpresa> configEmpresa = configEmpresaRepository.findByEmpresaId(calculoImpostoRequest.getEmpresaId());
		if (configEmpresa.isPresent()) {
			imposto.setContribuinteIPI(configEmpresa.get().isContribuinteIpi());
		} else {
			return imposto;
		}
		
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
			Item item = itemRepository.findById(calculoImpostoRequest.getItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrada o Produto com id " + calculoImpostoRequest.getItemId()));
			ValoresICMS valoresICMS = new ValoresICMS();
			valoresICMS.setOrig(item.getOrigem().getCodigo());
			imposto.setValoresICMS(valoresICMS);
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
