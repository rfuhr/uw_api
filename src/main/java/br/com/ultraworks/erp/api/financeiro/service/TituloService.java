package br.com.ultraworks.erp.api.financeiro.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistema;
import br.com.ultraworks.erp.api.configuracao.service.ConfigSistemaService;
import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.conta.Conta;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.parcela.ParcelaFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.Titulo;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.TituloDTO;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.BaixaEstornoRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.BaixaTituloRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.LancamentoTituloRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.NegociacaoRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.NovasParcelasNegociacaoRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SelecaoBaixaTituloRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SelecaoConsultaTituloRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SelecaoEstornoTituloRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SelecaoNegociacaoRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SelecaoSubstituicaoCarteiraRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SelecionadosBaixaTituloRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SelecionadosEstornoBaixaRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SelecionadosNegociacaoRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SelecionadosSubstituicaoCarteiraRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SubstituicaoCarteiraRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.response.SelecaoBaixaResponse;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.response.SelecaoConsultaResponse;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.response.SelecaoEstornoResponse;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.response.SelecaoNegociacaoResponse;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.response.SelecaoSubstituicaoCarteiraResponse;
import br.com.ultraworks.erp.api.financeiro.integrator.ServicoIntegracaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.integrator.TipoOperacaoIntegracaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.mapper.TituloMapper;
import br.com.ultraworks.erp.api.financeiro.repository.MovimentoRepository;
import br.com.ultraworks.erp.api.financeiro.repository.ParcelaRepository;
import br.com.ultraworks.erp.api.financeiro.repository.TituloRepository;
import br.com.ultraworks.erp.api.financeiro.repository.query.SelecaoBaixaTituloQuery;
import br.com.ultraworks.erp.api.financeiro.repository.query.SelecaoConsultaTituloQuery;
import br.com.ultraworks.erp.api.financeiro.repository.query.SelecaoEstornoTituloQuery;
import br.com.ultraworks.erp.api.financeiro.repository.query.SelecaoNegociacaoQuery;
import br.com.ultraworks.erp.api.financeiro.repository.query.SelecaoSubstituicaoCarteiraQuery;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.organograma.service.DepartamentoService;
import br.com.ultraworks.erp.api.organograma.service.EmpresaFilialService;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalService;
import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadrao;
import br.com.ultraworks.erp.api.tabela.service.HistoricoPadraoService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.util.NossoNumeroGenerator;

@Service
public class TituloService {

	private final ApplicationContext context;
	private TituloRepository tituloRepository;
	private ParcelaRepository parcelaRepository;
	private MovimentoRepository movimentoRepository;
	private TituloMapper tituloMapper;
	private EmpresaFilialService empresaFilialService;
	private DepartamentoService departamentoService;
	private TipoTituloService tipoTituloService;
	private GrupoFinanceiroService grupoFinanceiroService;
	private ParceiroLocalService parceiroLocalService;
	private FatoGeradorService fatoGeradorService;
	private CaracteristicaMovimentoFinanceiroService caracteristicaMovimentoFinanceiroService;
	private HistoricoPadraoService historicoPadraoService;
	private TipoOperacaoFinanceiraService tipoOperacaoFinanceiraService;
	private OperacaoAcessoriaFinanceiraService operacaoAcessoriaFinanceiraService;
	private CarteiraFinanceiraService carteiraFinanceiraService;
	private ContaService contaService;
	private SelecaoBaixaTituloQuery selecaoBaixaTituloQuery;
	private SelecaoEstornoTituloQuery selecaoEstornoTituloQuery;
	private SelecaoConsultaTituloQuery selecaoConsultaTituloQuery;
	private SelecaoSubstituicaoCarteiraQuery selecaoSubstituicaoCarteiraQuery;
	private SelecaoNegociacaoQuery selecaoNegociacaoQuery;
	private ConfigSistemaService configSistemaService;

