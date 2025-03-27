package br.com.ultraworks.erp.api.fiscal.service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.consSitNFe.TConsSitNFe;
import br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;
import br.com.swconsultoria.nfe.util.ChaveUtil;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.ultraworks.erp.api.configuracao.domain.configempresa.ConfigEmpresa;
import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFe;
import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistema;
import br.com.ultraworks.erp.api.configuracao.service.ConfigEmpresaNFeService;
import br.com.ultraworks.erp.api.configuracao.service.ConfigEmpresaService;
import br.com.ultraworks.erp.api.configuracao.service.ConfigSistemaService;
import br.com.ultraworks.erp.api.configuracao.service.ControleNumeracaoService;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.AtualizaEstoqueItensRequest;
import br.com.ultraworks.erp.api.estoque.domain.movimentoestoque.AtualizaEstoqueRequest;
import br.com.ultraworks.erp.api.estoque.domain.tipodocumentoestoque.TipoDocumentoEstoque;
import br.com.ultraworks.erp.api.estoque.domain.tipomovimentoestoque.TipoMovimentoEstoque;
import br.com.ultraworks.erp.api.estoque.service.EstoqueService;
import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.movimento.MovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.parcela.ParcelaFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.Titulo;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.BaixaEstornoRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SelecionadosEstornoBaixaRequest;
import br.com.ultraworks.erp.api.financeiro.integrator.ServicoIntegracaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.integrator.TipoOperacaoIntegracaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.repository.MovimentoRepository;
import br.com.ultraworks.erp.api.financeiro.repository.ParcelaRepository;
import br.com.ultraworks.erp.api.financeiro.service.TituloService;
import br.com.ultraworks.erp.api.fiscal.domain.documento.Documento;
import br.com.ultraworks.erp.api.fiscal.domain.documentoItem.DocumentoItem;
import br.com.ultraworks.erp.api.fiscal.domain.documentointegracao.DocumentoIntegracao;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.cache.CacheNFe;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.cache.ChacheNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFe;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeComunicacaoSEFAZ;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeEmit;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeIde;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.NFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.response.ItemListaNFeResponse;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.response.NFeComunicacaoSEFAZResponse;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.response.NovaNFeResponse;
import br.com.ultraworks.erp.api.fiscal.domain.tipocomunicacaonfe.TipoComunicacaoNfe;
import br.com.ultraworks.erp.api.fiscal.domain.tipoimpressaodanfe.TipoImpressaoDanfe;
import br.com.ultraworks.erp.api.fiscal.domain.tipointegracao.TipoIntegracao;
import br.com.ultraworks.erp.api.fiscal.domain.tiponfe.TipoNfe;
import br.com.ultraworks.erp.api.fiscal.domain.tipoprocessoemissao.TipoProcessoEmissao;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.IServicoCancelamentoNFe;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.IServicoConsultaStatusNFe;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.IServicoEnvioNFe;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.IServicoInutilizacaoNFe;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.RetornoNFeIntegracao;
import br.com.ultraworks.erp.api.fiscal.mapper.NFeComunicacaoSEFAZMapper;
import br.com.ultraworks.erp.api.fiscal.repository.CacheNFeRepository;
import br.com.ultraworks.erp.api.fiscal.repository.DocumentoIntegracaoRepository;
import br.com.ultraworks.erp.api.fiscal.repository.DocumentoRepository;
import br.com.ultraworks.erp.api.fiscal.repository.NFeComunicacaoSEFAZRepository;
import br.com.ultraworks.erp.api.fiscal.repository.NFeRepository;
import br.com.ultraworks.erp.api.fiscal.repository.query.BuscaListagemNFeQuery;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.organograma.service.EmpresaFilialService;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalEndereco.ParceiroLocalEndereco;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTelefone.ParceiroLocalTelefone;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroLocalEnderecoMapper;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalEnderecoService;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalService;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalTelefoneService;
import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadrao;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernaestoque.OperacaoInternaEstoque;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafinanceiro.OperacaoInternaFinanceiro;
import br.com.ultraworks.erp.api.tabela.domain.situacaodocumento.SituacaoDocumento;
import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumento;
import br.com.ultraworks.erp.api.tabela.service.OperacaoInternaEstoqueService;
import br.com.ultraworks.erp.api.tabela.service.OperacaoInternaFinanceiroService;
import br.com.ultraworks.erp.api.tabela.service.TipoDocumentoService;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.util.NossoNumeroGenerator;

@Service
public class NfeService {

	private ApplicationContext context;
	private TipoDocumentoService tipoDocumentoService;
	private EmpresaFilialService empresaFilialService;
	private NFeRepository nfeRepository;
	private ParceiroLocalService parceiroLocalService;
	private ConfigEmpresaService configEmpresaService;
	private ConfigEmpresaNFeService configEmpresaNFeService;
	private ControleNumeracaoService controleNumeracaoService;
	private ParceiroLocalEnderecoService parceiroLocalEnderecoService;
	private ParceiroLocalEnderecoMapper parceiroLocalEnderecoMapper;
	private ParceiroLocalTelefoneService parceiroLocalTelefoneService;
	private BuscaListagemNFeQuery buscaListagemNFeQuery;
	private CacheNFeRepository cacheNFeRepository;
	private MergeNFeRequestToNFeEntityService mergeNFeRequestToNFeEntityService;
	private EstoqueService estoqueService;
	private DocumentoService documentoService;
	private OperacaoInternaEstoqueService operacaoInternaEstoqueService;
	private DocumentoIntegracaoRepository documentoIntegracaoRepository;
	private ServicoIntegracaoFinanceira servicoIntegracaoFinanceira;
	private ConfigSistemaService configSistemaService;
	private OperacaoInternaFinanceiroService operacaoInternaFinanceiroService;
	private DocumentoRepository documentoRepository;
	private ParcelaRepository parcelaRepository;
	private MovimentoRepository movimentoRepository;
	private NFeComunicacaoSEFAZService nFeComunicacaoSEFAZService;
	private NFeComunicacaoSEFAZMapper nFeComunicacaoSEFAZMapper;
	private NFeComunicacaoSEFAZRepository nFeComunicacaoSEFAZRepository;
	
