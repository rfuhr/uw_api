package br.com.ultraworks.erp.api.financeiro.integrator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.glassfish.jaxb.core.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistema;
import br.com.ultraworks.erp.api.configuracao.service.ConfigSistemaService;
import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.conta.Conta;
import br.com.ultraworks.erp.api.financeiro.domain.documentobaixafinanceiro.DocumentoBaixaFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.estornobaixafinanceiro.EstornoBaixaFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.motivoestornofinanceiro.MotivoEstornoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.movimento.MovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.movimentocaixabanco.MovimentoCaixaBanco;
import br.com.ultraworks.erp.api.financeiro.domain.negociacao.NegociacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaocxbco.OperacaoCaixaBanco;
import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.parametrofinanceiro.ParametroFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.parcela.ParcelaFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.tipointegracaocaixabanco.TipoIntegracaoCaixaBanco;
import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.Titulo;
import br.com.ultraworks.erp.api.financeiro.integrator.dto.ContainerDefinicaoOperacao;
import br.com.ultraworks.erp.api.financeiro.integrator.dto.ContainerEstornoMovimento;
import br.com.ultraworks.erp.api.financeiro.integrator.dto.ContainerIntegracaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.integrator.dto.ContainerMovimento;
import br.com.ultraworks.erp.api.financeiro.integrator.dto.ContainerNegociacao;
import br.com.ultraworks.erp.api.financeiro.integrator.dto.ContainerParcela;
import br.com.ultraworks.erp.api.financeiro.integrator.dto.ContainerParcelaBaixa;
import br.com.ultraworks.erp.api.financeiro.integrator.dto.ContainerParcelaSubstituicao;
import br.com.ultraworks.erp.api.financeiro.integrator.dto.ContainerTitulo;
import br.com.ultraworks.erp.api.financeiro.repository.EstornoBaixaFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.MotivoEstornoFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.MovimentoRepository;
import br.com.ultraworks.erp.api.financeiro.repository.ParametroFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.ParcelaRepository;
import br.com.ultraworks.erp.api.financeiro.repository.TituloRepository;
import br.com.ultraworks.erp.api.financeiro.service.DocumentoBaixaFinanceiroService;
import br.com.ultraworks.erp.api.financeiro.service.MovimentoCaixaBancoService;
import br.com.ultraworks.erp.api.financeiro.service.NegociacaoFinanceiraService;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadrao;
import br.com.ultraworks.erp.api.tabela.domain.indicadorDC.IndicadorDC;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;

@Service
@Scope("prototype")
public class ServicoIntegracaoFinanceira {

	private ContainerIntegracaoFinanceira container;
	private TipoOperacaoIntegracaoFinanceira tipoOperacaoIntegracaoFinanceira;
	private TituloRepository tituloRepository;
	private ParcelaRepository parcelaRepository;
	private MovimentoRepository movimentoRepository;
	private ParametroFinanceiroRepository parametroFinanceiroRepository;
	private MotivoEstornoFinanceiroRepository motivoEstornoFinanceiroRepository;
	private EstornoBaixaFinanceiroRepository estornoBaixaFinanceiroRepository;
	private DocumentoBaixaFinanceiroService documentoBaixaFinanceiroService;
	private MovimentoCaixaBancoService movimentoCaixaBancoService;
	private NegociacaoFinanceiraService negociacaoService;
	private ConfigSistemaService configSistemaService;

	public ServicoIntegracaoFinanceira(TituloRepository tituloRepository, ParcelaRepository parcelaRepository,
			MovimentoRepository movimentoRepository, ParametroFinanceiroRepository parametroFinanceiroRepository,
			MotivoEstornoFinanceiroRepository motivoEstornoFinanceiroRepository,
			EstornoBaixaFinanceiroRepository estornoBaixaFinanceiroRepository,
			DocumentoBaixaFinanceiroService documentoBaixaFinanceiroService,
			MovimentoCaixaBancoService movimentoCaixaBancoService, NegociacaoFinanceiraService negociacaoService,
			ConfigSistemaService configSistemaService) {
		this.tituloRepository = tituloRepository;
		this.parcelaRepository = parcelaRepository;
		this.movimentoRepository = movimentoRepository;
		this.parametroFinanceiroRepository = parametroFinanceiroRepository;
		this.motivoEstornoFinanceiroRepository = motivoEstornoFinanceiroRepository;
		this.estornoBaixaFinanceiroRepository = estornoBaixaFinanceiroRepository;
		this.documentoBaixaFinanceiroService = documentoBaixaFinanceiroService;
		this.movimentoCaixaBancoService = movimentoCaixaBancoService;
		this.negociacaoService = negociacaoService;
		this.configSistemaService = configSistemaService;

	}

	public void iniciarIntegracao(TipoOperacaoIntegracaoFinanceira tipoOperacaoIntegracaoFinanceira) {
		this.tipoOperacaoIntegracaoFinanceira = tipoOperacaoIntegracaoFinanceira;
		this.container = new ContainerIntegracaoFinanceira();
	}

	public void definirOperacao(TipoOperacaoFinanceira tipoOperacaoFinanceira, BigDecimal valorOperacao,
			Departamento departamentoPelaOperacao, LocalDate dataMovimento, String observacao,
			Long motivoEstornoFinanceiroId, CarteiraFinanceira carteiraFinanceiraDestino) {
		MotivoEstornoFinanceiro motivoEstornoFinanceiro = null;

		if (this.tipoOperacaoIntegracaoFinanceira.isInclusaoLancamento()) {
			if (!tipoOperacaoFinanceira.isCriaTitulo()) {
				new RegisterNotFoundException(
						"Tipo de Operação Financeira para integração não é válida para inclusão de título ");
			}
		} else if (this.tipoOperacaoIntegracaoFinanceira.isBaixa()) {
			if (!tipoOperacaoFinanceira.isBaixaTitulo()) {
				new RegisterNotFoundException(
						"Tipo de Operação Financeira para integração não é válida para baixa de movimento de título ");
			}
		} else if (this.tipoOperacaoIntegracaoFinanceira.isEstorno()) {
			if (motivoEstornoFinanceiroId == null) {
				new RegisterNotFoundException("Motivo de estorno financeiro é obrigatório ser informado.");
			} else {
				motivoEstornoFinanceiro = motivoEstornoFinanceiroRepository.findById(motivoEstornoFinanceiroId)
						.orElseThrow(() -> new RegisterNotFoundException(
								"Não encontrado motivo de estorno financeira com id " + motivoEstornoFinanceiroId));

			}

		}

		if (valorOperacao.doubleValue() <= 0) {
			new RegisterNotFoundException(
					"Valor da operação de integração do movimento financeiro deve ser maior que R$ 0,00 ");
		}

		container.setContainerDefinicaoOperacao(ContainerDefinicaoOperacao.builder()
				.tipoOperacaoFinanceira(tipoOperacaoFinanceira).dataMovimento(dataMovimento)
				.valorOperacao(valorOperacao).departamentoPelaOperacao(departamentoPelaOperacao).observacao(observacao)
				.motivoEstornoFinanceiro(motivoEstornoFinanceiro).build());
	}

