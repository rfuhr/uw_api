package br.com.ultraworks.erp.api.comercial.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.comercial.domain.calculoprecos.Precos;
import br.com.ultraworks.erp.api.comercial.domain.configcalculopreco.ConfigCalculoPreco;
import br.com.ultraworks.erp.api.comercial.domain.configmarkupitem.ConfigMarkupItem;
import br.com.ultraworks.erp.api.comercial.domain.configmarkupitemindice.ConfigMarkupItemIndice;
import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitem.ConfigMarkupPlanoItem;
import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitemindice.ConfigMarkupPlanoItemIndice;
import br.com.ultraworks.erp.api.comercial.domain.indicemarkup.IndiceMarkup;
import br.com.ultraworks.erp.api.comercial.repository.query.BuscaPrecoBaseDoItemQuery;
import br.com.ultraworks.erp.api.configuracao.domain.configempresa.ConfigEmpresa;
import br.com.ultraworks.erp.api.configuracao.service.ConfigEmpresaService;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.estoque.service.ItemService;
import br.com.ultraworks.erp.api.fiscal.domain.cfop.Cfop;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.TributacaoRequest;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal.TributacaoResponse;
import br.com.ultraworks.erp.api.fiscal.domain.tipotributo.TipoTributo;
import br.com.ultraworks.erp.api.fiscal.service.ConfiguracaoFiscalService;
import br.com.ultraworks.erp.api.tabela.domain.indicadoroperacao.IndicadorOperacao;
import br.com.ultraworks.erp.api.tabela.domain.uf.Uf;

@Service
public class CalcularPrecoBaseService {
	
	private BuscaPrecoBaseDoItemQuery buscaPrecoBaseDoItemQuery;
	private ItemService itemService;
	private ConfigCalculoPrecoService configCalculoPrecoService;
	private ConfigMarkupItemService configMarkupItemService;
	private ConfigMarkupPlanoItemService configMarkupPlanoItemService;
	private ConfiguracaoFiscalService configuracaoFiscalService;
	private TributacaoResponse tributacaoResponse;
	private ConfigEmpresaService configEmpresaService;
	
	public CalcularPrecoBaseService(BuscaPrecoBaseDoItemQuery buscaPrecoBaseDoItemQuery,
			ItemService itemService,
			ConfigCalculoPrecoService configCalculoPrecoService,
			ConfigMarkupItemService configMarkupItemService,
			ConfigMarkupPlanoItemService configMarkupPlanoItemService,
			ConfiguracaoFiscalService configuracaoFiscalService,
			ConfigEmpresaService configEmpresaService) {
		this.buscaPrecoBaseDoItemQuery = buscaPrecoBaseDoItemQuery;
		this.itemService = itemService;
		this.configCalculoPrecoService = configCalculoPrecoService;
		this.configMarkupItemService = configMarkupItemService;
		this.configMarkupPlanoItemService = configMarkupPlanoItemService;
		this.configuracaoFiscalService = configuracaoFiscalService;
		this.configEmpresaService = configEmpresaService;
		this.tributacaoResponse = null;
	}
	
	public Precos calcularPrecoBaseItem(Long tipoTabelaId, Long itemId) {
		Precos precos = new Precos();
		Optional<Item> item = itemService.getById(itemId);
		if (item.isPresent()) {
			precos.setTipoPrecoId(tipoTabelaId);
			precos.setItemId(itemId);
			precos = buscaPrecoBaseDoItemQuery.executeSQL(precos);
			precos = calcularValoresMarkup(item.get(), precos);
		}
		
		return precos;
	}

