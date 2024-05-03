package br.com.ultraworks.erp.api.fiscal.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.estoque.service.ItemService;
import br.com.ultraworks.erp.api.fiscal.domain.cfop.Cfop;
import br.com.ultraworks.erp.api.fiscal.domain.destinooperacao.DestinoOperacao;
import br.com.ultraworks.erp.api.fiscal.domain.finalidadenfe.FinalidadeNfe;
import br.com.ultraworks.erp.api.fiscal.domain.meiopagamento.MeioPagamento;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFe;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeAut;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeCofinsItem;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeDest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeDetItem;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeDetPagamento;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeEmit;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeEntrega;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeIcmsItem;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeIde;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeImpostosItem;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeInfoAdic;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeInfoAdicItem;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeIpiItem;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeLacre;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFePagamento;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFePisItem;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeProdItem;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeReboque;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeRef;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeRetirada;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeTotais;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeTransporte;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeVolume;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.AutorizacaoRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.CofinsNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.DestinatarioNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.DetalhamentoItemNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.FinanceiroNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.IdentificacaoNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.InformacoesAdicionaisNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.IpiNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.ItemNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.ItensNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.LocalEntregaRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.LocalRetiradaRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.NFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.PisNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.TransporteNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.TributacaoIcmsNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.tipocalculo.TipoCalculo;
import br.com.ultraworks.erp.api.fiscal.domain.tipodocumentoreferenciado.TipoDocumentoReferenciado;
import br.com.ultraworks.erp.api.fiscal.domain.tipopresencacomprador.TipoPresencaComprador;
import br.com.ultraworks.erp.api.fiscal.domain.tipotransporte.TipoTransporte;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEmail.ParceiroLocalEmail;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEndereco;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTelefone.ParceiroLocalTelefone;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalService;
import br.com.ultraworks.erp.api.tabela.domain.cidade.Cidade;
import br.com.ultraworks.erp.api.tabela.domain.indicadoroperacao.IndicadorOperacao;
import br.com.ultraworks.erp.api.tabela.domain.situacaodocumento.SituacaoDocumento;
import br.com.ultraworks.erp.api.tabela.domain.tipopessoa.TipoPessoa;
import br.com.ultraworks.erp.api.tabela.domain.unidademedida.UnidadeMedida;
import br.com.ultraworks.erp.api.tabela.service.CidadeService;
import br.com.ultraworks.erp.api.tabela.service.UnidadeMedidaService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;

@Service
public class MergeNFeRequestToNFeEntityService {

	private ParceiroLocalService parceiroLocalService;
	private CidadeService cidadeService;
	private ItemService itemService;
	private CfopService cfopService;
	private UnidadeMedidaService unidadeMedidaService;

	public MergeNFeRequestToNFeEntityService(ParceiroLocalService parceiroLocalService, CidadeService cidadeService,
			ItemService itemService, CfopService cfopService, UnidadeMedidaService unidadeMedidaService) {
		this.parceiroLocalService = parceiroLocalService;
		this.cidadeService = cidadeService;
		this.itemService = itemService;
		this.cfopService = cfopService;
		this.unidadeMedidaService = unidadeMedidaService;
	}

	public NFe merge(NFe nfe, NFeRequest nFeRequest) {

		mergeIdentificacaoNFe(nfe.getNfeIde(), nFeRequest.getIdentificacaoNFeRequest());
		mergeEmitenteNFe(nfe.getNfeEmit(), nFeRequest.getIdentificacaoNFeRequest());
		mergeDestinatarioNFe(nfe.getNfeDest(), nFeRequest.getDestinatarioNFeRequest());
		mergeLocalRetirada(nfe.getNfeRetirada(), nFeRequest.getIdentificacaoNFeRequest().getLocalRetirada());
		mergeLocalEntrega(nfe.getNfeEntrega(), nFeRequest.getDestinatarioNFeRequest().getLocalEntrega());
		mergeAutorizacoes(nfe.getAutorizacoes(), nFeRequest.getIdentificacaoNFeRequest().getAutorizacoes());
		mergeItensNFe(nfe.getItens(), nFeRequest.getItensNFeRequest());
		mergeTotaisNFe(nfe);
		mergeTransporte(nfe, nFeRequest.getTransporteNFeRequest());
		mergePagamentos(nfe, nFeRequest.getFinanceiroNFeRequest());
		mergeInfoAdicionais(nfe, nFeRequest.getInformacoesAdicionaisNFeRequest());

		nfe.setSituacao(SituacaoDocumento.AGUARDANDOENVIO);

		return nfe;
	}

	private void mergeInfoAdicionais(NFe nfe, InformacoesAdicionaisNFeRequest informacoesAdicionaisNFeRequest) {
		if (informacoesAdicionaisNFeRequest != null) {
			NFeInfoAdic nfeInfoAdic = new NFeInfoAdic();
			nfeInfoAdic.setInfcpl(informacoesAdicionaisNFeRequest.getInfoAdicionaisComplementares());
			
			nfe.setNfeInfoAdic(nfeInfoAdic);
		}
	}