	public void atribuiValoresTitulo(TipoTitulo tipoTitulo, EmpresaFilial empresaFilial, Departamento departamento,
			GrupoFinanceiro grupoFinanceiro, ParceiroLocal parceiroLocal, FatoGerador fatoGerador,
			CaracteristicaMovimentoFinanceiro caracteristicaMovimentoFinanceiro, HistoricoPadrao historicoPadrao,
			CarteiraFinanceira carteiraFinanceira, String documento, String observacao, LocalDate dataDocumento,
			String historico, BigDecimal valorTotal, Conta conta, Long nossoNumero) {

		container.setContainerTitulo(ContainerTitulo.builder().empresaFilial(empresaFilial).departamento(departamento)
				.grupoFinanceiro(grupoFinanceiro).parceiroLocal(parceiroLocal).fatoGerador(fatoGerador)
				.caracteristicaMovimentoFinanceiro(caracteristicaMovimentoFinanceiro).historicoPadrao(historicoPadrao)
				.carteiraFinanceira(carteiraFinanceira).tipoTitulo(tipoTitulo).documento(documento)
				.observacaoTitulo(observacao).valorTotalTitulo(valorTotal).dataDocumento(dataDocumento)
				.historico(historico).conta(conta).nossoNumero(nossoNumero).build());
	}

	public void atribuiValoresParcela(int numeroParcela, LocalDate dataVencimento, BigDecimal valorParcela) {
		container.getListContainerParcela().add(ContainerParcela.builder().numeroParcela(numeroParcela)
				.sequenciaParcela(1).dataVencimento(dataVencimento).valorParcela(valorParcela).build());
	}

	public void atribuiValoresMovimento(int numeroParcela, int sequenciaMovimento, int subSequenciaMovimento,
			TipoOperacaoFinanceira tipoOperacaoFinanceira, OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro,
			OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira, CarteiraFinanceira carteira,
			GrupoFinanceiro grupoFinanceiro, BigDecimal valorMovimento, LocalDate dataMovimento,
			BigDecimal saldoParcela, Conta conta) {

		container.getListContainerMovimentos()
				.add(ContainerMovimento.builder().numeroParcela(numeroParcela).sequenciaParcela(1)
						.sequenciaMovimento(subSequenciaMovimento).subSequenciaMovimento(subSequenciaMovimento)
						.grupoFinanceiro(grupoFinanceiro).tipoOperacaoFinanceira(tipoOperacaoFinanceira)
						.operacaoMovimentoFinanceiro(operacaoMovimentoFinanceiro)
						.operacaoAcessoriaFinanceira(operacaoAcessoriaFinanceira).carteiraFinanceira(carteira)
						.valorMovimento(valorMovimento).dataMovimento(dataMovimento).saldoParcela(saldoParcela)
						.conta(conta).build());
	}

	public void atribuiMovimentosParaBaixa(OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro,
			OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira, Long movimentoParaBaixaId, Long bancoId,
			Long agenciaId, Long contaId, BigDecimal valorBaixa, CarteiraFinanceira carteiraFinanceiraSubstituicao,
			LocalDate dataMovimento, String observacao) {

		MovimentoFinanceiro movimentoFinanceiro = movimentoRepository.findById(movimentoParaBaixaId).get();

		container.getListContainerBaixaParcela()
				.add(ContainerParcelaBaixa.builder().operacaoMovimentoFinanceiro(operacaoMovimentoFinanceiro)
						.operacaoAcessoriaFinanceira(operacaoAcessoriaFinanceira)
						.parcelaFinanceiraId(movimentoFinanceiro.getParcela().getId())
						.idMovimentoParaBaixa(movimentoParaBaixaId).valorBaixa(valorBaixa)
						.criaSubSequenciaMovimento(operacaoAcessoriaFinanceira.isCriaSubSequenciaMovimento()).build());
	}

	public void atribuiMovimentosParaEstorno(OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro,
			OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira, Long movimentoParaEstornoId) {

		MovimentoFinanceiro movimentoFinanceiro = movimentoRepository.findById(movimentoParaEstornoId).get();

		container.getListContainerEstornoMovimentos()
				.add(ContainerEstornoMovimento.builder().idMovimentoEstorno(movimentoParaEstornoId)
						.operacaoMovimentoFinanceiro(operacaoMovimentoFinanceiro)
						.operacaoAcessoriaFinanceira(operacaoAcessoriaFinanceira).build());

		movimentoRepository
				.buscarMovimentosAcessorios(movimentoFinanceiro.getParcela().getId(), movimentoFinanceiro.getSeqMvto())
				.forEach(mov -> {

					container.getListContainerEstornoMovimentos()
							.add(ContainerEstornoMovimento.builder().idMovimentoEstorno(mov.getId())
									.operacaoMovimentoFinanceiro(operacaoMovimentoFinanceiro)
									.operacaoAcessoriaFinanceira(mov.getOperacaoAcessoriaFinanceira()).build());
				});
	}

