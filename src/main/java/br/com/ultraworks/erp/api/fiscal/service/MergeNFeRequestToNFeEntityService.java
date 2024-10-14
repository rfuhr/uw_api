package br.com.ultraworks.erp.api.fiscal.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.estoque.service.ItemService;
import br.com.ultraworks.erp.api.fiscal.domain.cfop.Cfop;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalcofins.ConfiguracaoFiscalCofins;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalicms.ConfiguracaoFiscalIcms;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalipi.ConfiguracaoFiscalIpi;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalpis.ConfiguracaoFiscalPis;
import br.com.ultraworks.erp.api.fiscal.domain.destinooperacao.DestinoOperacao;
import br.com.ultraworks.erp.api.fiscal.domain.finalidadenfe.FinalidadeNfe;
import br.com.ultraworks.erp.api.fiscal.domain.indicadoriedestinatario.IndicadorIEDestinatario;
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
	private ConfiguracaoFiscalIcmsService configuracaoFiscalIcmsService;
	private ConfiguracaoFiscalIpiService configuracaoFiscalIpiService;
	private ConfiguracaoFiscalPisService configuracaoFiscalPisService;
	private ConfiguracaoFiscalCofinsService configuracaoFiscalCofinsService;

	public MergeNFeRequestToNFeEntityService(ParceiroLocalService parceiroLocalService, CidadeService cidadeService,
			ItemService itemService, CfopService cfopService, UnidadeMedidaService unidadeMedidaService,
			ConfiguracaoFiscalIcmsService configuracaoFiscalIcmsService,
			ConfiguracaoFiscalIpiService configuracaoFiscalIpiService,
			ConfiguracaoFiscalPisService configuracaoFiscalPisService,
			ConfiguracaoFiscalCofinsService configuracaoFiscalCofinsService) {
		this.parceiroLocalService = parceiroLocalService;
		this.cidadeService = cidadeService;
		this.itemService = itemService;
		this.cfopService = cfopService;
		this.unidadeMedidaService = unidadeMedidaService;
		this.configuracaoFiscalIcmsService = configuracaoFiscalIcmsService;
		this.configuracaoFiscalIpiService = configuracaoFiscalIpiService;
		this.configuracaoFiscalPisService = configuracaoFiscalPisService;
		this.configuracaoFiscalCofinsService = configuracaoFiscalCofinsService;
	}

	public NFe merge(NFe nfe, NFeRequest nFeRequest) {

		mergeIdentificacaoNFe(nfe, nFeRequest.getIdentificacaoNFeRequest());
		mergeEmitenteNFe(nfe.getNfeEmit(), nFeRequest.getIdentificacaoNFeRequest());
		mergeDestinatarioNFe(nfe, nFeRequest.getDestinatarioNFeRequest());
		mergeLocalRetirada(nfe, nFeRequest.getIdentificacaoNFeRequest().getLocalRetirada());
		mergeLocalEntrega(nfe, nFeRequest.getDestinatarioNFeRequest().getLocalEntrega());
		mergeAutorizacoes(nfe, nFeRequest.getIdentificacaoNFeRequest().getAutorizacoes());
		mergeItensNFe(nfe, nFeRequest.getItensNFeRequest());
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
			nfeInfoAdic.setNfe(nfe);
			nfeInfoAdic.setInfcpl(informacoesAdicionaisNFeRequest.getInfoAdicionaisComplementares());

			nfe.setNfeInfoAdic(nfeInfoAdic);
		}
	}

	private void mergePagamentos(NFe nfe, FinanceiroNFeRequest financeiroNFeRequest) {
		NFePagamento nFePagamento = new NFePagamento();
		nFePagamento.setNfe(nfe);
		nFePagamento.setVtroco(financeiroNFeRequest.getValorTroco());
		if (financeiroNFeRequest.getPagamentos() != null && !financeiroNFeRequest.getPagamentos().isEmpty()) {
			financeiroNFeRequest.getPagamentos().forEach(pag -> {
				NFeDetPagamento pagamento = NFeDetPagamento.builder().nfePagamento(nFePagamento)
						.tpag(pag.getMeioPagamento()).vpag(pag.getValorPagamento()).build();
				if (!pag.getMeioPagamento().equals(MeioPagamento.SEMPAGAMENTO.getValue())) {
					pagamento.setIndpag(Integer.parseInt(pag.getIndicadorFormaPagamento()));
					if (StringUtils.equalsAny(pag.getMeioPagamento(), MeioPagamento.CARTAOCREDITO.getValue(),
							MeioPagamento.CARTAODEBITO.getValue())) {
						pagamento.setTpintegra(Integer.parseInt(pag.getTipoIntegracao()));
						pagamento.setCnpj(pag.getCnpj());
						pagamento.setTband(pag.getBandeiraCartao());
						pagamento.setCaut(pag.getNumeroAutorizacao());
					}
				}
				if (nFePagamento.getDetalhesPagamento() == null)
					nFePagamento.setDetalhesPagamento(new ArrayList<>());
				nFePagamento.getDetalhesPagamento().add(pagamento);
			});
		}

		nfe.setNfePagamento(nFePagamento);

	}

	private void mergeTransporte(NFe nfe, TransporteNFeRequest transporteNFeRequest) {
		NFeTransporte nFeTransport = new NFeTransporte();
		nFeTransport.setNfe(nfe);

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
					nFeTransport.getReboques()
							.add(NFeReboque.builder().nfeTransporte(nFeTransport).placa(reboque.getPlacaVeiculo())
									.ufplaca(reboque.getSiglaUf()).rntc(reboque.getRntc()).build());
				});
			}
			if (transporteNFeRequest.getVolumes() != null && !transporteNFeRequest.getVolumes().isEmpty()) {
				nFeTransport.setVolumes(new ArrayList<>());
				transporteNFeRequest.getVolumes().forEach(volume -> {
					NFeVolume nfeVolume = NFeVolume.builder().nfeTransporte(nFeTransport).qvol(volume.getQuantidade())
							.esp(volume.getEspecie()).marca(volume.getMarca()).nvol(volume.getNumeracao())
							.pesol(volume.getPesoLiquido()).pesob(volume.getPesoBruto()).build();

					if (volume.getLacres() != null && !volume.getLacres().isEmpty()) {
						nfeVolume.setLacres(new ArrayList<>());
						volume.getLacres().forEach(lacre -> {
							nfeVolume.getLacres().add(NFeLacre.builder().nfeVolume(nfeVolume).nlacre(lacre).build());
						});
					}

					nFeTransport.getVolumes().add(nfeVolume);
				});
			}

		}
		nfe.setNfeTransporte(nFeTransport);
	}

	private void mergeTotaisNFe(NFe nfe) {
		NFeTotais nfeTotais = new NFeTotais();
		nfeTotais.setNfe(nfe);
		nfeTotais.setVbc(getTotalVBc(nfe.getItens()));
		nfeTotais.setVicms(getTotalVIcms(nfe.getItens()));
		nfeTotais.setVicmsdeson(getTotalVIcmsDeson(nfe.getItens()));
		nfeTotais.setVfcpufdest(getTotalvFCPUFDest(nfe.getItens()));
		nfeTotais.setVicmsufdest(getTotalvicmsufdest(nfe.getItens()));
		nfeTotais.setVicmsufremet(getTotalVicmsufremet(nfe.getItens()));
		nfeTotais.setVfcp(getTotalVicmsufremet(nfe.getItens()));
		nfeTotais.setVbcst(getTotalVbcst(nfe.getItens()));
		nfeTotais.setVst(getTotalVst(nfe.getItens()));
		nfeTotais.setVfcpst(getTotalVfcpst(nfe.getItens()));
		nfeTotais.setVfcpstret(getTotalVfcpstret(nfe.getItens()));
		nfeTotais.setVprod(getTotalVprod(nfe.getItens()));
		nfeTotais.setVfrete(getTotalVfrete(nfe.getItens()));
		nfeTotais.setVseg(getTotalVseg(nfe.getItens()));
		nfeTotais.setVdesc(getTotalVdesc(nfe.getItens()));
		nfeTotais.setVii(getTotalVii(nfe.getItens()));
		nfeTotais.setVipi(getTotalVipi(nfe.getItens()));
		nfeTotais.setVipidevol(getTotalVipidevol(nfe.getItens()));
		nfeTotais.setVpis(getTotalVpis(nfe.getItens()));
		nfeTotais.setVcofins(getTotalVcofins(nfe.getItens()));
		nfeTotais.setVoutro(getTotalVoutro(nfe.getItens()));
		nfeTotais.setVnf(getTotalVnf(nfeTotais));
		nfeTotais.setVtottrib(BigDecimal.ZERO);

		nfe.setNfeTotais(nfeTotais);
	}

	private BigDecimal getTotalVnf(NFeTotais nfeTotais) {
		return nfeTotais.getVprod().subtract(nfeTotais.getVdesc()).subtract(nfeTotais.getVicmsdeson()).add(nfeTotais.getVst())
				.add(nfeTotais.getVfcpst()).add(nfeTotais.getVfrete()).add(nfeTotais.getVseg())
				.add(nfeTotais.getVoutro()).add(nfeTotais.getVii()).add(nfeTotais.getVipi())
				.add(nfeTotais.getVipidevol());
	}

	private BigDecimal getTotalVoutro(List<NFeDetItem> itens) {
		if (itens != null && !itens.isEmpty()) {
			return itens.stream().map(NFeDetItem::getNfeProdItem).filter(nfeProdItem -> nfeProdItem != null)
					.map(NFeProdItem::getVoutro).filter(v -> v != null).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal getTotalVcofins(List<NFeDetItem> itens) {
		if (itens != null && !itens.isEmpty()) {
			return itens.stream().map(NFeDetItem::getNfeImpostosItem).filter(nfeImpostosItem -> nfeImpostosItem != null)
					.map(NFeImpostosItem::getNfeCofinsItem).filter(nfeCofinsItem -> nfeCofinsItem != null)
					.map(NFeCofinsItem::getVcofins).filter(v -> v != null).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal getTotalVpis(List<NFeDetItem> itens) {
		if (itens != null && !itens.isEmpty()) {
			return itens.stream().map(NFeDetItem::getNfeImpostosItem).filter(nfeImpostosItem -> nfeImpostosItem != null)
					.map(NFeImpostosItem::getNfePisItem).filter(nfePisItem -> nfePisItem != null)
					.map(NFePisItem::getVpis).filter(v -> v != null).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal getTotalVipidevol(List<NFeDetItem> itens) {
		return BigDecimal.ZERO;
	}

	private BigDecimal getTotalVipi(List<NFeDetItem> itens) {
		if (itens != null && !itens.isEmpty()) {
			return itens.stream().map(NFeDetItem::getNfeImpostosItem).filter(nfeImpostosItem -> nfeImpostosItem != null)
					.map(NFeImpostosItem::getNfeIpiItem).filter(nfeIpiItem -> nfeIpiItem != null)
					.map(NFeIpiItem::getVipi).filter(v -> v != null).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal getTotalVii(List<NFeDetItem> itens) {
		return BigDecimal.ZERO;
	}

	private BigDecimal getTotalVdesc(List<NFeDetItem> itens) {
		if (itens != null && !itens.isEmpty()) {
			return itens.stream().map(NFeDetItem::getNfeProdItem).filter(nfeProdItem -> nfeProdItem != null)
					.map(NFeProdItem::getVdesc).filter(v -> v != null).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal getTotalVseg(List<NFeDetItem> itens) {
		if (itens != null && !itens.isEmpty()) {
			return itens.stream().map(NFeDetItem::getNfeProdItem).filter(nfeProdItem -> nfeProdItem != null)
					.map(NFeProdItem::getVseg).filter(v -> v != null).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal getTotalVfrete(List<NFeDetItem> itens) {
		if (itens != null && !itens.isEmpty()) {
			return itens.stream().map(NFeDetItem::getNfeProdItem).filter(nfeProdItem -> nfeProdItem != null)
					.map(NFeProdItem::getVfrete).filter(v -> v != null).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal getTotalVprod(List<NFeDetItem> itens) {
		if (itens != null && !itens.isEmpty()) {
			return itens.stream().map(NFeDetItem::getNfeProdItem).filter(nfeProdItem -> nfeProdItem != null)
					.map(NFeProdItem::getVprod).filter(v -> v != null).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal getTotalVfcpstret(List<NFeDetItem> itens) {
		if (itens != null && !itens.isEmpty()) {
			return itens.stream().map(NFeDetItem::getNfeImpostosItem).filter(nfeImpostosItem -> nfeImpostosItem != null)
					.map(NFeImpostosItem::getNfeIcmsItem).filter(nfeIcmsItem -> nfeIcmsItem != null)
					.map(NFeIcmsItem::getVfcpstret).filter(v -> v != null).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal getTotalVfcpst(List<NFeDetItem> itens) {
		if (itens != null && !itens.isEmpty()) {
			return itens.stream().map(NFeDetItem::getNfeImpostosItem).filter(nfeImpostosItem -> nfeImpostosItem != null)
					.map(NFeImpostosItem::getNfeIcmsItem).filter(nfeIcmsItem -> nfeIcmsItem != null)
					.map(NFeIcmsItem::getVfcpst).filter(v -> v != null).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal getTotalVst(List<NFeDetItem> itens) {
		if (itens != null && !itens.isEmpty()) {
			return itens.stream().map(NFeDetItem::getNfeImpostosItem).filter(nfeImpostosItem -> nfeImpostosItem != null)
					.map(NFeImpostosItem::getNfeIcmsItem).filter(nfeIcmsItem -> nfeIcmsItem != null)
					.map(NFeIcmsItem::getVicmsst).filter(v -> v != null).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal getTotalVbcst(List<NFeDetItem> itens) {
		if (itens != null && !itens.isEmpty()) {
			return itens.stream().map(NFeDetItem::getNfeImpostosItem).filter(nfeImpostosItem -> nfeImpostosItem != null)
					.map(NFeImpostosItem::getNfeIcmsItem).filter(nfeIcmsItem -> nfeIcmsItem != null)
					.map(NFeIcmsItem::getVbcst).filter(v -> v != null).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal getTotalVicmsufremet(List<NFeDetItem> itens) {
		return BigDecimal.ZERO;
//		if (itens != null && !itens.isEmpty()) {
//			return itens.stream().map(NFeDetItem::getNfeImpostosItem)
//			.filter(nfeImpostosItem -> nfeImpostosItem != null)
//			.map(NFeImpostosItem::getNfeIcmsItem)
//			.filter(nfeIcmsItem -> nfeIcmsItem != null)
//			.map(NFeIcmsItem::getVicmsdeson)
//			.filter(v -> v != null)
//			.reduce(BigDecimal.ZERO, BigDecimal::add);
//		} else {
//			return BigDecimal.ZERO;
//		}
	}

	private BigDecimal getTotalvicmsufdest(List<NFeDetItem> itens) {
		return BigDecimal.ZERO;
//		if (itens != null && !itens.isEmpty()) {
//			return itens.stream().map(NFeDetItem::getNfeImpostosItem)
//			.filter(nfeImpostosItem -> nfeImpostosItem != null)
//			.map(NFeImpostosItem::getNfeIcmsItem)
//			.filter(nfeIcmsItem -> nfeIcmsItem != null)
//			.map(NFeIcmsItem::getVicmsdeson)
//			.filter(v -> v != null)
//			.reduce(BigDecimal.ZERO, BigDecimal::add);
//		} else {
//			return BigDecimal.ZERO;
//		}
	}

	private BigDecimal getTotalvFCPUFDest(List<NFeDetItem> itens) {
		if (itens != null && !itens.isEmpty()) {
			return itens.stream().map(NFeDetItem::getNfeImpostosItem).filter(nfeImpostosItem -> nfeImpostosItem != null)
					.map(NFeImpostosItem::getNfeIcmsItem).filter(nfeIcmsItem -> nfeIcmsItem != null)
					.map(NFeIcmsItem::getVicmsdeson).filter(v -> v != null).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal getTotalVIcmsDeson(List<NFeDetItem> itens) {
		if (itens != null && !itens.isEmpty()) {
			return itens.stream().map(NFeDetItem::getNfeImpostosItem).filter(nfeImpostosItem -> nfeImpostosItem != null)
					.map(NFeImpostosItem::getNfeIcmsItem).filter(nfeIcmsItem -> nfeIcmsItem != null)
					.map(NFeIcmsItem::getVicmsdeson).filter(v -> v != null).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal getTotalVIcms(List<NFeDetItem> itens) {
		if (itens != null && !itens.isEmpty()) {
			return itens.stream().map(NFeDetItem::getNfeImpostosItem).filter(nfeImpostosItem -> nfeImpostosItem != null)
					.map(NFeImpostosItem::getNfeIcmsItem).filter(nfeIcmsItem -> nfeIcmsItem != null)
					.filter(nfeIcmsItem -> !Arrays.asList("40", "41", "50").contains(nfeIcmsItem.getCst()))
					.map(NFeIcmsItem::getVicms).filter(vicms -> vicms != null).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal getTotalVBc(List<NFeDetItem> itens) {
		if (itens != null && !itens.isEmpty()) {
			return itens.stream().map(NFeDetItem::getNfeImpostosItem).filter(nfeImpostosItem -> nfeImpostosItem != null)
					.map(NFeImpostosItem::getNfeIcmsItem).filter(nfeIcmsItem -> nfeIcmsItem != null)
					.map(NFeIcmsItem::getVbc).filter(vbc -> vbc != null).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private void mergeItensNFe(NFe nfe, ItensNFeRequest itensNFeRequest) {
		List<NFeDetItem> itens = nfe.getItens();
		if (itens == null) {
			itens = new ArrayList<>();
		} else {
			itens.clear();
		}

		if (itensNFeRequest.getItens() != null && itensNFeRequest.getItens().size() > 0) {
			int seqItem = 0;
			for (ItemNFeRequest itemNFeRequest : itensNFeRequest.getItens()) {
				Item item = itemService.getById(itemNFeRequest.getDetalhamentoItem().getItemId())
						.orElseThrow(() -> new RegisterNotFoundException(
								"Não encontrado item com id: " + itemNFeRequest.getDetalhamentoItem().getItemId()));

				NFeDetItem nfeDetItem = new NFeDetItem();
				nfeDetItem.setNfe(nfe);
				nfeDetItem.setNItem(++seqItem);
				nfeDetItem.setNfeProdItem(newNfeProdItem(nfeDetItem, item, itemNFeRequest.getDetalhamentoItem()));
				nfeDetItem.setNfeImpostosItem(newNfeImpostosItem(nfeDetItem, item, itemNFeRequest));
				nfeDetItem.setNfeInfoAdicItem(newNfeInfoAdicItem(nfeDetItem, itemNFeRequest));

				itens.add(nfeDetItem);
			}

		} else {
			itensNFeRequest.setItens(null);
		}

	}

	private NFeInfoAdicItem newNfeInfoAdicItem(NFeDetItem nfeDetItem, ItemNFeRequest itemNFeRequest) {
		if (StringUtils.isNotBlank(itemNFeRequest.getInfoAdicionais())) {
			NFeInfoAdicItem nFeInfoAdicItem = new NFeInfoAdicItem();
			nFeInfoAdicItem.setNFeDetItem(nfeDetItem);
			nFeInfoAdicItem.setInfadprod(itemNFeRequest.getInfoAdicionais());
			return nFeInfoAdicItem;
		}
		return null;
	}

	private NFeImpostosItem newNfeImpostosItem(NFeDetItem nfeDetItem, Item item, ItemNFeRequest itemNFeRequest) {
		NFeImpostosItem nFeImpostosItem = new NFeImpostosItem();
		nFeImpostosItem.setNFeDetItem(nfeDetItem);

		if (itemNFeRequest.getTributacaoIcms() != null)
			nFeImpostosItem
					.setNfeIcmsItem(newNfeImpostosItemIcms(nFeImpostosItem, item, itemNFeRequest.getTributacaoIcms()));
		if (itemNFeRequest.getIpi() != null)
			nFeImpostosItem.setNfeIpiItem(newNfeImpostosItemIpi(nFeImpostosItem, item, itemNFeRequest.getIpi()));
		if (itemNFeRequest.getPis() != null)
			nFeImpostosItem.setNfePisItem(newNfeImpostosItemPis(nFeImpostosItem, item, itemNFeRequest.getPis()));
		if (itemNFeRequest.getCofins() != null)
			nFeImpostosItem
					.setNfeCofinsItem(newNfeImpostosItemCofins(nFeImpostosItem, item, itemNFeRequest.getCofins()));

		return nFeImpostosItem;
	}

	private NFeCofinsItem newNfeImpostosItemCofins(NFeImpostosItem nFeImpostosItem, Item item,
			CofinsNFeRequest cofins) {
		if (cofins != null && cofins.getConfiguracaoFiscalCofinsId() != null) {
			ConfiguracaoFiscalCofins configuracaoFiscalCofins = this.configuracaoFiscalCofinsService
					.getById(cofins.getConfiguracaoFiscalCofinsId()).orElseThrow(
							() -> new RegisterNotFoundException("Não encontrado configuração fiscal de cofins com id: "
									+ cofins.getConfiguracaoFiscalCofinsId()));

			String cst = StringUtils
					.leftPad(String.valueOf(configuracaoFiscalCofins.getSituacaoTributaria().getCodigo()), 2, '0');

			NFeCofinsItem nFeCofinsItem = new NFeCofinsItem();
			nFeCofinsItem.setNFeImpostosItem(nFeImpostosItem);

			nFeCofinsItem.setCst(cst);
			if (!StringUtils.equalsAny(cst, "04", "05", "06", "07", "08", "09")) {
				if (configuracaoFiscalCofins.getTipoCalculo().equals(TipoCalculo.PERCENTUAL)) {
					nFeCofinsItem.setVbc(cofins.getBcCofins());
					nFeCofinsItem.setPcofins(configuracaoFiscalCofins.getAliquota());
					nFeCofinsItem.setVcofins(cofins.getValorCofins());
				}
				if (configuracaoFiscalCofins.getTipoCalculo().equals(TipoCalculo.VALOR)) {
					nFeCofinsItem.setQbcprod(cofins.getQuantidadeVendida());
					nFeCofinsItem.setValiqprod(configuracaoFiscalCofins.getAliquota());
					nFeCofinsItem.setVcofins(cofins.getValorCofins());
				}
			}
			return nFeCofinsItem;
		}
		return null;
	}

	private NFePisItem newNfeImpostosItemPis(NFeImpostosItem nFeImpostosItem, Item item, PisNFeRequest pis) {
		if (pis != null && pis.getConfiguracaoFiscalPisId() != null) {
			ConfiguracaoFiscalPis configuracaoFiscalPis = this.configuracaoFiscalPisService
					.getById(pis.getConfiguracaoFiscalPisId()).orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado configuração fiscal de pis com id: " + pis.getConfiguracaoFiscalPisId()));

			String cst = StringUtils.leftPad(String.valueOf(configuracaoFiscalPis.getSituacaoTributaria().getCodigo()),
					2, '0');

			NFePisItem nFePisItem = new NFePisItem();
			nFePisItem.setNFeImpostosItem(nFeImpostosItem);
			nFePisItem.setCst(cst);
			if (!StringUtils.equalsAny(cst, "04", "05", "06", "07", "08", "09")) {
				if (configuracaoFiscalPis.getTipoCalculo().equals(TipoCalculo.PERCENTUAL)) {
					nFePisItem.setVbc(pis.getBcPis());
					nFePisItem.setPpis(configuracaoFiscalPis.getAliquota());
					nFePisItem.setVpis(pis.getValorPis());
				}
				if (configuracaoFiscalPis.getTipoCalculo().equals(TipoCalculo.VALOR)) {
					nFePisItem.setQbcprod(pis.getQuantidadeVendida());
					nFePisItem.setValiqprod(configuracaoFiscalPis.getAliquota());
					nFePisItem.setVpis(pis.getValorPis());
				}
			}
			return nFePisItem;
		}
		return null;
	}

	private NFeIpiItem newNfeImpostosItemIpi(NFeImpostosItem nFeImpostosItem, Item item, IpiNFeRequest ipi) {
		if (ipi != null && ipi.getConfiguracaoFiscalIpiId() != null) {
			ConfiguracaoFiscalIpi configuracaoFiscalIpi = this.configuracaoFiscalIpiService
					.getById(ipi.getConfiguracaoFiscalIpiId()).orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado configuração fiscal de ipi com id: " + ipi.getConfiguracaoFiscalIpiId()));

			NFeIpiItem nFeIpiItem = new NFeIpiItem();
			nFeIpiItem.setNFeImpostosItem(nFeImpostosItem);

			nFeIpiItem.setCnpjprod(ipi.getCnpjProdutor());
			nFeIpiItem.setCselo(ipi.getCodigoSelo());
			if (ipi.getQuantidadeSelo() != null)
				nFeIpiItem.setQselo(Integer.parseInt(ipi.getQuantidadeSelo()));
			String cst = StringUtils.leftPad(String.valueOf(configuracaoFiscalIpi.getSituacaoTributaria().getCodigo()),
					2, '0');
			nFeIpiItem.setCenq(Integer.parseInt(configuracaoFiscalIpi.getEnquadramento().getCodigo()));
			nFeIpiItem.setCst(cst);
			if (StringUtils.equalsAny(cst, "00", "49", "50", "99")) {
				if (configuracaoFiscalIpi.getTipoCalculo().equals(TipoCalculo.PERCENTUAL)) {
					nFeIpiItem.setVbc(ipi.getBcIpi());
					nFeIpiItem.setPipi(configuracaoFiscalIpi.getAliquota());
				}
				if (configuracaoFiscalIpi.getTipoCalculo().equals(TipoCalculo.VALOR)) {
					nFeIpiItem.setQunid(ipi.getQuantidade());
					nFeIpiItem.setVunid(ipi.getValorUnidade());
				}
				nFeIpiItem.setVipi(ipi.getValorIpi());
			}
			return nFeIpiItem;
		}
		return null;
	}

	private NFeIcmsItem newNfeImpostosItemIcms(NFeImpostosItem nFeImpostosItem, Item item,
			TributacaoIcmsNFeRequest tributacaoIcms) {
		ConfiguracaoFiscalIcms configuracaoFiscalIcms = this.configuracaoFiscalIcmsService
				.getById(tributacaoIcms.getConfiguracaoFiscalIcmsId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado configuração fiscal de icms com id: "
						+ tributacaoIcms.getConfiguracaoFiscalIcmsId()));

		int cst = configuracaoFiscalIcms.getSituacaoTributaria().getCodigo();
		String cstStr = StringUtils.leftPad(String.valueOf(configuracaoFiscalIcms.getSituacaoTributaria().getCodigo()),
				2, '0');
		switch (cst) {
		case 0:
			return NFeIcmsItem.builder().nFeImpostosItem(nFeImpostosItem).orig(item.getOrigem().getCodigo()).cst(cstStr)
					.modbc(Integer.parseInt(configuracaoFiscalIcms.getModalidadeBaseCalculo().getValue()))
					.vbc(tributacaoIcms.getValorBCIcms()).picms(configuracaoFiscalIcms.getAliquota())
					.vicms(tributacaoIcms.getValorIcms()).pfcp(BigDecimal.ZERO).vfcp(BigDecimal.ZERO).build();
		case 10:
			return NFeIcmsItem.builder().nFeImpostosItem(nFeImpostosItem).orig(item.getOrigem().getCodigo()).cst(cstStr)
					.modbc(Integer.parseInt(configuracaoFiscalIcms.getModalidadeBaseCalculo().getValue()))
					.vbc(tributacaoIcms.getValorBCIcms()).picms(configuracaoFiscalIcms.getAliquota())
					.vicms(tributacaoIcms.getValorIcms()).vbcfcp(BigDecimal.ZERO).pfcp(BigDecimal.ZERO)
					.vfcp(BigDecimal.ZERO)
					.modbcst(Integer.parseInt(configuracaoFiscalIcms.getModalidadeBaseCalculoST().getValue()))
					.pmvast(configuracaoFiscalIcms.getMargemValorAgregadoST())
					.predbcst(configuracaoFiscalIcms.getReducaoBaseCalculoST()).vbcst(tributacaoIcms.getValorBCIcmsST())
					.picmsst(configuracaoFiscalIcms.getAliquotaST()).vicmsst(tributacaoIcms.getValorIcmsST())
					.vbcfcpst(BigDecimal.ZERO).pfcpst(BigDecimal.ZERO).vfcpst(BigDecimal.ZERO).build();
		case 20:
			return NFeIcmsItem.builder().nFeImpostosItem(nFeImpostosItem).orig(item.getOrigem().getCodigo()).cst(cstStr)
					.modbc(Integer.parseInt(configuracaoFiscalIcms.getModalidadeBaseCalculo().getValue()))
					.predbc(configuracaoFiscalIcms.getReducaoBaseCalculo()).vbc(tributacaoIcms.getValorBCIcms())
					.picms(configuracaoFiscalIcms.getAliquota()).vicms(tributacaoIcms.getValorIcms())
					.pfcp(BigDecimal.ZERO).vfcp(BigDecimal.ZERO).vicmsdeson(tributacaoIcms.getValorIcmsDesoneracao())
					.motdesicms(configuracaoFiscalIcms.getMotivoDesoneracao().getCodigo()).build();
		case 30:
			return NFeIcmsItem.builder().nFeImpostosItem(nFeImpostosItem).orig(item.getOrigem().getCodigo()).cst(cstStr)
					.modbcst(Integer.parseInt(configuracaoFiscalIcms.getModalidadeBaseCalculoST().getValue()))
					.pmvast(configuracaoFiscalIcms.getMargemValorAgregadoST())
					.predbcst(configuracaoFiscalIcms.getReducaoBaseCalculoST()).vbcst(tributacaoIcms.getValorBCIcmsST())
					.picmsst(configuracaoFiscalIcms.getAliquotaST()).vicmsst(tributacaoIcms.getValorIcmsST())
					.vbcfcpst(BigDecimal.ZERO).pfcpst(BigDecimal.ZERO).vfcpst(BigDecimal.ZERO)
					.vicmsdeson(tributacaoIcms.getValorIcmsDesoneracao())
					.motdesicms(configuracaoFiscalIcms.getMotivoDesoneracao().getCodigo()).build();
		case 40:
		case 41:
		case 50:
			return NFeIcmsItem.builder().nFeImpostosItem(nFeImpostosItem).orig(item.getOrigem().getCodigo()).cst(cstStr)
					.vicmsdeson(tributacaoIcms.getValorIcmsDesoneracao())
					.motdesicms(configuracaoFiscalIcms.getMotivoDesoneracao().getCodigo()).build();
		case 51:
			return NFeIcmsItem.builder().nFeImpostosItem(nFeImpostosItem).orig(item.getOrigem().getCodigo()).cst(cstStr)
					.modbc(Integer.parseInt(configuracaoFiscalIcms.getModalidadeBaseCalculo().getValue()))
					.predbc(configuracaoFiscalIcms.getReducaoBaseCalculo()).vbc(tributacaoIcms.getValorBCIcms())
					.picms(configuracaoFiscalIcms.getAliquota()).vicmsop(tributacaoIcms.getValorIcmsOperacao())
					.pdif(configuracaoFiscalIcms.getDiferencialAliquota())
					.vicmsdif(tributacaoIcms.getValorIcmsDiferido()).vicms(tributacaoIcms.getValorIcms())
					.vbcfcp(BigDecimal.ZERO).pfcp(BigDecimal.ZERO).vfcp(BigDecimal.ZERO).build();
		case 60:
			return NFeIcmsItem.builder().nFeImpostosItem(nFeImpostosItem).orig(item.getOrigem().getCodigo()).cst(cstStr)
					.vbcstret(tributacaoIcms.getValorBCIcmsStRetido()).pst(configuracaoFiscalIcms.getAliquotaST())
					.vicmssubstituto(tributacaoIcms.getValorIcmsProprioSubst())
					.vicmsstret(tributacaoIcms.getValorIcmsStRet()).vbcfcpstret(BigDecimal.ZERO)
					.pfcpstret(BigDecimal.ZERO).vfcpstret(BigDecimal.ZERO)
					.predbcefet(tributacaoIcms.getReducaoBaseCalculoEfetiva())
					.vbcefet(tributacaoIcms.getValorBCEfetivo()).picmsefet(tributacaoIcms.getAliquotaIcmsEfetiva())
					.vicmsefet(tributacaoIcms.getValorIcmsEfetiva()).build();
		case 70:
			return NFeIcmsItem.builder().nFeImpostosItem(nFeImpostosItem).orig(item.getOrigem().getCodigo()).cst(cstStr)
					.modbc(Integer.parseInt(configuracaoFiscalIcms.getModalidadeBaseCalculo().getValue()))
					.predbc(configuracaoFiscalIcms.getReducaoBaseCalculo()).vbc(tributacaoIcms.getValorBCIcms())
					.picms(configuracaoFiscalIcms.getAliquota()).vicms(tributacaoIcms.getValorIcms())
					.vbcfcp(BigDecimal.ZERO).pfcp(BigDecimal.ZERO).vfcp(BigDecimal.ZERO)
					.modbcst(Integer.parseInt(configuracaoFiscalIcms.getModalidadeBaseCalculoST().getValue()))
					.pmvast(configuracaoFiscalIcms.getMargemValorAgregadoST())
					.predbcst(configuracaoFiscalIcms.getReducaoBaseCalculoST()).vbcst(tributacaoIcms.getValorBCIcmsST())
					.picmsst(configuracaoFiscalIcms.getAliquotaST()).vicmsst(tributacaoIcms.getValorIcmsST())
					.vbcfcpst(BigDecimal.ZERO).pfcpst(BigDecimal.ZERO).vfcpst(BigDecimal.ZERO)
					.vicmsdeson(tributacaoIcms.getValorIcmsDesoneracao())
					.motdesicms(configuracaoFiscalIcms.getMotivoDesoneracao().getCodigo()).build();
		case 90:
			return NFeIcmsItem.builder().nFeImpostosItem(nFeImpostosItem).orig(item.getOrigem().getCodigo()).cst(cstStr)
					.modbc(Integer.parseInt(configuracaoFiscalIcms.getModalidadeBaseCalculo().getValue()))
					.predbc(configuracaoFiscalIcms.getReducaoBaseCalculo()).vbc(tributacaoIcms.getValorBCIcms())
					.picms(configuracaoFiscalIcms.getAliquota()).vicms(tributacaoIcms.getValorIcms())
					.vbcfcp(BigDecimal.ZERO).pfcp(BigDecimal.ZERO).vfcp(BigDecimal.ZERO)
					.modbcst(Integer.parseInt(configuracaoFiscalIcms.getModalidadeBaseCalculoST().getValue()))
					.pmvast(configuracaoFiscalIcms.getMargemValorAgregadoST())
					.predbcst(configuracaoFiscalIcms.getReducaoBaseCalculoST()).vbcst(tributacaoIcms.getValorBCIcmsST())
					.picmsst(configuracaoFiscalIcms.getAliquotaST()).vicmsst(tributacaoIcms.getValorIcmsST())
					.vbcfcpst(BigDecimal.ZERO).pfcpst(BigDecimal.ZERO).vfcpst(BigDecimal.ZERO)
					.vicmsdeson(tributacaoIcms.getValorIcmsDesoneracao())
					.motdesicms(configuracaoFiscalIcms.getMotivoDesoneracao().getCodigo()).build();
		case 101:
			return NFeIcmsItem.builder().nFeImpostosItem(nFeImpostosItem).orig(item.getOrigem().getCodigo()).csosn(cst)
					.pcredsn(configuracaoFiscalIcms.getAliquotaCredito())
					.vcredicmssn(tributacaoIcms.getValorCredIcmsSN()).build();
		case 102:
		case 103:
		case 300:
		case 400:
			return NFeIcmsItem.builder().nFeImpostosItem(nFeImpostosItem).orig(item.getOrigem().getCodigo())
					.csosn(configuracaoFiscalIcms.getSituacaoTributaria().getCodigo()).build();
		case 201:
			return NFeIcmsItem.builder().nFeImpostosItem(nFeImpostosItem).orig(item.getOrigem().getCodigo())
					.csosn(configuracaoFiscalIcms.getSituacaoTributaria().getCodigo())
					.modbcst(Integer.parseInt(configuracaoFiscalIcms.getModalidadeBaseCalculoST().getValue()))
					.pmvast(configuracaoFiscalIcms.getMargemValorAgregadoST())
					.predbcst(configuracaoFiscalIcms.getReducaoBaseCalculoST()).vbcst(tributacaoIcms.getValorBCIcmsST())
					.picmsst(tributacaoIcms.getValorIcmsST()).vbcfcpst(BigDecimal.ZERO).pfcpst(BigDecimal.ZERO)
					.vfcpst(BigDecimal.ZERO).pcredsn(configuracaoFiscalIcms.getAliquotaCredito())
					.vcredicmssn(tributacaoIcms.getValorCredIcmsSN()).build();
		case 202:
		case 203:
			return NFeIcmsItem.builder().nFeImpostosItem(nFeImpostosItem).orig(item.getOrigem().getCodigo())
					.csosn(configuracaoFiscalIcms.getSituacaoTributaria().getCodigo())
					.modbcst(Integer.parseInt(configuracaoFiscalIcms.getModalidadeBaseCalculoST().getValue()))
					.pmvast(configuracaoFiscalIcms.getMargemValorAgregadoST())
					.predbcst(configuracaoFiscalIcms.getReducaoBaseCalculoST()).vbcst(tributacaoIcms.getValorBCIcmsST())
					.picmsst(tributacaoIcms.getValorIcmsST()).vbcfcpst(BigDecimal.ZERO).pfcpst(BigDecimal.ZERO)
					.vfcpst(BigDecimal.ZERO).pcredsn(configuracaoFiscalIcms.getAliquotaCredito()).build();
		case 500:
			return NFeIcmsItem.builder().nFeImpostosItem(nFeImpostosItem).orig(item.getOrigem().getCodigo())
					.csosn(configuracaoFiscalIcms.getSituacaoTributaria().getCodigo())
					.vbcstret(tributacaoIcms.getValorBCIcmsStRetido()).pst(configuracaoFiscalIcms.getAliquotaST())
					.vicmssubstituto(tributacaoIcms.getValorIcmsProprioSubst())
					.vicmsstret(tributacaoIcms.getValorIcmsStRet()).vbcfcpstret(BigDecimal.ZERO)
					.pfcpstret(BigDecimal.ZERO).vfcpstret(BigDecimal.ZERO)
					.predbcefet(tributacaoIcms.getReducaoBaseCalculoEfetiva())
					.vbcefet(tributacaoIcms.getValorBCEfetivo()).picmsefet(tributacaoIcms.getAliquotaIcmsEfetiva())
					.vicmsefet(tributacaoIcms.getValorIcmsEfetiva()).build();
		case 900:
			return NFeIcmsItem.builder().nFeImpostosItem(nFeImpostosItem).orig(item.getOrigem().getCodigo())
					.csosn(configuracaoFiscalIcms.getSituacaoTributaria().getCodigo())
					.modbc(Integer.parseInt(configuracaoFiscalIcms.getModalidadeBaseCalculo().getValue()))
					.vbc(tributacaoIcms.getValorBCIcms()).predbc(configuracaoFiscalIcms.getReducaoBaseCalculo())
					.picms(configuracaoFiscalIcms.getAliquota()).vicms(tributacaoIcms.getValorIcms())
					.modbcst(Integer.parseInt(configuracaoFiscalIcms.getModalidadeBaseCalculoST().getValue()))
					.pmvast(configuracaoFiscalIcms.getMargemValorAgregadoST())
					.predbcst(configuracaoFiscalIcms.getReducaoBaseCalculoST()).vbcst(tributacaoIcms.getValorBCIcmsST())
					.picmsst(configuracaoFiscalIcms.getAliquotaST()).vicmsst(tributacaoIcms.getValorIcmsST())
					.vbcfcpst(BigDecimal.ZERO).pfcpst(BigDecimal.ZERO).vfcpst(BigDecimal.ZERO)
					.pcredsn(configuracaoFiscalIcms.getAliquotaCredito())
					.vcredicmssn(tributacaoIcms.getValorCredIcmsSN()).build();
		}

		return null;
	}

	private NFeProdItem newNfeProdItem(NFeDetItem nfeDetItem, Item item,
			DetalhamentoItemNFeRequest detalhamentoItemNFeRequest) {
		Cfop cfop = cfopService.getById(detalhamentoItemNFeRequest.getCfopId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado cfop com id: " + detalhamentoItemNFeRequest.getCfopId()));
		UnidadeMedida unidadeMedidaComercial = unidadeMedidaService.getById(item.getUnidadeMedidaComercial().getId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado unidade medica com id: " + detalhamentoItemNFeRequest.getUnidadeMedidaId()));

		NFeProdItem nfeProdItem = new NFeProdItem();
		nfeProdItem.setNFeDetItem(nfeDetItem);
		nfeProdItem.setCprod(Integer.toString(item.getCodigo()));
		nfeProdItem.setCean(item.getGtinEan());
		nfeProdItem.setXprod(item.getNome());
		nfeProdItem.setNcm(item.getNcm().getCodigo().replaceAll("[^0-9]", ""));
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
		nfeProdItem.setIndtot(1);

		return nfeProdItem;
	}

	private void mergeAutorizacoes(NFe nfe, List<AutorizacaoRequest> autorizacoesRequest) {
		if (autorizacoesRequest == null || autorizacoesRequest.isEmpty()) {
			if (nfe.getAutorizacoes() != null) {
				nfe.getAutorizacoes().clear();
			}
			return;
		}

		List<NFeAut> newAutorizacoes = new ArrayList<>();

		autorizacoesRequest.forEach(aut -> {
			NFeAut nFeAut = new NFeAut();
			nFeAut.setNfe(nfe);
			nFeAut.setCnpj(aut.getCnpj());
			nFeAut.setCpf(aut.getCpf());

			newAutorizacoes.add(nFeAut);
		});

		if (nfe.getAutorizacoes() == null)
			nfe.setAutorizacoes(new ArrayList<>());
		else
			nfe.getAutorizacoes().clear();

		nfe.getAutorizacoes().addAll(newAutorizacoes);
	}

	private void mergeLocalEntrega(NFe nfe, LocalEntregaRequest localEntrega) {
		NFeEntrega nfeEntrega = nfe.getNfeEntrega();

		if (localEntrega == null || StringUtils.isBlank(localEntrega.getNomeRazaoSocial())) {
			nfe.setNfeEntrega(null);
			return;
		}
		if (nfeEntrega == null) {
			nfeEntrega = new NFeEntrega();
			nfeEntrega.setNfe(nfe);
			nfe.setNfeEntrega(nfeEntrega);
		}

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

	private void mergeLocalRetirada(NFe nfe, LocalRetiradaRequest localRetirada) {
		NFeRetirada nfeRetirada = nfe.getNfeRetirada();

		if (localRetirada == null || StringUtils.isBlank(localRetirada.getNomeRazaoSocial())) {
			nfe.setNfeRetirada(null);
			return;
		}

		if (nfeRetirada == null) {
			nfeRetirada = new NFeRetirada();
			nfeRetirada.setNfe(nfe);
			nfe.setNfeRetirada(nfeRetirada);
		}

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

	private void mergeDestinatarioNFe(NFe nfe, DestinatarioNFeRequest destinatarioNFeRequest) {
		NFeDest nfeDest = nfe.getNfeDest();
		if (nfeDest == null) {
			nfeDest = new NFeDest();
		}
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
		if (parceiroLocalTelefone != null) {
			nfeDest.setFone(parceiroLocalTelefone.getNumero());			
		}
		if (parceiroLocal.getParceiro().getTipoPessoa().equals(TipoPessoa.PESSOA_JURIDICA)) {
			nfeDest.setIndiedest(Integer.parseInt(parceiroLocal.getDadosPessoaJuridica().get(0).getIndicadorIE().getCodigoReceita()));
			nfeDest.setIe(parceiroLocal.getDadosPessoaJuridica().get(0).getInscricaoEstadual());
//			nfeDest.setIsuf(parceiroLocal.getDadosPessoaJuridica().get(0).getInscricaoSuframa());
		} else {
			nfeDest.setIndiedest(Integer.parseInt(IndicadorIEDestinatario.NAOCONTRIBUINTE.getValue()));
		}
		if (parceiroLocalEmail != null) {
			nfeDest.setEmail(parceiroLocalEmail.getEmail());
		}
		
		nfe.setNfeDest(nfeDest);
		nfeDest.setNfe(nfe);
	}

	private void mergeEmitenteNFe(NFeEmit nfeEmit, IdentificacaoNFeRequest identificacaoNFeRequest) {
		// Já vem do IniciarNFe
	}

	private void mergeIdentificacaoNFe(NFe nfe, IdentificacaoNFeRequest identificacaoNFeRequest) {
		NFeIde nfeIde = nfe.getNfeIde();
		NFeEmit nFeEmit= nfe.getNfeEmit();
		
		nfeIde.setNatop(identificacaoNFeRequest.getNaturezaOperacao());
		nfeIde.setDhemi(identificacaoNFeRequest.getDataHoraEmissao());
		nfeIde.setDhsaient(identificacaoNFeRequest.getDataHoraSaidaEntrada());
		nfeIde.setTpnf(Integer.parseInt(
				IndicadorOperacao.fromValue(identificacaoNFeRequest.getIndicadorOperacao()).getCodigoReceita()));
		nfeIde.setIddest(Integer
				.parseInt(DestinoOperacao.fromValue(identificacaoNFeRequest.getDestinoOperacao()).getCodigoReceita()));
		nfeIde.setCmunfg(nFeEmit.getCmun());
		nfeIde.setFinnfe(Integer
				.parseInt(FinalidadeNfe.fromValue(identificacaoNFeRequest.getFinalidadeNFe()).getCodigoReceita()));
		nfeIde.setIndpres(Integer.parseInt(TipoPresencaComprador
				.fromValue(identificacaoNFeRequest.getTipoPresencaComprador()).getCodigoReceita()));

		if (identificacaoNFeRequest.getDocumentosReferenciados() != null
				&& !identificacaoNFeRequest.getDocumentosReferenciados().isEmpty()) {
			identificacaoNFeRequest.getDocumentosReferenciados().forEach(doc -> {
				NFeRef nFeRef = new NFeRef();
				nFeRef.setNfeIde(nfeIde);
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
			nfeIde.getNfesRefs().clear();
		}
	}

}