	@Autowired
    private PlatformTransactionManager transactionManager;

	@Autowired
	public NfeService(ApplicationContext context, EmpresaFilialService empresaFilialService, NFeRepository nfeRepository,
			ConfigEmpresaService configEmpresaService, ConfigEmpresaNFeService configEmpresaNFeService,
			TipoDocumentoService tipoDocumentoService, ControleNumeracaoService controleNumeracaoService,
			ParceiroLocalEnderecoService parceiroLocalEnderecoService,
			ParceiroLocalEnderecoMapper parceiroLocalEnderecoMapper,
			ParceiroLocalTelefoneService parceiroLocalTelefoneService, ParceiroLocalService parceiroLocalService,
			BuscaListagemNFeQuery buscaListagemNFeQuery, CacheNFeRepository cacheNFeRepository,
			MergeNFeRequestToNFeEntityService mergeNFeRequestToNFeEntityService,
			EstoqueService estoqueService, DocumentoService documentoService,
			OperacaoInternaEstoqueService operacaoInternaEstoqueService,
			DocumentoIntegracaoRepository documentoIntegracaoRepository,
			ServicoIntegracaoFinanceira servicoIntegracaoFinanceira,
			ConfigSistemaService configSistemaService,
			OperacaoInternaFinanceiroService operacaoInternaFinanceiroService,
			DocumentoRepository documentoRepository, TituloService tituloService,
			ParcelaRepository parcelaRepository,
			MovimentoRepository movimentoRepository,
			NFeComunicacaoSEFAZService nFeComunicacaoSEFAZService,
			NFeComunicacaoSEFAZMapper nFeComunicacaoSEFAZMapper,
			NFeComunicacaoSEFAZRepository nFeComunicacaoSEFAZRepository) {
		this.context = context;
		this.empresaFilialService = empresaFilialService;
		this.nfeRepository = nfeRepository;
		this.configEmpresaService = configEmpresaService;
		this.configEmpresaNFeService = configEmpresaNFeService;
		this.tipoDocumentoService = tipoDocumentoService;
		this.controleNumeracaoService = controleNumeracaoService;
		this.parceiroLocalEnderecoService = parceiroLocalEnderecoService;
		this.parceiroLocalEnderecoMapper = parceiroLocalEnderecoMapper;
		this.parceiroLocalTelefoneService = parceiroLocalTelefoneService;
		this.parceiroLocalService = parceiroLocalService;
		this.buscaListagemNFeQuery = buscaListagemNFeQuery;
		this.cacheNFeRepository = cacheNFeRepository;
		this.mergeNFeRequestToNFeEntityService = mergeNFeRequestToNFeEntityService;
		this.estoqueService = estoqueService;
		this.documentoService = documentoService;
		this.operacaoInternaEstoqueService = operacaoInternaEstoqueService;
		this.documentoIntegracaoRepository = documentoIntegracaoRepository;
		this.servicoIntegracaoFinanceira = servicoIntegracaoFinanceira;
		this.configSistemaService = configSistemaService;
		this.operacaoInternaFinanceiroService = operacaoInternaFinanceiroService;
		this.documentoRepository = documentoRepository;
		this.parcelaRepository = parcelaRepository;
		this.movimentoRepository = movimentoRepository;
		this.nFeComunicacaoSEFAZService = nFeComunicacaoSEFAZService;
		this.nFeComunicacaoSEFAZMapper = nFeComunicacaoSEFAZMapper;
		this.nFeComunicacaoSEFAZRepository = nFeComunicacaoSEFAZRepository;
	}

	public NFe save(NFe entity) {
		NFe newEntity = nfeRepository.save(entity);
//		if (entity.getOperacoesInternasFiscal() != null)
//			entity.getOperacoesInternasFiscal().forEach(oi -> oi.setOperacaoInterna(newEntity));
//		operacaoInternaFiscalService.persistList(newEntity.getId(), entity.getOperacoesInternasFiscal());

		return newEntity;
	}