	public void atribuiMovimentosParaSubstituicao(OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro,
			OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira, Long movimentoParaBaixaId, Long contaId,
			CarteiraFinanceira carteiraFinanceiraSubstituicao, LocalDate dataMovimento) {

		MovimentoFinanceiro movimentoFinanceiro = movimentoRepository.findById(movimentoParaBaixaId).get();

		container.getListContainerSubstituicaoParcela()
				.add(ContainerParcelaSubstituicao.builder()
						.parcelaFinanceiraId(movimentoFinanceiro.getParcela().getId())
						.idMovimentoParaBaixa(movimentoParaBaixaId).valorBaixa(movimentoFinanceiro.getSaldoParcela())
						.carteiraFinanceiraSubstituicao(carteiraFinanceiraSubstituicao)
						.operacaoMovimentoFinanceiro(operacaoMovimentoFinanceiro)
						.operacaoAcessoriaFinanceira(operacaoAcessoriaFinanceira)
						.criaSubSequenciaMovimento(operacaoAcessoriaFinanceira.isCriaSubSequenciaMovimento()).build());

		movimentoRepository
				.buscarMovimentosAcessorios(movimentoFinanceiro.getParcela().getId(), movimentoFinanceiro.getSeqMvto())
				.forEach(mov -> {

					container.getListContainerSubstituicaoParcela()
							.add(ContainerParcelaSubstituicao.builder().parcelaFinanceiraId(mov.getParcela().getId())
									.idMovimentoParaBaixa(movimentoParaBaixaId).valorBaixa(mov.getValorMovimento())
									.carteiraFinanceiraSubstituicao(carteiraFinanceiraSubstituicao)
									.operacaoMovimentoFinanceiro(operacaoMovimentoFinanceiro)
									.operacaoAcessoriaFinanceira(mov.getOperacaoAcessoriaFinanceira())
									.criaSubSequenciaMovimento(
											mov.getOperacaoAcessoriaFinanceira().isCriaSubSequenciaMovimento())
									.build());
				});
	}

	public Titulo executarIntegracao() {

		if (this.tipoOperacaoIntegracaoFinanceira.isInclusaoLancamento()) {
			return executarIntegracaoNovoTitulo();
		}
		if (this.tipoOperacaoIntegracaoFinanceira.isBaixa()) {
			executarIntegracaoBaixaParcela();
		}
		if (this.tipoOperacaoIntegracaoFinanceira.isEstorno()) {
			executarIntegracaoEstorno();
		}
		if (this.tipoOperacaoIntegracaoFinanceira.isSubstituicaoCarteira()) {
			executarIntegracaoSubstituicao();
		}
		if (this.tipoOperacaoIntegracaoFinanceira.isNegociacaoFinanceira()) {
			executarIntegracaoNegociacao();
		}
		
		return null;
	}

	private Titulo executarIntegracaoNovoTitulo() {
		validaInclusaoTitulo();
		Titulo titulo = persistirTitulo();
		persistirParcelasEMovimentos(titulo);
		return titulo;
	}

	private void validaInclusaoTitulo() {
		BigDecimal somaParcelas = container.getListContainerParcela().stream().map(ContainerParcela::getValorParcela)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		if (container.getListContainerParcela() == null || container.getListContainerParcela().size() <= 0) {
			throw new BusinessException(
					"Deve ser informado pelo menos uma parcela para integração de título financeiro");
		}

		BigDecimal somaMovimentos = BigDecimal.ZERO;

		for (ContainerParcela containerParcela : container.getListContainerParcela()) {
			List<ContainerMovimento> listaMovimentosDaParcela = container.getListContainerMovimentos().stream()
					.filter(el -> el.getNumeroParcela() == containerParcela.getNumeroParcela()
							&& el.getSequenciaParcela() == containerParcela.getSequenciaParcela())
					.collect(Collectors.toList());

			for (ContainerMovimento containerMovimento : listaMovimentosDaParcela) {
				validaParametroFinanceiro(
						container.getContainerDefinicaoOperacao().getDepartamentoPelaOperacao().getEmpresaFilial()
								.getEmpresa().getId(),
						container.getContainerTitulo().getTipoTitulo().getId(),
						container.getContainerTitulo().getCaracteristicaMovimentoFinanceiro().getId(),
						containerMovimento.getCarteiraFinanceira().getId(),
						container.getContainerTitulo().getFatoGerador().getId(),
						containerMovimento.getOperacaoMovimentoFinanceiro().getId(),
						containerMovimento.getOperacaoAcessoriaFinanceira().getId());
				if (containerParcela.getDataVencimento().isBefore(containerMovimento.getDataMovimento())) {
					throw new BusinessException(
							"Data de vencimento de parcela é menor que a data de movimento do título");
				}
				somaMovimentos = somaMovimentos.add(containerMovimento.getValorMovimento());
			}
		}

		if (container.getContainerTitulo().getValorTotalTitulo().compareTo(somaParcelas) != 0) {
			throw new BusinessException(
					"Verifique o valor do título e das parcelas, corrija os valores e grave novamente.");
		}

		if (somaParcelas.compareTo(somaMovimentos) != 0) {
			throw new BusinessException(
					"Verifique a soma das parcelas e soma dos movimentos, corrija os valores e grave novamente.");
		}
	}

