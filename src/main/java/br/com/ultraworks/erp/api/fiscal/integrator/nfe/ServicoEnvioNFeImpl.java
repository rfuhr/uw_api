package br.com.ultraworks.erp.api.fiscal.integrator.nfe;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnderEmi;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEndereco;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TInfRespTec;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TIpi;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TIpi.IPINT;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TIpi.IPITrib;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TLocal;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.AutXML;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Cana;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Cana.Deduc;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Cana.ForDia;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Cobr;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Cobr.Dup;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Cobr.Fat;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Compra;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Dest;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSQtde;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS00;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS10;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS20;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS30;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS40;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS51;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS60;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS70;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS90;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSST;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISNT;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISOutr;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISQtde;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.ImpostoDevol;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.ImpostoDevol.IPI;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Emit;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Exporta;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Ide;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Ide.NFref;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Ide.NFref.RefECF;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Ide.NFref.RefNF;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Ide.NFref.RefNFP;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.InfAdic;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.InfAdic.ObsCont;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.InfAdic.ObsFisco;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.InfAdic.ProcRef;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.InfIntermed;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Pag;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Pag.DetPag;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Pag.DetPag.Card;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ISSQNtot;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.RetTrib;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp.RetTransp;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp.Transporta;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp.Vol;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp.Vol.Lacres;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TUf;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TUfEmi;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TVeiculo;
import br.com.swconsultoria.nfe.schema_4.retConsReciNFe.TRetConsReciNFe;
import br.com.swconsultoria.nfe.util.ChaveUtil;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.ultraworks.erp.api.configuracao.domain.certificado.EmpresaCertificado;
import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFe;
import br.com.ultraworks.erp.api.configuracao.service.ConfigEmpresaNFeService;
import br.com.ultraworks.erp.api.configuracao.service.EmpresaCertificadoService;
import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.fiscal.domain.indicadoriedestinatario.IndicadorIEDestinatario;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.ContainerIntegracaoNFe;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.ContainerIntegracaoNFeAutorizacao;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.ContainerIntegracaoNFeIdentificacao;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.ContainerIntegracaoNFeItem;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.ContainerIntegracaoNFeLocalRetirada;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.RetornoNFeIntegracao;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiro.Parceiro;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEmail.ParceiroLocalEmail;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEndereco;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalJuridica.ParceiroLocalJuridica;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTelefone.ParceiroLocalTelefone;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalService;
import br.com.ultraworks.erp.api.tabela.domain.tipopessoa.TipoPessoa;
import br.com.ultraworks.erp.core.exception.BusinessException;

@Service
@Scope("prototype")
public class ServicoEnvioNFeImpl implements IServicoEnvioNFe {

	private ConfigEmpresaNFeService configEmpresaNFeService;
	private EmpresaCertificadoService empresaCertificadoService;
	private ResourceLoader resourceLoader;
	private ParceiroLocalService parceiroLocalService;

	@Autowired
	public ServicoEnvioNFeImpl(ConfigEmpresaNFeService configEmpresaNFeService,
			EmpresaCertificadoService empresaCertificadoService, ParceiroLocalService parceiroLocalService,
			ResourceLoader resourceLoader) {
		this.configEmpresaNFeService = configEmpresaNFeService;
		this.empresaCertificadoService = empresaCertificadoService;
		this.parceiroLocalService = parceiroLocalService;
		this.resourceLoader = resourceLoader;

	}

	@Override
	public RetornoNFeIntegracao emitirNFe(ContainerIntegracaoNFe containerNFe) throws BusinessException {
		ConfigEmpresaNFe configEmpresaNFe = configEmpresaNFeService
				.getByEmpresaFilialId(containerNFe.getEmpresaFilialEmissorId());
		ConfiguracoesNfe configuracoesNfe = criarConfiguracoesNfe(configEmpresaNFe);
		ChaveUtil chaveUtil = montaChaveNFe(configuracoesNfe.getEstado(), configEmpresaNFe,
				containerNFe.getNfeIdentificacao());
		TEnviNFe enviNFe = criaEnviNFe(configuracoesNfe, chaveUtil, containerNFe, configEmpresaNFe);

		try {
			enviNFe = Nfe.montaNfe(configuracoesNfe, enviNFe, true);
		} catch (NfeException e) {
			throw new BusinessException(
					"Erro ao realizar assinatura e validação da NFe. " + "Motivo: " + e.getMessage());
		}

		TRetEnviNFe retorno;
		try {
			retorno = Nfe.enviarNfe(configuracoesNfe, enviNFe, DocumentoEnum.NFE);
		} catch (NfeException e) {
			throw new BusinessException("Erro ao enviar da NFe. " + "Motivo: " + e.getMessage());
		}

		String xmlFinal;
		RetornoNFeIntegracao retornoNFeIntegracao = new RetornoNFeIntegracao();
		retornoNFeIntegracao.setRecibo(retorno.getInfRec().getNRec());
		try {
			if (RetornoUtil.isRetornoAssincrono(retorno)) {
				br.com.swconsultoria.nfe.schema_4.retConsReciNFe.TRetConsReciNFe tRetConsReciNFe = verificaEnvioAssincrono(
						configuracoesNfe, retorno);
				if (!tRetConsReciNFe.getCStat().equals(StatusEnum.LOTE_EM_PROCESSAMENTO.getCodigo())) {
					RetornoUtil.validaAssincrono(tRetConsReciNFe);
					retornoNFeIntegracao.setStatus(tRetConsReciNFe.getProtNFe().get(0).getInfProt().getCStat());
					retornoNFeIntegracao.setProtocolo(tRetConsReciNFe.getProtNFe().get(0).getInfProt().getNProt());
					xmlFinal = XmlNfeUtil.criaNfeProc(enviNFe, tRetConsReciNFe.getProtNFe().get(0));
					retornoNFeIntegracao.setXml(xmlFinal);
				} else {
					retornoNFeIntegracao.setStatus(tRetConsReciNFe.getCStat());
				}
			} else {
				RetornoUtil.validaSincrono(retorno);
				retornoNFeIntegracao.setStatus(retorno.getProtNFe().getInfProt().getCStat());
				retornoNFeIntegracao.setProtocolo(retorno.getProtNFe().getInfProt().getNProt());
				xmlFinal = XmlNfeUtil.criaNfeProc(enviNFe, retorno.getProtNFe());
				retornoNFeIntegracao.setXml(xmlFinal);
			}
		} catch (Exception e) {
			retornoNFeIntegracao.setErroValidarRetorno(e.getMessage());
		}

		return retornoNFeIntegracao;
	}

