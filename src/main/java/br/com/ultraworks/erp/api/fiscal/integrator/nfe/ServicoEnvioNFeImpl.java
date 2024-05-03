package br.com.ultraworks.erp.api.fiscal.integrator.nfe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp.Transporta;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp.Vol;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp.Vol.Lacres;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TUf;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TUfEmi;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TVeiculo;
import br.com.swconsultoria.nfe.schema_4.retConsReciNFe.TRetConsReciNFe;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.ultraworks.erp.api.configuracao.domain.certificado.EmpresaCertificado;
import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFe;
import br.com.ultraworks.erp.api.configuracao.service.ConfigEmpresaNFeService;
import br.com.ultraworks.erp.api.configuracao.service.EmpresaCertificadoService;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFe;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeAut;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeCofinsItem;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeDest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeDetItem;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeDetPagamento;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeEmit;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeIcmsItem;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeImpostosItem;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeInfoAdic;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeIpiItem;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeObsCont;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeObsFisco;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFePagamento;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFePisItem;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeProcReferenciado;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeProdItem;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeReboque;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeRef;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeRetirada;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeTotais;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeTransporte;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeVolume;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.RetornoNFeIntegracao;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalService;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.util.BigDecimalHelper;

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
	public RetornoNFeIntegracao enviarNFe(NFe nfe) throws BusinessException {
		ConfigEmpresaNFe configEmpresaNFe = configEmpresaNFeService
				.getByEmpresaFilialId(nfe.getEmpresaFilial().getId());
		ConfiguracoesNfe configuracoesNfe = criarConfiguracoesNfe(configEmpresaNFe);
		TEnviNFe enviNFe = criaEnviNFe(configuracoesNfe, nfe, configEmpresaNFe);

//		try {
//			enviNFe = Nfe.montaNfe(configuracoesNfe, enviNFe, true);
//		} catch (NfeException e) {
//			throw new BusinessException(
//					"Erro ao realizar assinatura e validação da NFe. " + "Motivo: " + e.getMessage());
//		}

		TRetEnviNFe retorno = null;
//		try {
//			retorno = Nfe.enviarNfe(configuracoesNfe, enviNFe, DocumentoEnum.NFE);
//		} catch (NfeException e) {
//			throw new BusinessException("Erro ao enviar da NFe. " + "Motivo: " + e.getMessage());
//		}

		String xmlFinal = null;
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
//					xmlFinal = XmlNfeUtil.criaNfeProc(enviNFe, tRetConsReciNFe.getProtNFe().get(0));
					retornoNFeIntegracao.setXml(xmlFinal);
				} else {
					retornoNFeIntegracao.setStatus(tRetConsReciNFe.getCStat());
				}
			} else {
				RetornoUtil.validaSincrono(retorno);
				retornoNFeIntegracao.setStatus(retorno.getProtNFe().getInfProt().getCStat());
				retornoNFeIntegracao.setProtocolo(retorno.getProtNFe().getInfProt().getNProt());
//				xmlFinal = XmlNfeUtil.criaNfeProc(enviNFe, retorno.getProtNFe());
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

	private TEnviNFe criaEnviNFe(ConfiguracoesNfe configuracoesNfe, NFe uwNFe, ConfigEmpresaNFe configEmpresaNFe) {
		TEnviNFe enviNFe = new TEnviNFe();
		enviNFe.setVersao(ConstantesUtil.VERSAO.NFE);
		enviNFe.setIdLote("1");
		enviNFe.setIndSinc("1");

		TNFe nfe = new TNFe();
		TNFe.InfNFe infNFe = getInfNFe(configuracoesNfe, uwNFe, configEmpresaNFe);
		nfe.setInfNFe(infNFe);
		enviNFe.getNFe().add(nfe);

		return enviNFe;
	}

	private InfNFe getInfNFe(ConfiguracoesNfe configuracoesNfe, NFe uwNFe, ConfigEmpresaNFe configEmpresaNFe) {
		TNFe.InfNFe infNFe = new TNFe.InfNFe();
		infNFe.setId(uwNFe.getChaveNfe());
		infNFe.setVersao(ConstantesUtil.VERSAO.NFE);
		infNFe.setIde(montaIde(configuracoesNfe, uwNFe, configEmpresaNFe));
		infNFe.setEmit(montaEmitente(configEmpresaNFe, uwNFe));
		infNFe.setDest(montaDestinatario(uwNFe));
		if (uwNFe.getNfeRetirada() != null)
			infNFe.setRetirada(montaLocalRetirada(uwNFe.getNfeRetirada()));
		if (uwNFe.getAutorizacoes() != null && !uwNFe.getAutorizacoes().isEmpty()) {
			infNFe.getAutXML().addAll(montaAutorizacaoXML(uwNFe.getAutorizacoes()));
		}
		infNFe.getDet().addAll(montaDetalhamentoProdutosServicos(uwNFe));
		infNFe.setTotal(montaTotal(uwNFe));
		infNFe.setTransp(montaTransportadora(uwNFe));
//		infNFe.setCobr(montaCobranca());
		infNFe.setPag(pagamento(uwNFe));
//		infNFe.setInfIntermed(montaInformacaoIntermediador());
		infNFe.setInfAdic(montaInfAdicionais(uwNFe));
//		infNFe.setExporta(montaInfExportacao());
//		infNFe.setCompra(montaInfCompra());
//		infNFe.setCana(montaInfAquisicaoCana());
//		infNFe.setInfRespTec(montaRespTecnico());

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

	private InfAdic montaInfAdicionais(NFe uwNFe) {
		NFeInfoAdic nfeInfoAdic = uwNFe.getNfeInfoAdic();
		TNFe.InfNFe.InfAdic infAdicionais = new TNFe.InfNFe.InfAdic();
		infAdicionais.setInfAdFisco(nfeInfoAdic.getInfadfisco());
		infAdicionais.setInfCpl(nfeInfoAdic.getInfcpl());
		if (nfeInfoAdic.getObservacoesContribuinte() != null && !nfeInfoAdic.getObservacoesContribuinte().isEmpty())
			infAdicionais.getObsCont().addAll(montaInfAdicionaisCont(nfeInfoAdic.getObservacoesContribuinte()));
		if (nfeInfoAdic.getObservacoesFisco() != null && !nfeInfoAdic.getObservacoesFisco().isEmpty())
			infAdicionais.getObsFisco().addAll(montaInfAdicionaisObsFisco(nfeInfoAdic.getObservacoesFisco()));
		if (nfeInfoAdic.getProcessosReferenciados() != null && !nfeInfoAdic.getProcessosReferenciados().isEmpty())
			infAdicionais.getProcRef().addAll(montaInfAdicionaisProcRef(nfeInfoAdic.getProcessosReferenciados()));

		return infAdicionais;
	}

	private Collection<? extends ProcRef> montaInfAdicionaisProcRef(List<NFeProcReferenciado> list) {
		List<ProcRef> listaProcRef = new ArrayList<>();
		list.forEach(inf -> {
			ProcRef proc = new ProcRef();
			proc.setNProc(inf.getNproc());
			proc.setIndProc(inf.getIndproc());

			listaProcRef.add(proc);
		});
		return listaProcRef;
	}

	private Collection<? extends ObsFisco> montaInfAdicionaisObsFisco(List<NFeObsFisco> list) {
		List<ObsFisco> listaObsFisco = new ArrayList<>();
		list.forEach(inf -> {
			ObsFisco obs = new ObsFisco();
			obs.setXCampo(inf.getXcampo());
			obs.setXTexto(inf.getXtexto());
			listaObsFisco.add(obs);
		});
		return listaObsFisco;
	}

	private Collection<? extends ObsCont> montaInfAdicionaisCont(List<NFeObsCont> list) {
		List<ObsCont> listaObsCont = new ArrayList<>();

		list.forEach(inf -> {
			ObsCont obs = new ObsCont();
			obs.setXCampo(inf.getXcampo());
			obs.setXTexto(inf.getXtexto());
			listaObsCont.add(obs);
		});

		return listaObsCont;
	}

	private InfIntermed montaInformacaoIntermediador() {
		TNFe.InfNFe.InfIntermed infIntermed = new TNFe.InfNFe.InfIntermed();
		infIntermed.setCNPJ("");
		infIntermed.setIdCadIntTran("");

		return infIntermed;
	}

	private Pag pagamento(NFe uwNFe) {
		NFePagamento nfePagamento = uwNFe.getNfePagamento();
		TNFe.InfNFe.Pag pag = new TNFe.InfNFe.Pag();

		pag.getDetPag().addAll(montaDetalhePagamento(nfePagamento.getDetalhesPagamento()));
		pag.setVTroco(BigDecimalHelper.toString(nfePagamento.getVtroco(), 2));

		return pag;

	}

	private Collection<? extends DetPag> montaDetalhePagamento(List<NFeDetPagamento> listPags) {
		List<DetPag> listaDetPag = new ArrayList<>();

		listPags.forEach(pag -> {
			DetPag detPag = new DetPag();
			detPag.setIndPag(Integer.toString(pag.getIndpag()));
			detPag.setTPag(pag.getTpag());
			detPag.setVPag(BigDecimalHelper.toString(pag.getVpag(), 2));

			if (StringUtils.containsAny(pag.getTpag(), "03", "04")) {
				Card card = new Card();
				card.setTpIntegra(Integer.toString(pag.getTpintegra()));
				card.setCNPJ(pag.getCnpj());
				card.setTBand(pag.getTband());
				card.setCAut(pag.getCaut());
				detPag.setCard(card);
			}
			listaDetPag.add(detPag);
		});

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

	private Transp montaTransportadora(NFe uwNFe) {
		NFeTransporte nfeTransporte = uwNFe.getNfeTransporte();
		TNFe.InfNFe.Transp transp = new TNFe.InfNFe.Transp();

		transp.setModFrete(Integer.toString(nfeTransporte.getModfrete()));

		if (nfeTransporte.getModfrete() != 9) {

			Transporta transporta = new Transporta();
			transporta.setCNPJ(nfeTransporte.getCnpj());
			transporta.setCPF(nfeTransporte.getCpf());
			transporta.setXNome(nfeTransporte.getXnome());
			transporta.setIE(nfeTransporte.getIe());
			transporta.setXEnder(nfeTransporte.getXender());
			transporta.setXMun(nfeTransporte.getXmun());
			transporta.setUF(TUf.valueOf(nfeTransporte.getUf()));
			transp.setTransporta(transporta);

//			RetTransp rettransp = new RetTransp();
//			rettransp.setVServ("");
//			rettransp.setVBCRet("");
//			rettransp.setPICMSRet("");
//			rettransp.setVICMSRet("");
//			rettransp.setCFOP("");
//			rettransp.setCMunFG("");
//			transp.setRetTransp(rettransp);

			TVeiculo veiculo = new TVeiculo();
			veiculo.setPlaca(nfeTransporte.getPlaca());
			veiculo.setUF(TUf.valueOf(nfeTransporte.getUfplaca()));
			veiculo.setRNTC(nfeTransporte.getRntc());
			transp.setVeicTransp(veiculo);

			if (nfeTransporte.getReboques() != null && nfeTransporte.getReboques().isEmpty()) {
				transp.getReboque().addAll(montaReboques(nfeTransporte.getReboques()));
			}
			transp.setVagao(nfeTransporte.getVagao());
			transp.setBalsa(nfeTransporte.getBalsa());

			if (nfeTransporte.getVolumes() != null && nfeTransporte.getVolumes().isEmpty()) {
				transp.getVol().addAll(montaVolumes(nfeTransporte.getVolumes()));
			}
		}
		return transp;
	}

	private Collection<? extends Vol> montaVolumes(List<NFeVolume> listVolumes) {
		List<Vol> listaVol = new ArrayList<>();

		listVolumes.forEach(volume -> {
			Vol vol = new Vol();
			vol.setQVol(BigDecimalHelper.toString(volume.getQvol(), 0));
			vol.setEsp(volume.getEsp());
			vol.setMarca(volume.getMarca());
			vol.setNVol(volume.getNvol());
			vol.setPesoL(BigDecimalHelper.toString(volume.getPesol(), 3));
			vol.setPesoB(BigDecimalHelper.toString(volume.getPesob(), 3));

			listaVol.add(vol);
		});

//		vol.getLacres().addAll(montaVolLacres());

		return listaVol;
	}

	private Collection<? extends Lacres> montaVolLacres() {
		List<Lacres> listaLacres = new ArrayList<>();

		Lacres lacre = new Lacres();
		lacre.setNLacre("");
		listaLacres.add(lacre);

		return listaLacres;
	}

	private Collection<? extends TVeiculo> montaReboques(List<NFeReboque> listReboques) {
		List<TVeiculo> listaReboque = new ArrayList<>();

		listReboques.forEach(reb -> {
			TVeiculo veiculo = new TVeiculo();
			veiculo.setPlaca(reb.getPlaca());
			veiculo.setUF(TUf.valueOf(reb.getUfplaca()));
			veiculo.setRNTC(reb.getRntc());
			listaReboque.add(veiculo);
		});

		return listaReboque;
	}

	private Total montaTotal(NFe uwNFe) {
		TNFe.InfNFe.Total total = new TNFe.InfNFe.Total();
		total.setICMSTot(montarICMSTot(uwNFe));
//		total.setISSQNtot(montarISSQNTot());
//		total.setRetTrib(montarRetTrib());

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

	private ICMSTot montarICMSTot(NFe uwNFe) {
		NFeTotais nfeTotais = uwNFe.getNfeTotais();
		TNFe.InfNFe.Total.ICMSTot icmsTot = new TNFe.InfNFe.Total.ICMSTot();

		icmsTot.setVBC(BigDecimalHelper.toString(nfeTotais.getVbc(), 2));
		icmsTot.setVICMS(BigDecimalHelper.toString(nfeTotais.getVicms(), 2));
		icmsTot.setVICMSDeson(BigDecimalHelper.toString(nfeTotais.getVicmsdeson(), 2));
		icmsTot.setVFCPUFDest(BigDecimalHelper.toString(nfeTotais.getVfcpufdest(), 2));
		icmsTot.setVICMSUFDest(BigDecimalHelper.toString(nfeTotais.getVicmsufdest(), 2));
		icmsTot.setVICMSUFRemet(BigDecimalHelper.toString(nfeTotais.getVicmsufremet(), 2));
		icmsTot.setVFCP(BigDecimalHelper.toString(nfeTotais.getVfcp(), 2));
		icmsTot.setVBCST(BigDecimalHelper.toString(nfeTotais.getVbcst(), 2));
		icmsTot.setVST(BigDecimalHelper.toString(nfeTotais.getVst(), 2));
		icmsTot.setVFCPST(BigDecimalHelper.toString(nfeTotais.getVfcpst(), 2));
		icmsTot.setVFCPSTRet(BigDecimalHelper.toString(nfeTotais.getVfcpstret(), 2));
		icmsTot.setVProd(BigDecimalHelper.toString(nfeTotais.getVprod(), 2));
		icmsTot.setVFrete(BigDecimalHelper.toString(nfeTotais.getVfrete(), 2));
		icmsTot.setVSeg(BigDecimalHelper.toString(nfeTotais.getVseg(), 2));
		icmsTot.setVDesc(BigDecimalHelper.toString(nfeTotais.getVdesc(), 2));
		icmsTot.setVII(BigDecimalHelper.toString(nfeTotais.getVii(), 2));
		icmsTot.setVIPI(BigDecimalHelper.toString(nfeTotais.getVipi(), 2));
		icmsTot.setVIPIDevol(BigDecimalHelper.toString(nfeTotais.getVipidevol(), 2));
		icmsTot.setVPIS(BigDecimalHelper.toString(nfeTotais.getVpis(), 2));
		icmsTot.setVCOFINS(BigDecimalHelper.toString(nfeTotais.getVcofins(), 2));
		icmsTot.setVOutro(BigDecimalHelper.toString(nfeTotais.getVoutro(), 2));
		icmsTot.setVNF(BigDecimalHelper.toString(nfeTotais.getVnf(), 2));
		icmsTot.setVTotTrib(BigDecimalHelper.toString(nfeTotais.getVtottrib(), 2));

		return icmsTot;
	}

	private Collection<? extends Det> montaDetalhamentoProdutosServicos(NFe uwNFe) {
		List<TNFe.InfNFe.Det> listDetalhamento = new ArrayList<>();

		for (NFeDetItem nfeItem : uwNFe.getItens()) {
			TNFe.InfNFe.Det det = new TNFe.InfNFe.Det();
			det.setNItem(Integer.toString(nfeItem.getNItem()));
			listDetalhamento.add(det);
			det.setProd(montaProduto(nfeItem.getNfeProdItem()));
			det.setImposto(montaImposto(nfeItem.getNfeImpostosItem()));
//			det.setImpostoDevol(montaImpostoDevol());
			det.setInfAdProd(nfeItem.getNfeInfoAdicItem().getInfadprod());
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

	private Imposto montaImposto(NFeImpostosItem nFeImpostosItem) {
		TNFe.InfNFe.Det.Imposto imposto = new TNFe.InfNFe.Det.Imposto();
		if (nFeImpostosItem.getNfeIcmsItem() != null)
			criaImpostoIcms(imposto, nFeImpostosItem.getNfeIcmsItem());
		criaImpostoIcmsUFDestino(imposto);
		if (nFeImpostosItem.getNfeIpiItem() != null)
			criaImpostoIPI(imposto, nFeImpostosItem.getNfeIpiItem());
//		criaImpostoII(imposto);
		if (nFeImpostosItem.getNfePisItem() != null) {
			criaImpostoPis(imposto, nFeImpostosItem.getNfePisItem());
//		criaImpostoPisST(imposto);
		}
		if (nFeImpostosItem.getNfeCofinsItem() != null) {
			criaImpostoCofins(imposto, nFeImpostosItem.getNfeCofinsItem());
//		criaImpostoCofinsST(imposto);
		}
//		criaImpostoISSQN(imposto);

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

	private void criaImpostoCofins(Imposto imposto, NFeCofinsItem nFeCofinsItem) {
		TNFe.InfNFe.Det.Imposto.COFINS cofins = new TNFe.InfNFe.Det.Imposto.COFINS();

		if (StringUtils.containsAny(nFeCofinsItem.getCst(), "01", "02")) {
			COFINSAliq cofinsAliq = new COFINSAliq();
			cofinsAliq.setCST(nFeCofinsItem.getCst());
			cofinsAliq.setVBC(BigDecimalHelper.toString(nFeCofinsItem.getVbc(), 2));
			cofinsAliq.setPCOFINS(BigDecimalHelper.toString(nFeCofinsItem.getPcofins(), 4));
			cofinsAliq.setVCOFINS(BigDecimalHelper.toString(nFeCofinsItem.getVcofins(), 2));
			cofins.setCOFINSAliq(cofinsAliq);

		} else if (StringUtils.containsAny(nFeCofinsItem.getCst(), "03")) {
			COFINSQtde cofinsQtde = new COFINSQtde();
			cofinsQtde.setCST(nFeCofinsItem.getCst());
			cofinsQtde.setQBCProd(BigDecimalHelper.toString(nFeCofinsItem.getQbcprod(), 4));
			cofinsQtde.setVAliqProd(BigDecimalHelper.toString(nFeCofinsItem.getValiqprod(), 2));
			cofinsQtde.setVCOFINS(BigDecimalHelper.toString(nFeCofinsItem.getVcofins(), 2));
			cofins.setCOFINSQtde(cofinsQtde);
		} else if (StringUtils.containsAny(nFeCofinsItem.getCst(), "04", "05", "06", "07", "08", "09")) {
			COFINSNT cofinsNT = new COFINSNT();
			cofinsNT.setCST(nFeCofinsItem.getCst());
			cofins.setCOFINSNT(cofinsNT);
		} else {
			COFINSOutr cofinsOutr = new COFINSOutr();
			cofinsOutr.setVBC(BigDecimalHelper.toString(nFeCofinsItem.getVbc(), 2));
			cofinsOutr.setPCOFINS(BigDecimalHelper.toString(nFeCofinsItem.getPcofins(), 4));
			cofins.setCOFINSOutr(cofinsOutr);
		}

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

	private void criaImpostoPis(Imposto imposto, NFePisItem nFePisItem) {
		TNFe.InfNFe.Det.Imposto.PIS pis = new TNFe.InfNFe.Det.Imposto.PIS();

		if (StringUtils.containsAny(nFePisItem.getCst(), "01", "02")) {
			PISAliq pisAliq = new PISAliq();
			pisAliq.setCST(nFePisItem.getCst());
			pisAliq.setVBC(BigDecimalHelper.toString(nFePisItem.getVbc(), 2));
			pisAliq.setPPIS(BigDecimalHelper.toString(nFePisItem.getPpis(), 4));
			pisAliq.setVPIS(BigDecimalHelper.toString(nFePisItem.getVpis(), 2));
			pis.setPISAliq(pisAliq);

		} else if (StringUtils.containsAny(nFePisItem.getCst(), "03")) {
			PISQtde pisQtde = new PISQtde();
			pisQtde.setCST(nFePisItem.getCst());
			pisQtde.setQBCProd(BigDecimalHelper.toString(nFePisItem.getQbcprod(), 4));
			pisQtde.setVAliqProd(BigDecimalHelper.toString(nFePisItem.getValiqprod(), 2));
			pisQtde.setVPIS(BigDecimalHelper.toString(nFePisItem.getVpis(), 2));
			pis.setPISQtde(pisQtde);
		} else if (StringUtils.containsAny(nFePisItem.getCst(), "04", "05", "06", "07", "08", "09")) {
			PISNT pisNT = new PISNT();
			pisNT.setCST(nFePisItem.getCst());
			pis.setPISNT(pisNT);
		} else {
			PISOutr pisOutr = new PISOutr();
			pisOutr.setVBC(BigDecimalHelper.toString(nFePisItem.getVbc(), 2));
			pisOutr.setPPIS(BigDecimalHelper.toString(nFePisItem.getPpis(), 4));
			pis.setPISOutr(pisOutr);
		}
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

	private void criaImpostoIPI(Imposto imposto, NFeIpiItem nFeIpiItem) {
		TIpi ipi = new TIpi();
		ipi.setCNPJProd(nFeIpiItem.getCnpjprod());
		ipi.setCSelo(nFeIpiItem.getCselo());
		ipi.setQSelo(Integer.toString(nFeIpiItem.getQselo()));
		ipi.setCEnq(Integer.toString(nFeIpiItem.getCenq()));

		if (StringUtils.containsAny(nFeIpiItem.getCst(), "00", "49", "50", "99")) {
			IPITrib ipiTrib = new IPITrib();
			ipiTrib.setCST(nFeIpiItem.getCst());
			ipiTrib.setVBC(BigDecimalHelper.toString(nFeIpiItem.getVbc(), 2));
			ipiTrib.setPIPI(BigDecimalHelper.toString(nFeIpiItem.getPipi(), 4));
			ipiTrib.setQUnid(BigDecimalHelper.toString(nFeIpiItem.getQunid(), 4));
			ipiTrib.setVUnid(BigDecimalHelper.toString(nFeIpiItem.getVunid(), 2));
			ipiTrib.setVIPI(BigDecimalHelper.toString(nFeIpiItem.getVipi(), 2));
			ipi.setIPITrib(ipiTrib);
		}

		if (StringUtils.containsAny(nFeIpiItem.getCst(), "01", "02", "03", "04", "51", "52", "53")) {
			IPINT ipiINT = new IPINT();
			ipiINT.setCST(nFeIpiItem.getCst());
			ipi.setIPINT(ipiINT);
		}

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

	private void criaImpostoIcms(Imposto imposto, NFeIcmsItem nFeIcmsItem) {
		TNFe.InfNFe.Det.Imposto.ICMS icms = new TNFe.InfNFe.Det.Imposto.ICMS();
		if (nFeIcmsItem.getCst().equals("00"))
			icms.setICMS00(criaImpostoIcms00(nFeIcmsItem));
		if (nFeIcmsItem.getCst().equals("10"))
			icms.setICMS10(criaImpostoIcms10(nFeIcmsItem));
		if (nFeIcmsItem.getCst().equals("20"))
			icms.setICMS20(criaImpostoIcms20(nFeIcmsItem));
		if (nFeIcmsItem.getCst().equals("30"))
			icms.setICMS30(criaImpostoIcms30(nFeIcmsItem));
		if (nFeIcmsItem.getCst().equals("40"))
			icms.setICMS40(criaImpostoIcms40(nFeIcmsItem));
		if (nFeIcmsItem.getCst().equals("51"))
			icms.setICMS51(criaImpostoIcms51(nFeIcmsItem));
		if (nFeIcmsItem.getCst().equals("60"))
			icms.setICMS60(criaImpostoIcms60(nFeIcmsItem));
		if (nFeIcmsItem.getCst().equals("70"))
			icms.setICMS70(criaImpostoIcms70(nFeIcmsItem));
		if (nFeIcmsItem.getCst().equals("90"))
			icms.setICMS90(criaImpostoIcms90(nFeIcmsItem));
//		icms.setICMSPart(criaImportoIcmsPart());
//		icms.setICMSST(criaImportoIcmsST());
		if (nFeIcmsItem.getCst().equals("101"))
			icms.setICMSSN101(criaImportoIcmsSN101(nFeIcmsItem));
		if (nFeIcmsItem.getCst().equals("102"))
			icms.setICMSSN102(criaImportoIcmsSN102(nFeIcmsItem));
		if (nFeIcmsItem.getCst().equals("201"))
			icms.setICMSSN201(criaImportoIcmsSN201(nFeIcmsItem));
		if (nFeIcmsItem.getCst().equals("202"))
			icms.setICMSSN202(criaImportoIcmsSN202(nFeIcmsItem));
		if (nFeIcmsItem.getCst().equals("500"))
			icms.setICMSSN500(criaImportoIcmsSN500(nFeIcmsItem));
		if (nFeIcmsItem.getCst().equals("900"))
			icms.setICMSSN900(criaImportoIcmsSN900(nFeIcmsItem));

		imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
	}

	private ICMSSN900 criaImportoIcmsSN900(NFeIcmsItem nFeIcmsItem) {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900 icmsSN900 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900();

		icmsSN900.setOrig(Integer.toString(nFeIcmsItem.getOrig()));
		icmsSN900.setCSOSN(Integer.toString(nFeIcmsItem.getCsosn()));

		icmsSN900.setModBC(Integer.toString(nFeIcmsItem.getModbc()));
		icmsSN900.setVBC(BigDecimalHelper.toString(nFeIcmsItem.getVbc(), 2));
		icmsSN900.setPRedBC(BigDecimalHelper.toString(nFeIcmsItem.getPredbc(), 4));
		icmsSN900.setPICMS(BigDecimalHelper.toString(nFeIcmsItem.getPicms(), 4));
		icmsSN900.setVICMS(BigDecimalHelper.toString(nFeIcmsItem.getVicms(), 2));

		icmsSN900.setModBCST(Integer.toString(nFeIcmsItem.getModbcst()));
		icmsSN900.setPMVAST(BigDecimalHelper.toString(nFeIcmsItem.getPmvast(), 4));
		icmsSN900.setPRedBCST(BigDecimalHelper.toString(nFeIcmsItem.getPredbcst(), 4));
		icmsSN900.setVBCST(BigDecimalHelper.toString(nFeIcmsItem.getVbcst(), 2));
		icmsSN900.setPICMSST(BigDecimalHelper.toString(nFeIcmsItem.getPicmsst(), 4));
		icmsSN900.setVICMSST(BigDecimalHelper.toString(nFeIcmsItem.getVicmsst(), 2));

//		icmsSN900.setVBCFCPST("");
//		icmsSN900.setPFCPST("");
//		icmsSN900.setVFCPST("");

		icmsSN900.setPCredSN(BigDecimalHelper.toString(nFeIcmsItem.getPcredsn(), 4));
		icmsSN900.setVCredICMSSN(BigDecimalHelper.toString(nFeIcmsItem.getVcredicmssn(), 2));

		return icmsSN900;
	}

	private ICMSSN500 criaImportoIcmsSN500(NFeIcmsItem nFeIcmsItem) {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500 icmsSN500 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500();

		icmsSN500.setOrig(Integer.toString(nFeIcmsItem.getOrig()));
		icmsSN500.setCSOSN(Integer.toString(nFeIcmsItem.getCsosn()));
		icmsSN500.setVBCSTRet(BigDecimalHelper.toString(nFeIcmsItem.getVbcstret(), 2));
		icmsSN500.setPST(BigDecimalHelper.toString(nFeIcmsItem.getPst(), 4));
		icmsSN500.setVICMSSubstituto(BigDecimalHelper.toString(nFeIcmsItem.getVicmssubstituto(), 2));
		icmsSN500.setVICMSSTRet(BigDecimalHelper.toString(nFeIcmsItem.getVicmsstret(), 2));

//		icmsSN500.setVBCFCPSTRet("");
//		icmsSN500.setPFCPSTRet("");
//		icmsSN500.setVFCPSTRet("");

		icmsSN500.setPRedBCEfet(BigDecimalHelper.toString(nFeIcmsItem.getPredbcefet(), 4));
		icmsSN500.setVBCEfet(BigDecimalHelper.toString(nFeIcmsItem.getVbcefet(), 2));
		icmsSN500.setPICMSEfet(BigDecimalHelper.toString(nFeIcmsItem.getPicmsefet(), 4));
		icmsSN500.setVICMSEfet(BigDecimalHelper.toString(nFeIcmsItem.getVicmsefet(), 2));

		return icmsSN500;
	}

	private ICMSSN202 criaImportoIcmsSN202(NFeIcmsItem nFeIcmsItem) {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202 icmsSN202 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202();

		icmsSN202.setOrig(Integer.toString(nFeIcmsItem.getOrig()));
		icmsSN202.setCSOSN(Integer.toString(nFeIcmsItem.getCsosn()));
		icmsSN202.setModBCST(Integer.toString(nFeIcmsItem.getModbcst()));
		icmsSN202.setPMVAST(BigDecimalHelper.toString(nFeIcmsItem.getPmvast(), 4));
		icmsSN202.setPRedBCST(BigDecimalHelper.toString(nFeIcmsItem.getPredbcst(), 4));
		icmsSN202.setVBCST(BigDecimalHelper.toString(nFeIcmsItem.getVbcst(), 2));
		icmsSN202.setVICMSST(BigDecimalHelper.toString(nFeIcmsItem.getVicmsst(), 2));

//		icmsSN202.setVBCFCPST("");
//		icmsSN202.setPFCPST("");
//		icmsSN202.setVFCPST("");

		return icmsSN202;
	}

	private ICMSSN201 criaImportoIcmsSN201(NFeIcmsItem nFeIcmsItem) {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201 icmsSN201 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201();

		icmsSN201.setOrig(Integer.toString(nFeIcmsItem.getOrig()));
		icmsSN201.setCSOSN(Integer.toString(nFeIcmsItem.getCsosn()));
		icmsSN201.setModBCST(Integer.toString(nFeIcmsItem.getModbcst()));
		icmsSN201.setPMVAST(BigDecimalHelper.toString(nFeIcmsItem.getPmvast(), 4));
		icmsSN201.setPRedBCST(BigDecimalHelper.toString(nFeIcmsItem.getPredbcst(), 4));
		icmsSN201.setVBCST(BigDecimalHelper.toString(nFeIcmsItem.getVbcst(), 2));
		icmsSN201.setVICMSST(BigDecimalHelper.toString(nFeIcmsItem.getVicmsst(), 2));

//		icmsSN201.setVBCFCPST("");
//		icmsSN201.setPFCPST("");
//		icmsSN201.setVFCPST("");
		icmsSN201.setPCredSN(BigDecimalHelper.toString(nFeIcmsItem.getPcredsn(), 4));
		icmsSN201.setVCredICMSSN(BigDecimalHelper.toString(nFeIcmsItem.getVcredicmssn(), 2));

		return icmsSN201;
	}

	private ICMSSN102 criaImportoIcmsSN102(NFeIcmsItem nFeIcmsItem) {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102 icmsSN102 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102();

		icmsSN102.setOrig(Integer.toString(nFeIcmsItem.getOrig()));
		icmsSN102.setCSOSN(Integer.toString(nFeIcmsItem.getCsosn()));

		return icmsSN102;
	}

	private ICMSSN101 criaImportoIcmsSN101(NFeIcmsItem nFeIcmsItem) {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101 icmsSN101 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101();

		icmsSN101.setOrig(Integer.toString(nFeIcmsItem.getOrig()));
		icmsSN101.setCSOSN(Integer.toString(nFeIcmsItem.getCsosn()));
		icmsSN101.setPCredSN(BigDecimalHelper.toString(nFeIcmsItem.getPcredsn(), 4));
		icmsSN101.setVCredICMSSN(BigDecimalHelper.toString(nFeIcmsItem.getVcredicmssn(), 2));

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

	private ICMS90 criaImpostoIcms90(NFeIcmsItem nFeIcmsItem) {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMS90 icms90 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS90();
		icms90.setOrig(Integer.toString(nFeIcmsItem.getOrig()));
		icms90.setCST(nFeIcmsItem.getCst());

		icms90.setModBC(Integer.toString(nFeIcmsItem.getModbc()));
		icms90.setVBC(BigDecimalHelper.toString(nFeIcmsItem.getVbc(), 2));
		icms90.setPRedBC(BigDecimalHelper.toString(nFeIcmsItem.getPredbc(), 4));
		icms90.setPICMS(BigDecimalHelper.toString(nFeIcmsItem.getPicms(), 2));
		icms90.setVICMS(BigDecimalHelper.toString(nFeIcmsItem.getVicms(), 2));

//		icms90.setVBCFCP("");
//		icms90.setPFCP("");
//		icms90.setVFCP("");

		icms90.setModBCST(Integer.toString(nFeIcmsItem.getModbcst()));
		icms90.setPMVAST(BigDecimalHelper.toString(nFeIcmsItem.getPmvast(), 4));
		icms90.setPRedBCST(BigDecimalHelper.toString(nFeIcmsItem.getPredbcst(), 4));
		icms90.setVBCST(BigDecimalHelper.toString(nFeIcmsItem.getVbcst(), 2));
		icms90.setPICMSST(BigDecimalHelper.toString(nFeIcmsItem.getPicmsst(), 2));
		icms90.setVICMSST(BigDecimalHelper.toString(nFeIcmsItem.getVicmsst(), 2));

//		icms90.setVBCFCPST("");
//		icms90.setPFCPST("");
//		icms90.setVFCPST("");

		icms90.setVICMSDeson(BigDecimalHelper.toString(nFeIcmsItem.getVicmsdeson(), 2));
		icms90.setMotDesICMS(Integer.toString(nFeIcmsItem.getMotdesicms()));

		return icms90;
	}

	private ICMS70 criaImpostoIcms70(NFeIcmsItem nFeIcmsItem) {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMS70 icms70 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS70();
		icms70.setOrig(Integer.toString(nFeIcmsItem.getOrig()));
		icms70.setCST(nFeIcmsItem.getCst());

		icms70.setModBC(Integer.toString(nFeIcmsItem.getModbc()));
		icms70.setPRedBC(BigDecimalHelper.toString(nFeIcmsItem.getPredbc(), 4));
		icms70.setVBC(BigDecimalHelper.toString(nFeIcmsItem.getVbc(), 2));
		icms70.setPICMS(BigDecimalHelper.toString(nFeIcmsItem.getPicms(), 4));
		icms70.setVICMS(BigDecimalHelper.toString(nFeIcmsItem.getVicms(), 2));

//		icms70.setVBCFCP("");
//		icms70.setPFCP("");
//		icms70.setVFCP("");

		icms70.setModBCST(Integer.toString(nFeIcmsItem.getModbc()));
		icms70.setPMVAST(BigDecimalHelper.toString(nFeIcmsItem.getPmvast(), 4));
		icms70.setPRedBCST(BigDecimalHelper.toString(nFeIcmsItem.getPredbcst(), 4));
		icms70.setVBCST(BigDecimalHelper.toString(nFeIcmsItem.getVbcst(), 2));
		icms70.setPICMSST(BigDecimalHelper.toString(nFeIcmsItem.getPicmsst(), 4));
		icms70.setVICMSST(BigDecimalHelper.toString(nFeIcmsItem.getVicmsst(), 2));

		return icms70;
	}

	private ICMS60 criaImpostoIcms60(NFeIcmsItem nFeIcmsItem) {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMS60 icms60 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS60();
		icms60.setOrig(Integer.toString(nFeIcmsItem.getOrig()));
		icms60.setCST(nFeIcmsItem.getCst());

		icms60.setVBCSTRet(BigDecimalHelper.toString(nFeIcmsItem.getVbcstret(), 2));
		icms60.setPST(BigDecimalHelper.toString(nFeIcmsItem.getPst(), 4));
		icms60.setVICMSSubstituto(BigDecimalHelper.toString(nFeIcmsItem.getVicmssubstituto(), 2));
		icms60.setVICMSSTRet(BigDecimalHelper.toString(nFeIcmsItem.getVicmsstret(), 2));

//		icms60.setVBCFCPSTRet("");
//		icms60.setPFCPSTRet("");
//		icms60.setVFCPSTRet("");

		icms60.setPRedBCEfet(BigDecimalHelper.toString(nFeIcmsItem.getPredbcefet(), 4));
		icms60.setVBCEfet(BigDecimalHelper.toString(nFeIcmsItem.getVbcefet(), 2));
		icms60.setPICMSEfet(BigDecimalHelper.toString(nFeIcmsItem.getPicmsefet(), 4));
		icms60.setVICMSEfet(BigDecimalHelper.toString(nFeIcmsItem.getVicmsefet(), 2));

		return icms60;
	}

	private ICMS51 criaImpostoIcms51(NFeIcmsItem nFeIcmsItem) {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMS51 icms51 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS51();
		icms51.setOrig(Integer.toString(nFeIcmsItem.getOrig()));
		icms51.setCST(nFeIcmsItem.getCst());
		icms51.setModBC(Integer.toString(nFeIcmsItem.getModbc()));
		icms51.setPRedBC(BigDecimalHelper.toString(nFeIcmsItem.getPredbc(), 4));
		icms51.setVBC(BigDecimalHelper.toString(nFeIcmsItem.getVbc(), 2));
		icms51.setPICMS(BigDecimalHelper.toString(nFeIcmsItem.getPicms(), 4));
		icms51.setVICMSOp(BigDecimalHelper.toString(nFeIcmsItem.getVicmsop(), 2));
		icms51.setPDif(BigDecimalHelper.toString(nFeIcmsItem.getPdif(), 4));
		icms51.setVICMSDif(BigDecimalHelper.toString(nFeIcmsItem.getVicmsdif(), 2));
		icms51.setVICMS(BigDecimalHelper.toString(nFeIcmsItem.getVicms(), 2));

//		icms51.setVBCFCP("");
//		icms51.setPFCP("");
//		icms51.setVFCP("");

		return icms51;
	}

	private ICMS40 criaImpostoIcms40(NFeIcmsItem nFeIcmsItem) {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMS40 icms40 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS40();
		icms40.setOrig(Integer.toString(nFeIcmsItem.getOrig()));
		icms40.setCST(nFeIcmsItem.getCst());

		icms40.setVICMSDeson(BigDecimalHelper.toString(nFeIcmsItem.getVicmsdeson(),2));
		icms40.setMotDesICMS(Integer.toString(nFeIcmsItem.getMotdesicms()));

		return icms40;
	}

	private ICMS30 criaImpostoIcms30(NFeIcmsItem nFeIcmsItem) {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMS30 icms30 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS30();
		icms30.setOrig(Integer.toString(nFeIcmsItem.getOrig()));
		icms30.setCST(nFeIcmsItem.getCst());
		icms30.setModBCST(Integer.toString(nFeIcmsItem.getModbcst()));
		icms30.setPMVAST(BigDecimalHelper.toString(nFeIcmsItem.getPmvast(), 4));
		icms30.setPRedBCST(BigDecimalHelper.toString(nFeIcmsItem.getPredbcst(), 4));
		icms30.setVBCST(BigDecimalHelper.toString(nFeIcmsItem.getVbcst(), 2));
		icms30.setPICMSST(BigDecimalHelper.toString(nFeIcmsItem.getPicmsst(), 4));
		icms30.setVICMSST(BigDecimalHelper.toString(nFeIcmsItem.getVicmsst(), 2));

//		icms30.setVBCFCPST("");
//		icms30.setPFCPST("");
//		icms30.setVFCPST("");

		icms30.setVICMSDeson(BigDecimalHelper.toString(nFeIcmsItem.getVicmsdeson(), 2));
		icms30.setMotDesICMS(Integer.toString(nFeIcmsItem.getMotdesicms()));

		return icms30;
	}

	private ICMS20 criaImpostoIcms20(NFeIcmsItem nFeIcmsItem) {

		TNFe.InfNFe.Det.Imposto.ICMS.ICMS20 icms20 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS20();
		icms20.setOrig(Integer.toString(nFeIcmsItem.getOrig()));
		icms20.setCST(nFeIcmsItem.getCst());
		icms20.setModBC(Integer.toString(nFeIcmsItem.getModbc()));
		icms20.setVBC(BigDecimalHelper.toString(nFeIcmsItem.getVbc(), 2));
		icms20.setPICMS(BigDecimalHelper.toString(nFeIcmsItem.getPicms(), 4));
		icms20.setVICMS(BigDecimalHelper.toString(nFeIcmsItem.getVicms(), 2));

//		icms20.setVBCFCP("");
//		icms20.setPFCP("");
//		icms20.setVFCP("");

		icms20.setVICMSDeson(BigDecimalHelper.toString(nFeIcmsItem.getVicmsdeson(), 2));
		icms20.setMotDesICMS(Integer.toString(nFeIcmsItem.getMotdesicms()));

		return icms20;
	}

	private ICMS10 criaImpostoIcms10(NFeIcmsItem nFeIcmsItem) {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMS10 icms10 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS10();
		icms10.setOrig(Integer.toString(nFeIcmsItem.getOrig()));
		icms10.setCST(nFeIcmsItem.getCst());
		icms10.setModBC(Integer.toString(nFeIcmsItem.getModbc()));
		icms10.setVBC(BigDecimalHelper.toString(nFeIcmsItem.getVbc(), 2));
		icms10.setPICMS(BigDecimalHelper.toString(nFeIcmsItem.getPicms(), 4));
		icms10.setVICMS(BigDecimalHelper.toString(nFeIcmsItem.getVicms(), 2));

//		icms10.setVBCFCP("");
//		icms10.setPFCP("");
//		icms10.setVFCP("");

		icms10.setModBCST(Integer.toString(nFeIcmsItem.getModbcst()));
		icms10.setPMVAST(BigDecimalHelper.toString(nFeIcmsItem.getPmvast(), 4));
		icms10.setPRedBCST(BigDecimalHelper.toString(nFeIcmsItem.getPredbcst(), 4));
		icms10.setVBCST(BigDecimalHelper.toString(nFeIcmsItem.getVbcst(), 2));
		icms10.setPICMSST(BigDecimalHelper.toString(nFeIcmsItem.getPicmsst(), 4));
		icms10.setVICMSST(BigDecimalHelper.toString(nFeIcmsItem.getVicmsst(), 2));

//		icms10.setVBCFCPST("");
//		icms10.setPFCPST("");
//		icms10.setVFCPST("");

		return icms10;
	}

	private ICMS00 criaImpostoIcms00(NFeIcmsItem nFeIcmsItem) {
		TNFe.InfNFe.Det.Imposto.ICMS.ICMS00 icms00 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMS00();
		icms00.setOrig(Integer.toString(nFeIcmsItem.getOrig()));
		icms00.setModBC(Integer.toString(nFeIcmsItem.getModbc()));
		icms00.setCST(nFeIcmsItem.getCst());
		icms00.setVBC(BigDecimalHelper.toString(nFeIcmsItem.getVbc(), 2));
		icms00.setPICMS(BigDecimalHelper.toString(nFeIcmsItem.getPicms(), 4));
		icms00.setVICMS(BigDecimalHelper.toString(nFeIcmsItem.getVicms(), 2));

		return icms00;
	}

	private Prod montaProduto(NFeProdItem uwNfeProdItem) {
		TNFe.InfNFe.Det.Prod produto = new TNFe.InfNFe.Det.Prod();
		produto.setCProd(uwNfeProdItem.getCprod());
		if (StringUtils.isNotBlank(uwNfeProdItem.getCean()))
			produto.setCEAN(uwNfeProdItem.getCean());
		else
			produto.setCEAN("SEM GTIN");
		produto.setXProd(uwNfeProdItem.getXprod());
		produto.setNCM(uwNfeProdItem.getNcm());
		produto.setCFOP(uwNfeProdItem.getCfop());
		produto.setUCom(uwNfeProdItem.getUcom());
		produto.setQCom(BigDecimalHelper.toString(uwNfeProdItem.getQcom(), 4));
		produto.setVUnCom(BigDecimalHelper.toString(uwNfeProdItem.getVuncom(), 10));
		produto.setVProd(BigDecimalHelper.toString(uwNfeProdItem.getVprod(), 2));
		if (StringUtils.isNotBlank(uwNfeProdItem.getCeantrib()))
			produto.setCEANTrib(uwNfeProdItem.getCeantrib());
		else
			produto.setCEANTrib("SEM GTIN");
		produto.setUTrib(uwNfeProdItem.getUtrib());
		produto.setQTrib(BigDecimalHelper.toString(uwNfeProdItem.getQtrib(), 4));
		produto.setVUnTrib(BigDecimalHelper.toString(uwNfeProdItem.getVuntrib(), 10));
		produto.setVFrete(BigDecimalHelper.toString(uwNfeProdItem.getVfrete(), 2));
		produto.setVSeg(BigDecimalHelper.toString(uwNfeProdItem.getVseg(), 2));
		produto.setVDesc(BigDecimalHelper.toString(uwNfeProdItem.getVdesc(), 2));
		produto.setVOutro(BigDecimalHelper.toString(uwNfeProdItem.getVoutro(), 2));
		produto.setIndTot(Integer.toString(uwNfeProdItem.getIndtot()));

		return produto;
	}

	private Collection<? extends AutXML> montaAutorizacaoXML(List<NFeAut> autorizacoes) {
		List<AutXML> listAutXMLs = new ArrayList<>();
		for (NFeAut autorizacao : autorizacoes) {
			AutXML aut = new AutXML();
			aut.setCNPJ(autorizacao.getCnpj());
			aut.setCPF(autorizacao.getCpf());
			listAutXMLs.add(aut);
		}
		return listAutXMLs;
	}

	private TLocal montaLocalRetirada(NFeRetirada nFeRetirada) {
		TLocal local = new TLocal();
		local.setCNPJ(nFeRetirada.getCnpj());
		local.setCPF(nFeRetirada.getCpf());
		local.setXNome(nFeRetirada.getXnome());
		local.setXLgr(nFeRetirada.getXlgr());
		local.setNro(nFeRetirada.getNro());
		local.setXCpl(nFeRetirada.getXcpl());
		local.setXBairro(nFeRetirada.getXbairro());
		local.setCMun(Integer.toString(nFeRetirada.getCmun()));
		local.setXMun(nFeRetirada.getXmun());
		local.setUF(TUf.valueOf(nFeRetirada.getUf()));
		local.setCEP(nFeRetirada.getCep());
		local.setCPais(nFeRetirada.getCpais());
		local.setXPais(nFeRetirada.getXpais());
		local.setFone(nFeRetirada.getFone());
		local.setEmail(nFeRetirada.getEmail());
		local.setIE(nFeRetirada.getIe());

		return local;
	}

	private Dest montaDestinatario(NFe uwNFe) {
		NFeDest uwNfeDest = uwNFe.getNfeDest();

		TNFe.InfNFe.Dest dest = new TNFe.InfNFe.Dest();
		dest.setCNPJ(uwNfeDest.getCnpj());
		dest.setCPF(uwNfeDest.getCpf());
		dest.setXNome(uwNfeDest.getXnome());
		dest.setIndIEDest(uwNfeDest.getIndieest());
		dest.setIE(uwNfeDest.getIe());
		dest.setEmail(uwNfeDest.getEmail());

		TEndereco enderecoDestinatario = new TEndereco();
		enderecoDestinatario.setXLgr(uwNfeDest.getXlgr());
		enderecoDestinatario.setNro(uwNfeDest.getNro());
		enderecoDestinatario.setXCpl(uwNfeDest.getXcpl());
		enderecoDestinatario.setXBairro(uwNfeDest.getXbairro());
		enderecoDestinatario.setCMun(Integer.toString(uwNfeDest.getCmun()));
		enderecoDestinatario.setXMun(uwNfeDest.getXmun());
		enderecoDestinatario.setUF(TUf.valueOf(uwNfeDest.getUf()));
		enderecoDestinatario.setCEP(uwNfeDest.getCep());
		enderecoDestinatario.setCPais(uwNfeDest.getCpais());
		enderecoDestinatario.setXPais(uwNfeDest.getXpais());
		enderecoDestinatario.setFone(uwNfeDest.getFone());

		dest.setEnderDest(enderecoDestinatario);
		return dest;
	}

	private Emit montaEmitente(ConfigEmpresaNFe configEmpresaNFe, NFe uwNFe) {
		TNFe.InfNFe.Emit emit = new TNFe.InfNFe.Emit();
		NFeEmit uwNfeEmit = uwNFe.getNfeEmit();
		emit.setCNPJ(uwNfeEmit.getCnpj());
		emit.setXNome(uwNfeEmit.getXnome());
		emit.setXFant(uwNfeEmit.getXfant());
		emit.setIE(uwNfeEmit.getIe());
		emit.setCRT(Integer.toString(uwNfeEmit.getCrt()));

		TEnderEmi enderecoEmitente = new TEnderEmi();
		enderecoEmitente.setXLgr(uwNfeEmit.getXlgr());
		enderecoEmitente.setNro(uwNfeEmit.getNro());
		enderecoEmitente.setXCpl(uwNfeEmit.getXcpl());
		enderecoEmitente.setXBairro(uwNfeEmit.getXbairro());
		enderecoEmitente.setCMun(Integer.toString(uwNfeEmit.getCmun()));
		enderecoEmitente.setXMun(uwNfeEmit.getXmun());
		enderecoEmitente.setUF(TUfEmi.valueOf(uwNfeEmit.getUf()));
		enderecoEmitente.setCEP(uwNfeEmit.getCep());
		enderecoEmitente.setCPais(uwNfeEmit.getCpais());
		enderecoEmitente.setXPais(uwNfeEmit.getXpais());
		enderecoEmitente.setFone(uwNfeEmit.getFone());

		emit.setEnderEmit(enderecoEmitente);

		return emit;
	}

	private Ide montaIde(ConfiguracoesNfe configuracoesNfe, NFe uwNFe, ConfigEmpresaNFe configEmpresaNFe) {
		TNFe.InfNFe.Ide ide = new TNFe.InfNFe.Ide();
		ide.setCUF(Integer.toString(uwNFe.getNfeIde().getCuf()));
		ide.setCNF(Integer.toString(uwNFe.getNfeIde().getCnf()));
		ide.setNatOp(uwNFe.getNfeIde().getNatop());
		ide.setMod(Integer.toString(uwNFe.getNfeIde().getMod()));
		ide.setSerie(Integer.toString(uwNFe.getNfeIde().getSerie()));
		ide.setNNF(Integer.toString(uwNFe.getNfeIde().getNnf()));
		ide.setDhEmi(XmlNfeUtil.dataNfe(uwNFe.getNfeIde().getDhemi()));
		ide.setDhSaiEnt(XmlNfeUtil.dataNfe(uwNFe.getNfeIde().getDhsaient()));
		ide.setTpNF(Integer.toString(uwNFe.getNfeIde().getTpnf()));
		ide.setIdDest(Integer.toString(uwNFe.getNfeIde().getIddest()));
		if (uwNFe.getNfeIde().getCmunfg() != 0)
			ide.setCMunFG(Long.toString(uwNFe.getNfeIde().getCmunfg()));
		ide.setTpImp(Integer.toString(uwNFe.getNfeIde().getTpimp()));
		ide.setTpEmis(configEmpresaNFe.getTipoEmissao().getValue());
		ide.setCDV(Integer.toString(uwNFe.getNfeIde().getCdv()));
		ide.setTpAmb(configuracoesNfe.getAmbiente().getCodigo());
		ide.setFinNFe(Integer.toString(uwNFe.getNfeIde().getFinnfe()));
		ide.setIndFinal(Integer.toString(uwNFe.getNfeIde().getIndfinal()));
		ide.setIndPres(Integer.toString(uwNFe.getNfeIde().getIndpres()));
		ide.setIndIntermed(Integer.toString(uwNFe.getNfeIde().getIndintermed()));
		ide.setProcEmi(Integer.toString(uwNFe.getNfeIde().getProcemi()));
		ide.setVerProc(Integer.toString(uwNFe.getNfeIde().getVerproc()));

		if (uwNFe.getNfeIde().getDhcont() != null) {
			ide.setDhCont(XmlNfeUtil.dataNfe(uwNFe.getNfeIde().getDhcont()));
			ide.setXJust(uwNFe.getNfeIde().getXjust());
		}

		ide.getNFref().addAll(montaNFReferenciadas(uwNFe.getNfeIde().getNfesRefs()));

		return ide;
	}

	private Collection<? extends NFref> montaNFReferenciadas(List<NFeRef> listNFeRef) {
		List<NFref> notas = new ArrayList<>();
		if (listNFeRef != null && !listNFeRef.isEmpty()) {
			listNFeRef.forEach(nfeRef -> {
				NFref nota = new NFref();
				nota.setRefNFe(nfeRef.getRefNFe());

				if (nfeRef.getMod().equals("01") || nfeRef.getMod().equals("02")) {
					RefNF refNF = new RefNF();
					refNF.setCUF(Integer.toString(nfeRef.getCuf()));
					refNF.setAAMM(Integer.toString(nfeRef.getAamm()));
					refNF.setCNPJ(nfeRef.getCnpj());
					refNF.setMod(nfeRef.getMod());
					refNF.setSerie(Integer.toString(nfeRef.getSerie()));
					refNF.setNNF(Integer.toString(nfeRef.getNnf()));

					nota.setRefNF(refNF);
				}

				if (nfeRef.getMod().equals("04")) {
					RefNFP refNFP = new RefNFP();
					refNFP.setCUF(Integer.toString(nfeRef.getCuf()));
					refNFP.setAAMM(Integer.toString(nfeRef.getAamm()));
					refNFP.setCNPJ(nfeRef.getCnpj());
					refNFP.setCPF(nfeRef.getCpf());
					refNFP.setIE(nfeRef.getIe());
					refNFP.setMod(nfeRef.getMod());
					refNFP.setSerie(Integer.toString(nfeRef.getSerie()));
					refNFP.setNNF(Integer.toString(nfeRef.getNnf()));
					nota.setRefNFP(refNFP);
				}

				nota.setRefCTe(nfeRef.getRefcte());

				if (nfeRef.getMod().equals("2B") || nfeRef.getMod().equals("2C") || nfeRef.getMod().equals("2D")) {
					RefECF refECF = new RefECF();
					refECF.setMod(nfeRef.getMod());
					refECF.setNECF(nfeRef.getNecf());
					refECF.setNCOO(nfeRef.getNcoo());
					nota.setRefECF(refECF);
				}

				notas.add(nota);
			});
		}
		return notas;

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