	private void executarIntegracaoBaixaParcela() {
		int _sequenciaMovimento = 0;
		int _subSequenciaMovimento = 0;
		BigDecimal _saldoParcela = BigDecimal.ZERO;
		Long _parcelaId = 0L;
		CarteiraFinanceira _carteiraFinanceira;
		GrupoFinanceiro _grupoFinanceiro;
		BigDecimal _valorBaixa = BigDecimal.ZERO;

		validaBaixaParcela();

		EmpresaFilial empresaFilial = container.getContainerDefinicaoOperacao().getDepartamentoPelaOperacao()
				.getEmpresaFilial();
		Empresa empresa = empresaFilial.getEmpresa();

		DocumentoBaixaFinanceiro docBaixa = documentoBaixaFinanceiroService.create(empresa, empresaFilial,
				container.getContainerDefinicaoOperacao().getDataMovimento(),
				container.getContainerDefinicaoOperacao().getDepartamentoPelaOperacao(),
				container.getContainerDefinicaoOperacao().getTipoOperacaoFinanceira(),
				container.getContainerDefinicaoOperacao().getConta(),
				container.getContainerDefinicaoOperacao().getObservacao());

		for (ContainerParcelaBaixa containerBaixaParcela : container.getListContainerBaixaParcela()) {			
			
			List<MovimentoFinanceiro> listaMovimentoGravado = buscaMovimentoParaBaixa(
					containerBaixaParcela.getIdMovimentoParaBaixa());
			
			BigDecimal valorMovimentoTotal = BigDecimal.ZERO;
			
			if (containerBaixaParcela.isCriaSubSequenciaMovimento()) {
				valorMovimentoTotal = listaMovimentoGravado.get(0).getSaldoParcela();
				_sequenciaMovimento = listaMovimentoGravado.get(0).getSeqMvto();
				_subSequenciaMovimento = listaMovimentoGravado.get(0).getSubSeqMvto() + 1;
				_saldoParcela = valorMovimentoTotal;
			} else {
				valorMovimentoTotal = buscaSomaMovimento(container.getListContainerBaixaParcela(),
						containerBaixaParcela.getIdMovimentoParaBaixa());
				_sequenciaMovimento = listaMovimentoGravado.get(0).getSeqMvto() + 1;
				_subSequenciaMovimento = 1;
				_saldoParcela = listaMovimentoGravado.get(0).getSaldoParcela().subtract(valorMovimentoTotal);
			}
			
			validarSaldoParcelaParaBaixa(containerBaixaParcela, valorMovimentoTotal);
			
			_parcelaId = listaMovimentoGravado.get(0).getParcela().getId();
			_carteiraFinanceira = listaMovimentoGravado.get(0).getCarteiraFinanceira();
			_valorBaixa = containerBaixaParcela.getValorBaixa();
			_grupoFinanceiro = listaMovimentoGravado.get(0).getGrupoFinanceiro();

			geraMovimento(_parcelaId, _sequenciaMovimento, _subSequenciaMovimento,
					container.getContainerDefinicaoOperacao().getTipoOperacaoFinanceira(),
					containerBaixaParcela.getOperacaoMovimentoFinanceiro(),
					containerBaixaParcela.getOperacaoAcessoriaFinanceira(), _carteiraFinanceira, _grupoFinanceiro,
					_valorBaixa, _saldoParcela, container.getContainerDefinicaoOperacao().getDepartamentoPelaOperacao(),
					container.getContainerDefinicaoOperacao().getDataMovimento(),
					container.getContainerDefinicaoOperacao().getConta(),
					container.getContainerDefinicaoOperacao().getObservacao(), docBaixa, null);
		}

		if (!container.getContainerDefinicaoOperacao().getTipoOperacaoFinanceira().getTipoIntegracaoCaixaBanco()
				.equals(TipoIntegracaoCaixaBanco.SEM_INTEGRACAO)) {
			OperacaoCaixaBanco operacaoCaixaBanco = container.getContainerDefinicaoOperacao()
					.getTipoOperacaoFinanceira().getOperacaoCaixaBanco();

			MovimentoCaixaBanco movimentoCaixaBanco = new MovimentoCaixaBanco();
			movimentoCaixaBanco.setOperacaoCaixaBanco(operacaoCaixaBanco);
			movimentoCaixaBanco.setConta(container.getContainerDefinicaoOperacao().getConta());
			movimentoCaixaBanco.setDataMovimento(container.getContainerDefinicaoOperacao().getDataMovimento());
			movimentoCaixaBanco.setNumeroDocumento(String.valueOf(docBaixa.getNumero()));
			movimentoCaixaBanco.setDataMovimentoBanco(container.getContainerDefinicaoOperacao().getDataMovimento());
			movimentoCaixaBanco.setParceiroLocal(operacaoCaixaBanco.getParceiroLocalDefault());
			movimentoCaixaBanco.setFatoGerador(operacaoCaixaBanco.getFatoGeradorDefault());
			movimentoCaixaBanco.setHistoricoPadrao(operacaoCaixaBanco.getHistoricoPadraoDefault());
			movimentoCaixaBanco.setValor(container.getContainerDefinicaoOperacao().getValorOperacao());
			movimentoCaixaBanco.setIndicadorDC(operacaoCaixaBanco.getIndicadorDC());
			movimentoCaixaBanco.setCompensado(false);
			movimentoCaixaBanco
					.setDepartamento(container.getContainerDefinicaoOperacao().getDepartamentoPelaOperacao());
			movimentoCaixaBancoService.inserirMovimento(movimentoCaixaBanco);
		}

	}

	private void validaBaixaParcela() {
		if ((container.getListContainerBaixaParcela() == null || container.getListContainerBaixaParcela().isEmpty())) {
			throw new BusinessException("Deve ser informado pelo menos uma parcela para integração da baixa");
		}
	}

	private ParametroFinanceiro validaParametroFinanceiro(Long empresaId, Long tipoTituloId,
			Long caracteristicaMovimentoFinanceiroId, Long carteiraFinanceiraId, Long fatoGeradorId,
			Long operacaoMovimentoFinanceiroId, Long operacaoAcessoriaFinanceiraId) {
		ParametroFinanceiro parametroFinanceiro = parametroFinanceiroRepository.findByParametros(tipoTituloId,
				caracteristicaMovimentoFinanceiroId, carteiraFinanceiraId, fatoGeradorId, operacaoMovimentoFinanceiroId,
				operacaoAcessoriaFinanceiraId);
		if (parametroFinanceiro == null) {
			String msg = String.format(
					"Não encontrado parâmetro financeiro para Empresa: %s Tipo de Título: %s Característica Movimento: %s Carteira Financeira: %s Fato Gerador: %s Operação Movimento: %s Operação Acessória: %s",
					String.valueOf(empresaId), String.valueOf(tipoTituloId),
					String.valueOf(caracteristicaMovimentoFinanceiroId), String.valueOf(carteiraFinanceiraId),
					String.valueOf(fatoGeradorId), String.valueOf(operacaoMovimentoFinanceiroId),
					String.valueOf(operacaoAcessoriaFinanceiraId));

			new BusinessException(msg);
		}
		return parametroFinanceiro;
	}

	private Titulo persistirTitulo() {
		ContainerTitulo containerTitulo = this.container.getContainerTitulo();
		Titulo titulo = Titulo.builder().empresaFilial(containerTitulo.getEmpresaFilial())
				.departamento(containerTitulo.getDepartamento()).parceiroLocal(containerTitulo.getParceiroLocal())
				.tipoTitulo(containerTitulo.getTipoTitulo()).grupoFinanceiro(containerTitulo.getGrupoFinanceiro())
				.fatoGerador(containerTitulo.getFatoGerador())
				.caracteristicaMovimentoFinanceiro(containerTitulo.getCaracteristicaMovimentoFinanceiro())
				.historicoPadrao(containerTitulo.getHistoricoPadrao()).documento(containerTitulo.getDocumento())
				.dataDocumento(containerTitulo.getDataDocumento()).observacao(containerTitulo.getObservacaoTitulo())
				.valorTotal(containerTitulo.getValorTotalTitulo()).historico(containerTitulo.getHistorico())
				.nossoNumero(containerTitulo.getNossoNumero())
				.negociacaoFinanceira(containerTitulo.getNegociacaoFinanceira()).build();

		titulo = tituloRepository.saveAndFlush(titulo);
		return titulo;
	}