	private TRetConsReciNFe verificaEnvioAssincrono(ConfiguracoesNfe configuracoesNfe, TRetEnviNFe retorno)
			throws Exception {
		String recibo = retorno.getInfRec().getNRec();
		int tentativa = 1;
		br.com.swconsultoria.nfe.schema_4.retConsReciNFe.TRetConsReciNFe retornoConsulta = null;
		while (true) {
			retornoConsulta = Nfe.consultaRecibo(configuracoesNfe, recibo, DocumentoEnum.NFE);
			if (retornoConsulta.getCStat().equals(StatusEnum.LOTE_EM_PROCESSAMENTO.getCodigo())) {
				Thread.sleep(1000);
				tentativa++;
				if (tentativa > 30) {
					return retornoConsulta;
				}
			} else {
				break;
			}
		}

		return retornoConsulta;
	}

	private TEnviNFe criaEnviNFe(ConfiguracoesNfe configuracoesNfe, ChaveUtil chaveUtil,
			ContainerIntegracaoNFe containerIntegracaoNFe, ConfigEmpresaNFe configEmpresaNFe) {
		TEnviNFe enviNFe = new TEnviNFe();
		enviNFe.setVersao(ConstantesUtil.VERSAO.NFE);
		enviNFe.setIdLote("1");
		enviNFe.setIndSinc("1");

		TNFe nfe = new TNFe();
		TNFe.InfNFe infNFe = getInfNFe(configuracoesNfe, chaveUtil, containerIntegracaoNFe, configEmpresaNFe);
		nfe.setInfNFe(infNFe);
		enviNFe.getNFe().add(nfe);

		return enviNFe;
	}

	private InfNFe getInfNFe(ConfiguracoesNfe configuracoesNfe, ChaveUtil chaveUtil,
			ContainerIntegracaoNFe containerIntegracaoNFe, ConfigEmpresaNFe configEmpresaNFe) {
		TNFe.InfNFe infNFe = new TNFe.InfNFe();
		infNFe.setId(chaveUtil.getChaveNF());
		infNFe.setVersao(ConstantesUtil.VERSAO.NFE);
		infNFe.setIde(montaIde(configuracoesNfe, chaveUtil, containerIntegracaoNFe, configEmpresaNFe));
		infNFe.setEmit(montaEmitente(configEmpresaNFe));
		infNFe.setDest(montaDestinatario(containerIntegracaoNFe));
		if (containerIntegracaoNFe.getNfeLocalRetirada() != null)
			infNFe.setRetirada(montaLocalRetirada(containerIntegracaoNFe.getNfeLocalRetirada()));
		if (containerIntegracaoNFe.getNfeAutorizacaoXML() != null
				&& containerIntegracaoNFe.getNfeAutorizacaoXML().size() > 0) {
			infNFe.getAutXML().addAll(montaAutorizacaoXML(containerIntegracaoNFe.getNfeAutorizacaoXML()));
		}
		infNFe.getDet().addAll(montaDetalhamentoProdutosServicos(containerIntegracaoNFe));
		infNFe.setTotal(montaTotal());
		infNFe.setTransp(montaTransportadora());
		infNFe.setCobr(montaCobranca());
		infNFe.setPag(pagamento());
		infNFe.setInfIntermed(montaInformacaoIntermediador());
		infNFe.setInfAdic(montaInfAdicionais());
		infNFe.setExporta(montaInfExportacao());
		infNFe.setCompra(montaInfCompra());
		infNFe.setCana(montaInfAquisicaoCana());
		infNFe.setInfRespTec(montaRespTecnico());

		return infNFe;
	}

	private TInfRespTec montaRespTecnico() {
		TInfRespTec respTec = new TInfRespTec();
		respTec.setCNPJ("");
		respTec.setXContato("");
		respTec.setEmail("");
		respTec.setFone("");
		respTec.setIdCSRT("");
		respTec.setHashCSRT(null);

		return respTec;
	}

	private Cana montaInfAquisicaoCana() {
		TNFe.InfNFe.Cana cana = new TNFe.InfNFe.Cana();

		cana.setSafra("");
		cana.setRef("");
		cana.getForDia().addAll(montaInfAquisicaoCanaForDia());
		cana.setQTotMes("");
		cana.setQTotAnt("");
		cana.setQTotGer("");
		cana.getDeduc().addAll(montaInfAquisicaoCanaDeducoes());
		cana.setVFor("");
		cana.setVTotDed("");
		cana.setVLiqFor("");

		return cana;
	}

	private Collection<? extends Deduc> montaInfAquisicaoCanaDeducoes() {
		List<Deduc> listaDeducoes = new ArrayList<>();
		Deduc deducao = new Deduc();
		deducao.setXDed("");
		deducao.setVDed("");

		return listaDeducoes;
	}

	private Collection<? extends ForDia> montaInfAquisicaoCanaForDia() {
		List<ForDia> listForDia = new ArrayList<>();

		ForDia forDia = new ForDia();
		forDia.setDia("");
		forDia.setQtde("");
		listForDia.add(forDia);

		return listForDia;
	}

	private Compra montaInfCompra() {
		TNFe.InfNFe.Compra compra = new TNFe.InfNFe.Compra();
		compra.setXNEmp("");
		compra.setXPed("");
		compra.setXCont("");

		return compra;
	}

	private Exporta montaInfExportacao() {
		TNFe.InfNFe.Exporta exporta = new TNFe.InfNFe.Exporta();
		exporta.setUFSaidaPais(TUfEmi.PR);
		exporta.setXLocDespacho("");
		exporta.setXLocDespacho("");

		return exporta;
	}

	private InfAdic montaInfAdicionais() {
		TNFe.InfNFe.InfAdic infAdicionais = new TNFe.InfNFe.InfAdic();
		infAdicionais.setInfAdFisco("");
		infAdicionais.setInfCpl("");
		infAdicionais.getObsCont().addAll(montaInfAdicionaisCont());
		infAdicionais.getObsFisco().addAll(montaInfAdicionaisObsFisco());
		infAdicionais.getProcRef().addAll(montaInfAdicionaisProcRef());

		return infAdicionais;
	}

	private Collection<? extends ProcRef> montaInfAdicionaisProcRef() {
		List<ProcRef> listaProcRef = new ArrayList<>();
		ProcRef proc = new ProcRef();
		proc.setNProc("");
		proc.setIndProc("");

		listaProcRef.add(proc);

		return listaProcRef;
	}

	private Collection<? extends ObsFisco> montaInfAdicionaisObsFisco() {
		List<ObsFisco> listaObsFisco = new ArrayList<>();
		ObsFisco obs = new ObsFisco();
		obs.setXCampo("");
		obs.setXTexto("");
		listaObsFisco.add(obs);

		return listaObsFisco;
	}

	private Collection<? extends ObsCont> montaInfAdicionaisCont() {
		List<ObsCont> listaObsCont = new ArrayList<>();
		ObsCont obs = new ObsCont();
		obs.setXCampo("");
		obs.setXTexto("");
		listaObsCont.add(obs);

		return listaObsCont;
	}