	private void mergePagamentos(NFe nfe, FinanceiroNFeRequest financeiroNFeRequest) {
		NFePagamento nFePagamento = new NFePagamento();
		nFePagamento.setVtroco(financeiroNFeRequest.getValorTroco());
		if (financeiroNFeRequest.getPagamentos() != null && !financeiroNFeRequest.getPagamentos().isEmpty()) {
			financeiroNFeRequest.getPagamentos().forEach(pag -> {
				NFeDetPagamento pagamento = NFeDetPagamento.builder()
						.indpag(Integer.parseInt(pag.getIndicadorFormaPagamento())).tpag(pag.getMeioPagamento())
						.vpag(pag.getValorPagamento()).build();
				if (!StringUtils.containsAny(pag.getMeioPagamento(), MeioPagamento.CARTAOCREDITO.getValue(),
						MeioPagamento.CARTAODEBITO.getValue())) {
					pagamento.setTpintegra(Integer.parseInt(pag.getTipoIntegracao()));
					pagamento.setCnpj(pag.getCnpj());
					pagamento.setTband(pag.getBandeiraCartao());
					pagamento.setCaut(pag.getNumeroAutorizacao());
				}
				nFePagamento.getDetalhesPagamento().add(pagamento);
			});
		}

		nfe.setNfePagamento(nFePagamento);
		;

	}

	private void mergeTransporte(NFe nfe, TransporteNFeRequest transporteNFeRequest) {
		NFeTransporte nFeTransport = new NFeTransporte();
		nFeTransport.setModfrete(Integer.parseInt(transporteNFeRequest.getModalidadeFrete()));
		if (!transporteNFeRequest.getModalidadeFrete().equals("9")) {
			Cidade cidade = cidadeService.getById(transporteNFeRequest.getCidadeId())
					.orElseThrow(() -> new RegisterNotFoundException("Cidade do transportador não econtrada"));
			nFeTransport.setCnpj(transporteNFeRequest.getCnpj());
			nFeTransport.setCpf(transporteNFeRequest.getCpf());
			nFeTransport.setXnome(transporteNFeRequest.getNomeRazaoSocial());
			nFeTransport.setIe(transporteNFeRequest.getInscricaoEstadual());
			nFeTransport.setXender(transporteNFeRequest.getEndereco());
			nFeTransport.setXmun(cidade.getNome());
			nFeTransport.setUf(cidade.getUf().getSigla());
			if (transporteNFeRequest.getTipoTransporte().equals(TipoTransporte.VEICULO.getValue())) {
				nFeTransport.setPlaca(transporteNFeRequest.getPlacaVeiculo());
				nFeTransport.setUfplaca(transporteNFeRequest.getSiglaUf());
				nFeTransport.setRntc(transporteNFeRequest.getRntc());
				;
			}
			if (transporteNFeRequest.getTipoTransporte().equals(TipoTransporte.BALSA.getValue())) {
				nFeTransport.setBalsa(transporteNFeRequest.getBalsa());
			}
			if (transporteNFeRequest.getTipoTransporte().equals(TipoTransporte.VAGAO.getValue())) {
				nFeTransport.setVagao(transporteNFeRequest.getVagao());
			}
			if (transporteNFeRequest.getReboques() != null && !transporteNFeRequest.getReboques().isEmpty()) {
				nFeTransport.setReboques(new ArrayList<>());
				transporteNFeRequest.getReboques().forEach(reboque -> {
					nFeTransport.getReboques().add(NFeReboque.builder().placa(reboque.getPlacaVeiculo())
							.ufplaca(reboque.getSiglaUf()).rntc(reboque.getRntc()).build());
				});
			}
			if (transporteNFeRequest.getVolumes() != null && !transporteNFeRequest.getVolumes().isEmpty()) {
				nFeTransport.setVolumes(new ArrayList<>());
				transporteNFeRequest.getVolumes().forEach(volume -> {
					NFeVolume nfeVolume = NFeVolume.builder().qvol(volume.getQuantidade()).esp(volume.getEspecie())
							.marca(volume.getMarca()).nvol(volume.getNumeracao()).pesol(volume.getPesoLiquido())
							.pesob(volume.getPesoBruto()).build();
					
					if (volume.getLacres() != null && !volume.getLacres().isEmpty()) {
						nfeVolume.setLacres(new ArrayList<>());
						volume.getLacres().forEach(lacre -> {
							nfeVolume.getLacres().add(NFeLacre.builder().nlacre(lacre).build());
						});
					}
					
					nFeTransport.getVolumes()
							.add(nfeVolume);
				});
			}
			
		}
		nfe.setNfeTransporte(nFeTransport);
	}

	private void mergeTotaisNFe(NFe nfe) {
		NFeTotais nfeTotais = new NFeTotais();
		nfeTotais.setVbc(BigDecimal.ZERO);
		nfeTotais.setVicms(BigDecimal.ZERO);
		nfeTotais.setVicmsdeson(BigDecimal.ZERO);
		nfeTotais.setVfcpufdest(BigDecimal.ZERO);
		nfeTotais.setVicmsufdest(BigDecimal.ZERO);
		nfeTotais.setVicmsufremet(BigDecimal.ZERO);
		nfeTotais.setVfcp(BigDecimal.ZERO);
		nfeTotais.setVbcst(BigDecimal.ZERO);
		nfeTotais.setVst(BigDecimal.ZERO);
		nfeTotais.setVfcpst(BigDecimal.ZERO);
		nfeTotais.setVfcpstret(BigDecimal.ZERO);
		nfeTotais.setVprod(BigDecimal.ZERO);
		nfeTotais.setVfrete(BigDecimal.ZERO);
		nfeTotais.setVseg(BigDecimal.ZERO);
		nfeTotais.setVdesc(BigDecimal.ZERO);
		nfeTotais.setVii(BigDecimal.ZERO);
		nfeTotais.setVipi(BigDecimal.ZERO);
		nfeTotais.setVipidevol(BigDecimal.ZERO);
		nfeTotais.setVpis(BigDecimal.ZERO);
		nfeTotais.setVcofins(BigDecimal.ZERO);
		nfeTotais.setVoutro(BigDecimal.ZERO);
		nfeTotais.setVnf(BigDecimal.ZERO);
		nfeTotais.setVtottrib(BigDecimal.ZERO);

		nfe.setNfeTotais(nfeTotais);
	}