	private void persistirParcelasEMovimentos(Titulo titulo) {
		for (ContainerParcela containerParcela : container.getListContainerParcela()) {
			ParcelaFinanceiro parcela = ParcelaFinanceiro.builder().titulo(titulo)
					.numParcela(containerParcela.getNumeroParcela()).seqParcela(containerParcela.getSequenciaParcela())
					.dataVencimento(containerParcela.getDataVencimento())
					.valorParcela(containerParcela.getValorParcela()).ultimaSeqMvto(1).build();
			parcela = parcelaRepository.saveAndFlush(parcela);

			persistirMovimentos(parcela);
		}
	}

	private void persistirMovimentos(ParcelaFinanceiro parcela) {
		List<ContainerMovimento> listaMovimentosDaParcela = container.getListContainerMovimentos().stream()
				.filter(el -> el.getNumeroParcela() == parcela.getNumParcela()
						&& el.getSequenciaParcela() == parcela.getSeqParcela())
				.collect(Collectors.toList());
		if (listaMovimentosDaParcela != null) {
			for (ContainerMovimento containerMovimento : listaMovimentosDaParcela) {
				MovimentoFinanceiro movimento = MovimentoFinanceiro.builder().parcela(parcela)
						.departamento(container.getContainerDefinicaoOperacao().getDepartamentoPelaOperacao())
						.seqMvto(containerMovimento.getSequenciaMovimento())
						.subSeqMvto(containerMovimento.getSubSequenciaMovimento())
						.tipoOperacaoFinanceira(container.getContainerDefinicaoOperacao().getTipoOperacaoFinanceira())
						.operacaoMovimentoFinanceiro(containerMovimento.getOperacaoMovimentoFinanceiro())
						.operacaoAcessoriaFinanceira(containerMovimento.getOperacaoAcessoriaFinanceira())
						.grupoFinanceiro(containerMovimento.getGrupoFinanceiro())
						.carteiraFinanceira(containerMovimento.getCarteiraFinanceira())
						.valorMovimento(containerMovimento.getValorMovimento())
						.dataMovimento(containerMovimento.getDataMovimento())
						.saldoParcela(containerMovimento.getSaldoParcela()).dataLancamento(LocalDateTime.now())
						.observacao(containerMovimento.getObservacao()).conta(containerMovimento.getConta()).build();

				movimentoRepository.saveAndFlush(movimento);
			}
		}
	}

	private List<MovimentoFinanceiro> buscaMovimentoParaBaixa(Long movimentoParaBaixaId) {
		List<MovimentoFinanceiro> listaMovimento = movimentoRepository.findAllById(Arrays.asList(movimentoParaBaixaId));
		if (listaMovimento == null || listaMovimento.isEmpty()) {
			throw new BusinessException(
					"Não encontrado movimento financeiro para baixa com id " + movimentoParaBaixaId);
		}
		if (this.tipoOperacaoIntegracaoFinanceira.isBaixa()) {
			listaMovimento = movimentoRepository
					.buscarListaMovimentoUltimaSequencia(listaMovimento.get(0).getParcela().getId());
			if (listaMovimento == null || listaMovimento.isEmpty()) {
				throw new BusinessException("Não encontrado movimento financeiro para baixa para parcela com id "
						+ listaMovimento.get(0).getParcela().getId());
			}
		}
		return listaMovimento;
	}

	private BigDecimal buscaSomaMovimento(List<ContainerParcelaBaixa> listContainerParcelaBaixa, Long movimentoId) {
		MovimentoFinanceiro movimentoFinanceiro = movimentoRepository.findById(movimentoId).get();
		ParcelaFinanceiro parcelaFinanceiro = movimentoFinanceiro.getParcela();
		Titulo titulo = parcelaFinanceiro.getTitulo();
		Long parcelaId = movimentoFinanceiro.getParcela().getId();
		Long empresaId = movimentoFinanceiro.getParcela().getTitulo().getDepartamento().getEmpresaFilial().getEmpresa()
				.getId();

		List<ContainerParcelaBaixa> movimentosDaParcela = listContainerParcelaBaixa.stream()
				.filter(f -> f.getParcelaFinanceiraId().equals(parcelaId)).collect(Collectors.toList());
		AtomicReference<BigDecimal> valor = new AtomicReference<>(BigDecimal.ZERO);
		if (movimentosDaParcela == null || movimentosDaParcela.size() <= 0) {
			throw new BusinessException("Não encontrado movimento financeiro para parcela com id " + parcelaId);
		}
		movimentosDaParcela.forEach(mov -> {
			ParametroFinanceiro parametroFinanceiro = parametroFinanceiroRepository.findByParametros(
					titulo.getTipoTitulo().getId(), titulo.getCaracteristicaMovimentoFinanceiro().getId(),
					movimentoFinanceiro.getCarteiraFinanceira().getId(), titulo.getFatoGerador().getId(),
					mov.getOperacaoMovimentoFinanceiro().getId(), mov.getOperacaoAcessoriaFinanceira().getId());

			if (mov.getOperacaoAcessoriaFinanceira().isCompoeSaldo()) {
				if (parametroFinanceiro.getIndicadorDC().equals(IndicadorDC.CRÉDITO)) {
					valor.updateAndGet(v -> v.subtract(mov.getValorBaixa()));
				} else {
					valor.updateAndGet(v -> v.add(mov.getValorBaixa()));
				}
			}
		});

		return valor.get();
	}