	private Precos calcularValoresMarkup(Item item, Precos precos) {
		if ((precos.getValorPrecoBase() != null && precos.getValorPrecoBase().doubleValue() > 0) && precos.getConfigCalculoPrecoId() != null) {
			Optional<ConfigCalculoPreco> configCalculoPreco = configCalculoPrecoService.getById(precos.getConfigCalculoPrecoId());
			if (configCalculoPreco.isPresent()) {
				Double valorMarkup = 0.00;
				if (configCalculoPreco.get().isAplicaPercentualFixo()) {
					valorMarkup = precos.getValorPrecoBase().doubleValue() * (configCalculoPreco.get().getPercentual().doubleValue() / 100);
				} else {
					ConfigMarkupItem configMarkupItem = configMarkupItemService.buscaConfigMarkupItemVigente(item.getId());
					if (configMarkupItem != null) {
						valorMarkup = calcularValorMarkupPorItem(configMarkupItem, precos, configCalculoPreco.get());
					} else {
						valorMarkup = calcularValorMarkupPorPlanoClassificacaoItem(item, precos, configCalculoPreco.get());
					}
				}
				precos.setValorMarkup(new BigDecimal(valorMarkup));	
				precos.setValorCalculado(precos.getValorPrecoBase().add(precos.getValorMarkup()));	
			}
		}
		return precos;
	}

	private Double calcularValorMarkupPorPlanoClassificacaoItem(Item item, Precos precos, ConfigCalculoPreco configCalculoPreco) {
		Double valorMarkup = 0.00;
		if (item.getPlanoClassificacaoItem() == null) {
			return valorMarkup;
		}
		ConfigMarkupPlanoItem configMarkupPlanoItem = configMarkupPlanoItemService.buscaConfigMarkupPlanoItemVigente(item.getPlanoClassificacaoItem().getId());
		if (configMarkupPlanoItem != null &&
				(configMarkupPlanoItem.getConfigMarkupPlanoItemIndices() != null && configMarkupPlanoItem.getConfigMarkupPlanoItemIndices().size() > 0)) {
			for (ConfigMarkupPlanoItemIndice indice : configMarkupPlanoItem.getConfigMarkupPlanoItemIndices()) {
				Double valorCalculado = 0.00;
				if (indice.getIndiceMarkup().isTributo()) {
					valorCalculado = calcularValorMarkupPorTributo(indice.getIndiceMarkup(), precos, configCalculoPreco);
				} else {
					valorCalculado = precos.getValorPrecoBase().doubleValue() * (indice.getPercentual().doubleValue() / 100);
				}
				valorMarkup = valorMarkup + valorCalculado;
			}
		}
		
		return valorMarkup;
	}

	private Double calcularValorMarkupPorItem(ConfigMarkupItem configMarkupItem, Precos precos, ConfigCalculoPreco configCalculoPreco) {
		Double valorMarkup = 0.00;
		if (configMarkupItem.getConfigMarkupItemIndices() != null && configMarkupItem.getConfigMarkupItemIndices().size() > 0) {
			for (ConfigMarkupItemIndice indice : configMarkupItem.getConfigMarkupItemIndices()) {
				Double valorCalculado = 0.00;
				if (indice.getIndiceMarkup().isTributo()) {
					valorCalculado = calcularValorMarkupPorTributo(indice.getIndiceMarkup(), precos, configCalculoPreco);
				} else {
					valorCalculado = precos.getValorPrecoBase().doubleValue() * (indice.getPercentual().doubleValue() / 100);
				}
				valorMarkup = valorMarkup + valorCalculado;
			}
		}
		return valorMarkup;
	}
	