	@Autowired
	public TituloService(ApplicationContext context, TituloRepository tituloRepository, TituloMapper tituloMapper,
			ParcelaRepository parcelaRepository, MovimentoRepository movimentoRepository,
			EmpresaFilialService empresaFilialService, DepartamentoService departamentoService,
			TipoTituloService tipoTituloService, GrupoFinanceiroService grupoFinanceiroService,
			ParceiroLocalService parceiroLocalService, FatoGeradorService fatoGeradorService,
			CaracteristicaMovimentoFinanceiroService caracteristicaMovimentoFinanceiroService,
			HistoricoPadraoService historicoPadraoService, TipoOperacaoFinanceiraService tipoOperacaoFinanceiraService,
			OperacaoMovimentoFinanceiroService operacaoMovimentoFinanceiroService,
			OperacaoAcessoriaFinanceiraService operacaoAcessoriaFinanceiraService,
			CarteiraFinanceiraService carteiraFinanceiraService, ContaService contaService,
			SelecaoEstornoTituloQuery selecaoEstornoTituloQuery, SelecaoBaixaTituloQuery selecaoBaixaTituloQuery,
			SelecaoConsultaTituloQuery selecaoConsultaTituloQuery,
			SelecaoSubstituicaoCarteiraQuery selecaoSubstituicaoCarteiraQuery,
			SelecaoNegociacaoQuery selecaoNegociacaoQuery, ConfigSistemaService configSistemaService) {
		this.context = context;
		this.tituloRepository = tituloRepository;
		this.tituloMapper = tituloMapper;
		this.parcelaRepository = parcelaRepository;
		this.movimentoRepository = movimentoRepository;
		this.empresaFilialService = empresaFilialService;
		this.departamentoService = departamentoService;
		this.tipoTituloService = tipoTituloService;
		this.grupoFinanceiroService = grupoFinanceiroService;
		this.parceiroLocalService = parceiroLocalService;
		this.fatoGeradorService = fatoGeradorService;
		this.caracteristicaMovimentoFinanceiroService = caracteristicaMovimentoFinanceiroService;
		this.historicoPadraoService = historicoPadraoService;
		this.operacaoAcessoriaFinanceiraService = operacaoAcessoriaFinanceiraService;
		this.carteiraFinanceiraService = carteiraFinanceiraService;
		this.contaService = contaService;
		this.tipoOperacaoFinanceiraService = tipoOperacaoFinanceiraService;
		this.selecaoEstornoTituloQuery = selecaoEstornoTituloQuery;
		this.selecaoBaixaTituloQuery = selecaoBaixaTituloQuery;
		this.selecaoConsultaTituloQuery = selecaoConsultaTituloQuery;
		this.selecaoSubstituicaoCarteiraQuery = selecaoSubstituicaoCarteiraQuery;
		this.selecaoNegociacaoQuery = selecaoNegociacaoQuery;
		this.configSistemaService = configSistemaService;
	}