	private InfIntermed montaInformacaoIntermediador() {
		TNFe.InfNFe.InfIntermed infIntermed = new TNFe.InfNFe.InfIntermed();
		infIntermed.setCNPJ("");
		infIntermed.setIdCadIntTran("");

		return infIntermed;
	}

	private Pag pagamento() {
		TNFe.InfNFe.Pag pag = new TNFe.InfNFe.Pag();

		pag.getDetPag().addAll(montaDetalhePagamento());
		pag.setVTroco("");

		return pag;

	}

	private Collection<? extends DetPag> montaDetalhePagamento() {
		List<DetPag> listaDetPag = new ArrayList<>();

		DetPag detPag = new DetPag();
		detPag.setIndPag("");
		detPag.setTPag("");
		detPag.setVPag("");

		Card card = new Card();
		card.setTpIntegra("");
		card.setCNPJ("");
		card.setTBand("");
		card.setCAut("");
		detPag.setCard(card);
		listaDetPag.add(detPag);

		return listaDetPag;
	}

	private Cobr montaCobranca() {
		TNFe.InfNFe.Cobr cobranca = new TNFe.InfNFe.Cobr();

		Fat fatura = new Fat();
		fatura.setNFat("");
		fatura.setVOrig("");
		fatura.setVDesc("");
		fatura.setVLiq("");
		cobranca.setFat(fatura);

		cobranca.getDup().addAll(montaCobrancaParcelas());

		return cobranca;
	}

	private Collection<? extends Dup> montaCobrancaParcelas() {
		List<Dup> listaDup = new ArrayList<>();

		Dup dup = new Dup();
		dup.setNDup("");
		dup.setDVenc("");
		dup.setVDup("");

		listaDup.add(dup);

		return listaDup;
	}

	private Transp montaTransportadora() {
		TNFe.InfNFe.Transp transp = new TNFe.InfNFe.Transp();

		transp.setModFrete("");

		Transporta transporta = new Transporta();
		transporta.setCNPJ("");
		transporta.setCPF("");
		transporta.setXNome("");
		transporta.setIE("");
		transporta.setXEnder("");
		transporta.setXMun("");
		transporta.setUF(TUf.PR);
		transp.setTransporta(transporta);

		RetTransp rettransp = new RetTransp();
		rettransp.setVServ("");
		rettransp.setVBCRet("");
		rettransp.setPICMSRet("");
		rettransp.setVICMSRet("");
		rettransp.setCFOP("");
		rettransp.setCMunFG("");
		transp.setRetTransp(rettransp);

		TVeiculo veiculo = new TVeiculo();
		veiculo.setPlaca("");
		veiculo.setUF(TUf.PR);
		veiculo.setRNTC("");
		transp.setVeicTransp(veiculo);

		transp.getReboque().addAll(montaReboques());
		transp.setVagao("");
		transp.setBalsa("");
		;

		transp.getVol().addAll(montaVolumes());

		return transp;
	}

	private Collection<? extends Vol> montaVolumes() {
		List<Vol> listaVol = new ArrayList<>();

		Vol vol = new Vol();
		vol.setQVol("");
		vol.setEsp("");
		vol.setMarca("");
		vol.setNVol("");
		vol.setPesoL("");
		vol.setPesoB("");

		vol.getLacres().addAll(montaVolLacres());

		listaVol.add(vol);

		return listaVol;
	}

	private Collection<? extends Lacres> montaVolLacres() {
		List<Lacres> listaLacres = new ArrayList<>();

		Lacres lacre = new Lacres();
		lacre.setNLacre("");
		listaLacres.add(lacre);

		return listaLacres;
	}

	private Collection<? extends TVeiculo> montaReboques() {
		List<TVeiculo> listaReboque = new ArrayList<>();

		TVeiculo veiculo = new TVeiculo();
		veiculo.setPlaca("");
		veiculo.setUF(TUf.PR);
		veiculo.setRNTC("");
		listaReboque.add(veiculo);

		return listaReboque;
	}

	private Total montaTotal() {
		TNFe.InfNFe.Total total = new TNFe.InfNFe.Total();
		total.setICMSTot(montarICMSTot());
		total.setISSQNtot(montarISSQNTot());
		total.setRetTrib(montarRetTrib());

		return total;

	}

	private RetTrib montarRetTrib() {
		TNFe.InfNFe.Total.RetTrib ret = new TNFe.InfNFe.Total.RetTrib();

		ret.setVRetPIS("");
		ret.setVRetCOFINS("");
		ret.setVRetCSLL("");
		ret.setVBCIRRF("");
		ret.setVIRRF("");
		ret.setVBCRetPrev("");
		ret.setVBCRetPrev("");

		return ret;
	}

	private ISSQNtot montarISSQNTot() {
		TNFe.InfNFe.Total.ISSQNtot issqnTot = new TNFe.InfNFe.Total.ISSQNtot();

		issqnTot.setVServ("");
		issqnTot.setVBC("");
		issqnTot.setVISS("");
		issqnTot.setVPIS("");
		issqnTot.setVCOFINS("");
		issqnTot.setDCompet("");
		issqnTot.setVDeducao("");
		issqnTot.setVOutro("");
		issqnTot.setVDescIncond("");
		issqnTot.setVDescCond("");
		issqnTot.setVISSRet("");
		issqnTot.setCRegTrib("");

		return issqnTot;
	}

	private ICMSTot montarICMSTot() {
		TNFe.InfNFe.Total.ICMSTot icmsTot = new TNFe.InfNFe.Total.ICMSTot();

		icmsTot.setVBC("0.00");
		icmsTot.setVICMS("0.00");
		icmsTot.setVICMSDeson("0.00");
		icmsTot.setVFCPUFDest("");
		icmsTot.setVICMSUFDest("");
		icmsTot.setVICMSUFRemet("");
		icmsTot.setVFCP("0.00");
		icmsTot.setVBCST("0.00");
		icmsTot.setVST("0.00");
		icmsTot.setVFCPST("0.00");
		icmsTot.setVFCPSTRet("0.00");
		icmsTot.setVProd("0.00");
		icmsTot.setVFrete("0.00");
		icmsTot.setVSeg("0.00");
		icmsTot.setVDesc("0.00");
		icmsTot.setVII("0.00");
		icmsTot.setVIPI("0.00");
		icmsTot.setVIPIDevol("0.00");
		icmsTot.setVPIS("0.00");
		icmsTot.setVCOFINS("0.00");
		icmsTot.setVOutro("0.00");
		icmsTot.setVNF("0.00");
		icmsTot.setVTotTrib("");

		return icmsTot;
	}

	private Collection<? extends Det> montaDetalhamentoProdutosServicos(ContainerIntegracaoNFe containerIntegracaoNFe) {
		List<ContainerIntegracaoNFeItem> nfeItens = containerIntegracaoNFe.getNfeItens();
		int numeroItem = 1;
		List<TNFe.InfNFe.Det> listDetalhamento = new ArrayList<>();

		for (ContainerIntegracaoNFeItem nfeItem : nfeItens) {
			TNFe.InfNFe.Det det = new TNFe.InfNFe.Det();
			det.setNItem("1");
			listDetalhamento.add(det);
			det.setProd(montaProduto(nfeItem));
			det.setImposto(montaImposto());
			det.setImpostoDevol(montaImpostoDevol());
			det.setInfAdProd("");
		}
		return listDetalhamento;
	}

