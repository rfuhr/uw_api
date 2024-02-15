package br.com.ultraworks.erp.api.financeiro.integrator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.movimento.MovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaofinanceira.OperacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.parametrofinanceiro.ParametroFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.parcela.ParcelaFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.Titulo;
import br.com.ultraworks.erp.api.financeiro.integrator.dto.ContainerDefinicaoOperacao;
import br.com.ultraworks.erp.api.financeiro.integrator.dto.ContainerIntegracaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.integrator.dto.ContainerMovimento;
import br.com.ultraworks.erp.api.financeiro.integrator.dto.ContainerParcelaBaixa;
import br.com.ultraworks.erp.api.financeiro.integrator.dto.ContainerParcela;
import br.com.ultraworks.erp.api.financeiro.integrator.dto.ContainerTitulo;
import br.com.ultraworks.erp.api.financeiro.repository.MovimentoRepository;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoFinanceiraRepository;
import br.com.ultraworks.erp.api.financeiro.repository.ParcelaRepository;
import br.com.ultraworks.erp.api.financeiro.repository.TipoOperacaoFinanceiraRepository;
import br.com.ultraworks.erp.api.financeiro.repository.TituloRepository;
import br.com.ultraworks.erp.api.financeiro.repository.query.BuscaSomaValorBaixaFinanceiraQuery;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
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
	private OperacaoFinanceiraRepository operacaoFinanceiraRepository;
	private TituloRepository tituloRepository;
	private ParcelaRepository parcelaRepository;
	private MovimentoRepository movimentoRepository;
	private TipoOperacaoFinanceiraRepository tipoOperacaoFinanceiraRepository;
	private BuscaSomaValorBaixaFinanceiraQuery buscaSomaValorBaixaFinanceiraQuery;

	@Autowired
	public ServicoIntegracaoFinanceira(TipoOperacaoFinanceiraRepository tipoOperacaoFinanceiraRepository,
			OperacaoFinanceiraRepository operacaoFinanceiraRepository, TituloRepository tituloRepository,
			ParcelaRepository parcelaRepository, MovimentoRepository movimentoRepository,
			BuscaSomaValorBaixaFinanceiraQuery buscaSomaValorBaixaFinanceiraQuery) {
		this.tipoOperacaoFinanceiraRepository = tipoOperacaoFinanceiraRepository;
		this.operacaoFinanceiraRepository = operacaoFinanceiraRepository;
		this.tituloRepository = tituloRepository;
		this.parcelaRepository = parcelaRepository;
		this.movimentoRepository = movimentoRepository;
		this.buscaSomaValorBaixaFinanceiraQuery = buscaSomaValorBaixaFinanceiraQuery;

	}

	public void iniciarIntegracao(TipoOperacaoIntegracaoFinanceira tipoOperacaoIntegracaoFinanceira) {
		this.tipoOperacaoIntegracaoFinanceira = tipoOperacaoIntegracaoFinanceira;
		this.container = new ContainerIntegracaoFinanceira();
	}

	public void definirOperacao(long tipoOperacaoFinanceiraId, BigDecimal valorOperacao,
			Departamento departamentoPelaOperacao) {
		TipoOperacaoFinanceira tipoOperacaoFinanceira = tipoOperacaoFinanceiraRepository
				.findById(tipoOperacaoFinanceiraId).orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado tipo operação financeira com id " + tipoOperacaoFinanceiraId));

//		if (this.tipoOperacaoIntegracaoFinanceira.isInclusaoLancamento()) {
//			if (!tipoOperacaoFinanceira.isCriaParcela()) {
//				new RegisterNotFoundException(
//						"Operação Financeira para integração não é válida para inclusão de título ");
//			}
//		} else if (this.tipoOperacaoIntegracaoFinanceira.isBaixa()) {
//			if (!tipoOperacaoFinanceira.isBaixaTitulo()) {
//				new RegisterNotFoundException(
//						"Operação Financeira para integração não é válida para baixa de movimento de título ");
//			}
//		}
//
//		if (valorOperacao.doubleValue() <= 0) {
//			new RegisterNotFoundException(
//					"Valor da operação de integração do movimento financeiro deve ser maior que R$ 0,00 ");
//		}

		container.setContainerDefinicaoOperacao(
				ContainerDefinicaoOperacao.builder().tipoOperacaoFinanceira(tipoOperacaoFinanceira)
						.valorOperacao(valorOperacao).departamentoPelaOperacao(departamentoPelaOperacao).build());
	}

	public void atribuiValoresTitulo(TipoTitulo tipoTitulo, Departamento departamento, GrupoFinanceiro grupoFinanceiro,
			ParceiroLocal parceiroLocal, FatoGerador fatoGerador,
			CaracteristicaMovimentoFinanceiro caracteristicaMovimentoFinanceiro, HistoricoPadrao historicoPadrao,
			String documento, String observacao, LocalDate dataDocumento, String historico, BigDecimal valorTotal,
			Long bancoId, Long agenciaId, Long contaId) {

		container.setContainerTitulo(ContainerTitulo.builder().departamento(departamento)
				.grupoFinanceiro(grupoFinanceiro).parceiroLocal(parceiroLocal).fatoGerador(fatoGerador)
				.caracteristicaMovimentoFinanceiro(caracteristicaMovimentoFinanceiro).historicoPadrao(historicoPadrao)
				.tipoTitulo(tipoTitulo).documento(documento).observacaoTitulo(observacao).valorTotalTitulo(valorTotal)
				.dataDocumento(dataDocumento).historico(historico).bancoId(bancoId).agenciaId(agenciaId)
				.contaId(contaId).build());
	}

	public void atribuiValoresParcela(int numeroParcela, LocalDate dataVencimento, BigDecimal valorParcela) {
		container.getListContainerParcela().add(ContainerParcela.builder().numeroParcela(numeroParcela)
				.sequenciaParcela(1).dataVencimento(dataVencimento).valorParcela(valorParcela).build());
	}

	public void atribuiValoresMovimento(int numeroParcela, int sequenciaMovimento, int subSequenciaMovimento,
			OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira, CarteiraFinanceira carteira,
			GrupoFinanceiro grupoFinanceiro, BigDecimal valorMovimento, LocalDate dataMovimento,
			BigDecimal saldoParcela) {

		OperacaoFinanceira operacaoFinanceira = validaOperacaoFinanceira(
				container.getContainerDefinicaoOperacao().getTipoOperacaoFinanceira().getId(), 1,
				operacaoAcessoriaFinanceira.getId());
		
		container.getListContainerMovimentos()
				.add(ContainerMovimento.builder().numeroParcela(numeroParcela).sequenciaParcela(1)
						.sequenciaMovimento(subSequenciaMovimento).subSequenciaMovimento(subSequenciaMovimento)
						.operacaoFinanceira(operacaoFinanceira).carteiraFinanceira(carteira)
						.valorMovimento(valorMovimento).dataMovimento(dataMovimento).saldoParcela(saldoParcela)
						.build());
	}

	public void atribuiMovimentosParaBaixa(OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira, Long movimentoParaBaixaId,
			Long bancoId, Long agenciaId, Long contaId, BigDecimal valorBaixa, 
			CarteiraFinanceira carteiraFinanceiraSubstituicao, LocalDate dataMovimento, String observacao) {

		OperacaoFinanceira operacaoFinanceira = validaOperacaoFinanceira(
				container.getContainerDefinicaoOperacao().getTipoOperacaoFinanceira().getId(), 51,
				operacaoAcessoriaFinanceira.getId());
		
		container.getListContainerBaixaParcela()
				.add(ContainerParcelaBaixa.builder().operacaoFinanceira(operacaoFinanceira)
						.idMovimentoParaBaixa(movimentoParaBaixaId).bancoId(bancoId).agenciaId(agenciaId)
						.contaId(contaId).valorBaixa(valorBaixa)
						.carteiraFinanceiraSubstituicao(carteiraFinanceiraSubstituicao).dataMovimento(dataMovimento)
						.observacao(observacao)
						.criaSubSequenciaMovimento(
								operacaoFinanceira.getOperacaoAcessoriaFinanceira().isCriaSubSequenciaMovimento())
						.build());
	}

	public void executarIntegracao() {

//		if (container.getContainerDefinicaoOperacao().getTipoOperacaoFinanceira().isCriaTitulo()) {
//			executarIntegracaoNovoTitulo();
//		}
//		if ((container.getContainerDefinicaoOperacao().getTipoOperacaoFinanceira().isBaixaTitulo()) &&
//				!this.tipoOperacaoIntegracaoFinanceira.isEstorno()) {
//			executarIntegracaoBaixaParcela();
//		}
	}

	public void executarIntegracaoNovoTitulo() {
		validaInclusaoTitulo();
		Titulo titulo = persistirTitulo();
		persistirParcelasEMovimentos(titulo);
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
				ParametroFinanceiro parametroFinanceiro = validaParametroFinanceiro(
						container.getContainerDefinicaoOperacao().getDepartamentoPelaOperacao().getEmpresaFilial()
								.getEmpresa().getId(),
						container.getContainerTitulo().getTipoTitulo().getId(),
						container.getContainerTitulo().getCaracteristicaMovimentoFinanceiro().getId(),
						containerMovimento.getCarteiraFinanceira().getId(),
						container.getContainerTitulo().getFatoGerador().getId(),
						containerMovimento.getOperacaoFinanceira().getId());
				if (containerParcela.getDataVencimento().isBefore(containerMovimento.getDataMovimento())) {
					throw new BusinessException(
							"Data de vencimento de parcela é menor que a data de movimento do título");
				}
				if (parametroFinanceiro.getIndicadorDC() == IndicadorDC.CRÉDITO) {
					somaMovimentos.add(containerMovimento.getValorMovimento());
				} else {
					somaMovimentos.subtract(containerMovimento.getValorMovimento());
				}
			}
		}

		if (container.getContainerTitulo().getValorTotalTitulo() != somaParcelas) {
			throw new BusinessException(
					"Verifique o valor do título e das parcelas, corrija os valores e grave novamente.");
		}

		if (somaParcelas != somaMovimentos) {
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
		BigDecimal _valorBaixa = BigDecimal.ZERO;
		
		validaBaixaParcela();
		for (ContainerParcelaBaixa containerBaixaParcela : container.getListContainerBaixaParcela()) {
			List<MovimentoFinanceiro> listaMovimentoGravado = buscarListaMovimentoGravadoParaBaixa(
					containerBaixaParcela.getIdMovimentoParaBaixa());
			if (containerBaixaParcela.isCriaSubSequenciaMovimento()) {
				_sequenciaMovimento = listaMovimentoGravado.get(0).getSeqMvto();
				_subSequenciaMovimento = listaMovimentoGravado.get(0).getSubSeqMvto() + 1;
				_saldoParcela = listaMovimentoGravado.get(0).getSaldoParcela();
			} else {
				BigDecimal valorMovimentoTotal = buscaSomaMovimento(
						containerBaixaParcela.getIdMovimentoParaBaixa());
				_sequenciaMovimento = listaMovimentoGravado.get(0).getSeqMvto() + 1;
				_subSequenciaMovimento = 1;
				_saldoParcela = listaMovimentoGravado.get(0).getSaldoParcela().subtract(valorMovimentoTotal);
			}
			_parcelaId = listaMovimentoGravado.get(0).getParcela().getId();
			_carteiraFinanceira = listaMovimentoGravado.get(0).getCarteiraFinanceira();
			_valorBaixa = containerBaixaParcela.getValorBaixa();
			
			baixaMovimento(_parcelaId, _sequenciaMovimento, _subSequenciaMovimento,
					containerBaixaParcela.getOperacaoFinanceira(), _carteiraFinanceira, _valorBaixa, _saldoParcela,
					containerBaixaParcela.getDepartamento(),
					containerBaixaParcela.getDataMovimento(), containerBaixaParcela.getBancoId(),
					containerBaixaParcela.getAgenciaId(), containerBaixaParcela.getContaId(),
					containerBaixaParcela.getObservacao());
		}
	}
	
	private void validaBaixaParcela() {
		if ((container.getListContainerBaixaParcela() == null || container.getListContainerBaixaParcela().isEmpty())) {
			throw new BusinessException("Deve ser informado pelo menos uma parcela para integração da baixa");
		}
	}

	private ParametroFinanceiro validaParametroFinanceiro(Long empresaId, Long tipoTituloId,
			Long caracteristicaMovimentoFinanceiroId, Long carteiraFinanceiraId, Long fatoGeradorId,
			Long operacaoFinanceiraId) {
		// validar Parametro Financeiro
		return null;
	}

	private OperacaoFinanceira validaOperacaoFinanceira(long tipoOperacaoFinanceiraId,
			long operacaoMovimentoFinanceiroId, long operacaoAcessoriaFinanceiaId) {
		// validar Parametro Financeiro
		return null;
	}

	private Titulo persistirTitulo() {
		ContainerTitulo containerTitulo = this.container.getContainerTitulo();
		Titulo titulo = Titulo.builder().departamento(containerTitulo.getDepartamento())
				.parceiroLocal(containerTitulo.getParceiroLocal()).tipoTitulo(containerTitulo.getTipoTitulo())
				.grupoFinanceiro(containerTitulo.getGrupoFinanceiro()).fatoGerador(containerTitulo.getFatoGerador())
				.caracteristicaMovimentoFinanceiro(containerTitulo.getCaracteristicaMovimentoFinanceiro())
				.historicoPadrao(containerTitulo.getHistoricoPadrao()).documento(containerTitulo.getDocumento())
				.dataDocumento(containerTitulo.getDataDocumento()).observacao(containerTitulo.getObservacaoTitulo())
				.valorTotal(containerTitulo.getValorTotalTitulo()).historico(containerTitulo.getHistorico()).build();
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
						.operacaoFinanceira(containerMovimento.getOperacaoFinanceira())
						.grupoFinanceiro(containerMovimento.getGrupoFinanceiro())
						.carteiraFinanceira(containerMovimento.getCarteiraFinanceira())
						.valorMovimento(containerMovimento.getValorMovimento())
						.dataMovimento(containerMovimento.getDataMovimento())
						.saldoParcela(containerMovimento.getSaldoParcela()).dataLancamento(LocalDateTime.now())
						.observacao(containerMovimento.getObservacao()).build();
				movimentoRepository.saveAndFlush(movimento);
			}
		}
	}
	
	private List<MovimentoFinanceiro> buscarListaMovimentoGravadoParaBaixa(Long movimentoParaBaixaId) {
		List<MovimentoFinanceiro> listaMovimento = movimentoRepository.findAllById(Arrays.asList(movimentoParaBaixaId));
		if (listaMovimento != null && listaMovimento.size() > 0) {
			throw new BusinessException(
					"Não encontrado movimento financeiro para baixa com id " + movimentoParaBaixaId);
		}
		if (this.tipoOperacaoIntegracaoFinanceira.isBaixa()) {
			listaMovimento = movimentoRepository
					.buscarListaMovimentoGravadoParaBaixa(listaMovimento.get(0).getParcela().getId());
			if (listaMovimento != null && listaMovimento.size() > 0) {
				throw new BusinessException("Não encontrado movimento financeiro para baixa para parcela com id "
						+ listaMovimento.get(0).getParcela().getId());
			}
		}
		return listaMovimento;
	}
	
	private BigDecimal buscaSomaMovimento(Long movimentoId) {
		MovimentoFinanceiro movimentoFinanceiro = movimentoRepository.findById(movimentoId).get();
		ParcelaFinanceiro parcelaFinanceiro = movimentoFinanceiro.getParcela();
		Titulo titulo = parcelaFinanceiro.getTitulo();
		Long parcelaId = movimentoFinanceiro.getParcela().getId();
		int sequenciaMovimento = movimentoFinanceiro.getSeqMvto();
		Long empresaId = movimentoFinanceiro.getParcela().getTitulo().getDepartamento().getEmpresaFilial().getEmpresa()
				.getId();

		BigDecimal valor = buscaSomaValorBaixaFinanceiraQuery.executeSQL(parcelaId, sequenciaMovimento, empresaId);
		if (valor == null) {
			throw new BusinessException("Não encontrado movimento financeiro para parcela com id " + parcelaId);
		} else {
			return valor;
		}
	}
	
	public void baixaMovimento(Long parcelaId, int sequenciaMovimento, int subSequenciaMovimento,
			OperacaoFinanceira operacaoFinanceira, CarteiraFinanceira carteira, BigDecimal valorMovimento,
			BigDecimal saldoParcela, Departamento departamento, LocalDate dataMovimento, Long bancoId,
			Long agenciaId, Long contaId, String observacao) {
		Optional<ParcelaFinanceiro> parcelaFinanceiroOptional = parcelaRepository.findById(parcelaId);
		if (!parcelaFinanceiroOptional.isPresent()) {
			throw new BusinessException("Não encontrado parcela financeira para id " + parcelaId);
		}
		ParcelaFinanceiro parcelaFinanceiro = parcelaFinanceiroOptional.get();
		validaParametroFinanceiro(
				parcelaFinanceiro.getTitulo().getDepartamento().getEmpresaFilial().getEmpresa().getId(),
				parcelaFinanceiro.getTitulo().getTipoTitulo().getId(),
				parcelaFinanceiro.getTitulo().getCaracteristicaMovimentoFinanceiro().getId(), carteira.getId(),
				parcelaFinanceiro.getTitulo().getFatoGerador().getId(), operacaoFinanceira.getId());

		if (saldoParcela.doubleValue() < 0) {
			throw new BusinessException(
					"A parcela não possui saldo suficiente para realizar a baixa para id " + parcelaId);
		}

		MovimentoFinanceiro movimentoFinanceiro = MovimentoFinanceiro.builder().parcela(parcelaFinanceiro)
				.seqMvto(sequenciaMovimento).subSeqMvto(subSequenciaMovimento).operacaoFinanceira(operacaoFinanceira)
				.carteiraFinanceira(carteira).valorMovimento(valorMovimento).dataMovimento(dataMovimento)
				.saldoParcela(saldoParcela).departamento(departamento).observacao(observacao).build();

		movimentoFinanceiro = movimentoRepository.saveAndFlush(movimentoFinanceiro);
		if (this.tipoOperacaoIntegracaoFinanceira.isBaixa()) {
			// fuhr vincula recibo
		}
		// fuhr grava movimento banco
		// fuhr atualiza limite credito
	}
}
