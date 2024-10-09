package br.com.ultraworks.erp.api.fiscal.service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.util.ChaveUtil;
import br.com.ultraworks.erp.api.configuracao.domain.configempresa.ConfigEmpresa;
import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFe;
import br.com.ultraworks.erp.api.configuracao.service.ConfigEmpresaNFeService;
import br.com.ultraworks.erp.api.configuracao.service.ConfigEmpresaService;
import br.com.ultraworks.erp.api.configuracao.service.ControleNumeracaoService;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.cache.CacheNFe;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.cache.ChacheNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFe;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeEmit;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.entity.NFeIde;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.NFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.response.ItemListaNFeResponse;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.response.NovaNFeResponse;
import br.com.ultraworks.erp.api.fiscal.domain.tipoimpressaodanfe.TipoImpressaoDanfe;
import br.com.ultraworks.erp.api.fiscal.domain.tipoprocessoemissao.TipoProcessoEmissao;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.IServicoEnvioNFe;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.IServicoImpressaoNFe;
import br.com.ultraworks.erp.api.fiscal.integrator.nfe.dto.RetornoNFeIntegracao;
import br.com.ultraworks.erp.api.fiscal.repository.CacheNFeRepository;
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
import br.com.ultraworks.erp.api.tabela.domain.situacaodocumento.SituacaoDocumento;
import br.com.ultraworks.erp.api.tabela.domain.tipodocumento.TipoDocumento;
import br.com.ultraworks.erp.api.tabela.service.TipoDocumentoService;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;

@Service
public class NfeService {

	private ResourceLoader resourceLoader;
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
	
	@Autowired
    private PlatformTransactionManager transactionManager;