	private void mergeItensNFe(List<NFeDetItem> itens, ItensNFeRequest itensNFeRequest) {
		if (itensNFeRequest.getItens() != null && itensNFeRequest.getItens().size() > 0) {
			itens = new ArrayList<>();
			int seqItem = 0;
			for (ItemNFeRequest itemNFeRequest : itensNFeRequest.getItens()) {
				Item item = itemService.getById(itemNFeRequest.getDetalhamentoItem().getItemId())
						.orElseThrow(() -> new RegisterNotFoundException(
								"Não encontrado item com id: " + itemNFeRequest.getDetalhamentoItem().getItemId()));

				NFeDetItem nfeDetItem = new NFeDetItem();
				nfeDetItem.setNItem(++seqItem);
				nfeDetItem.setNfeProdItem(newNfeProdItem(item, itemNFeRequest.getDetalhamentoItem()));
				nfeDetItem.setNfeImpostosItem(newNfeImpostosItem(item, itemNFeRequest));
				nfeDetItem.setNfeInfoAdicItem(newNfeInfoAdicItem(itemNFeRequest));

				itens.add(nfeDetItem);
			}

		} else {
			itensNFeRequest.setItens(null);
		}

	}

	private NFeInfoAdicItem newNfeInfoAdicItem(ItemNFeRequest itemNFeRequest) {
		if (StringUtils.isNotBlank(itemNFeRequest.getInfoAdicionais())) {
			NFeInfoAdicItem nFeInfoAdicItem = new NFeInfoAdicItem();
			nFeInfoAdicItem.setInfadprod(itemNFeRequest.getInfoAdicionais());
			return nFeInfoAdicItem;
		}
		return null;
	}

	private NFeImpostosItem newNfeImpostosItem(Item item, ItemNFeRequest itemNFeRequest) {
		NFeImpostosItem nFeImpostosItem = new NFeImpostosItem();
		if (itemNFeRequest.getTributacaoIcms() != null)
			nFeImpostosItem.setNfeIcmsItem(newNfeImpostosItemIcms(item, itemNFeRequest.getTributacaoIcms()));
		if (itemNFeRequest.getIpi() != null)
			nFeImpostosItem.setNfeIpiItem(newNfeImpostosItemIpi(item, itemNFeRequest.getIpi()));
		if (itemNFeRequest.getPis() != null)
			nFeImpostosItem.setNfePisItem(newNfeImpostosItemPis(item, itemNFeRequest.getPis()));
		if (itemNFeRequest.getCofins() != null)
			nFeImpostosItem.setNfeCofinsItem(newNfeImpostosItemCofins(item, itemNFeRequest.getCofins()));
		return nFeImpostosItem;
	}

	private NFeCofinsItem newNfeImpostosItemCofins(Item item, CofinsNFeRequest cofins) {
		if (cofins != null) {
			NFeCofinsItem nFeCofinsItem = new NFeCofinsItem();
			nFeCofinsItem.setCst(cofins.getCst());
			if (!StringUtils.containsAny(cofins.getCst(), "04", "05", "06", "07", "08", "09")) {
				if (cofins.getTipoCalculo().equals(TipoCalculo.PERCENTUAL.getValue())) {
					nFeCofinsItem.setVbc(cofins.getBcCofins());
					nFeCofinsItem.setPcofins(cofins.getAliquota());
					nFeCofinsItem.setVcofins(cofins.getValorCofins());
				}
				if (cofins.getTipoCalculo().equals(TipoCalculo.VALOR.getValue())) {
					nFeCofinsItem.setQbcprod(cofins.getQuantidadeVendida());
					nFeCofinsItem.setValiqprod(cofins.getAliquota());
					nFeCofinsItem.setVcofins(cofins.getValorCofins());
				}
			}
			return nFeCofinsItem;
		}
		return null;
	}

	private NFePisItem newNfeImpostosItemPis(Item item, PisNFeRequest pis) {
		if (pis != null) {
			NFePisItem nFePisItem = new NFePisItem();
			nFePisItem.setCst(pis.getCst());
			if (!StringUtils.containsAny(pis.getCst(), "04", "05", "06", "07", "08", "09")) {
				if (pis.getTipoCalculo().equals(TipoCalculo.PERCENTUAL.getValue())) {
					nFePisItem.setVbc(pis.getBcPis());
					nFePisItem.setPpis(pis.getAliquota());
					nFePisItem.setVpis(pis.getValorPis());
				}
				if (pis.getTipoCalculo().equals(TipoCalculo.VALOR.getValue())) {
					nFePisItem.setQbcprod(pis.getQuantidadeVendida());
					nFePisItem.setValiqprod(pis.getAliquota());
					nFePisItem.setVpis(pis.getValorPis());
				}
			}
			return nFePisItem;
		}
		return null;
	}