	private BigDecimal buscaSomaMovimentoSubstituicao(
			List<ContainerParcelaSubstituicao> listContainerParcelaSubstituicao, Long movimentoId) {
		MovimentoFinanceiro movimentoFinanceiro = movimentoRepository.findById(movimentoId).get();
		ParcelaFinanceiro parcelaFinanceiro = movimentoFinanceiro.getParcela();
		Titulo titulo = parcelaFinanceiro.getTitulo();
		Long parcelaId = movimentoFinanceiro.getParcela().getId();
		Long empresaId = movimentoFinanceiro.getParcela().getTitulo().getDepartamento().getEmpresaFilial().getEmpresa()
				.getId();

		List<ContainerParcelaSubstituicao> movimentosDaParcela = listContainerParcelaSubstituicao.stream()
				.filter(f -> f.getParcelaFinanceiraId().equals(parcelaId)).collect(Collectors.toList());
		AtomicReference<BigDecimal> valor = new AtomicReference<>(BigDecimal.ZERO);
		if (movimentosDaParcela == null || movimentosDaParcela.size() <= 0) {
			throw new BusinessException("Não encontrado movimento financeiro para parcela com id " + parcelaId);
		}
		movimentosDaParcela.forEach(mov -> {
			ParametroFinanceiro parametroFinanceiro = parametroFinanceiroRepository.findByParametros(
					titulo.getTipoTitulo().getId(), titulo.getCaracteristicaMovimentoFinanceiro().getId(),
					movimentoFinanceiro.getCarteiraFinanceira().getId(), titulo.getFatoGerador().getId(),
					mov.getOperacaoMovimentoFinanceiro().getId(), mov.getOperacaoAcessoriaFinanceira().getId());

			if (mov.getOperacaoAcessoriaFinanceira().isCompoeSaldo()) {
				if (parametroFinanceiro.getIndicadorDC().equals(IndicadorDC.CRÉDITO)) {
					valor.updateAndGet(v -> v.subtract(mov.getValorBaixa()));
				} else {
					valor.updateAndGet(v -> v.add(mov.getValorBaixa()));
				}
			}
		});

		return valor.get();
	}

	public MovimentoFinanceiro geraMovimento(Long parcelaId, int sequenciaMovimento, int subSequenciaMovimento,
			TipoOperacaoFinanceira tipoOperacaoFinanceira, OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro,
			OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira, CarteiraFinanceira carteira,
			GrupoFinanceiro grupoFinanceiro, BigDecimal valorMovimento, BigDecimal saldoParcela,
			Departamento departamento, LocalDate dataMovimento, Conta conta, String observacao,
			DocumentoBaixaFinanceiro documentoBaixaFinanceiro, NegociacaoFinanceira negociacaoFinanceira) {
		Optional<ParcelaFinanceiro> parcelaFinanceiroOptional = parcelaRepository.findById(parcelaId);
		if (!parcelaFinanceiroOptional.isPresent()) {
			throw new BusinessException("Não encontrado parcela financeira para id " + parcelaId);
		}
		ParcelaFinanceiro parcelaFinanceiro = parcelaFinanceiroOptional.get();
		validaParametroFinanceiro(
				parcelaFinanceiro.getTitulo().getDepartamento().getEmpresaFilial().getEmpresa().getId(),
				parcelaFinanceiro.getTitulo().getTipoTitulo().getId(),
				parcelaFinanceiro.getTitulo().getCaracteristicaMovimentoFinanceiro().getId(), carteira.getId(),
				parcelaFinanceiro.getTitulo().getFatoGerador().getId(), operacaoMovimentoFinanceiro.getId(),
				operacaoAcessoriaFinanceira.getId());

		if (saldoParcela.doubleValue() < 0) {
			throw new BusinessException(
					"A parcela não possui saldo suficiente para realizar a baixa para id " + parcelaId);
		}

		MovimentoFinanceiro movimentoFinanceiro = MovimentoFinanceiro.builder().parcela(parcelaFinanceiro)
				.departamento(departamento).grupoFinanceiro(grupoFinanceiro).seqMvto(sequenciaMovimento)
				.subSeqMvto(subSequenciaMovimento).tipoOperacaoFinanceira(tipoOperacaoFinanceira)
				.operacaoMovimentoFinanceiro(operacaoMovimentoFinanceiro)
				.operacaoAcessoriaFinanceira(operacaoAcessoriaFinanceira).carteiraFinanceira(carteira)
				.valorMovimento(valorMovimento).dataMovimento(dataMovimento).saldoParcela(saldoParcela)
				.departamento(departamento).observacao(observacao).dataLancamento(LocalDateTime.now()).conta(conta)
				.documentoBaixaFinanceiro(documentoBaixaFinanceiro).negociacaoFinanceira(negociacaoFinanceira).build();

		movimentoFinanceiro = movimentoRepository.saveAndFlush(movimentoFinanceiro);
		parcelaFinanceiro.setUltimaSeqMvto(movimentoFinanceiro.getSeqMvto());
		parcelaRepository.saveAndFlush(parcelaFinanceiro);

		return movimentoFinanceiro;
	}

