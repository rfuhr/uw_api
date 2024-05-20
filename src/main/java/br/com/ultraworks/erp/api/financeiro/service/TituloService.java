package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.LancamentoTituloRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SelecaoBaixaTituloRequest;
import br.com.ultraworks.erp.api.financeiro.integrator.ServicoIntegracaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.integrator.TipoOperacaoIntegracaoFinanceira;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.organograma.service.DepartamentoService;
import br.com.ultraworks.erp.api.organograma.service.EmpresaFilialService;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalService;
import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadrao;
import br.com.ultraworks.erp.api.tabela.service.HistoricoPadraoService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;

@Service
public class TituloService {

	private final ApplicationContext context;
	private EmpresaFilialService empresaFilialService;
	private DepartamentoService departamentoService;
	private TipoTituloService tipoTituloService;
	private GrupoFinanceiroService grupoFinanceiroService;
	private ParceiroLocalService parceiroLocalService;
	private FatoGeradorService fatoGeradorService;
	private CaracteristicaMovimentoFinanceiroService caracteristicaMovimentoFinanceiroService;
	private HistoricoPadraoService historicoPadraoService;
	private OperacaoAcessoriaFinanceiraService operacaoAcessoriaFinanceiraService;
	private CarteiraFinanceiraService carteiraFinanceiraService;

	@Autowired
	public TituloService(ApplicationContext context, EmpresaFilialService empresaFilialService,
			DepartamentoService departamentoService,
			TipoTituloService tipoTituloService, GrupoFinanceiroService grupoFinanceiroService,
			ParceiroLocalService parceiroLocalService, FatoGeradorService fatoGeradorService,
			CaracteristicaMovimentoFinanceiroService caracteristicaMovimentoFinanceiroService,
			HistoricoPadraoService historicoPadraoService, OperacaoAcessoriaFinanceiraService operacaoAcessoriaFinanceiraService,
			CarteiraFinanceiraService carteiraFinanceiraService) {
		this.context = context;
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
	}

	public void inserirTitulo(LancamentoTituloRequest lancamentoTituloRequest) {

		EmpresaFilial empresaFilial = empresaFilialService.getById(lancamentoTituloRequest.getEmpresaFilialId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado filial da empresa com id "
						+ lancamentoTituloRequest.getEmpresaFilialId()));
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
		OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira = operacaoAcessoriaFinanceiraService.getById(1L).get();
		CarteiraFinanceira carteiraFinanceira = carteiraFinanceiraService.getById(lancamentoTituloRequest.getCarteiraFinanceiraId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado carteira financeira com id " + lancamentoTituloRequest.getCarteiraFinanceiraId()));
		
		Long bancoId = null;
		Long agenciaId = null;
		Long contaId = null;

		ServicoIntegracaoFinanceira servicoIntegracaoFinanceira = context.getBean(ServicoIntegracaoFinanceira.class);
		servicoIntegracaoFinanceira.iniciarIntegracao(TipoOperacaoIntegracaoFinanceira.INCLUSAOPORLANCAMENTO);
		servicoIntegracaoFinanceira.definirOperacao(1, lancamentoTituloRequest.getValorTitulo(), departamento);
		servicoIntegracaoFinanceira.atribuiValoresTitulo(tipoTitulo, empresaFilial, departamento, grupoFinanceiro, parceiroLocal,
				fatoGerador, caracteristicaMovimentoFinanceiro, historicoPadrao, lancamentoTituloRequest.getDocumento(),
				lancamentoTituloRequest.getObservacao(), lancamentoTituloRequest.getDataDocumento(),
				lancamentoTituloRequest.getComplemento(), lancamentoTituloRequest.getValorTitulo(), bancoId, agenciaId,
				contaId);

		lancamentoTituloRequest.getParcelas().forEach(parc -> {
			servicoIntegracaoFinanceira.atribuiValoresParcela(parc.getNumeroParcela(), parc.getDataVencimento(),
					parc.getValorParcela());
			
			servicoIntegracaoFinanceira.atribuiValoresMovimento(parc.getNumeroParcela(),
					1,1, operacaoAcessoriaFinanceira, carteiraFinanceira, grupoFinanceiro, parc.getValorParcela(),
					lancamentoTituloRequest.getDataMovimento(), parc.getValorParcela());
		});
		servicoIntegracaoFinanceira.executarIntegracao();
	}

	public void selecaoBaixa(SelecaoBaixaTituloRequest selecaoBaixaTituloRequest) {
		
	}

}