	private NFeIpiItem newNfeImpostosItemIpi(Item item, IpiNFeRequest ipi) {
		if (ipi != null) {
			NFeIpiItem nFeIpiItem = new NFeIpiItem();
			nFeIpiItem.setCnpjprod(ipi.getCnpjProdutor());
			nFeIpiItem.setCselo(ipi.getCodigoSelo());
			nFeIpiItem.setQselo(Integer.parseInt(ipi.getQuantidadeSelo()));
			nFeIpiItem.setCenq(ipi.getEnquadramentoId().intValue());
			nFeIpiItem.setCst(ipi.getCst());
			if (StringUtils.containsAny(ipi.getCst(), "00", "49", "50", "99")) {
				if (ipi.getTipoCalculo().equals(TipoCalculo.PERCENTUAL.getValue())) {
					nFeIpiItem.setVbc(ipi.getBcIpi());
					nFeIpiItem.setPipi(ipi.getAliquota());
				}
				if (ipi.getTipoCalculo().equals(TipoCalculo.VALOR.getValue())) {
					nFeIpiItem.setQunid(ipi.getQuantidade());
					nFeIpiItem.setVunid(ipi.getValorUnidade());
				}
				nFeIpiItem.setVipi(ipi.getValorIpi());
			}
			return nFeIpiItem;
		}
		return null;
	}