	@Autowired
	public NfeService(ResourceLoader resourceLoader, ApplicationContext context, EmpresaFilialService empresaFilialService, NFeRepository nfeRepository,
			ConfigEmpresaService configEmpresaService, ConfigEmpresaNFeService configEmpresaNFeService,
			TipoDocumentoService tipoDocumentoService, ControleNumeracaoService controleNumeracaoService,
			ParceiroLocalEnderecoService parceiroLocalEnderecoService,
			ParceiroLocalEnderecoMapper parceiroLocalEnderecoMapper,
			ParceiroLocalTelefoneService parceiroLocalTelefoneService, ParceiroLocalService parceiroLocalService,
			BuscaListagemNFeQuery buscaListagemNFeQuery, CacheNFeRepository cacheNFeRepository,
			MergeNFeRequestToNFeEntityService mergeNFeRequestToNFeEntityService) {
		this.resourceLoader = resourceLoader;
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
	}

//	public NfeService(
//			ApplicationContext context
//			, ResourceLoader resourceLoader,
//			EmpresaFilialService empresaFilialService
//			TipoDocumentoService tipoDocumentoService,
//			ParceiroLocalService parceiroLocalService, ConfigEmpresaService configEmpresaService,
//			ConfigEmpresaNFeService configEmpresaNFeService, ControleNumeracaoService controleNumeracaoService,
//			ParceiroLocalEnderecoService parceiroLocalEnderecoService, ParceiroLocalEnderecoMapper parceiroLocalEnderecoMapper
//	) {
//		this.context = context;
//		this.resourceLoader = resourceLoader;
//		this.empresaFilialService = empresaFilialService;
//		this.tipoDocumentoService = tipoDocumentoService;
//		this.parceiroLocalService = parceiroLocalService;
//		this.configEmpresaService = configEmpresaService;
//		this.configEmpresaNFeService = configEmpresaNFeService;
//		this.controleNumeracaoService = controleNumeracaoService;
//		this.parceiroLocalEnderecoService = parceiroLocalEnderecoService;
//		this.parceiroLocalEnderecoMapper = parceiroLocalEnderecoMapper;
//	}

//	public byte[] gerarDanfeTeste() {
//		IServicoImpressaoNFe servicoImpressaoNFe = context.getBean(IServicoImpressaoNFe.class);
//		try {
//			Resource resource = resourceLoader.getResource("classpath:demos");
//			String path = resource.getFile().getAbsolutePath();
//			String xml = ImpressaoUtil.leArquivo(path.concat("/nfeteste.xml"));
//			return servicoImpressaoNFe.imprimeParaBytes(xml);
//		} catch (IOException e) {
//			return null;
//		}
//	}

//	public byte[] gerarNFeTeste(Long empresaLocalId) {
//		IServicoEnvioNFe servicoIntegracaoNFe = context.getBean(IServicoEnvioNFe.class);
//		RetornoNFeIntegracao retornoNfeIntegracao = servicoIntegracaoNFe
//				.emitirNFe(montarContainerIntegracaoNFe(empresaLocalId));
//		if (StringUtils.isNotBlank(retornoNfeIntegracao.getXml())) {
//			IServicoImpressaoNFe servicoImpressaoNFe = context.getBean(IServicoImpressaoNFe.class);
//			return servicoImpressaoNFe.imprimeParaBytes(retornoNfeIntegracao.getXml());
//		}
//		return null;
//	}

//	private ContainerIntegracaoNFe montarContainerIntegracaoNFe(Long empresaFilialId) {
//		EmpresaFilial empresaFilial = empresaFilialService.getById(empresaFilialId).get();
//		ParceiroLocal parceiroLocalEmitente = parceiroLocalService.getById(empresaFilial.getParceiroLocal().getId())
//				.get();
//		return ContainerIntegracaoNFe.builder().empresaFilialEmissorId(empresaFilialId)
//				.nfeIdentificacao(montaNfeIdentificacao(parceiroLocalEmitente)).nfeDestinatario(montaNfeDestinatario())
//				.nfeItens(montaNfeItens()).build();
//	}

//	private List<ContainerIntegracaoNFeItem> montaNfeItens() {
//		List<ContainerIntegracaoNFeItem> listaItens = new ArrayList<>();
//
//		ContainerIntegracaoNFeItem nfeItem = ContainerIntegracaoNFeItem.builder()
//
//				.build();
//
//		listaItens.add(nfeItem);
//		return listaItens;
//	}

//	private ContainerIntegracaoNFeDestinatario montaNfeDestinatario() {
//		ParceiroLocal parceiroLocal = parceiroLocalService.findByCpfCnpj("19560114000114");
//		return ContainerIntegracaoNFeDestinatario.builder().parceiroLocalEndereco(parceiroLocal.getEnderecos().get(0))
//				.indicadorIEDestinatario(IndicadorIEDestinatario.NAOCONTRIBUINTE).build();
//	}

//	private ContainerIntegracaoNFeIdentificacao montaNfeIdentificacao(ParceiroLocal parceiroLocalEmitente) {
//		return ContainerIntegracaoNFeIdentificacao.builder()
//				.tipoDocumento(tipoDocumentoService.findByCodigoReceita("55")).serie(1).numeroNFe(1)
//				.naturezaOperacao("Venda").dataHoraSaidaEntrada(LocalDateTime.now())
//				.indicadorOperacao(IndicadorOperacao.SAIDA).localDestinoOperacao(DestinoOperacao.ESTADUAL)
//				.municipioOcorrenciaFatoGeradorICMS(parceiroLocalEmitente.getEnderecos().get(0).getCidade())
//				.tipoImpressaoDanfe(TipoImpressaoDanfe.DANFENORMALRETRATO).finalidadeNfe(FinalidadeNfe.NORMAL)
//				.tipoConsumidor(TipoConsumidor.FINAL).tipoPresencaComprador(TipoPresencaComprador.PRESENCIAL)
//				.processoEmissao(TipoProcessoEmissao.APLICATIVOPROPRIO).build();
//	}

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
				.orElseThrow(() -> new RegisterNotFoundException("Não foi encontrada cache para nfe informada"));
		byte[] cache = cacheNFe.getCache();
		return new String(cache, StandardCharsets.UTF_8);
	}

	@Transactional
	public void salvarNFe(NFeRequest nFeRequest) {
		NFe nfe = nfeRepository.findById(nFeRequest.getNfeId()).orElse(new NFe());		nfe = mergeNFeRequestToNFeEntityService.merge(nfe, nFeRequest);
		nfe = this.save(nfe);
	}

	
	public byte[] enviarNFe(Long nfeId) {
		RetornoNFeIntegracao retornoNfeIntegracao = enviar(nfeId);
		if (StringUtils.isNotBlank(retornoNfeIntegracao.getXml())) {
			IServicoImpressaoNFe servicoImpressaoNFe = context.getBean(IServicoImpressaoNFe.class);
			return servicoImpressaoNFe.imprimeParaBytes(retornoNfeIntegracao.getXml());
		}
		return null;
	}
	
	private RetornoNFeIntegracao enviar(Long nfeId) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
      
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
        	NFe nfe = nfeRepository.findById(nfeId).orElseThrow(() -> new RegisterNotFoundException("Não encontrado nfe para o id informado"));
        	IServicoEnvioNFe servicoIntegracaoNFe = context.getBean(IServicoEnvioNFe.class);
        	RetornoNFeIntegracao retornoNfeIntegracao =  servicoIntegracaoNFe.enviarNFe(nfe);
        	
    		if (retornoNfeIntegracao.getErroValidarRetorno() == null && retornoNfeIntegracao.getStatus().equals("100")) {
    			nfe.setSituacao(SituacaoDocumento.AUTORIZADO);
    			nfe.setCstat(retornoNfeIntegracao.getStatus());
    			nfe.setXml(retornoNfeIntegracao.getXml().getBytes()); 
    			nfe.setNprotnfe(retornoNfeIntegracao.getProtocolo());
    		} else {
    			nfe.setSituacao(SituacaoDocumento.REJEITADO);
    			nfe.setXmotivo(retornoNfeIntegracao.getErroValidarRetorno());
    		}
    		this.save(nfe);
			
        	transactionManager.commit(status);
        	
        	return retornoNfeIntegracao;
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		}
        
	}

}