	public void inserirTitulo(LancamentoTituloRequest lancamentoTituloRequest) {

		ConfigSistema configSistema = configSistemaService.getById(1L).get();

		EmpresaFilial empresaFilial = empresaFilialService.getById(lancamentoTituloRequest.getEmpresaFilialId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado filial da empresa com id " + lancamentoTituloRequest.getEmpresaFilialId()));
		Departamento departamento = departamentoService.getById(lancamentoTituloRequest.getDepartamentoId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado tipo operação financeira com id "
						+ lancamentoTituloRequest.getDepartamentoId()));
		TipoTitulo tipoTitulo = tipoTituloService.getById(lancamentoTituloRequest.getTipoTituloId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado tipo de título com id " + lancamentoTituloRequest.getTipoTituloId()));
		GrupoFinanceiro grupoFinanceiro = grupoFinanceiroService.getById(lancamentoTituloRequest.getGrupoFinanceiroId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado grupo financeiro com id " + lancamentoTituloRequest.getGrupoFinanceiroId()));
		ParceiroLocal parceiroLocal = parceiroLocalService.getById(lancamentoTituloRequest.getParceiroLocalId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado local de parceiro com id " + lancamentoTituloRequest.getParceiroLocalId()));
		FatoGerador fatoGerador = fatoGeradorService.getById(lancamentoTituloRequest.getFatoGeradorId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado fato gerador com id " + lancamentoTituloRequest.getFatoGeradorId()));
		CaracteristicaMovimentoFinanceiro caracteristicaMovimentoFinanceiro = caracteristicaMovimentoFinanceiroService
				.getById(lancamentoTituloRequest.getCaracteristicaMovimentoFinanceiroId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado característica de movimento com id "
						+ lancamentoTituloRequest.getCaracteristicaMovimentoFinanceiroId()));
		HistoricoPadrao historicoPadrao = historicoPadraoService.getById(lancamentoTituloRequest.getHistoricoPadraoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado histórico padrão com id " + lancamentoTituloRequest.getHistoricoPadraoId()));

		TipoOperacaoFinanceira tipoOperacaoFinanceira = configSistema.getConfiguracoesFinanceiro().get(0)
				.getTipoOperacaoFinanceiraLancamento();
		OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro = configSistema.getConfiguracoesFinanceiro().get(0)
				.getOperacaoMovimentoFinanceiroInclusao();
		OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira = configSistema.getConfiguracoesFinanceiro().get(0)
				.getOperacaoAcessoriaFinanceiraPrincipal();

		CarteiraFinanceira carteiraFinanceira = carteiraFinanceiraService
				.getById(lancamentoTituloRequest.getCarteiraFinanceiraId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado carteira financeira com id "
						+ lancamentoTituloRequest.getCarteiraFinanceiraId()));

		Conta conta = null;
		if (carteiraFinanceira.isInformaBanco()) {
			conta = contaService.getById(lancamentoTituloRequest.getContaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado conta com id " + lancamentoTituloRequest.getContaId()));
		}
		AtomicReference<Conta> contaReference = new AtomicReference<>(conta);

		ServicoIntegracaoFinanceira servicoIntegracaoFinanceira = context.getBean(ServicoIntegracaoFinanceira.class);
		servicoIntegracaoFinanceira.iniciarIntegracao(TipoOperacaoIntegracaoFinanceira.INCLUSAOPORLANCAMENTO);
		servicoIntegracaoFinanceira.definirOperacao(tipoOperacaoFinanceira, lancamentoTituloRequest.getValorTitulo(),
				departamento, lancamentoTituloRequest.getDataMovimento(), lancamentoTituloRequest.getObservacao(), null,
				null);

		servicoIntegracaoFinanceira.atribuiValoresTitulo(tipoTitulo, empresaFilial, departamento, grupoFinanceiro,
				parceiroLocal, fatoGerador, caracteristicaMovimentoFinanceiro, historicoPadrao, carteiraFinanceira,
				lancamentoTituloRequest.getDocumento(), lancamentoTituloRequest.getObservacao(),
				lancamentoTituloRequest.getDataDocumento(), lancamentoTituloRequest.getComplemento(),
				lancamentoTituloRequest.getValorTitulo(), conta, NossoNumeroGenerator.gerarNossoNumero());

		lancamentoTituloRequest.getParcelas().forEach(parc -> {
			servicoIntegracaoFinanceira.atribuiValoresParcela(parc.getNumeroParcela(), parc.getDataVencimento(),
					parc.getValorParcela());

			servicoIntegracaoFinanceira.atribuiValoresMovimento(parc.getNumeroParcela(), 1, 1, tipoOperacaoFinanceira,
					operacaoMovimentoFinanceiro, operacaoAcessoriaFinanceira, carteiraFinanceira, grupoFinanceiro,
					parc.getValorParcela(), lancamentoTituloRequest.getDataMovimento(), parc.getValorParcela(),
					carteiraFinanceira.isInformaBanco() ? contaReference.get() : null);
		});
		servicoIntegracaoFinanceira.executarIntegracao();
	}

	public List<SelecaoBaixaResponse> selecaoBaixa(SelecaoBaixaTituloRequest selecaoBaixaTituloRequest) {
		Long tipoTituloId = selecaoBaixaTituloRequest.getTipoTituloId() != null
				? selecaoBaixaTituloRequest.getTipoTituloId()
				: 0L;
		Long departamentoId = selecaoBaixaTituloRequest.getDepartamentoId() != null
				? selecaoBaixaTituloRequest.getDepartamentoId()
				: 0L;
		Long parceiroLocalId = selecaoBaixaTituloRequest.getParceiroLocalId() != null
				? selecaoBaixaTituloRequest.getParceiroLocalId()
				: 0L;
		Long caracteristicaMovimentoFinanceiroId = selecaoBaixaTituloRequest
				.getCaracteristicaMovimentoFinanceiroId() != null
						? selecaoBaixaTituloRequest.getCaracteristicaMovimentoFinanceiroId()
						: 0L;
		Long carteiraFinanceiraId = selecaoBaixaTituloRequest.getCarteiraFinanceiraId() != null
				? selecaoBaixaTituloRequest.getCarteiraFinanceiraId()
				: 0L;
		Long grupoFinanceiroId = selecaoBaixaTituloRequest.getGrupoFinanceiroId() != null
				? selecaoBaixaTituloRequest.getGrupoFinanceiroId()
				: 0L;
		Long fatoGeradorId = selecaoBaixaTituloRequest.getFatoGeradorId() != null
				? selecaoBaixaTituloRequest.getFatoGeradorId()
				: 0L;
		String documento = StringUtils.isNotBlank(selecaoBaixaTituloRequest.getDocumento())
				? selecaoBaixaTituloRequest.getDocumento()
				: "";
		Long nossoNumero = selecaoBaixaTituloRequest.getNossoNumero() != null
				? selecaoBaixaTituloRequest.getNossoNumero()
				: 0L;

		List<SelecaoBaixaResponse> registros = selecaoBaixaTituloQuery.executeSQL(
				selecaoBaixaTituloRequest.getEmpresaFilialId(), tipoTituloId, departamentoId, parceiroLocalId,
				caracteristicaMovimentoFinanceiroId, carteiraFinanceiraId, grupoFinanceiroId, fatoGeradorId,
				nossoNumero, documento, selecaoBaixaTituloRequest.getDataMovimentoInicial(),
				selecaoBaixaTituloRequest.getDataMovimentoFinal(), selecaoBaixaTituloRequest.getVencimentoInicial(),
				selecaoBaixaTituloRequest.getVencimentoFinal());

		return registros;
	}

	public void realizarBaixa(BaixaTituloRequest baixaTituloRequest) {
		ConfigSistema configSistema = configSistemaService.getById(1L).get();

		TipoOperacaoFinanceira tipoOperacaoFinanceira = tipoOperacaoFinanceiraService
				.getById(baixaTituloRequest.getTipoOperacaoFinanceiraId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado tipo de operação financeira com id "
						+ baixaTituloRequest.getTipoOperacaoFinanceiraId()));

		OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro = configSistema.getConfiguracoesFinanceiro().get(0)
				.getOperacaoMovimentoFinanceiroBaixa();

		Departamento departamento = departamentoService.getById(baixaTituloRequest.getDepartamentoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado departamento com id " + baixaTituloRequest.getDepartamentoId()));

		BigDecimal valoOperacao = baixaTituloRequest.getSelecionados().stream()
				.map(SelecionadosBaixaTituloRequest::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

		ServicoIntegracaoFinanceira servicoIntegracaoFinanceira = context.getBean(ServicoIntegracaoFinanceira.class);
		servicoIntegracaoFinanceira.iniciarIntegracao(TipoOperacaoIntegracaoFinanceira.BAIXA);
		servicoIntegracaoFinanceira.definirOperacao(tipoOperacaoFinanceira, valoOperacao, departamento,
				baixaTituloRequest.getDataMovimento(), baixaTituloRequest.getObservacao(), null, null);

		baixaTituloRequest.getSelecionados().forEach(titulo -> {
			Long bancoId = null;
			Long agenciaId = null;
			Long contaId = null;

			OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira = configSistema.getConfiguracoesFinanceiro().get(0)
					.getOperacaoAcessoriaFinanceiraPrincipal();

			servicoIntegracaoFinanceira.atribuiMovimentosParaBaixa(operacaoMovimentoFinanceiro,
					operacaoAcessoriaFinanceira, titulo.getMovimentoFinanceiroId(), bancoId, agenciaId, contaId,
					titulo.getValorBaixa(), null, baixaTituloRequest.getDataMovimento(),
					baixaTituloRequest.getObservacao());

			titulo.getDescontos().forEach(desconto -> {
				OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceiraDesconto = operacaoAcessoriaFinanceiraService
						.getById(desconto.getOperacaoAcessoriaFinanceiraId()).get();

				servicoIntegracaoFinanceira.atribuiMovimentosParaBaixa(operacaoMovimentoFinanceiro,
						operacaoAcessoriaFinanceiraDesconto, titulo.getMovimentoFinanceiroId(), bancoId, agenciaId,
						contaId, desconto.getValor(), null, baixaTituloRequest.getDataMovimento(),
						baixaTituloRequest.getObservacao());
			});

			titulo.getJuros().forEach(juros -> {
				OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceiraDesconto = operacaoAcessoriaFinanceiraService
						.getById(juros.getOperacaoAcessoriaFinanceiraId()).get();

				servicoIntegracaoFinanceira.atribuiMovimentosParaBaixa(operacaoMovimentoFinanceiro,
						operacaoAcessoriaFinanceiraDesconto, titulo.getMovimentoFinanceiroId(), bancoId, agenciaId,
						contaId, juros.getValor(), null, baixaTituloRequest.getDataMovimento(),
						baixaTituloRequest.getObservacao());
			});

		});
		servicoIntegracaoFinanceira.executarIntegracao();
	}

	public List<SelecaoEstornoResponse> selecaoEstorno(SelecaoEstornoTituloRequest selecaoEstornoTituloRequest) {
		Long tipoTituloId = selecaoEstornoTituloRequest.getTipoTituloId() != null
				? selecaoEstornoTituloRequest.getTipoTituloId()
				: 0L;
		Long departamentoId = selecaoEstornoTituloRequest.getDepartamentoId() != null
				? selecaoEstornoTituloRequest.getDepartamentoId()
				: 0L;
		Long parceiroLocalId = selecaoEstornoTituloRequest.getParceiroLocalId() != null
				? selecaoEstornoTituloRequest.getParceiroLocalId()
				: 0L;
		Long caracteristicaMovimentoFinanceiroId = selecaoEstornoTituloRequest
				.getCaracteristicaMovimentoFinanceiroId() != null
						? selecaoEstornoTituloRequest.getCaracteristicaMovimentoFinanceiroId()
						: 0L;
		Long carteiraFinanceiraId = selecaoEstornoTituloRequest.getCarteiraFinanceiraId() != null
				? selecaoEstornoTituloRequest.getCarteiraFinanceiraId()
				: 0L;
		Long grupoFinanceiroId = selecaoEstornoTituloRequest.getGrupoFinanceiroId() != null
				? selecaoEstornoTituloRequest.getGrupoFinanceiroId()
				: 0L;
		Long fatoGeradorId = selecaoEstornoTituloRequest.getFatoGeradorId() != null
				? selecaoEstornoTituloRequest.getFatoGeradorId()
				: 0L;
		String documento = StringUtils.isNotBlank(selecaoEstornoTituloRequest.getDocumento())
				? selecaoEstornoTituloRequest.getDocumento()
				: "";
		Long nossoNumero = selecaoEstornoTituloRequest.getNossoNumero() != null
				? selecaoEstornoTituloRequest.getNossoNumero()
				: 0L;
		int numeroRecibo = selecaoEstornoTituloRequest.getNumeroRecibo();

		List<SelecaoEstornoResponse> registros = selecaoEstornoTituloQuery.executeSQL(
				selecaoEstornoTituloRequest.getEmpresaFilialId(), tipoTituloId, departamentoId, parceiroLocalId,
				caracteristicaMovimentoFinanceiroId, carteiraFinanceiraId, grupoFinanceiroId, fatoGeradorId,
				nossoNumero, documento, selecaoEstornoTituloRequest.getDataMovimentoInicial(),
				selecaoEstornoTituloRequest.getDataMovimentoFinal(), numeroRecibo);

		return registros;
	}

	public void realizarEstorno(BaixaEstornoRequest baixaEstornoRequest) {
		ConfigSistema configSistema = configSistemaService.getById(1L).get();

		TipoOperacaoFinanceira tipoOperacaoFinanceira = configSistema.getConfiguracoesFinanceiro().get(0)
				.getTipoOperacaoFinanceiraEstorno();

		OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro = configSistema.getConfiguracoesFinanceiro().get(0)
				.getOperacaoMovimentoFinanceiroInclusao();

		Departamento departamento = departamentoService.getById(baixaEstornoRequest.getDepartamentoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado tipo operação financeira com id " + baixaEstornoRequest.getDepartamentoId()));

		BigDecimal valoOperacao = baixaEstornoRequest.getBaixasSelecionadas().stream()
				.map(SelecionadosEstornoBaixaRequest::getValorMovimento).reduce(BigDecimal.ZERO, BigDecimal::add);
		ServicoIntegracaoFinanceira servicoIntegracaoFinanceira = context.getBean(ServicoIntegracaoFinanceira.class);
		servicoIntegracaoFinanceira.iniciarIntegracao(TipoOperacaoIntegracaoFinanceira.ESTORNO);
		servicoIntegracaoFinanceira.definirOperacao(tipoOperacaoFinanceira, valoOperacao, departamento,
				baixaEstornoRequest.getDataMovimento(), baixaEstornoRequest.getObservacao(),
				baixaEstornoRequest.getMotivoEstornoFinanceiroId(), null);

		baixaEstornoRequest.getBaixasSelecionadas().forEach(baixa -> {

			OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira = configSistema.getConfiguracoesFinanceiro().get(0)
					.getOperacaoAcessoriaFinanceiraPrincipal();

			servicoIntegracaoFinanceira.atribuiMovimentosParaEstorno(operacaoMovimentoFinanceiro,
					operacaoAcessoriaFinanceira, baixa.getMovimentoFinanceiroId());

		});

		servicoIntegracaoFinanceira.executarIntegracao();
	}

	public List<SelecaoConsultaResponse> selecaoConsulta(SelecaoConsultaTituloRequest selecaoConsultaTituloRequest) {
		Long tipoTituloId = selecaoConsultaTituloRequest.getTipoTituloId() != null
				? selecaoConsultaTituloRequest.getTipoTituloId()
				: 0L;
		Long departamentoId = selecaoConsultaTituloRequest.getDepartamentoId() != null
				? selecaoConsultaTituloRequest.getDepartamentoId()
				: 0L;
		Long parceiroLocalId = selecaoConsultaTituloRequest.getParceiroLocalId() != null
				? selecaoConsultaTituloRequest.getParceiroLocalId()
				: 0L;
		Long caracteristicaMovimentoFinanceiroId = selecaoConsultaTituloRequest
				.getCaracteristicaMovimentoFinanceiroId() != null
						? selecaoConsultaTituloRequest.getCaracteristicaMovimentoFinanceiroId()
						: 0L;
		Long carteiraFinanceiraId = selecaoConsultaTituloRequest.getCarteiraFinanceiraId() != null
				? selecaoConsultaTituloRequest.getCarteiraFinanceiraId()
				: 0L;
		Long grupoFinanceiroId = selecaoConsultaTituloRequest.getGrupoFinanceiroId() != null
				? selecaoConsultaTituloRequest.getGrupoFinanceiroId()
				: 0L;
		Long fatoGeradorId = selecaoConsultaTituloRequest.getFatoGeradorId() != null
				? selecaoConsultaTituloRequest.getFatoGeradorId()
				: 0L;
		String documento = StringUtils.isNotBlank(selecaoConsultaTituloRequest.getDocumento())
				? selecaoConsultaTituloRequest.getDocumento()
				: "";
		Long nossoNumero = selecaoConsultaTituloRequest.getNossoNumero() != null
				? selecaoConsultaTituloRequest.getNossoNumero()
				: 0L;

		List<SelecaoConsultaResponse> registros = selecaoConsultaTituloQuery.executeSQL(
				selecaoConsultaTituloRequest.getEmpresaFilialId(), tipoTituloId, departamentoId, parceiroLocalId,
				caracteristicaMovimentoFinanceiroId, carteiraFinanceiraId, grupoFinanceiroId, fatoGeradorId,
				nossoNumero, documento, selecaoConsultaTituloRequest.getDataMovimentoInicial(),
				selecaoConsultaTituloRequest.getDataMovimentoFinal());

		return registros;
	}

	public TituloDTO consultaTitulo(Long tituloId) {
		Titulo titulo = tituloRepository.findById(tituloId)
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado título com id " + tituloId));

		List<ParcelaFinanceiro> parcelas = parcelaRepository.findByTituloIdOrderByNumParcelaAscSeqParcelaAsc(tituloId);
		if (parcelas.size() > 0) {
			parcelas.forEach(parcela -> {
				parcela.setMovimentos(new ArrayList<>());
				parcela.getMovimentos()
						.addAll(movimentoRepository.findByParcelaIdOrderBySeqMvtoAscSubSeqMvtoAsc(parcela.getId()));
			});
			titulo.setParcelas(new ArrayList<>());
			titulo.getParcelas().addAll(parcelas);
		}
		return tituloMapper.toDto(titulo);
	}

	public List<SelecaoSubstituicaoCarteiraResponse> selecaoSubstituicaoCarteira(
			SelecaoSubstituicaoCarteiraRequest selecaoSubstituicaoCarteiraRequest) {
		Long tipoTituloId = selecaoSubstituicaoCarteiraRequest.getTipoTituloId() != null
				? selecaoSubstituicaoCarteiraRequest.getTipoTituloId()
				: 0L;
		Long departamentoId = selecaoSubstituicaoCarteiraRequest.getDepartamentoId() != null
				? selecaoSubstituicaoCarteiraRequest.getDepartamentoId()
				: 0L;
		Long parceiroLocalId = selecaoSubstituicaoCarteiraRequest.getParceiroLocalId() != null
				? selecaoSubstituicaoCarteiraRequest.getParceiroLocalId()
				: 0L;
		Long caracteristicaMovimentoFinanceiroId = selecaoSubstituicaoCarteiraRequest
				.getCaracteristicaMovimentoFinanceiroId() != null
						? selecaoSubstituicaoCarteiraRequest.getCaracteristicaMovimentoFinanceiroId()
						: 0L;
		Long carteiraFinanceiraId = selecaoSubstituicaoCarteiraRequest.getCarteiraFinanceiraId() != null
				? selecaoSubstituicaoCarteiraRequest.getCarteiraFinanceiraId()
				: 0L;
		Long grupoFinanceiroId = selecaoSubstituicaoCarteiraRequest.getGrupoFinanceiroId() != null
				? selecaoSubstituicaoCarteiraRequest.getGrupoFinanceiroId()
				: 0L;
		Long fatoGeradorId = selecaoSubstituicaoCarteiraRequest.getFatoGeradorId() != null
				? selecaoSubstituicaoCarteiraRequest.getFatoGeradorId()
				: 0L;
		String documento = StringUtils.isNotBlank(selecaoSubstituicaoCarteiraRequest.getDocumento())
				? selecaoSubstituicaoCarteiraRequest.getDocumento()
				: "";
		Long nossoNumero = selecaoSubstituicaoCarteiraRequest.getNossoNumero() != null
				? selecaoSubstituicaoCarteiraRequest.getNossoNumero()
				: 0L;

		Long carteiraFinanceiraDestinoId = selecaoSubstituicaoCarteiraRequest.getCarteiraFinanceiraDestinoId() != null
				? selecaoSubstituicaoCarteiraRequest.getCarteiraFinanceiraDestinoId()
				: 0L;

		List<SelecaoSubstituicaoCarteiraResponse> registros = selecaoSubstituicaoCarteiraQuery.executeSQL(
				selecaoSubstituicaoCarteiraRequest.getEmpresaFilialId(), tipoTituloId, departamentoId, parceiroLocalId,
				caracteristicaMovimentoFinanceiroId, carteiraFinanceiraId, grupoFinanceiroId, fatoGeradorId,
				nossoNumero, documento, selecaoSubstituicaoCarteiraRequest.getDataMovimentoInicial(),
				selecaoSubstituicaoCarteiraRequest.getDataMovimentoFinal(),
				selecaoSubstituicaoCarteiraRequest.getVencimentoInicial(),
				selecaoSubstituicaoCarteiraRequest.getVencimentoFinal(), carteiraFinanceiraDestinoId);

		return registros;
	}

	public void realizarSubstituicaoCarteira(SubstituicaoCarteiraRequest substituicaoCarteiraRequest) {
		ConfigSistema configSistema = configSistemaService.getById(1L).get();

		String observacaoSubstituicaoCarteira = "Substituição de carteira";

		TipoOperacaoFinanceira tipoOperacaoFinanceira = tipoOperacaoFinanceiraService
				.getById(substituicaoCarteiraRequest.getTipoOperacaoFinanceiraId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado tipo de operação financeira com id "
						+ substituicaoCarteiraRequest.getTipoOperacaoFinanceiraId()));

		OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro = configSistema.getConfiguracoesFinanceiro().get(0)
				.getOperacaoMovimentoFinanceiroBaixa();

		Departamento departamento = departamentoService.getById(substituicaoCarteiraRequest.getDepartamentoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado departamento com id " + substituicaoCarteiraRequest.getDepartamentoId()));

		CarteiraFinanceira carteiraFinanceiraDestino = carteiraFinanceiraService
				.getById(substituicaoCarteiraRequest.getCarteiraFinanceiraDestinoId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado carteira financeira destino com id "
						+ substituicaoCarteiraRequest.getCarteiraFinanceiraDestinoId()));

		BigDecimal valorOperacao = substituicaoCarteiraRequest.getSelecionados().stream()
				.map(SelecionadosSubstituicaoCarteiraRequest::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

		ServicoIntegracaoFinanceira servicoIntegracaoFinanceira = context.getBean(ServicoIntegracaoFinanceira.class);
		servicoIntegracaoFinanceira.iniciarIntegracao(TipoOperacaoIntegracaoFinanceira.SUBSTITUICAOCARTEIRA);
		servicoIntegracaoFinanceira.definirOperacao(tipoOperacaoFinanceira, valorOperacao, departamento,
				substituicaoCarteiraRequest.getDataMovimento(), observacaoSubstituicaoCarteira, null,
				carteiraFinanceiraDestino);

		substituicaoCarteiraRequest.getSelecionados().forEach(titulo -> {

			OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira = configSistema.getConfiguracoesFinanceiro().get(0)
					.getOperacaoAcessoriaFinanceiraPrincipal();

			servicoIntegracaoFinanceira.atribuiMovimentosParaSubstituicao(operacaoMovimentoFinanceiro,
					operacaoAcessoriaFinanceira, titulo.getMovimentoFinanceiroId(),
					substituicaoCarteiraRequest.getContaId(), carteiraFinanceiraDestino,
					substituicaoCarteiraRequest.getDataMovimento());

		});
		servicoIntegracaoFinanceira.executarIntegracao();
	}

	public List<SelecaoNegociacaoResponse> selecaoNegociacao(SelecaoNegociacaoRequest selecaoNegociacaoRequest) {
		Long tipoTituloId = selecaoNegociacaoRequest.getTipoTituloId() != null
				? selecaoNegociacaoRequest.getTipoTituloId()
				: 0L;
		Long departamentoId = selecaoNegociacaoRequest.getDepartamentoId() != null
				? selecaoNegociacaoRequest.getDepartamentoId()
				: 0L;
		Long parceiroLocalId = selecaoNegociacaoRequest.getParceiroLocalId() != null
				? selecaoNegociacaoRequest.getParceiroLocalId()
				: 0L;
		Long caracteristicaMovimentoFinanceiroId = selecaoNegociacaoRequest
				.getCaracteristicaMovimentoFinanceiroId() != null
						? selecaoNegociacaoRequest.getCaracteristicaMovimentoFinanceiroId()
						: 0L;
		Long carteiraFinanceiraId = selecaoNegociacaoRequest.getCarteiraFinanceiraId() != null
				? selecaoNegociacaoRequest.getCarteiraFinanceiraId()
				: 0L;
		Long grupoFinanceiroId = selecaoNegociacaoRequest.getGrupoFinanceiroId() != null
				? selecaoNegociacaoRequest.getGrupoFinanceiroId()
				: 0L;
		Long fatoGeradorId = selecaoNegociacaoRequest.getFatoGeradorId() != null
				? selecaoNegociacaoRequest.getFatoGeradorId()
				: 0L;
		String documento = StringUtils.isNotBlank(selecaoNegociacaoRequest.getDocumento())
				? selecaoNegociacaoRequest.getDocumento()
				: "";
		Long nossoNumero = selecaoNegociacaoRequest.getNossoNumero() != null ? selecaoNegociacaoRequest.getNossoNumero()
				: 0L;

		List<SelecaoNegociacaoResponse> registros = selecaoNegociacaoQuery.executeSQL(
				selecaoNegociacaoRequest.getEmpresaFilialId(), tipoTituloId, departamentoId, parceiroLocalId,
				caracteristicaMovimentoFinanceiroId, carteiraFinanceiraId, grupoFinanceiroId, fatoGeradorId,
				nossoNumero, documento, selecaoNegociacaoRequest.getDataMovimentoInicial(),
				selecaoNegociacaoRequest.getDataMovimentoFinal(), selecaoNegociacaoRequest.getVencimentoInicial(),
				selecaoNegociacaoRequest.getVencimentoFinal());

		return registros;
	}

	public void realizarNegociacao(NegociacaoRequest negociacaoRequest) {
		ConfigSistema configSistema = configSistemaService.getById(1L).get();

		TipoOperacaoFinanceira tipoOperacaoFinanceira = tipoOperacaoFinanceiraService
				.getById(negociacaoRequest.getTipoOperacaoFinanceiraId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado tipo de operação financeira com id "
						+ negociacaoRequest.getTipoOperacaoFinanceiraId()));

		Departamento departamento = departamentoService.getById(negociacaoRequest.getDepartamentoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado departamento com id " + negociacaoRequest.getDepartamentoId()));

		ParceiroLocal parceiroLocal = parceiroLocalService.getById(negociacaoRequest.getParceiroLocalId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado local de parceiro com id " + negociacaoRequest.getParceiroLocalId()));

		TipoTitulo tipoTitulo = tipoTituloService.getById(negociacaoRequest.getTipoTituloId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado tipo de título com id " + negociacaoRequest.getTipoTituloId()));

		GrupoFinanceiro grupoFinanceiro = configSistema.getConfiguracoesFinanceiro().get(0)
				.getGrupoFinanceiroNegociacao();

		FatoGerador fatoGerador = configSistema.getConfiguracoesFinanceiro().get(0).getFatoGeradorNegociacao();

		CaracteristicaMovimentoFinanceiro caracteristicaMovimentoFinanceiro = configSistema.getConfiguracoesFinanceiro()
				.get(0).getCaracteristicaMovimentoFinanceiroNegociacao();

		HistoricoPadrao historicoPadrao = configSistema.getConfiguracoesFinanceiro().get(0)
				.getHistoricoPadraoNegociacao();

		CarteiraFinanceira carteiraFinanceira = configSistema.getConfiguracoesFinanceiro().get(0)
				.getCarteiraFinanceiraNegociacao();

		BigDecimal valorOperacao = negociacaoRequest.getSelecionados().stream()
				.map(SelecionadosNegociacaoRequest::getValorBaixa).reduce(BigDecimal.ZERO, BigDecimal::add);

		ServicoIntegracaoFinanceira servicoIntegracaoFinanceira = context.getBean(ServicoIntegracaoFinanceira.class);
		servicoIntegracaoFinanceira.iniciarIntegracao(TipoOperacaoIntegracaoFinanceira.NEGOCIACAO);
		servicoIntegracaoFinanceira.definirOperacao(tipoOperacaoFinanceira, valorOperacao, departamento,
				negociacaoRequest.getDataNegociacao(), negociacaoRequest.getObservacao(), null, null);

		OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiroBaixa = configSistema.getConfiguracoesFinanceiro().get(0)
				.getOperacaoMovimentoFinanceiroBaixa();

		OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceiraPrincipal = configSistema.getConfiguracoesFinanceiro()
				.get(0).getOperacaoAcessoriaFinanceiraPrincipal();

		negociacaoRequest.getSelecionados().forEach(titulo -> {
			Long bancoId = null;
			Long agenciaId = null;
			Long contaId = null;

			servicoIntegracaoFinanceira.atribuiMovimentosParaBaixa(operacaoMovimentoFinanceiroBaixa,
					operacaoAcessoriaFinanceiraPrincipal, titulo.getMovimentoFinanceiroId(), bancoId, agenciaId,
					contaId, titulo.getValorBaixa(), null, negociacaoRequest.getDataNegociacao(),
					negociacaoRequest.getObservacao());

		});

		OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiroInclusao = configSistema.getConfiguracoesFinanceiro()
				.get(0).getOperacaoMovimentoFinanceiroInclusao();

		OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceiraJurosPactuado = configSistema
				.getConfiguracoesFinanceiro().get(0).getOperacaoAcessoriaFinanceiraJurosPactuado();

		BigDecimal valorTitulo = negociacaoRequest.getNovasParcelas().stream()
				.map(NovasParcelasNegociacaoRequest::getValorParcela).reduce(BigDecimal.ZERO, BigDecimal::add);

		servicoIntegracaoFinanceira.atribuiValoresTitulo(tipoTitulo, departamento.getEmpresaFilial(), departamento,
				grupoFinanceiro, parceiroLocal, fatoGerador, caracteristicaMovimentoFinanceiro, historicoPadrao,
				carteiraFinanceira, null, negociacaoRequest.getObservacao(), negociacaoRequest.getDataNegociacao(), "",
				valorTitulo, null, NossoNumeroGenerator.gerarNossoNumero());

		BigDecimal juros = negociacaoRequest.getValorJurosNegociado()
				.divide(BigDecimal.valueOf(negociacaoRequest.getNovasParcelas().size()), 2, RoundingMode.HALF_UP);
		BigDecimal ajusteJuros = negociacaoRequest.getValorJurosNegociado()
				.subtract(juros.multiply(BigDecimal.valueOf(negociacaoRequest.getNovasParcelas().size())));

		AtomicReference<BigDecimal> atomicAjusteJuros = new AtomicReference<BigDecimal>(ajusteJuros);

		negociacaoRequest.getNovasParcelas().forEach(parc -> {

			servicoIntegracaoFinanceira.atribuiValoresParcela(parc.getNumeroParcela(), parc.getDataVencimento(),
					parc.getValorParcela());

			BigDecimal valorPrincipal = parc.getValorParcela().subtract(juros.add(ajusteJuros));

			servicoIntegracaoFinanceira.atribuiValoresMovimento(parc.getNumeroParcela(), 1, 1, tipoOperacaoFinanceira,
					operacaoMovimentoFinanceiroInclusao, operacaoAcessoriaFinanceiraPrincipal, carteiraFinanceira,
					grupoFinanceiro, valorPrincipal, negociacaoRequest.getDataNegociacao(), parc.getValorParcela(),
					null);

			servicoIntegracaoFinanceira.atribuiValoresMovimento(parc.getNumeroParcela(), 1, 2, tipoOperacaoFinanceira,
					operacaoMovimentoFinanceiroInclusao, operacaoAcessoriaFinanceiraJurosPactuado, carteiraFinanceira,
					grupoFinanceiro, juros.add(ajusteJuros), negociacaoRequest.getDataNegociacao(),
					parc.getValorParcela(), null);

			atomicAjusteJuros.set(BigDecimal.ZERO);
		});

		servicoIntegracaoFinanceira.atribuirInformacoesNegociacao(departamento, parceiroLocal, valorOperacao,
				negociacaoRequest.getValorJurosNegociado(), negociacaoRequest.getValorDescontoNegociado(), valorTitulo,
				negociacaoRequest.getDataNegociacao(), negociacaoRequest.getObservacao(),
				negociacaoRequest.getNovasParcelas().size());

		servicoIntegracaoFinanceira.executarIntegracao();

	}
}