	@Transactional
	public NovaNFeResponse iniciarNovaNFe(Long empresaFilialId) {
		EmpresaFilial empresaFilial = empresaFilialService.getById(empresaFilialId)
				.orElseThrow(() -> new RegisterNotFoundException("Não foi encontrada filial informada"));

		ConfigEmpresa configEmpresa = configEmpresaService.getById(empresaFilial.getEmpresa().getId()).orElseThrow(
				() -> new RegisterNotFoundException("Não foi encontrado configuração para empresa informada"));
		ConfigEmpresaNFe configEmpresaNFe = configEmpresaNFeService.getByEmpresaFilialId(empresaFilialId);
		if (configEmpresaNFe == null)
			throw new BusinessException("Não foi encontrado configuração de nfe para filial informada");
		TipoDocumento tipoDocumento = tipoDocumentoService.findByCodigoReceita("55");
		int proximoNumero = controleNumeracaoService.getProximoNumero(empresaFilial.getEmpresa().getId(), empresaFilialId, tipoDocumento.getId(),
				configEmpresaNFe.getSerie());

		ParceiroLocal parceiroLocal = parceiroLocalService.getById(empresaFilialId).orElseThrow(
				() -> new RegisterNotFoundException("Não foi encontrado dados cadastrais para filial informada"));

		ParceiroLocalEndereco enderecoEmitente = parceiroLocalEnderecoService
				.getEnderecoNFe(empresaFilial.getParceiroLocal().getId());

		ParceiroLocalTelefone telefoneEmitente = parceiroLocalTelefoneService.getTelefoneNFe(empresaFilialId);

		EstadosEnum estado = EstadosEnum.getByCodigoIbge(enderecoEmitente.getCidade().getUf().getCodigo().toString());
		String cnf = String.format("%08d", new Random().nextInt(99999999));
		LocalDateTime dataAtual = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("America/Sao_Paulo"));

		ChaveUtil chaveUtil = new ChaveUtil(estado, empresaFilial.getParceiroLocal().getCpfCnpj(), "55",
				configEmpresaNFe.getSerie(), proximoNumero, configEmpresaNFe.getTipoEmissao().getValue(), cnf,
				LocalDateTime.ofInstant(Instant.now(), ZoneId.of("America/Sao_Paulo")));

		NFe nfe = new NFe();
		nfe.setEmpresaFilial(empresaFilial);
		nfe.setChaveNfe(chaveUtil.getChaveNF());
		nfe.setSituacao(SituacaoDocumento.EMDIGITACAO);
		nfe.setTipoNfe(TipoNfe.PROPRIA);

		NFeIde nfeIde = new NFeIde();
		nfeIde.setCuf(enderecoEmitente.getCidade().getUf().getCodigo().intValue());
		nfeIde.setCnf(Integer.parseInt(cnf));
		nfeIde.setNatop("");
		nfeIde.setMod(55);
		nfeIde.setSerie(configEmpresaNFe.getSerie());
		nfeIde.setNnf(proximoNumero);
		nfeIde.setDhemi(dataAtual);
		nfeIde.setDhsaient(dataAtual);
		nfeIde.setTpimp(Integer.parseInt(TipoImpressaoDanfe.DANFENORMALRETRATO.getCodigoReceita()));
		nfeIde.setTpemis(Integer.parseInt(configEmpresaNFe.getTipoEmissao().getValue()));
		nfeIde.setCdv(Integer.parseInt(chaveUtil.getDigitoVerificador()));
		nfeIde.setTpamb(Integer.parseInt(configEmpresaNFe.getTipoAmbiente().getValue()));
		nfeIde.setProcemi(Integer.parseInt(TipoProcessoEmissao.APLICATIVOPROPRIO.getCodigoReceita()));
		nfeIde.setVerproc(1);
		nfe.setNfeIde(nfeIde);
		nfeIde.setNfe(nfe);

		NFeEmit nfeEmit = new NFeEmit();
		nfeEmit.setNfe(nfe);
		nfeEmit.setCnpj(empresaFilial.getParceiroLocal().getCpfCnpj());
		nfeEmit.setXnome(empresaFilial.getParceiroLocal().getParceiro().getNomeRazaoSocial());
		nfeEmit.setXfant(empresaFilial.getParceiroLocal().getParceiro().getNomeFantasia());
		nfeEmit.setXlgr(enderecoEmitente.getEndereco());
		nfeEmit.setNro(enderecoEmitente.getNumero());
		nfeEmit.setXcpl(enderecoEmitente.getComplemento());
		nfeEmit.setXbairro(enderecoEmitente.getBairro());
		nfeEmit.setCmun(enderecoEmitente.getCidade().getCodigoIBGE().intValue());
		nfeEmit.setXmun(enderecoEmitente.getCidade().getNome());
		nfeEmit.setUf(enderecoEmitente.getCidade().getUf().getSigla());
		nfeEmit.setCep(enderecoEmitente.getCep());
		nfeEmit.setCpais(enderecoEmitente.getCidade().getPais().getCodigoIBGE());
		nfeEmit.setXpais(enderecoEmitente.getCidade().getPais().getNome());
		nfeEmit.setFone(telefoneEmitente != null ? telefoneEmitente.getNumero() : "");
		nfeEmit.setIe(parceiroLocal.getDadosPessoaJuridica().get(0).getInscricaoEstadual());
		nfeEmit.setCrt(configEmpresa.getRegimeTributario().getCodigo());
		nfe.setNfeEmit(nfeEmit);

		nfe = this.save(nfe);