	private void executarIntegracaoEstorno() {
		int _subSequenciaMovimento = 0;
		int _sequenciaMovimento = 0;
		BigDecimal _saldoParcela = BigDecimal.ZERO;
		Long _parcelaId = 0L;
		CarteiraFinanceira _carteiraFinanceira;
		GrupoFinanceiro _grupoFinanceiro;
		BigDecimal _valorBaixa = BigDecimal.ZERO;

		validaEstornoBaixa();

		List<ContainerEstornoMovimento> listContainerEstornoMovimentos = container.getListContainerEstornoMovimentos();
		listContainerEstornoMovimentos = listContainerEstornoMovimentos.stream()
				.sorted(Comparator.comparingLong(ContainerEstornoMovimento::getIdMovimentoEstorno))
				.collect(Collectors.toList());

		for (ContainerEstornoMovimento containerEstornoMovimento : listContainerEstornoMovimentos) {
			Optional<MovimentoFinanceiro> movimentoFinanceiro = movimentoRepository
					.findById(containerEstornoMovimento.getIdMovimentoEstorno());

			if (movimentoFinanceiro.isEmpty()) {
				throw new BusinessException("Não encontrado movimento financeiro para baixa com id "
						+ containerEstornoMovimento.getIdMovimentoEstorno());
			}
			MovimentoFinanceiro ultimoMovimentoParaEstorno = buscaUltimoMovimentoParaEstorno(
					containerEstornoMovimento.getIdMovimentoEstorno());

			if (!containerEstornoMovimento.getOperacaoAcessoriaFinanceira().isCriaSubSequenciaMovimento()) {
				_sequenciaMovimento = ultimoMovimentoParaEstorno.getSeqMvto() + 1;
				_subSequenciaMovimento = 1;
				if (containerEstornoMovimento.getOperacaoAcessoriaFinanceira().isCompoeSaldo())
					_saldoParcela = movimentoFinanceiro.get().getValorMovimento()
							.add(ultimoMovimentoParaEstorno.getSaldoParcela());
				else
					_saldoParcela = ultimoMovimentoParaEstorno.getSaldoParcela();
			} else {
				_sequenciaMovimento = ultimoMovimentoParaEstorno.getSeqMvto();
				_subSequenciaMovimento = ultimoMovimentoParaEstorno.getSubSeqMvto() + 1;
				if (containerEstornoMovimento.getOperacaoAcessoriaFinanceira().isCompoeSaldo())
					_saldoParcela = movimentoFinanceiro.get().getValorMovimento()
							.add(ultimoMovimentoParaEstorno.getSaldoParcela());
				else
					_saldoParcela = ultimoMovimentoParaEstorno.getSaldoParcela();
			}
			_parcelaId = ultimoMovimentoParaEstorno.getParcela().getId();
			_carteiraFinanceira = ultimoMovimentoParaEstorno.getCarteiraFinanceira();
			_valorBaixa = movimentoFinanceiro.get().getValorMovimento();
			_grupoFinanceiro = ultimoMovimentoParaEstorno.getGrupoFinanceiro();

			Conta conta = null;
			DocumentoBaixaFinanceiro docBaixa = null;

			MovimentoFinanceiro movimentoFinanceiroGerado = geraMovimento(_parcelaId, _sequenciaMovimento,
					_subSequenciaMovimento, container.getContainerDefinicaoOperacao().getTipoOperacaoFinanceira(),
					containerEstornoMovimento.getOperacaoMovimentoFinanceiro(),
					containerEstornoMovimento.getOperacaoAcessoriaFinanceira(), _carteiraFinanceira, _grupoFinanceiro,
					_valorBaixa, _saldoParcela, container.getContainerDefinicaoOperacao().getDepartamentoPelaOperacao(),
					container.getContainerDefinicaoOperacao().getDataMovimento(), conta,
					container.getContainerDefinicaoOperacao().getObservacao(), docBaixa, null);

			estornoBaixaFinanceiroRepository.save(EstornoBaixaFinanceiro.builder()
					.movimentoEstornado(ultimoMovimentoParaEstorno).movimentoGerado(movimentoFinanceiroGerado)
					.motivoEstorno(container.getContainerDefinicaoOperacao().getMotivoEstornoFinanceiro())
					.observacao(container.getContainerDefinicaoOperacao().getObservacao())
					.dataEstorno(container.getContainerDefinicaoOperacao().getDataMovimento())
					.dataLancamento(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))).build());

		}

	}

	private void validaEstornoBaixa() {
		if ((container.getListContainerEstornoMovimentos() == null
				|| container.getListContainerEstornoMovimentos().isEmpty())) {
			throw new BusinessException("Deve ser informado pelo menos uma baixa para integração do estorno");
		}
	}

	private MovimentoFinanceiro buscaUltimoMovimentoParaEstorno(Long movimentoId) {
		MovimentoFinanceiro movimentoFinanceiro = movimentoRepository.findById(movimentoId).get();
		ParcelaFinanceiro parcelaFinanceiro = movimentoFinanceiro.getParcela();
		List<MovimentoFinanceiro> listaMovimento = movimentoRepository
				.buscarListaMovimentoUltimaSequencia(parcelaFinanceiro.getId());
		return listaMovimento.get(0);
	}

	private void executarIntegracaoSubstituicao() {
		ConfigSistema configSistema = configSistemaService.getById(1L).get();

		int _sequenciaMovimento = 0;
		int _subSequenciaMovimento = 0;
		BigDecimal _saldoParcela = BigDecimal.ZERO;
		Long _parcelaId = 0L;
		CarteiraFinanceira _carteiraFinanceira;
		GrupoFinanceiro _grupoFinanceiro;
		BigDecimal _valorBaixa = BigDecimal.ZERO;

		OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiroInclusao = configSistema.getConfiguracoesFinanceiro()
				.get(0).getOperacaoMovimentoFinanceiroInclusao();

		for (ContainerParcelaSubstituicao containerSubstituicaoParcela : container
				.getListContainerSubstituicaoParcela()) {
			List<MovimentoFinanceiro> listaMovimentoGravado = buscaMovimentoParaBaixa(
					containerSubstituicaoParcela.getIdMovimentoParaBaixa());
			if (containerSubstituicaoParcela.isCriaSubSequenciaMovimento()) {
				_sequenciaMovimento = listaMovimentoGravado.get(0).getSeqMvto();
				_subSequenciaMovimento = listaMovimentoGravado.get(0).getSubSeqMvto() + 1;
				_saldoParcela = listaMovimentoGravado.get(0).getSaldoParcela();
			} else {
				BigDecimal valorMovimentoTotal = buscaSomaMovimentoSubstituicao(
						container.getListContainerSubstituicaoParcela(),
						containerSubstituicaoParcela.getIdMovimentoParaBaixa());
				_sequenciaMovimento = listaMovimentoGravado.get(0).getSeqMvto() + 1;
				_subSequenciaMovimento = 1;
				_saldoParcela = listaMovimentoGravado.get(0).getSaldoParcela().subtract(valorMovimentoTotal);
			}
			_parcelaId = listaMovimentoGravado.get(0).getParcela().getId();
			_carteiraFinanceira = listaMovimentoGravado.get(0).getCarteiraFinanceira();
			_valorBaixa = containerSubstituicaoParcela.getValorBaixa();
			_grupoFinanceiro = listaMovimentoGravado.get(0).getGrupoFinanceiro();

			geraMovimento(_parcelaId, _sequenciaMovimento, _subSequenciaMovimento,
					container.getContainerDefinicaoOperacao().getTipoOperacaoFinanceira(),
					containerSubstituicaoParcela.getOperacaoMovimentoFinanceiro(),
					containerSubstituicaoParcela.getOperacaoAcessoriaFinanceira(), _carteiraFinanceira,
					_grupoFinanceiro, _valorBaixa, _saldoParcela,
					container.getContainerDefinicaoOperacao().getDepartamentoPelaOperacao(),
					container.getContainerDefinicaoOperacao().getDataMovimento(),
					container.getContainerDefinicaoOperacao().getConta(),
					container.getContainerDefinicaoOperacao().getObservacao(), null, null);

			geraMovimento(_parcelaId, _sequenciaMovimento + 1, _subSequenciaMovimento,
					container.getContainerDefinicaoOperacao().getTipoOperacaoFinanceira(),
					operacaoMovimentoFinanceiroInclusao, containerSubstituicaoParcela.getOperacaoAcessoriaFinanceira(),
					containerSubstituicaoParcela.getCarteiraFinanceiraSubstituicao(), _grupoFinanceiro, _valorBaixa,
					_saldoParcela.add(_valorBaixa),
					container.getContainerDefinicaoOperacao().getDepartamentoPelaOperacao(),
					container.getContainerDefinicaoOperacao().getDataMovimento(),
					container.getContainerDefinicaoOperacao().getConta(),
					container.getContainerDefinicaoOperacao().getObservacao(), null, null);
		}

	}

	private void executarIntegracaoNegociacao_Baixa(NegociacaoFinanceira negociacao) {
		int _sequenciaMovimento = 0;
		int _subSequenciaMovimento = 0;
		BigDecimal _saldoParcela = BigDecimal.ZERO;
		Long _parcelaId = 0L;
		CarteiraFinanceira _carteiraFinanceira;
		GrupoFinanceiro _grupoFinanceiro;
		BigDecimal _valorBaixa = BigDecimal.ZERO;

		for (ContainerParcelaBaixa containerBaixaParcela : container.getListContainerBaixaParcela()) {
			List<MovimentoFinanceiro> listaMovimentoGravado = buscaMovimentoParaBaixa(
					containerBaixaParcela.getIdMovimentoParaBaixa());
			if (containerBaixaParcela.isCriaSubSequenciaMovimento()) {
				_sequenciaMovimento = listaMovimentoGravado.get(0).getSeqMvto();
				_subSequenciaMovimento = listaMovimentoGravado.get(0).getSubSeqMvto() + 1;
				_saldoParcela = listaMovimentoGravado.get(0).getSaldoParcela();
			} else {
				BigDecimal valorMovimentoTotal = buscaSomaMovimento(container.getListContainerBaixaParcela(),
						containerBaixaParcela.getIdMovimentoParaBaixa());
				_sequenciaMovimento = listaMovimentoGravado.get(0).getSeqMvto() + 1;
				_subSequenciaMovimento = 1;
				_saldoParcela = listaMovimentoGravado.get(0).getSaldoParcela().subtract(valorMovimentoTotal);
			}
			_parcelaId = listaMovimentoGravado.get(0).getParcela().getId();
			_carteiraFinanceira = listaMovimentoGravado.get(0).getCarteiraFinanceira();
			_valorBaixa = containerBaixaParcela.getValorBaixa();
			_grupoFinanceiro = listaMovimentoGravado.get(0).getGrupoFinanceiro();

			geraMovimento(_parcelaId, _sequenciaMovimento, _subSequenciaMovimento,
					container.getContainerDefinicaoOperacao().getTipoOperacaoFinanceira(),
					containerBaixaParcela.getOperacaoMovimentoFinanceiro(),
					containerBaixaParcela.getOperacaoAcessoriaFinanceira(), _carteiraFinanceira, _grupoFinanceiro,
					_valorBaixa, _saldoParcela, container.getContainerDefinicaoOperacao().getDepartamentoPelaOperacao(),
					container.getContainerDefinicaoOperacao().getDataMovimento(),
					container.getContainerDefinicaoOperacao().getConta(),
					container.getContainerDefinicaoOperacao().getObservacao(), null, negociacao);
		}
	}

	private void executarIntegracaoNegociacao_NovoTitulo(NegociacaoFinanceira negociacao) {
		if (negociacao != null) {
			container.getContainerTitulo().setNegociacaoFinanceira(negociacao);
			container.getContainerTitulo().setDocumento("NEG_" + String.valueOf(negociacao.getNossoNumero()));
		}
		executarIntegracaoNovoTitulo();
	}

	private void executarIntegracaoNegociacao() {

		NegociacaoFinanceira negociacao = NegociacaoFinanceira.builder()
				.departamento(container.getContainerNegociacao().getDepartamento())
				.parceiroLocal(container.getContainerNegociacao().getParceiroLocal())
				.valorTotalAtraso(container.getContainerNegociacao().getValorTotalAtraso())
				.valorJurosNegociado(container.getContainerNegociacao().getValorJurosNegociado())
				.valorDescontoNegociado(container.getContainerNegociacao().getValorDescontoNegociado())
				.valorNegociadoPagar(container.getContainerNegociacao().getValorNegociadoPagar())
				.dataNegociacao(container.getContainerNegociacao().getDataNegociacao())
				.observacao(container.getContainerNegociacao().getObservacao())
				.quantidadeParcelas(container.getContainerNegociacao().getQuantidadeParcelas()).build();

		negociacao = negociacaoService.inserirNegociacao(negociacao);

		executarIntegracaoNegociacao_Baixa(negociacao);
		executarIntegracaoNegociacao_NovoTitulo(negociacao);
	}

	public void atribuirInformacoesNegociacao(Departamento departamento, ParceiroLocal parceiroLocal,
			BigDecimal valorTotalAtraso, BigDecimal valorJurosNegociado, BigDecimal valorDescontoNegociado,
			BigDecimal valorNegociadoPagar, LocalDate dataNegociacao, String observacao, int quantidadeParcelas) {

		container.setContainerNegociacao(ContainerNegociacao.builder().departamento(departamento)
				.parceiroLocal(parceiroLocal).valorTotalAtraso(valorTotalAtraso)
				.valorJurosNegociado(valorJurosNegociado).valorDescontoNegociado(valorDescontoNegociado)
				.valorNegociadoPagar(valorNegociadoPagar).dataNegociacao(dataNegociacao).observacao(observacao)
				.quantidadeParcelas(quantidadeParcelas).build());

	}
	
	public void validarBaixaPorCancelamento(Long tituloId) {
		// TODO - Validar se o título está com saldo total disponível, caso não esteja deve apresentar uma mensagem de erro
	}
	
	private void validarSaldoParcelaParaBaixa(ContainerParcelaBaixa containerBaixaParcela,
			BigDecimal valorMovimentoTotal) {
		// TODO - Validar se o saldo da parcela - o valor do movimento vai deixar com saldo negativo
		
	}
	
}