	private NFeIcmsItem newNfeImpostosItemIcms(Item item, TributacaoIcmsNFeRequest tributacaoIcms) {
		switch (tributacaoIcms.getCst()) {
		case "00":
			return NFeIcmsItem.builder().orig(item.getOrigem().getCodigo()).cst(tributacaoIcms.getCst())
					.modbc(Integer.parseInt(tributacaoIcms.getModalidadeBaseCalculo()))
					.vbc(tributacaoIcms.getValorBCIcms()).picms(tributacaoIcms.getAliquota())
					.vicms(tributacaoIcms.getValorIcms()).pfcp(BigDecimal.ZERO).vfcp(BigDecimal.ZERO).build();
		case "10":
			return NFeIcmsItem.builder().orig(item.getOrigem().getCodigo()).cst(tributacaoIcms.getCst())
					.modbc(Integer.parseInt(tributacaoIcms.getModalidadeBaseCalculo()))
					.vbc(tributacaoIcms.getValorBCIcms()).picms(tributacaoIcms.getAliquota())
					.vicms(tributacaoIcms.getValorIcms()).vbcfcp(BigDecimal.ZERO).pfcp(BigDecimal.ZERO)
					.vfcp(BigDecimal.ZERO).modbcst(Integer.parseInt(tributacaoIcms.getModalidadeBaseCalculoST()))
					.pmvast(tributacaoIcms.getMargemValorAgregadoST())
					.predbcst(tributacaoIcms.getReducaoBaseCalculoST()).vbcst(tributacaoIcms.getValorBCIcmsST())
					.picmsst(tributacaoIcms.getAliquotaST()).vicmsst(tributacaoIcms.getValorIcmsST())
					.vbcfcpst(BigDecimal.ZERO).pfcpst(BigDecimal.ZERO).vfcpst(BigDecimal.ZERO).build();
		case "20":
			return NFeIcmsItem.builder().orig(item.getOrigem().getCodigo()).cst(tributacaoIcms.getCst())
					.modbc(Integer.parseInt(tributacaoIcms.getModalidadeBaseCalculo()))
					.predbc(tributacaoIcms.getReducaoBaseCalculo()).vbc(tributacaoIcms.getValorBCIcms())
					.picms(tributacaoIcms.getAliquota()).vicms(tributacaoIcms.getValorIcms()).pfcp(BigDecimal.ZERO)
					.vfcp(BigDecimal.ZERO).vicmsdeson(tributacaoIcms.getValorIcmsDesoneracao())
					.motdesicms(tributacaoIcms.getMotivoDesoneracaoId().intValue()).build();
		case "30":
			return NFeIcmsItem.builder().orig(item.getOrigem().getCodigo()).cst(tributacaoIcms.getCst())
					.modbcst(Integer.parseInt(tributacaoIcms.getModalidadeBaseCalculoST()))
					.pmvast(tributacaoIcms.getMargemValorAgregadoST())
					.predbcst(tributacaoIcms.getReducaoBaseCalculoST()).vbcst(tributacaoIcms.getValorBCIcmsST())
					.picmsst(tributacaoIcms.getAliquotaST()).vicmsst(tributacaoIcms.getValorIcmsST())
					.vbcfcpst(BigDecimal.ZERO).pfcpst(BigDecimal.ZERO).vfcpst(BigDecimal.ZERO)
					.vicmsdeson(tributacaoIcms.getValorIcmsDesoneracao())
					.motdesicms(tributacaoIcms.getMotivoDesoneracaoId().intValue()).build();
		case "40":
		case "41":
		case "50":
			return NFeIcmsItem.builder().orig(item.getOrigem().getCodigo()).cst(tributacaoIcms.getCst())
					.vicmsdeson(tributacaoIcms.getValorIcmsDesoneracao())
					.motdesicms(tributacaoIcms.getMotivoDesoneracaoId().intValue()).build();
		case "51":
			return NFeIcmsItem.builder().orig(item.getOrigem().getCodigo()).cst(tributacaoIcms.getCst())
					.modbc(Integer.parseInt(tributacaoIcms.getModalidadeBaseCalculo()))
					.predbc(tributacaoIcms.getReducaoBaseCalculo()).vbc(tributacaoIcms.getValorBCIcms())
					.picms(tributacaoIcms.getAliquota()).vicmsop(tributacaoIcms.getValorIcmsOperacao())
					.pdif(tributacaoIcms.getDiferencialAliquota()).vicmsdif(tributacaoIcms.getValorIcmsDiferido())
					.vicms(tributacaoIcms.getValorIcms()).vbcfcp(BigDecimal.ZERO).pfcp(BigDecimal.ZERO)
					.vfcp(BigDecimal.ZERO).build();
		case "60":
			return NFeIcmsItem.builder().orig(item.getOrigem().getCodigo()).cst(tributacaoIcms.getCst())
					.vbcstret(tributacaoIcms.getValorBCIcmsStRetido()).pst(tributacaoIcms.getAliquotaST())
					.vicmssubstituto(tributacaoIcms.getValorIcmsProprioSubst())
					.vicmsstret(tributacaoIcms.getValorIcmsStRet()).vbcfcpstret(BigDecimal.ZERO)
					.pfcpstret(BigDecimal.ZERO).vfcpstret(BigDecimal.ZERO)
					.predbcefet(tributacaoIcms.getReducaoBaseCalculoEfetiva())
					.vbcefet(tributacaoIcms.getValorBCEfetivo()).picmsefet(tributacaoIcms.getAliquotaIcmsEfetiva())
					.vicmsefet(tributacaoIcms.getValorIcmsEfetiva()).build();
		case "70":
			return NFeIcmsItem.builder().orig(item.getOrigem().getCodigo()).cst(tributacaoIcms.getCst())
					.modbc(Integer.parseInt(tributacaoIcms.getModalidadeBaseCalculo()))
					.predbc(tributacaoIcms.getReducaoBaseCalculo()).vbc(tributacaoIcms.getValorBCIcms())
					.picms(tributacaoIcms.getAliquota()).vicms(tributacaoIcms.getValorIcms()).vbcfcp(BigDecimal.ZERO)
					.pfcp(BigDecimal.ZERO).vfcp(BigDecimal.ZERO)
					.modbcst(Integer.parseInt(tributacaoIcms.getModalidadeBaseCalculoST()))
					.pmvast(tributacaoIcms.getMargemValorAgregadoST())
					.predbcst(tributacaoIcms.getReducaoBaseCalculoST()).vbcst(tributacaoIcms.getValorBCIcmsST())
					.picmsst(tributacaoIcms.getAliquotaST()).vicmsst(tributacaoIcms.getValorIcmsST())
					.vbcfcpst(BigDecimal.ZERO).pfcpst(BigDecimal.ZERO).vfcpst(BigDecimal.ZERO)
					.vicmsdeson(tributacaoIcms.getValorIcmsDesoneracao())
					.motdesicms(tributacaoIcms.getMotivoDesoneracaoId().intValue()).build();
		case "90":
			return NFeIcmsItem.builder().orig(item.getOrigem().getCodigo()).cst(tributacaoIcms.getCst())
					.modbc(Integer.parseInt(tributacaoIcms.getModalidadeBaseCalculo()))
					.predbc(tributacaoIcms.getReducaoBaseCalculo()).vbc(tributacaoIcms.getValorBCIcms())
					.picms(tributacaoIcms.getAliquota()).vicms(tributacaoIcms.getValorIcms()).vbcfcp(BigDecimal.ZERO)
					.pfcp(BigDecimal.ZERO).vfcp(BigDecimal.ZERO)
					.modbcst(Integer.parseInt(tributacaoIcms.getModalidadeBaseCalculoST()))
					.pmvast(tributacaoIcms.getMargemValorAgregadoST())
					.predbcst(tributacaoIcms.getReducaoBaseCalculoST()).vbcst(tributacaoIcms.getValorBCIcmsST())
					.picmsst(tributacaoIcms.getAliquotaST()).vicmsst(tributacaoIcms.getValorIcmsST())
					.vbcfcpst(BigDecimal.ZERO).pfcpst(BigDecimal.ZERO).vfcpst(BigDecimal.ZERO)
					.vicmsdeson(tributacaoIcms.getValorIcmsDesoneracao())
					.motdesicms(tributacaoIcms.getMotivoDesoneracaoId().intValue()).build();
		case "101":
			return NFeIcmsItem.builder().orig(item.getOrigem().getCodigo())
					.csosn(Integer.parseInt(tributacaoIcms.getCst())).pcredsn(tributacaoIcms.getAliquotaCredito())
					.vcredicmssn(tributacaoIcms.getValorCredIcmsSN()).build();
		case "102":
		case "103":
		case "300":
		case "400":
			return NFeIcmsItem.builder().orig(item.getOrigem().getCodigo())
					.csosn(Integer.parseInt(tributacaoIcms.getCst())).build();
		case "201":
			return NFeIcmsItem.builder().orig(item.getOrigem().getCodigo())
					.csosn(Integer.parseInt(tributacaoIcms.getCst()))
					.modbcst(Integer.parseInt(tributacaoIcms.getModalidadeBaseCalculoST()))
					.pmvast(tributacaoIcms.getMargemValorAgregadoST())
					.predbcst(tributacaoIcms.getReducaoBaseCalculoST()).vbcst(tributacaoIcms.getValorBCIcmsST())
					.picmsst(tributacaoIcms.getValorIcmsST()).vbcfcpst(BigDecimal.ZERO).pfcpst(BigDecimal.ZERO)
					.vfcpst(BigDecimal.ZERO).pcredsn(tributacaoIcms.getAliquotaCredito())
					.vcredicmssn(tributacaoIcms.getValorCredIcmsSN()).build();
		case "202":
		case "203":
			return NFeIcmsItem.builder().orig(item.getOrigem().getCodigo())
					.csosn(Integer.parseInt(tributacaoIcms.getCst()))
					.modbcst(Integer.parseInt(tributacaoIcms.getModalidadeBaseCalculoST()))
					.pmvast(tributacaoIcms.getMargemValorAgregadoST())
					.predbcst(tributacaoIcms.getReducaoBaseCalculoST()).vbcst(tributacaoIcms.getValorBCIcmsST())
					.picmsst(tributacaoIcms.getValorIcmsST()).vbcfcpst(BigDecimal.ZERO).pfcpst(BigDecimal.ZERO)
					.vfcpst(BigDecimal.ZERO).pcredsn(tributacaoIcms.getAliquotaCredito()).build();
		case "500":
			return NFeIcmsItem.builder().orig(item.getOrigem().getCodigo())
					.csosn(Integer.parseInt(tributacaoIcms.getCst())).vbcstret(tributacaoIcms.getValorBCIcmsStRetido())
					.pst(tributacaoIcms.getAliquotaST()).vicmssubstituto(tributacaoIcms.getValorIcmsProprioSubst())
					.vicmsstret(tributacaoIcms.getValorIcmsStRet()).vbcfcpstret(BigDecimal.ZERO)
					.pfcpstret(BigDecimal.ZERO).vfcpstret(BigDecimal.ZERO)
					.predbcefet(tributacaoIcms.getReducaoBaseCalculoEfetiva())
					.vbcefet(tributacaoIcms.getValorBCEfetivo()).picmsefet(tributacaoIcms.getAliquotaIcmsEfetiva())
					.vicmsefet(tributacaoIcms.getValorIcmsEfetiva()).build();
		case "900":
			return NFeIcmsItem.builder().orig(item.getOrigem().getCodigo())
					.csosn(Integer.parseInt(tributacaoIcms.getCst()))
					.modbc(Integer.parseInt(tributacaoIcms.getModalidadeBaseCalculo()))
					.vbc(tributacaoIcms.getValorBCIcms()).predbc(tributacaoIcms.getReducaoBaseCalculo())
					.picms(tributacaoIcms.getAliquota()).vicms(tributacaoIcms.getValorIcms())
					.modbcst(Integer.parseInt(tributacaoIcms.getModalidadeBaseCalculoST()))
					.pmvast(tributacaoIcms.getMargemValorAgregadoST())
					.predbcst(tributacaoIcms.getReducaoBaseCalculoST()).vbcst(tributacaoIcms.getValorBCIcmsST())
					.picmsst(tributacaoIcms.getAliquotaST()).vicmsst(tributacaoIcms.getValorIcmsST())
					.vbcfcpst(BigDecimal.ZERO).pfcpst(BigDecimal.ZERO).vfcpst(BigDecimal.ZERO)
					.pcredsn(tributacaoIcms.getAliquotaCredito()).vcredicmssn(tributacaoIcms.getValorCredIcmsSN())
					.build();
		}

		return null;
	}