		return NovaNFeResponse.builder().empresaFilialId(empresaFilial.getId())
				.regimeTributarioId(configEmpresa.getRegimeTributario().getId())
				.tipoAmbiente(configEmpresaNFe.getTipoAmbiente().getValue())
				.tipoEmissao(configEmpresaNFe.getTipoEmissao().getValue()).serie(configEmpresaNFe.getSerie())
				.numero(proximoNumero).chaveNFe(chaveUtil.getChaveNF())
				.enderecoEmitente(parceiroLocalEnderecoMapper.toDto(enderecoEmitente)).nfeId(nfe.getId()).build();
	}

	public List<ItemListaNFeResponse> getListaNFe(Long empresaFilialId) {
		return buscaListagemNFeQuery.executeSQL(empresaFilialId);
	}

	public void saveCache(ChacheNFeRequest cacheNFeRequest) {
		CacheNFe cacheNFe = cacheNFeRepository.findByNfeId(cacheNFeRequest.getNfeId()).orElse(new CacheNFe());
		cacheNFe.setNfeId(cacheNFeRequest.getNfeId());
		cacheNFe.setCache(cacheNFeRequest.getCache().getBytes(StandardCharsets.UTF_8));
		cacheNFeRepository.save(cacheNFe);

	}

	public String getCacheNFe(Long cacheId) {
		CacheNFe cacheNFe = cacheNFeRepository.findByNfeId(cacheId)
				.orElse(new CacheNFe());
		if (cacheNFe.getNfeId() == null) {
			return null;
		}
		byte[] cache = cacheNFe.getCache();
		return new String(cache, StandardCharsets.UTF_8);
	}
	
	public List<NFeComunicacaoSEFAZResponse> getNFeComunicacaoSEFAZ(Long nfeId) {
		List<NFeComunicacaoSEFAZ> lista = nFeComunicacaoSEFAZService.findByNFeId(nfeId);
		if (lista == null || lista.size() == 0) {
			return null;
		}
		List<NFeComunicacaoSEFAZResponse> listaResponse = new ArrayList<>();
		lista.forEach(comunicacao -> {
			NFeComunicacaoSEFAZResponse nFeComunicacaoSEFAZResponse = nFeComunicacaoSEFAZMapper.toDto(comunicacao);
			listaResponse.add(nFeComunicacaoSEFAZResponse);
		});
		return listaResponse;
	}

	@Transactional
	public void salvarNFe(NFeRequest nFeRequest) {
		NFe nfe = nfeRepository.findById(nFeRequest.getNfeId()).orElse(new NFe());		nfe = mergeNFeRequestToNFeEntityService.merge(nfe, nFeRequest);
		nfe = this.save(nfe);
		documentoService.criarDocumentoByNFe(nFeRequest, nfe);
	}
	
	@Transactional
	public RetornoNFeIntegracao inutilizar(Long nfeId) {
		RetornoNFeIntegracao retornoNfeIntegracao = null;
		NFe nfe = nfeRepository.findById(nfeId).orElseThrow(() -> new RegisterNotFoundException("Não encontrado nfe para o id informado"));
		
		IServicoInutilizacaoNFe servicoInutilizacaoNFe = context.getBean(IServicoInutilizacaoNFe.class);
		
		retornoNfeIntegracao = servicoInutilizacaoNFe.inutilizarNFe(nfe);
		
		criarNFeComunicacaoSEFAZ(nfe, retornoNfeIntegracao, TipoComunicacaoNfe.INUTILIZACAO);
		
		if (retornoNfeIntegracao.getStatus().equals("102")) {
			nfe.setSituacao(SituacaoDocumento.INUTILIZADO);
			nfe.setXml(retornoNfeIntegracao.getXml().getBytes()); 
			nfe.setNprotnfe(retornoNfeIntegracao.getProtocolo());
    		nfe.setCstat(retornoNfeIntegracao.getStatus());
    		this.save(nfe);
		}
		
		return retornoNfeIntegracao;
	}

	@Transactional
	public RetornoNFeIntegracao cancelarNFe(Long nfeId) {
		TransactionStatus status = this.criaNovaTransacao(transactionManager);
		boolean atualizaIntegracoes = false;
		RetornoNFeIntegracao retornoNfeIntegracao = null;
		NFe nfe = nfeRepository.findById(nfeId).orElseThrow(() -> new RegisterNotFoundException("Não encontrado nfe para o id informado"));
		
		Documento documento = documentoService.findByNFeId(nfe.getId());
		if (documento == null) {
			throw new BusinessException("Não encontrado o Documento para a NFe com ID: "+nfeId);
		}
		
		validacoesCancelamentoNFe(documento);
		
		IServicoCancelamentoNFe servicoCancelamentoNFe = context.getBean(IServicoCancelamentoNFe.class);
		
		try {
			retornoNfeIntegracao = servicoCancelamentoNFe.cancelarNFe(nfe);
			
			criarNFeComunicacaoSEFAZ(nfe, retornoNfeIntegracao, TipoComunicacaoNfe.CANCELAMENTO);
			
			if (retornoNfeIntegracao.getStatus().equals("101")) {
				nfe.setSituacao(SituacaoDocumento.CANCELADO);
				nfe.setCstat(retornoNfeIntegracao.getStatus());
				this.save(nfe);
				if (criouIntegracoesCancelamento(nfe)) {
					atualizaIntegracoes = true;					
				}
			}	
			
        	transactionManager.commit(status);
        	
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw new BusinessException(e.getMessage());
		}
		
		if (atualizaIntegracoes) {
			integrarEstoque(nfe, true);
        	integrarFinanceiro(nfe, true);
		}
		
		return retornoNfeIntegracao;
	}

	private void validacoesCancelamentoNFe(Documento documento) {
		if (documento.getTitulo() != null) {
			this.validarSeTituloEstaPendente(documento.getTitulo());			
		}
	}

	private boolean criouIntegracoesCancelamento(NFe nfe) {
		Documento documento = documentoService.findByNFeId(nfe.getId());
		if (documento != null) {
			boolean integracoes = false;
			if (documento.getOperacaoInterna().isCaracteristicaEstoque()) {
				DocumentoIntegracao documentoIntegracao = new DocumentoIntegracao();
				documentoIntegracao.setDocumento(documento);
				documentoIntegracao.setTipoIntegracao(TipoIntegracao.ESTOQUE);
				documentoIntegracao.setIntegrado(false);
				documentoIntegracao.setCancelamento(true);
				documentoIntegracaoRepository.save(documentoIntegracao);
				integracoes = true;
			}
			if (documento.getOperacaoInterna().isCaracteristicaFinanceira()) {
				DocumentoIntegracao documentoIntegracao = new DocumentoIntegracao();
				documentoIntegracao.setDocumento(documento);
				documentoIntegracao.setTipoIntegracao(TipoIntegracao.FINANCEIRO);
				documentoIntegracao.setIntegrado(false);
				documentoIntegracao.setCancelamento(true);
				documentoIntegracaoRepository.save(documentoIntegracao);
				integracoes = true;
			}
			return integracoes;
		}
		return false;
	}
	
	@Transactional
	public String consultarStatusNFe(Long nfeId) {
		TransactionStatus status = this.criaNovaTransacao(transactionManager);
		boolean atualizaIntegracoes = false;
		
		NFe nfe = nfeRepository.findById(nfeId).orElseThrow(() -> new RegisterNotFoundException("Não encontrado nfe para o id informado"));
		
		try {
			IServicoConsultaStatusNFe servicoConsultaStatusNFe = context.getBean(IServicoConsultaStatusNFe.class);
			
			TRetConsSitNFe retornoConsulta = servicoConsultaStatusNFe.consultarStatusNFe(nfe);
			
			RetornoNFeIntegracao retornoNFeIntegracao = new RetornoNFeIntegracao();
			retornoNFeIntegracao.setStatus(retornoConsulta.getCStat());
			if (retornoConsulta.getProtNFe() != null) {
				retornoNFeIntegracao.setProtocolo(retornoConsulta.getProtNFe().getInfProt().getNProt());
			} else if (retornoConsulta.getProcEventoNFe() != null) {
				retornoNFeIntegracao.setProtocolo(retornoConsulta.getProcEventoNFe().get(0).getRetEvento().getInfEvento().getNProt());				
			}
			retornoNFeIntegracao.setXmlEnvio(montaXMLEnvio(nfe, retornoConsulta.getTpAmb()));
			retornoNFeIntegracao.setXmlRetorno(montaXMLRetorno(retornoConsulta));
			retornoNFeIntegracao.setErroValidarRetorno(retornoConsulta.getXMotivo());
			
			criarNFeComunicacaoSEFAZ(nfe, retornoNFeIntegracao, TipoComunicacaoNfe.CONSULTA_STATUS);
			
			if (retornoConsulta.getCStat().equals("100") && !nfe.getSituacao().equals(SituacaoDocumento.AUTORIZADO)) {
				nfe.setSituacao(SituacaoDocumento.AUTORIZADO);
				nfe.setXmotivo(retornoConsulta.getXMotivo()); 
				nfe.setNprotnfe(retornoNFeIntegracao.getProtocolo());
				this.save(nfe);
				atualizaIntegracoes = true;
			}
			if (retornoConsulta.getCStat().equals("101") && !nfe.getSituacao().equals(SituacaoDocumento.CANCELADO)) {
				nfe.setSituacao(SituacaoDocumento.CANCELADO);
				nfe.setCstat(retornoConsulta.getCStat());
				this.save(nfe);
				if (criouIntegracoesCancelamento(nfe)) {
					atualizaIntegracoes = true;					
				}
			}
			if (retornoConsulta.getCStat().equals("102") && !nfe.getSituacao().equals(SituacaoDocumento.INUTILIZADO)) {
				nfe.setSituacao(SituacaoDocumento.INUTILIZADO); 
				nfe.setNprotnfe(retornoNFeIntegracao.getProtocolo());
	    		nfe.setCstat(retornoConsulta.getCStat());
	    		this.save(nfe);
			}
			
	    	transactionManager.commit(status);
	    	
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw new BusinessException(e.getMessage());
		}
		
        if (atualizaIntegracoes) {
        	integrarEstoque(nfe, nfe.getSituacao().equals(SituacaoDocumento.AUTORIZADO) ? false : true);
        	integrarFinanceiro(nfe, nfe.getSituacao().equals(SituacaoDocumento.AUTORIZADO) ? false : true);
        }
        
        return nfe.getSituacao().getName();
		
	}

	private String montaXMLRetorno(TRetConsSitNFe retornoConsulta) {
		try {
			String xmlRetorno = XmlNfeUtil.objectToXml(retornoConsulta, Charset.defaultCharset());
			return xmlRetorno;
		} catch (JAXBException | NfeException e) {
			return "Erro ao recuperar o XML de Retorno da Consulta de Status da NFe.";
		}
	}

	private String montaXMLEnvio(NFe nfe, String tpAmbiente) {
		String chaveNFe = nfe.getChaveNfe();
		if (chaveNFe.startsWith("NFe")) {
			chaveNFe = chaveNFe.replaceFirst("^NFe", "");
        }		
		
        TConsSitNFe consSitNFe = new TConsSitNFe();
        consSitNFe.setVersao(ConstantesUtil.VERSAO.NFE);
        consSitNFe.setTpAmb(tpAmbiente);
        consSitNFe.setXServ("CONSULTAR");
        consSitNFe.setChNFe(chaveNFe);

		try {
			String xml = XmlNfeUtil.objectToXml(consSitNFe, Charset.defaultCharset());
			return xml;
		} catch (JAXBException | NfeException e) {
			return "Erro ao recuperar o XML do Envio da Consulta de Status da NFe.";
		}
	}

	public byte[] enviarNFe(Long nfeId) {
		RetornoNFeIntegracao retornoNfeIntegracao = enviar(nfeId);
//		if (StringUtils.isNotBlank(retornoNfeIntegracao.getXml())) {
//			IServicoImpressaoNFe servicoImpressaoNFe = context.getBean(IServicoImpressaoNFe.class);
//			return servicoImpressaoNFe.imprimeParaBytes(retornoNfeIntegracao.getXml());
//		}
		return null;
	}
	
	private RetornoNFeIntegracao enviar(Long nfeId) {
		TransactionStatus status = this.criaNovaTransacao(transactionManager);
		boolean atualizaIntegracoes = false;
		NFe nfe = null;
		RetornoNFeIntegracao retornoNfeIntegracao = null;
        try {
        	nfe = nfeRepository.findById(nfeId).orElseThrow(() -> new RegisterNotFoundException("Não encontrado nfe para o id informado"));
        	IServicoEnvioNFe servicoIntegracaoNFe = context.getBean(IServicoEnvioNFe.class);
        	retornoNfeIntegracao = servicoIntegracaoNFe.enviarNFe(nfe);
			
        	criarNFeComunicacaoSEFAZ(nfe, retornoNfeIntegracao, TipoComunicacaoNfe.ENVIO);
        	
    		if (retornoNfeIntegracao.getStatus().equals("100")) {
    			nfe.setSituacao(SituacaoDocumento.AUTORIZADO);
    			nfe.setXml(retornoNfeIntegracao.getXml().getBytes()); 
    			nfe.setNprotnfe(retornoNfeIntegracao.getProtocolo());
    			atualizaIntegracoes = true;
    		} else {
    			nfe.setSituacao(SituacaoDocumento.REJEITADO);
    			nfe.setXmotivo(retornoNfeIntegracao.getErroValidarRetorno());
    		}
    		nfe.setCstat(retornoNfeIntegracao.getStatus());
    		this.save(nfe);
			
        	transactionManager.commit(status);
        	
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		}
        
        if (atualizaIntegracoes) {
        	integrarEstoque(nfe, false);
        	integrarFinanceiro(nfe, false);
        }
        
        return retornoNfeIntegracao;
	}
	
	private void integrarEstoque (NFe nfe, Boolean cancelar) {
		Documento documento = documentoService.findByNFeId(nfe.getId());
		if (documento == null || documento.getAtualizaEstoque() == null || (documento.getAtualizaEstoque().isIntegrado() && !cancelar)) {
			return;
		}
		
		TransactionStatus status = this.criaNovaTransacao(transactionManager);
		try {
			AtualizaEstoqueRequest atuaEstoqueRequest = criaAtualizaEstoqueRequest(documento, cancelar);
			if (atuaEstoqueRequest != null) {
				estoqueService.atualizar(atuaEstoqueRequest);
				LocalDateTime dataAtual = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("America/Sao_Paulo"));
				DocumentoIntegracao documentoIntegracao = documento.getAtualizaEstoque();
				documentoIntegracao.setIntegrado(true);
				documentoIntegracao.setDataIntegracao(dataAtual);
				documentoIntegracaoRepository.save(documentoIntegracao);
			}
			
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		}
	}
	
	private AtualizaEstoqueRequest criaAtualizaEstoqueRequest(Documento documento, Boolean cancelar) {
		
		OperacaoInternaEstoque operacaoInternaEstoque = operacaoInternaEstoqueService.getByOperacaoInterna(documento.getOperacaoInterna().getId());
		if (operacaoInternaEstoque == null || operacaoInternaEstoque.getGrupoContabil() == null || operacaoInternaEstoque.getLocalEstoque() == null) {
			return null;
		}

		AtualizaEstoqueRequest atualizaEstoqueRequest = new AtualizaEstoqueRequest();
		atualizaEstoqueRequest.setEmpresaFilialId(documento.getDepartamento().getEmpresaFilial().getId());
		atualizaEstoqueRequest.setGrupoContabilId(operacaoInternaEstoque.getGrupoContabil().getId());
		atualizaEstoqueRequest.setLocalEstoqueId(operacaoInternaEstoque.getLocalEstoque().getId());
		atualizaEstoqueRequest.setOperacaoInternaId(documento.getOperacaoInterna().getId());
		atualizaEstoqueRequest.setEntrada(documento.getOperacaoInterna().getNaturezaOperacao().getIndicadorOperacao().getValue() == "E" ? true : false);
		if (cancelar) {
			atualizaEstoqueRequest.setTipoMovimentoEstoque(TipoMovimentoEstoque.CANCELAMENTO.getValue());
			atualizaEstoqueRequest.setEntrada(atualizaEstoqueRequest.isEntrada() ? false : true);
		} else {
			atualizaEstoqueRequest.setTipoMovimentoEstoque(TipoMovimentoEstoque.INCLUSAO.getValue());			
		}
		atualizaEstoqueRequest.setData(documento.getDataDocumento());
		atualizaEstoqueRequest.setDocumento(documento.getNumero().toString());
		atualizaEstoqueRequest.setTipoDocumentoEstoque(TipoDocumentoEstoque.NFE.getValue());
		atualizaEstoqueRequest.setProtocoloDocumento(documento.getId());
			
		atualizaEstoqueRequest.setListaItens(incluiItensParaAtualizarEstoque(documento));
		
		return atualizaEstoqueRequest;
	}

	private List<AtualizaEstoqueItensRequest> incluiItensParaAtualizarEstoque(Documento documento) {
		List<AtualizaEstoqueItensRequest> listaItens = new ArrayList<>();
		
		for (DocumentoItem item : documento.getItens()) {
			AtualizaEstoqueItensRequest novoItem = new AtualizaEstoqueItensRequest();
			novoItem.setCustoMedio(item.getValorUnitario());
			novoItem.setItemId(item.getItem().getId());
			novoItem.setQuantidade(item.getQuantidade());
			novoItem.setValor(item.getValorLiquido());
			
			listaItens.add(novoItem);
		}
		
		return listaItens;
	}

	private void integrarFinanceiro (NFe nfe, Boolean cancelar) {
		Documento documento = documentoService.findByNFeId(nfe.getId());
		if (documento == null || documento.getAtualizaFinanceiro() == null || documento.getAtualizaFinanceiro().isIntegrado()) {
			return;
		}
		
		OperacaoInternaFinanceiro operacaoInternaFinanceiro = operacaoInternaFinanceiroService.getByOperacaoInterna(documento.getOperacaoInterna().getId());
		
		TransactionStatus status = this.criaNovaTransacao(transactionManager);
		try {
			if (cancelar) {
				estornarTitulo(nfe, documento, operacaoInternaFinanceiro);
			} else {
				incluiTitulo(documento, operacaoInternaFinanceiro);				
			}
			
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		}
	}

	private void estornarTitulo(NFe nfe, Documento documento, OperacaoInternaFinanceiro operacaoInternaFinanceiro) {
		ConfigSistema configSistema = configSistemaService.getById(1L).get();
		OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiroInclusao = configSistema.getConfiguracoesFinanceiro().get(0)
				.getOperacaoMovimentoFinanceiroInclusao();
		
		LocalDate dataAtual = LocalDate.ofInstant(Instant.now(), ZoneId.of("America/Sao_Paulo"));
		BaixaEstornoRequest estorno = new BaixaEstornoRequest();
		estorno.setDataMovimento(dataAtual);
		estorno.setDepartamentoId(documento.getDepartamento().getId());
		estorno.setObservacao("Foi realizado o cancelamento da NFe");
		estorno.setMotivoEstornoFinanceiroId(operacaoInternaFinanceiro.getMotivoEstornoFinanceiro().getId());
		
		this.validarSeTituloEstaPendente(documento.getTitulo());
		
		List<SelecionadosEstornoBaixaRequest> titulos = new ArrayList<SelecionadosEstornoBaixaRequest>();
		List<ParcelaFinanceiro> parcelas = parcelaRepository.findByTituloIdOrderByNumParcelaAscSeqParcelaAsc(documento.getTitulo().getId());
		parcelas.forEach(parcela -> {
			SelecionadosEstornoBaixaRequest titulo = new SelecionadosEstornoBaixaRequest();
			List<MovimentoFinanceiro> movimentos = movimentoRepository.buscarListaMovimentoUltimaSequencia(parcela.getId());
			movimentos.forEach(movimento -> {
				if (movimento.getOperacaoMovimentoFinanceiro().getId().equals(operacaoMovimentoFinanceiroInclusao.getId())) {
					titulo.setMovimentoFinanceiroId(movimento.getId());
					return;
				}
			});
			titulo.setParcelaFinanceiraId(parcela.getId());
			titulo.setTituloId(documento.getTitulo().getId());
			titulo.setValorMovimento(parcela.getValorParcela());
			titulos.add(titulo);
		});
		
		estorno.setBaixasSelecionadas(titulos);
		
		TipoOperacaoFinanceira tipoOperacaoFinanceira = configSistema.getConfiguracoesFinanceiro().get(0)
				.getTipoOperacaoFinanceiraEstorno();

		OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro = configSistema.getConfiguracoesFinanceiro().get(0)
				.getOperacaoMovimentoFinanceiroInclusao();


		ServicoIntegracaoFinanceira servicoIntegracaoFinanceira = context.getBean(ServicoIntegracaoFinanceira.class);
		servicoIntegracaoFinanceira.iniciarIntegracao(TipoOperacaoIntegracaoFinanceira.ESTORNO);
		servicoIntegracaoFinanceira.definirOperacao(tipoOperacaoFinanceira, documento.getValor(), documento.getDepartamento(),
				estorno.getDataMovimento(), estorno.getObservacao(),
				estorno.getMotivoEstornoFinanceiroId(), null);

		estorno.getBaixasSelecionadas().forEach(baixa -> {

			OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira = configSistema.getConfiguracoesFinanceiro().get(0)
					.getOperacaoAcessoriaFinanceiraPrincipal();

			servicoIntegracaoFinanceira.atribuiMovimentosParaEstorno(operacaoMovimentoFinanceiro,
					operacaoAcessoriaFinanceira, baixa.getMovimentoFinanceiroId());

		});

		servicoIntegracaoFinanceira.executarIntegracao();
		
		LocalDateTime dataHoraAtual = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("America/Sao_Paulo"));
		DocumentoIntegracao documentoIntegracao = documento.getAtualizaFinanceiro();
		documentoIntegracao.setIntegrado(true);
		documentoIntegracao.setDataIntegracao(dataHoraAtual);
		documentoIntegracaoRepository.save(documentoIntegracao);
	}

	private void validarSeTituloEstaPendente(Titulo titulo) {
		servicoIntegracaoFinanceira.validarBaixaPorCancelamento(titulo.getId());		
	}

	private void incluiTitulo(Documento documento, OperacaoInternaFinanceiro operacaoInternaFinanceiro) {
		ConfigSistema configSistema = configSistemaService.getById(1L).get();
		TipoOperacaoFinanceira tipoOperacaoFinanceira = configSistema.getConfiguracoesFinanceiro().get(0)
				.getTipoOperacaoFinanceiraLancamento();
		TipoTitulo tipoTitulo = operacaoInternaFinanceiro.getTipoTitulo();
		GrupoFinanceiro grupoFinanceiro = operacaoInternaFinanceiro.getGrupoFinanceiro();
		CaracteristicaMovimentoFinanceiro caracteristicaMovimentoFinanceiro = operacaoInternaFinanceiro.getCaracteristicaMovimentoFinanceiro();
		HistoricoPadrao historicoPadrao = operacaoInternaFinanceiro.getHistoricoPadrao();
		OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro = configSistema.getConfiguracoesFinanceiro().get(0)
				.getOperacaoMovimentoFinanceiroInclusao();
		OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira = configSistema.getConfiguracoesFinanceiro().get(0)
				.getOperacaoAcessoriaFinanceiraPrincipal();

		servicoIntegracaoFinanceira.iniciarIntegracao(TipoOperacaoIntegracaoFinanceira.INCLUSAOPORLANCAMENTO);
		String observacao = "Inclusão através da emissão da NFe nº " + documento.getNumero();
		servicoIntegracaoFinanceira.definirOperacao(tipoOperacaoFinanceira, documento.getValor(),
				documento.getDepartamento(), documento.getDataDocumento(), observacao, null,
				operacaoInternaFinanceiro.getCarteiraFinanceira());
		servicoIntegracaoFinanceira.atribuiValoresTitulo(tipoTitulo,
				documento.getDepartamento().getEmpresaFilial(), documento.getDepartamento(),
				grupoFinanceiro, documento.getParceiroLocal(), operacaoInternaFinanceiro.getFatoGerador(),
				caracteristicaMovimentoFinanceiro, historicoPadrao, operacaoInternaFinanceiro.getCarteiraFinanceira(),
				String.valueOf(documento.getNumero()), observacao, documento.getDataDocumento(),
				String.valueOf(documento.getNumero()), documento.getValor(), null,
				NossoNumeroGenerator.gerarNossoNumero());
		documento.getParcelas().forEach(parc -> {
			servicoIntegracaoFinanceira.atribuiValoresParcela(parc.getNumero(), parc.getDataVencimento(),
					parc.getValor());

			servicoIntegracaoFinanceira.atribuiValoresMovimento(parc.getNumero(), 1, 1, tipoOperacaoFinanceira,
					operacaoMovimentoFinanceiro, operacaoAcessoriaFinanceira, operacaoInternaFinanceiro.getCarteiraFinanceira(),
					grupoFinanceiro, parc.getValor(), documento.getDataDocumento(),
					parc.getValor(), null);
		});
		Titulo titulo = servicoIntegracaoFinanceira.executarIntegracao();
		
		documento.setTitulo(titulo);
		documentoRepository.save(documento);

		LocalDateTime dataAtual = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("America/Sao_Paulo"));
		DocumentoIntegracao documentoIntegracao = documento.getAtualizaFinanceiro();
		documentoIntegracao.setIntegrado(true);
		documentoIntegracao.setDataIntegracao(dataAtual);
		documentoIntegracaoRepository.save(documentoIntegracao);
	}
	
	public NFeComunicacaoSEFAZ criarNFeComunicacaoSEFAZ(NFe nfe, RetornoNFeIntegracao retornoNFeIntegracao, TipoComunicacaoNfe tipoComunicacaoNfe) {
		NFeComunicacaoSEFAZ nFeComunicacaoSEFAZ = new NFeComunicacaoSEFAZ();
		nFeComunicacaoSEFAZ.setNfe(nfe);
		nFeComunicacaoSEFAZ.setTipoComunicacaoNfe(tipoComunicacaoNfe);
		nFeComunicacaoSEFAZ.setCstat(retornoNFeIntegracao.getStatus());
		nFeComunicacaoSEFAZ.setNprotnfe(retornoNFeIntegracao.getProtocolo());
		nFeComunicacaoSEFAZ.setNrecibo(retornoNFeIntegracao.getRecibo());
		nFeComunicacaoSEFAZ.setStatus("Integrado");
		nFeComunicacaoSEFAZ.setXmlEnvio(retornoNFeIntegracao.getXmlEnvio().getBytes());
		nFeComunicacaoSEFAZ.setXmlRetorno(retornoNFeIntegracao.getXmlRetorno().getBytes());
		nFeComunicacaoSEFAZ.setXmotivo(retornoNFeIntegracao.getErroValidarRetorno());
		return nFeComunicacaoSEFAZRepository.save(nFeComunicacaoSEFAZ);
	}

	public void integrarNFe(Long nfeId) {
		NFe nfe = nfeRepository.findById(nfeId).orElseThrow(() -> new RegisterNotFoundException("Não encontrado nfe para o id informado"));
		if (nfe.getSituacao() == SituacaoDocumento.AUTORIZADO) {
			integrarEstoque(nfe, false);
			integrarFinanceiro(nfe, false);
		} else if (nfe.getSituacao() == SituacaoDocumento.CANCELADO) {
			integrarEstoque(nfe, true);
			integrarFinanceiro(nfe, true);
		}
	}
	
	private TransactionStatus criaNovaTransacao(PlatformTransactionManager transactionManager) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
      
        TransactionStatus status = transactionManager.getTransaction(def);
		return status;
	}

}