	private ImpostoDevol montaImpostoDevol() {
		TNFe.InfNFe.Det.ImpostoDevol impostoDev = new TNFe.InfNFe.Det.ImpostoDevol();

		IPI ipi = new IPI();
		ipi.setVIPIDevol("");

		impostoDev.setPDevol("");
		impostoDev.setIPI(ipi);

		return impostoDev;
	}

	private Imposto montaImposto() {
		TNFe.InfNFe.Det.Imposto imposto = new TNFe.InfNFe.Det.Imposto();
		criaImpostoIcms(imposto);
		criaImpostoIcmsUFDestino(imposto);
		criaImpostoIPI(imposto);
		criaImpostoII(imposto);
		criaImpostoPis(imposto);
		criaImpostoPisST(imposto);
		criaImpostoCofins(imposto);
		criaImpostoCofinsST(imposto);
		criaImpostoISSQN(imposto);

		return imposto;
	}

	private void criaImpostoISSQN(Imposto imposto) {
		TNFe.InfNFe.Det.Imposto.ISSQN iss = new TNFe.InfNFe.Det.Imposto.ISSQN();

		iss.setVBC("");
		iss.setVAliq("");
		iss.setVISSQN("");
		iss.setCMunFG("");
		iss.setCListServ("");
		iss.setVDeducao("");
		iss.setVOutro("");
		iss.setVDescIncond("");
		iss.setVDescCond("");
		iss.setVISSRet("");
		iss.setIndISS("");
		iss.setCServico("");
		iss.setCMun("");
		iss.setCPais("");
		iss.setNProcesso("");
		iss.setIndIncentivo("");

		imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoISSQN(iss));
	}

	private void criaImpostoCofinsST(Imposto imposto) {
		TNFe.InfNFe.Det.Imposto.COFINSST cofinsST = new TNFe.InfNFe.Det.Imposto.COFINSST();

		cofinsST.setVBC("");
		cofinsST.setPCOFINS("");
		cofinsST.setQBCProd("");
		cofinsST.setVAliqProd("");
		cofinsST.setVCOFINS("");

		imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoCOFINSST(cofinsST));
	}

	private void criaImpostoCofins(Imposto imposto) {
		TNFe.InfNFe.Det.Imposto.COFINS cofins = new TNFe.InfNFe.Det.Imposto.COFINS();

		COFINSAliq conAliq = new COFINSAliq();
		conAliq.setCST("");
		conAliq.setVBC("");
		conAliq.setPCOFINS("");
		conAliq.setVCOFINS("");
		cofins.setCOFINSAliq(conAliq);

		COFINSQtde qtde = new COFINSQtde();
		qtde.setCST("");
		qtde.setQBCProd("");
		qtde.setVAliqProd("");
		qtde.setVCOFINS("");
		cofins.setCOFINSQtde(qtde);

		COFINSNT nt = new COFINSNT();
		nt.setCST("");
		cofins.setCOFINSNT(nt);

		COFINSOutr outros = new COFINSOutr();
		outros.setCST("");
		outros.setVBC("");
		outros.setPCOFINS("");
		outros.setQBCProd("");
		outros.setVAliqProd("");
		outros.setVCOFINS("");
		cofins.setCOFINSOutr(outros);

		imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoCOFINS(cofins));
	}

	private void criaImpostoPisST(Imposto imposto) {
		TNFe.InfNFe.Det.Imposto.PISST pisST = new TNFe.InfNFe.Det.Imposto.PISST();

		pisST.setVBC("");
		pisST.setPPIS("");
		pisST.setQBCProd("");
		pisST.setVAliqProd("");
		pisST.setVPIS("");

		imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoPISST(pisST));
	}

	private void criaImpostoPis(Imposto imposto) {
		TNFe.InfNFe.Det.Imposto.PIS pis = new TNFe.InfNFe.Det.Imposto.PIS();

		PISAliq pisAliq = new PISAliq();
		pisAliq.setCST("");
		pisAliq.setVBC("");
		pisAliq.setPPIS("");
		pisAliq.setVPIS("");
		pis.setPISAliq(pisAliq);

		PISQtde pisQtde = new PISQtde();
		pisQtde.setCST("");
		pisQtde.setQBCProd("");
		pisQtde.setVAliqProd("");
		pisQtde.setVPIS("");
		pis.setPISQtde(pisQtde);

		PISNT pisNT = new PISNT();
		pisNT.setCST("");
		pis.setPISNT(pisNT);

		PISOutr pisOutr = new PISOutr();
		pisOutr.setVBC("");
		pisOutr.setPPIS("");
		pis.setPISOutr(pisOutr);

		imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoPIS(pis));
	}

	private void criaImpostoII(Imposto imposto) {
		TNFe.InfNFe.Det.Imposto.II ii = new TNFe.InfNFe.Det.Imposto.II();

		ii.setVBC("");
		ii.setVDespAdu("");
		ii.setVII("");
		ii.setVIOF("");

		imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoII(ii));
	}

	private void criaImpostoIPI(Imposto imposto) {
		TIpi ipi = new TIpi();
		ipi.setCNPJProd("");
		ipi.setCSelo("");
		ipi.setQSelo("");
		ipi.setCEnq("");

		IPITrib ipiTrib = new IPITrib();
		ipiTrib.setCST("");
		ipiTrib.setVBC("");
		ipiTrib.setPIPI("");
		ipiTrib.setQUnid("");
		ipiTrib.setVUnid("");
		ipiTrib.setVIPI("");
		ipi.setIPITrib(ipiTrib);

		IPINT ipiINT = new IPINT();
		ipiINT.setCST("");
		ipi.setIPINT(ipiINT);

		imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoIPI(ipi));
	}

	private void criaImpostoIcmsUFDestino(Imposto imposto) {
		TNFe.InfNFe.Det.Imposto.ICMSUFDest icmsUFDest = new TNFe.InfNFe.Det.Imposto.ICMSUFDest();

		icmsUFDest.setVBCUFDest("");
		icmsUFDest.setVBCFCPUFDest("");
		icmsUFDest.setPFCPUFDest("");
		icmsUFDest.setPICMSUFDest("");
		icmsUFDest.setPICMSInter("");
		icmsUFDest.setPICMSInterPart("");
		icmsUFDest.setVFCPUFDest("");
		icmsUFDest.setVICMSUFDest("");
		icmsUFDest.setVICMSUFRemet("");

		imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMSUFDest(icmsUFDest));
	}

	private void criaImpostoIcms(Imposto imposto) {
		TNFe.InfNFe.Det.Imposto.ICMS icms = new TNFe.InfNFe.Det.Imposto.ICMS();
		icms.setICMS00(criaImpostoIcms00());
		icms.setICMS10(criaImpostoIcms10());
		icms.setICMS20(criaImpostoIcms20());
		icms.setICMS30(criaImpostoIcms30());
		icms.setICMS40(criaImpostoIcms40());
		icms.setICMS51(criaImpostoIcms51());
		icms.setICMS60(criaImpostoIcms60());
		icms.setICMS70(criaImpostoIcms70());
		icms.setICMS90(criaImpostoIcms90());
		icms.setICMSPart(criaImportoIcmsPart());
		icms.setICMSST(criaImportoIcmsST());
		icms.setICMSSN101(criaImportoIcmsSN101());
		icms.setICMSSN102(criaImportoIcmsSN102());
		icms.setICMSSN201(criaImportoIcmsSN201());
		;
		icms.setICMSSN202(criaImportoIcmsSN202());
		icms.setICMSSN500(criaImportoIcmsSN500());
		icms.setICMSSN900(criaImportoIcmsSN900());

		imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
	}

	private ICMSSN900 criaImportoIcmsSN900() {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900 icmsSN900 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900();

		icmsSN900.setOrig("");
		icmsSN900.setCSOSN("");

		icmsSN900.setModBC("");
		icmsSN900.setVBC("");
		icmsSN900.setPRedBC("");
		icmsSN900.setPICMS("");
		icmsSN900.setVICMS("");

		icmsSN900.setModBCST("");
		icmsSN900.setPMVAST("");
		icmsSN900.setPRedBCST("");
		icmsSN900.setVBCST("");
		icmsSN900.setPICMSST("");
		icmsSN900.setVICMSST("");

		icmsSN900.setVBCFCPST("");
		icmsSN900.setPFCPST("");
		icmsSN900.setVFCPST("");

		icmsSN900.setPCredSN("");
		icmsSN900.setVCredICMSSN("");

		return icmsSN900;
	}

	private ICMSSN500 criaImportoIcmsSN500() {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500 icmsSN500 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500();

		icmsSN500.setOrig("");
		icmsSN500.setCSOSN("");
		icmsSN500.setVBCSTRet("");
		icmsSN500.setPST("");
		icmsSN500.setVICMSSubstituto("");
		icmsSN500.setVICMSSTRet("");

		icmsSN500.setVBCFCPSTRet("");
		icmsSN500.setPFCPSTRet("");
		icmsSN500.setVFCPSTRet("");

		icmsSN500.setPRedBCEfet("");
		icmsSN500.setVBCEfet("");
		icmsSN500.setPICMSEfet("");
		icmsSN500.setVICMSEfet("");

		return icmsSN500;
	}

	private ICMSSN202 criaImportoIcmsSN202() {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202 icmsSN202 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202();

		icmsSN202.setOrig("");
		icmsSN202.setCSOSN("");
		icmsSN202.setModBCST("");
		icmsSN202.setPMVAST("");
		icmsSN202.setPRedBCST("");
		icmsSN202.setVBCST("");
		icmsSN202.setVICMSST("");

		icmsSN202.setVBCFCPST("");
		icmsSN202.setPFCPST("");
		icmsSN202.setVFCPST("");

		return icmsSN202;
	}

	private ICMSSN201 criaImportoIcmsSN201() {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201 icmsSN201 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201();

		icmsSN201.setOrig("");
		icmsSN201.setCSOSN("");
		icmsSN201.setModBCST("");
		icmsSN201.setPMVAST("");
		icmsSN201.setPRedBCST("");
		icmsSN201.setVBCST("");
		icmsSN201.setVICMSST("");

		icmsSN201.setVBCFCPST("");
		icmsSN201.setPFCPST("");
		icmsSN201.setVFCPST("");
		icmsSN201.setPCredSN("");
		icmsSN201.setVCredICMSSN("");

		return icmsSN201;
	}

	private ICMSSN102 criaImportoIcmsSN102() {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102 icmsSN102 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102();

		icmsSN102.setOrig("");
		icmsSN102.setCSOSN("");

		return icmsSN102;
	}

	private ICMSSN101 criaImportoIcmsSN101() {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101 icmsSN101 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101();

		icmsSN101.setOrig("");
		icmsSN101.setCSOSN("");
		icmsSN101.setPCredSN("");
		icmsSN101.setVCredICMSSN("");

		return icmsSN101;
	}

	private ICMSST criaImportoIcmsST() {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMSST icmsST = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSST();

		icmsST.setOrig("0");
		icmsST.setCST("");

		icmsST.setVBCSTRet("");
		icmsST.setPST("");
		icmsST.setVICMSSubstituto("");
		icmsST.setVICMSSTRet("");

		icmsST.setVBCFCPSTRet("");
		icmsST.setPFCPSTRet("");
		icmsST.setVBCSTDest("");
		icmsST.setVICMSSTDest("");

		icmsST.setPRedBCEfet("");
		icmsST.setVBCEfet("");
		icmsST.setPICMSEfet("");
		icmsST.setVICMSEfet("");

		return icmsST;
	}

	private ICMSPart criaImportoIcmsPart() {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart icmsPart = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart();
		icmsPart.setOrig("0");
		icmsPart.setCST("");

		icmsPart.setModBC("");
		icmsPart.setVBC("");
		icmsPart.setPRedBC("");
		icmsPart.setPICMS("");
		icmsPart.setVICMS("");

		icmsPart.setModBCST("");
		icmsPart.setPMVAST("");
		icmsPart.setPRedBCST("");
		icmsPart.setVBCST("");
		icmsPart.setPICMSST("");
		icmsPart.setVICMSST("");
		icmsPart.setPBCOp("");
		icmsPart.setUFST(TUf.PR);

		return icmsPart;
	}

	private ICMS90 criaImpostoIcms90() {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMS90 icms90 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS90();
		icms90.setOrig("0");
		icms90.setCST("");

		icms90.setModBC("");
		icms90.setVBC("");
		icms90.setPRedBC("");
		icms90.setPICMS("");
		icms90.setVICMS("");

		icms90.setVBCFCP("");
		icms90.setPFCP("");
		icms90.setVFCP("");

		icms90.setModBCST("");
		icms90.setPMVAST("");
		icms90.setPRedBCST("");
		icms90.setVBCST("");
		icms90.setPICMSST("");
		icms90.setVICMSST("");

		icms90.setVBCFCPST("");
		icms90.setPFCPST("");
		icms90.setVFCPST("");

		icms90.setVICMSDeson("");
		icms90.setMotDesICMS("");

		return icms90;
	}

	private ICMS70 criaImpostoIcms70() {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMS70 icms70 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS70();
		icms70.setOrig("0");
		icms70.setCST("");

		icms70.setModBC("");
		icms70.setPRedBC("");
		icms70.setVBC("");
		icms70.setPICMS("");
		icms70.setVICMS("");

		icms70.setVBCFCP("");
		icms70.setPFCP("");
		icms70.setVFCP("");
		icms70.setModBCST("");
		icms70.setPMVAST("");
		icms70.setPRedBCST("");
		icms70.setVBCST("");
		icms70.setPICMSST("");
		icms70.setVICMSST("");

		return icms70;
	}

	private ICMS60 criaImpostoIcms60() {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMS60 icms60 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS60();
		icms60.setOrig("0");
		icms60.setCST("");

		icms60.setVBCSTRet("");
		icms60.setPST("");
		icms60.setVICMSSubstituto("");
		icms60.setVICMSSTRet("");

		icms60.setVBCFCPSTRet("");
		icms60.setPFCPSTRet("");
		icms60.setVFCPSTRet("");

		icms60.setPRedBCEfet("");
		icms60.setVBCEfet("");
		icms60.setPICMSEfet("");
		icms60.setVICMSEfet("");

		return icms60;
	}

	private ICMS51 criaImpostoIcms51() {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMS51 icms51 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS51();
		icms51.setOrig("0");
		icms51.setCST("");
		icms51.setModBC("");
		icms51.setPRedBC("");
		icms51.setVBC("");
		icms51.setPICMS("");
		icms51.setVICMSOp("");
		icms51.setPDif("");
		icms51.setVICMSDif("");
		icms51.setVICMS("");

		icms51.setVBCFCP("");
		icms51.setPFCP("");
		icms51.setVFCP("");

		return icms51;
	}

	private ICMS40 criaImpostoIcms40() {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMS40 icms40 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS40();
		icms40.setOrig("0");
		icms40.setCST("");

		icms40.setVICMSDeson("");
		icms40.setMotDesICMS("");

		return icms40;
	}

	private ICMS30 criaImpostoIcms30() {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMS30 icms30 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS30();
		icms30.setOrig("0");
		icms30.setCST("");
		icms30.setModBCST("");
		icms30.setPMVAST("");
		icms30.setPRedBCST("");
		icms30.setVBCST("");
		icms30.setPICMSST("");
		icms30.setVICMSST("");
		;

		icms30.setVBCFCPST("");
		icms30.setPFCPST("");
		icms30.setVFCPST("");

		icms30.setVICMSDeson("");
		icms30.setMotDesICMS("");

		return icms30;
	}

	private ICMS20 criaImpostoIcms20() {

		TNFe.InfNFe.Det.Imposto.ICMS.ICMS20 icms20 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS20();
		icms20.setOrig("0");
		icms20.setCST("");
		icms20.setModBC("");
		icms20.setVBC("");
		icms20.setPICMS("");
		icms20.setVICMS("");

		icms20.setVBCFCP("");
		icms20.setPFCP("");
		icms20.setVFCP("");

		icms20.setVICMSDeson("");
		icms20.setMotDesICMS("");

		return icms20;
	}

	private ICMS10 criaImpostoIcms10() {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMS10 icms10 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS10();
		icms10.setOrig("0");
		icms10.setCST("");
		icms10.setModBC("");
		icms10.setVBC("");
		icms10.setPICMS("");
		icms10.setVICMS("");

		icms10.setVBCFCP("");
		icms10.setPFCP("");
		icms10.setVFCP("");

		icms10.setModBCST("");
		icms10.setPMVAST("");
		icms10.setPRedBCST("");
		icms10.setVBCST("");
		icms10.setPICMSST("");
		icms10.setVICMSST("");

		icms10.setVBCFCPST("");
		icms10.setPFCPST("");
		icms10.setVFCPST("");

		return icms10;
	}

	private ICMS00 criaImpostoIcms00() {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMS00 icms00 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS00();
		icms00.setOrig("0");
		icms00.setModBC("0");
		icms00.setCST("00");
		icms00.setVBC("10.00");
		icms00.setPICMS("10");
		icms00.setVICMS("1.00");

		return icms00;
	}

	private Prod montaProduto(ContainerIntegracaoNFeItem nfeItem) {
		Item item = nfeItem.getItem();
		TNFe.InfNFe.Det.Prod produto = new TNFe.InfNFe.Det.Prod();
		produto.setCProd(Integer.toString(item.getCodigo()));
		if (StringUtils.isNotBlank(item.getGtinEan()))
			produto.setCEAN(item.getGtinEan());
		else
			produto.setCEAN("SEM GTIN");
		produto.setXProd(item.getNome());
		produto.setNCM(item.getNcm().getCodigo());
		produto.setCFOP(nfeItem.getCfop());
		produto.setUCom(item.getUnidadeMedidaComercial().getSigla());
		produto.setQCom(nfeItem.getQuantidade().toString());
		produto.setVUnCom(nfeItem.getValorUnitario().toString());
		produto.setVProd(nfeItem.getValorTotal().toString());
		produto.setCEANTrib("SEM GTIN");
		produto.setUTrib(item.getUnidadeMedidaTributavel().getSigla());
		produto.setQTrib(nfeItem.getQuantidadeTributavel().toString());
		produto.setVUnTrib(nfeItem.getValorUnitarioTributavel().toString());
		produto.setVFrete(nfeItem.getValorTotalFrete().toString());
		produto.setVSeg(nfeItem.getValorTotalSeguro().toString());
		produto.setVDesc(nfeItem.getValorDesconto().toString());
		produto.setVOutro(nfeItem.getValorOutrasDespesas().toString());
		produto.setIndTot(nfeItem.isValorItemCompoeValorTotalNFe() ? "1" : "0");
		;

		return produto;
	}

	private Collection<? extends AutXML> montaAutorizacaoXML(
			List<ContainerIntegracaoNFeAutorizacao> nfeAutorizacaoXML) {
		List<AutXML> listAutXMLs = new ArrayList<>();
		for (ContainerIntegracaoNFeAutorizacao autorizacao : nfeAutorizacaoXML) {
			AutXML aut = new AutXML();
			aut.setCNPJ(autorizacao.getCnpj());
			aut.setCPF(autorizacao.getCpf());
			listAutXMLs.add(aut);
		}
		return listAutXMLs;
	}

	private TLocal montaLocalRetirada(ContainerIntegracaoNFeLocalRetirada containerIntegracaoNFeLocalRetirada) {
		TLocal local = new TLocal();
		if (StringUtils.isNotBlank(containerIntegracaoNFeLocalRetirada.getCnpj())) {
			local.setCNPJ(containerIntegracaoNFeLocalRetirada.getCnpj());
		}
		if (StringUtils.isNotBlank(containerIntegracaoNFeLocalRetirada.getCpf())) {
			local.setCNPJ(containerIntegracaoNFeLocalRetirada.getCpf());
		}
		local.setXNome(containerIntegracaoNFeLocalRetirada.getRazaoSocialNome());
		local.setXLgr(containerIntegracaoNFeLocalRetirada.getLogradouro());
		local.setNro(containerIntegracaoNFeLocalRetirada.getNumero());
		local.setXCpl(containerIntegracaoNFeLocalRetirada.getComplemento());
		local.setXBairro(containerIntegracaoNFeLocalRetirada.getBairro());
		local.setCMun(Long.toString(containerIntegracaoNFeLocalRetirada.getMunicipio().getCodigoIBGE()));
		local.setXMun(containerIntegracaoNFeLocalRetirada.getMunicipio().getNome());
		local.setUF(TUf.valueOf(containerIntegracaoNFeLocalRetirada.getMunicipio().getUf().getSigla()));
		local.setCEP(containerIntegracaoNFeLocalRetirada.getCep());
		local.setCPais(containerIntegracaoNFeLocalRetirada.getMunicipio().getPais().getCodigoIBGE());
		local.setXPais(containerIntegracaoNFeLocalRetirada.getMunicipio().getPais().getNome());
		local.setFone(containerIntegracaoNFeLocalRetirada.getTelefone());
		local.setEmail(containerIntegracaoNFeLocalRetirada.getEmail());
		local.setIE(containerIntegracaoNFeLocalRetirada.getInscricaoEstadual());

		return local;
	}

	private Dest montaDestinatario(ContainerIntegracaoNFe containerIntegracaoNFe) {
		ParceiroLocalEndereco parceiroLocalEndereco = containerIntegracaoNFe.getNfeDestinatario()
				.getParceiroLocalEndereco();
		ParceiroLocal parceiroLocal = parceiroLocalService.getById(parceiroLocalEndereco.getParceiroLocal().getId()).get();
		ParceiroLocalJuridica parceiroLocalJuridica = parceiroLocal.getDadosPessoaJuridica().get(0);
		Parceiro parceiro = parceiroLocal.getParceiro();
		ParceiroLocalTelefone parceiroLocalTelefone = parceiroLocal.getTelefones().get(0);
		ParceiroLocalEmail parceiroLocalEmail = parceiroLocal.getEmails().get(0);

		TNFe.InfNFe.Dest dest = new TNFe.InfNFe.Dest();
		if (parceiro.getTipoPessoa().equals(TipoPessoa.PESSOA_FISICA))
			dest.setCNPJ(parceiroLocal.getCpfCnpj());
		if (parceiro.getTipoPessoa().equals(TipoPessoa.PESSOA_JURIDICA))
			dest.setCPF(parceiroLocal.getCpfCnpj());
		dest.setXNome(parceiro.getNomeRazaoSocial());
		dest.setIndIEDest(containerIntegracaoNFe.getNfeDestinatario().getIndicadorIEDestinatario().getCodigoReceita());
		if (containerIntegracaoNFe.getNfeDestinatario()
				.getIndicadorIEDestinatario() == IndicadorIEDestinatario.CONTRIBUINTEICMS) {
			dest.setIE(parceiroLocalJuridica.getInscricaoEstadual());
		}
		if (parceiroLocalEmail != null) {
			dest.setEmail(parceiroLocalEmail.getEmail());
		}

		TEndereco enderecoDestinatario = new TEndereco();
		enderecoDestinatario.setXLgr(parceiroLocalEndereco.getEndereco());
		enderecoDestinatario.setNro(parceiroLocalEndereco.getNumero());
		enderecoDestinatario.setXCpl(parceiroLocalEndereco.getComplemento());
		enderecoDestinatario.setXBairro(parceiroLocalEndereco.getBairro());
		enderecoDestinatario.setCMun(Long.toString(parceiroLocalEndereco.getCidade().getCodigoIBGE()));
		enderecoDestinatario.setXMun(parceiroLocalEndereco.getCidade().getNome());
		enderecoDestinatario.setUF(TUf.valueOf(parceiroLocalEndereco.getCidade().getUf().getSigla()));
		enderecoDestinatario.setCEP(parceiroLocalEndereco.getCep());
		enderecoDestinatario.setCPais(parceiroLocalEndereco.getCidade().getPais().getCodigoIBGE());
		enderecoDestinatario.setXPais(parceiroLocalEndereco.getCidade().getPais().getNome());
		enderecoDestinatario.setFone(parceiroLocalTelefone.getNumero());

		dest.setEnderDest(enderecoDestinatario);
		return dest;
	}

	private Emit montaEmitente(ConfigEmpresaNFe configEmpresaNFe) {
		ParceiroLocal parceiroLocalEmitente = parceiroLocalService.getById(configEmpresaNFe.getEmpresaFilial().getParceiroLocal().getId()).get();
		
		ParceiroLocalJuridica parceiroLocalJuridica = parceiroLocalEmitente.getDadosPessoaJuridica().get(0);

		TNFe.InfNFe.Emit emit = new TNFe.InfNFe.Emit();
		emit.setCNPJ(parceiroLocalEmitente.getCpfCnpj());
		emit.setXNome(parceiroLocalEmitente.getParceiro().getNomeRazaoSocial());
		if (StringUtils
				.isNotBlank(parceiroLocalEmitente.getParceiro().getNomeFantasia()))
			emit.setXFant(parceiroLocalEmitente.getParceiro().getNomeFantasia());
		emit.setIE(parceiroLocalJuridica.getInscricaoEstadual());
		emit.setCRT("3");// revisar

		TEnderEmi enderecoEmitente = new TEnderEmi();
		ParceiroLocalEndereco parceiroLocalEndereco = parceiroLocalEmitente
				.getEnderecos().get(0);
		ParceiroLocalTelefone parceiroLocalTelefone = parceiroLocalEmitente
				.getTelefones().get(0);
		enderecoEmitente.setXLgr(parceiroLocalEndereco.getEndereco());
		enderecoEmitente.setNro(parceiroLocalEndereco.getNumero());
		enderecoEmitente.setXCpl(parceiroLocalEndereco.getComplemento());
		enderecoEmitente.setXBairro(parceiroLocalEndereco.getBairro());
		enderecoEmitente.setCMun(Long.toString(parceiroLocalEndereco.getCidade().getCodigoIBGE()));
		enderecoEmitente.setXMun(parceiroLocalEndereco.getCidade().getNome());
		enderecoEmitente.setUF(TUfEmi.valueOf(parceiroLocalEndereco.getCidade().getUf().getSigla()));
		enderecoEmitente.setCEP(parceiroLocalEndereco.getCep());
		enderecoEmitente.setCPais("1058");
		enderecoEmitente.setXPais("Brasil");
		enderecoEmitente.setFone(parceiroLocalTelefone.getNumero());

		emit.setEnderEmit(enderecoEmitente);

		return emit;
	}

	private Ide montaIde(ConfiguracoesNfe configuracoesNfe, ChaveUtil chaveUtil,
			ContainerIntegracaoNFe containerIntegracaoNFe, ConfigEmpresaNFe configEmpresaNFe) {
		// Tag: B01
		TNFe.InfNFe.Ide ide = new TNFe.InfNFe.Ide();
		ide.setCUF(configuracoesNfe.getEstado().getCodigoUF());
		ide.setCNF(containerIntegracaoNFe.getNfeIdentificacao().getCnf());
		ide.setNatOp(containerIntegracaoNFe.getNfeIdentificacao().getNaturezaOperacao());
		ide.setMod(containerIntegracaoNFe.getNfeIdentificacao().getTipoDocumento().getCodigoReceita());
		ide.setSerie(String.valueOf(containerIntegracaoNFe.getNfeIdentificacao().getSerie()));
		ide.setNNF(String.valueOf(containerIntegracaoNFe.getNfeIdentificacao().getNumeroNFe()));
		ide.setDhEmi(XmlNfeUtil.dataNfe(containerIntegracaoNFe.getNfeIdentificacao().getDataEmissao()));
		if (containerIntegracaoNFe.getNfeIdentificacao().getDataHoraSaidaEntrada() != null)
			ide.setDhSaiEnt(XmlNfeUtil.dataNfe(containerIntegracaoNFe.getNfeIdentificacao().getDataHoraSaidaEntrada()));
		ide.setTpNF(containerIntegracaoNFe.getNfeIdentificacao().getIndicadorOperacao().getCodigoReceita());
		ide.setIdDest(containerIntegracaoNFe.getNfeIdentificacao().getLocalDestinoOperacao().getCodigoReceita());
		if (containerIntegracaoNFe.getNfeIdentificacao().getMunicipioOcorrenciaFatoGeradorICMS() != null)
			ide.setCMunFG(Long.toString(containerIntegracaoNFe.getNfeIdentificacao()
					.getMunicipioOcorrenciaFatoGeradorICMS().getCodigoIBGE()));
		ide.setTpImp(containerIntegracaoNFe.getNfeIdentificacao().getTipoImpressaoDanfe().getCodigoReceita());
		ide.setTpEmis(configEmpresaNFe.getTipoEmissao().getValue());
		ide.setCDV(chaveUtil.getDigitoVerificador());
		ide.setTpAmb(configuracoesNfe.getAmbiente().getCodigo());
		ide.setFinNFe(containerIntegracaoNFe.getNfeIdentificacao().getFinalidadeNfe().getCodigoReceita());
		ide.setIndFinal(containerIntegracaoNFe.getNfeIdentificacao().getTipoConsumidor().getCodigoReceita());
		ide.setIndPres(containerIntegracaoNFe.getNfeIdentificacao().getTipoPresencaComprador().getCodigoReceita());
		if (containerIntegracaoNFe.getNfeIdentificacao().getTipoIntermediador() != null)
			ide.setIndIntermed(containerIntegracaoNFe.getNfeIdentificacao().getTipoIntermediador().getCodigoReceita());
		ide.setProcEmi(containerIntegracaoNFe.getNfeIdentificacao().getProcessoEmissao().getCodigoReceita());
		ide.setVerProc("1.0.0");

		ide.setDhCont("");
		ide.setXJust("");

		ide.getNFref().addAll(montaNFReferenciadas());

		return ide;
	}

	private Collection<? extends NFref> montaNFReferenciadas() {
		List<NFref> notas = new ArrayList<>();
		NFref nota = new NFref();
		nota.setRefNFe("");

		RefNF refNF = new RefNF();
		refNF.setCUF("");
		refNF.setAAMM("");
		refNF.setCNPJ("");
		refNF.setMod("");
		refNF.setSerie("");
		refNF.setNNF("");
		nota.setRefNF(refNF);

		RefNFP refNFP = new RefNFP();
		refNFP.setCUF("");
		refNFP.setAAMM("");
		refNFP.setCNPJ("");
		refNFP.setCPF("");
		refNFP.setIE("");
		refNFP.setMod("");
		refNFP.setSerie("");
		refNFP.setNNF("");
		nota.setRefNFP(refNFP);

		nota.setRefCTe("");

		RefECF refECF = new RefECF();
		refECF.setMod("");
		refECF.setNECF("");
		refECF.setNCOO("");
		nota.setRefECF(refECF);

		notas.add(nota);

		return notas;

	}

	private ChaveUtil montaChaveNFe(EstadosEnum estado, ConfigEmpresaNFe configEmpresaNFe,
			ContainerIntegracaoNFeIdentificacao containerNFeIdentificacao) {
		containerNFeIdentificacao.setCnf(String.format("%08d", new Random().nextInt(99999999)));
		if (containerNFeIdentificacao.getDataEmissao() == null) {
			containerNFeIdentificacao.setDataEmissao(LocalDateTime.now());
		}

		return new ChaveUtil(estado, configEmpresaNFe.getEmpresaFilial().getParceiroLocal().getCpfCnpj(),
				containerNFeIdentificacao.getTipoDocumento().getCodigoReceita(), containerNFeIdentificacao.getSerie(),
				containerNFeIdentificacao.getNumeroNFe(), configEmpresaNFe.getTipoEmissao().getValue(),
				containerNFeIdentificacao.getCnf(), containerNFeIdentificacao.getDataEmissao());
	}

	private ConfiguracoesNfe criarConfiguracoesNfe(ConfigEmpresaNFe configEmpresaNFe) throws BusinessException {
		
		try {
			Resource resource = resourceLoader.getResource("classpath:schemas");
			String path = resource.getFile().getAbsolutePath();
			
			Certificado certificado = carregarCertificado(configEmpresaNFe);
			System.out.println(certificado.getCnpjCpf());
			System.out.println(certificado.getVencimento());
			AmbienteEnum ambiente = AmbienteEnum.getByCodigo(configEmpresaNFe.getTipoAmbiente().getValue());
			return ConfiguracoesNfe.criarConfiguracoes(EstadosEnum.PR, ambiente, certificado, path);
		} catch (CertificadoException | IOException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	private Certificado carregarCertificado(ConfigEmpresaNFe configEmpresaNFe) throws BusinessException {
		Optional<EmpresaCertificado> optionalEmpresaCertificado = empresaCertificadoService
				.encontrarCertificadoValidoMaisProximoVencimento(configEmpresaNFe.getConfigEmpresa().getEmpresa(),
						configEmpresaNFe.getTipoCertificado());
		if (optionalEmpresaCertificado.isPresent()) {
			EmpresaCertificado empresaCertificado = optionalEmpresaCertificado.get();
			try {
				return CertificadoService.certificadoPfxBytes(empresaCertificado.getArquivoPfx(),
						empresaCertificado.getSenha());
			} catch (CertificadoException e) {
				throw new BusinessException(e.getMessage());
			}
		} else {
			throw new BusinessException("Não encontrado certificado válido para empresa "
					+ configEmpresaNFe.getConfigEmpresa().getEmpresa().getNome());
		}
	}

}