	private NFeProdItem newNfeProdItem(Item item, DetalhamentoItemNFeRequest detalhamentoItemNFeRequest) {
		Cfop cfop = cfopService.getById(detalhamentoItemNFeRequest.getCfopId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado cfop com id: " + detalhamentoItemNFeRequest.getCfopId()));
		UnidadeMedida unidadeMedidaComercial = unidadeMedidaService
				.getById(detalhamentoItemNFeRequest.getUnidadeMedidaId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado unidade medica com id: " + detalhamentoItemNFeRequest.getUnidadeMedidaId()));

		NFeProdItem nfeProdItem = new NFeProdItem();
		nfeProdItem.setCprod(Integer.toString(item.getCodigo()));
		nfeProdItem.setCean(item.getGtinEan());
		nfeProdItem.setXprod(item.getNome());
		nfeProdItem.setNcm(item.getNcm().getCodigo());
		nfeProdItem.setNve(null);
		nfeProdItem.setCest(null);
		nfeProdItem.setIndescala(null);
		nfeProdItem.setCnpjfab(null);
		nfeProdItem.setCbenef(null);
		nfeProdItem.setExtipi(null);
		nfeProdItem.setCfop(Integer.toString(cfop.getCodigo()));
		nfeProdItem.setUcom(unidadeMedidaComercial.getSigla());
		nfeProdItem.setQcom(detalhamentoItemNFeRequest.getQuantidade());
		nfeProdItem.setVuncom(detalhamentoItemNFeRequest.getValorUnitario());
		nfeProdItem.setVprod(detalhamentoItemNFeRequest.getSubTotal());
		nfeProdItem.setUtrib(unidadeMedidaComercial.getSigla());
		nfeProdItem.setQtrib(detalhamentoItemNFeRequest.getQuantidade());
		nfeProdItem.setVuntrib(detalhamentoItemNFeRequest.getValorUnitario());
		nfeProdItem.setVfrete(detalhamentoItemNFeRequest.getValorFrete());
		nfeProdItem.setVseg(detalhamentoItemNFeRequest.getValorSeguro());
		nfeProdItem.setVdesc(detalhamentoItemNFeRequest.getValorDesconto());
		nfeProdItem.setVoutro(detalhamentoItemNFeRequest.getValorOutrasDespesas());
		nfeProdItem.setIndtot(0);

		return nfeProdItem;
	}

	private void mergeAutorizacoes(List<NFeAut> autorizacoes, List<AutorizacaoRequest> autorizacoesRequest) {
		if (autorizacoesRequest == null || autorizacoesRequest.isEmpty()) {
			autorizacoes = null;
			return;
		}

		autorizacoes = new ArrayList<>();
		autorizacoesRequest.forEach(aut -> {
			NFeAut nFeAut = new NFeAut();
			nFeAut.setCnpj(aut.getCnpj());
			nFeAut.setCpf(aut.getCpf());
		});
	}

	private void mergeLocalEntrega(NFeEntrega nfeEntrega, LocalEntregaRequest localEntrega) {
		if (localEntrega == null) {
			nfeEntrega = null;
			return;
		}
		if (nfeEntrega == null)
			nfeEntrega = new NFeEntrega();

		Cidade cidade = cidadeService.getById(localEntrega.getCidadeId())
				.orElseThrow(() -> new RegisterNotFoundException("Cidade do local de entrega não econtrada"));

		if (localEntrega.getTipoPessoa().equals(TipoPessoa.PESSOA_JURIDICA.getValue()))
			nfeEntrega.setCnpj(localEntrega.getCnpj());
		if (localEntrega.getTipoPessoa().equals(TipoPessoa.PESSOA_FISICA.getValue()))
			nfeEntrega.setCpf(localEntrega.getCpf());
		nfeEntrega.setXnome(localEntrega.getNomeRazaoSocial());
		nfeEntrega.setXlgr(localEntrega.getEndereco());
		nfeEntrega.setNro(localEntrega.getNumero());
		nfeEntrega.setXcpl(localEntrega.getComplemento());
		nfeEntrega.setXbairro(localEntrega.getBairro());
		nfeEntrega.setCmun(cidade.getCodigoIBGE().intValue());
		nfeEntrega.setXmun(cidade.getNome());
		nfeEntrega.setUf(cidade.getUf().getSigla());
		nfeEntrega.setCep(localEntrega.getCep());
		nfeEntrega.setCpais(cidade.getPais().getCodigoIBGE());
		nfeEntrega.setXpais(cidade.getPais().getNome());
		nfeEntrega.setFone(localEntrega.getTelefone());
		nfeEntrega.setEmail(localEntrega.getEmail());
	}

	private void mergeLocalRetirada(NFeRetirada nfeRetirada, LocalRetiradaRequest localRetirada) {
		if (localRetirada == null) {
			nfeRetirada = null;
			return;
		}
		if (nfeRetirada == null)
			nfeRetirada = new NFeRetirada();

		Cidade cidade = cidadeService.getById(localRetirada.getCidadeId())
				.orElseThrow(() -> new RegisterNotFoundException("Cidade do local de retirada não econtrada"));

		if (localRetirada.getTipoPessoa().equals(TipoPessoa.PESSOA_JURIDICA.getValue()))
			nfeRetirada.setCnpj(localRetirada.getCnpj());
		if (localRetirada.getTipoPessoa().equals(TipoPessoa.PESSOA_FISICA.getValue()))
			nfeRetirada.setCpf(localRetirada.getCpf());
		nfeRetirada.setXnome(localRetirada.getNomeRazaoSocial());
		nfeRetirada.setXlgr(localRetirada.getEndereco());
		nfeRetirada.setNro(localRetirada.getNumero());
		nfeRetirada.setXcpl(localRetirada.getComplemento());
		nfeRetirada.setXbairro(localRetirada.getBairro());
		nfeRetirada.setCmun(cidade.getCodigoIBGE().intValue());
		nfeRetirada.setXmun(cidade.getNome());
		nfeRetirada.setUf(cidade.getUf().getSigla());
		nfeRetirada.setCep(localRetirada.getCep());
		nfeRetirada.setCpais(cidade.getPais().getCodigoIBGE());
		nfeRetirada.setXpais(cidade.getPais().getNome());
		nfeRetirada.setFone(localRetirada.getTelefone());
		if (localRetirada.getTipoPessoa().equals(TipoPessoa.PESSOA_JURIDICA.getValue())) {
			nfeRetirada.setIe(localRetirada.getInscricaoEstadual());
		}
		nfeRetirada.setEmail(localRetirada.getEmail());
	}

	private void mergeDestinatarioNFe(NFeDest nfeDest, DestinatarioNFeRequest destinatarioNFeRequest) {
		if (nfeDest == null)
			nfeDest = new NFeDest();
		ParceiroLocal parceiroLocal = parceiroLocalService.getById(destinatarioNFeRequest.getDestinatarioId())
				.orElseThrow(() -> new RegisterNotFoundException("Parceiro não encontrado"));
		ParceiroLocalEndereco parceiroLocalEndereco = parceiroLocal.getEnderecoNFe();
		ParceiroLocalTelefone parceiroLocalTelefone = parceiroLocal.getTelefoneNFe();
		ParceiroLocalEmail parceiroLocalEmail = parceiroLocal.getEmailNFe();

		if (parceiroLocal.getParceiro().getTipoPessoa().equals(TipoPessoa.PESSOA_JURIDICA))
			nfeDest.setCnpj(parceiroLocal.getCpfCnpj());
		if (parceiroLocal.getParceiro().getTipoPessoa().equals(TipoPessoa.PESSOA_FISICA))
			nfeDest.setCpf(parceiroLocal.getCpfCnpj());
		nfeDest.setXnome(parceiroLocal.getParceiro().getNomeRazaoSocial());
		nfeDest.setXlgr(parceiroLocalEndereco.getEndereco());
		nfeDest.setNro(parceiroLocalEndereco.getNumero());
		nfeDest.setXcpl(parceiroLocalEndereco.getComplemento());
		nfeDest.setXbairro(parceiroLocalEndereco.getBairro());
		nfeDest.setCmun(parceiroLocalEndereco.getCidade().getCodigoIBGE().intValue());
		nfeDest.setXmun(parceiroLocalEndereco.getCidade().getNome());
		nfeDest.setUf(parceiroLocalEndereco.getCidade().getUf().getSigla());
		nfeDest.setCep(parceiroLocalEndereco.getCep());
		nfeDest.setCpais(parceiroLocalEndereco.getCidade().getPais().getCodigoIBGE());
		nfeDest.setXpais(parceiroLocalEndereco.getCidade().getPais().getNome());
		nfeDest.setFone(parceiroLocalTelefone.getNumero());
		if (parceiroLocal.getParceiro().getTipoPessoa().equals(TipoPessoa.PESSOA_JURIDICA)) {
			nfeDest.setIndieest(parceiroLocal.getDadosPessoaJuridica().get(0).getIndicadorIE().getCodigoReceita());
			nfeDest.setIe(parceiroLocal.getDadosPessoaJuridica().get(0).getInscricaoEstadual());
//			nfeDest.setIsuf(parceiroLocal.getDadosPessoaJuridica().get(0).getInscricaoSuframa());
		}
		nfeDest.setEmail(parceiroLocalEmail.getEmail());
	}

	private void mergeEmitenteNFe(NFeEmit nfeEmit, IdentificacaoNFeRequest identificacaoNFeRequest) {

	}

	private void mergeIdentificacaoNFe(NFeIde nfeIde, IdentificacaoNFeRequest identificacaoNFeRequest) {

		nfeIde.setNatop(identificacaoNFeRequest.getNaturezaOperacao());
		nfeIde.setDhemi(identificacaoNFeRequest.getDataHoraEmissao());
		nfeIde.setDhsaient(identificacaoNFeRequest.getDataHoraSaidaEntrada());
		nfeIde.setTpnf(Integer.parseInt(
				IndicadorOperacao.fromValue(identificacaoNFeRequest.getIndicadorOperacao()).getCodigoReceita()));
		nfeIde.setIddest(Integer
				.parseInt(DestinoOperacao.fromValue(identificacaoNFeRequest.getDestinoOperacao()).getCodigoReceita()));
		nfeIde.setCmunfg(0);
		nfeIde.setFinnfe(Integer
				.parseInt(FinalidadeNfe.fromValue(identificacaoNFeRequest.getFinalidadeNFe()).getCodigoReceita()));
		nfeIde.setIndpres(Integer.parseInt(TipoPresencaComprador
				.fromValue(identificacaoNFeRequest.getTipoPresencaComprador()).getCodigoReceita()));

		if (identificacaoNFeRequest.getDocumentosReferenciados() != null
				&& !identificacaoNFeRequest.getDocumentosReferenciados().isEmpty()) {
			identificacaoNFeRequest.getDocumentosReferenciados().forEach(doc -> {
				NFeRef nFeRef = new NFeRef();
				if (doc.getTipoDocumentoReferenciado().equals(TipoDocumentoReferenciado.NFENFCE)) {
					nFeRef.setRefNFe(doc.getChaveAcesso());
				} else if (doc.getTipoDocumentoReferenciado().equals(TipoDocumentoReferenciado.NF11A2)) {
					nFeRef.setCuf(doc.getUfIdEmitente().intValue());
					nFeRef.setAamm(Integer.parseInt(doc.getAnoMes()));
					nFeRef.setCnpj(doc.getCnpjEmitente());
					nFeRef.setMod(doc.getModeloDocumentoFiscal());
					nFeRef.setSerie(Integer.parseInt(doc.getSerie()));
					nFeRef.setNnf(doc.getNumero());
				} else if (doc.getTipoDocumentoReferenciado().equals(TipoDocumentoReferenciado.NFPRODUTORRURAL)) {
					nFeRef.setCuf(doc.getUfIdEmitente().intValue());
					nFeRef.setAamm(Integer.parseInt(doc.getAnoMes()));
					nFeRef.setCnpj(doc.getCnpjEmitente());
					nFeRef.setCpf(doc.getCpfEmitente());
					nFeRef.setIe(doc.getIEEmitente());
					nFeRef.setMod(doc.getModeloDocumentoFiscal());
					nFeRef.setSerie(Integer.parseInt(doc.getSerie()));
					nFeRef.setNnf(doc.getNumero());
				} else if (doc.getTipoDocumentoReferenciado().equals(TipoDocumentoReferenciado.CTe)) {
					nFeRef.setRefcte(doc.getChaveAcesso());
				} else if (doc.getTipoDocumentoReferenciado().equals(TipoDocumentoReferenciado.CUPOMFISCAL)) {
					nFeRef.setMod(doc.getModeloDocumentoFiscal());
					nFeRef.setNecf(doc.getNumeroECF());
					nFeRef.setNcoo(doc.getNumeroCOO());
				}
			});
		} else {
			nfeIde.setNfesRefs(null);
		}
	}

}