	private Double calcularValorMarkupPorTributo(IndiceMarkup indice, Precos precos, ConfigCalculoPreco configCalculoPreco) {
		Double valorMarkup = 0.00;
		Double aliquota = 0.00;
		
		if (configCalculoPreco.getOperacaoInterna().isCaracteristicaFiscal()) {
			if (tributacaoResponse == null) {
				TributacaoRequest tributacaoRequest = new TributacaoRequest();
				if (configCalculoPreco.getOperacaoInterna().getOperacoesInternasFiscal().get(0).getCfops() != null &&
						configCalculoPreco.getOperacaoInterna().getOperacoesInternasFiscal().get(0).getCfops().size() > 0) {
					Date utilDate = new Date();
					Instant instant = utilDate.toInstant();
					LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
					Cfop cfop = configCalculoPreco.getOperacaoInterna().getOperacoesInternasFiscal().get(0).getCfops().get(0).getCfop();
					Optional<ConfigEmpresa> configEmpresa = configEmpresaService.getByEmpresaId(configCalculoPreco.getEmpresaFilial().getEmpresa().getId());
					Uf uf = configCalculoPreco.getEmpresaFilial().getParceiroLocal().getEnderecoNFe().getCidade().getUf();
					
					tributacaoRequest.setCfopId(cfop.getId());
					tributacaoRequest.setOperacaoInternaId(configCalculoPreco.getOperacaoInterna().getId());
					tributacaoRequest.setDataBase(localDateTime);
					tributacaoRequest.setIndicadorOperacaoValue(IndicadorOperacao.SAIDA.getValue());
					tributacaoRequest.setItemId(precos.getItemId());
					tributacaoRequest.setRegimeTributarioId(configEmpresa.get().getRegimeTributario().getId());
					tributacaoRequest.setUfDestinoId(uf.getId());
					tributacaoRequest.setUfOrigemId(uf.getId());
					
					try {
						tributacaoResponse = configuracaoFiscalService.buscaConfiguracaoFiscalParaTributacao(tributacaoRequest);				
					} catch (Exception e) {
						tributacaoResponse = null;
					}
				}
			}
			if (indice.getTipoTributo().equals(TipoTributo.ICMS)
					&& tributacaoResponse != null && tributacaoResponse.getConfiguracaoFiscalIcms() != null 
					&& tributacaoResponse.getConfiguracaoFiscalIcms().getAliquota() != null
					&& tributacaoResponse.getConfiguracaoFiscalIcms().getAliquota().doubleValue() > 0) {
				aliquota = tributacaoResponse.getConfiguracaoFiscalIcms().getAliquota().doubleValue();
			}
			if (indice.getTipoTributo().equals(TipoTributo.IPI)
					&& tributacaoResponse != null && tributacaoResponse.getConfiguracaoFiscalIpi() != null 
					&& tributacaoResponse.getConfiguracaoFiscalIpi().getAliquota() != null
					&& tributacaoResponse.getConfiguracaoFiscalIpi().getAliquota().doubleValue() > 0) {
				aliquota = tributacaoResponse.getConfiguracaoFiscalIpi().getAliquota().doubleValue();
			}
			if (indice.getTipoTributo().equals(TipoTributo.PIS)
					&& tributacaoResponse != null && tributacaoResponse.getConfiguracaoFiscalPis() != null 
					&& tributacaoResponse.getConfiguracaoFiscalPis().getAliquota() != null
					&& tributacaoResponse.getConfiguracaoFiscalPis().getAliquota().doubleValue() > 0) {
				aliquota = tributacaoResponse.getConfiguracaoFiscalPis().getAliquota().doubleValue();
			}
			if (indice.getTipoTributo().equals(TipoTributo.COFINS)
					&& tributacaoResponse != null && tributacaoResponse.getConfiguracaoFiscalCofins() != null 
					&& tributacaoResponse.getConfiguracaoFiscalCofins().getAliquota() != null
					&& tributacaoResponse.getConfiguracaoFiscalCofins().getAliquota().doubleValue() > 0) {
				aliquota = tributacaoResponse.getConfiguracaoFiscalCofins().getAliquota().doubleValue();
			}
		}
		if (aliquota > 0) {
			Double valorCalculado = 0.00;
			valorCalculado = precos.getValorPrecoBase().doubleValue() * (aliquota / 100);
			valorMarkup = valorMarkup + valorCalculado;			
		}
		
		return valorMarkup;
	}

	public List<Precos> calcularPrecoBaseItem(Long tipoTabelaId, List<Long> listaItens) {
		List<Precos> listaCalculada = new ArrayList<Precos>();
		for (Long itemId : listaItens) {
			Precos preco = calcularPrecoBaseItem(tipoTabelaId, itemId);
			listaCalculada.add(preco);
		}
		
		return listaCalculada;
	}

}
