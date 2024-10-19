package br.com.ultraworks.erp.api.agricola.service.contratoagricola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricolaSelecaoResponse;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricoladesconto.CalculoDescontoContratoRequest;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricoladesconto.CalculoDescontoContratoResponse;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricolamovimento.ContratoAgricolaMovimento;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricolaparcela.ContratoAgricolaParcela;
import br.com.ultraworks.erp.api.agricola.domain.taxacalculoagricola.TaxaCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipocalculoagricola.TipoCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipotaxaagricola.TipoTaxaAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validacalculoagricola.ValidaCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.mapper.ContratoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.ContratoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.query.SelecionaContratoAgricolaParaFixacaoQuery;
import br.com.ultraworks.erp.api.agricola.repository.query.SelecionaContratoAgricolaParaRomaneioQuery;
import br.com.ultraworks.erp.api.agricola.service.TaxaCalculoAgricolaService;
import br.com.ultraworks.erp.api.agricola.service.ValidaCalculoAgricolaService;
import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistema;
import br.com.ultraworks.erp.api.configuracao.service.ConfigSistemaService;
import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.financeiro.integrator.ServicoIntegracaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.integrator.TipoOperacaoIntegracaoFinanceira;
import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadrao;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernaagricola.OperacaoInternaAgricola;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.NossoNumeroGenerator;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ContratoAgricolaService extends GenericService<ContratoAgricola, Long, ContratoAgricolaDTO> {

	private SelecionaContratoAgricolaParaRomaneioQuery selecionaContratoAgricolaParaRomaneioQuery;
	private SelecionaContratoAgricolaParaFixacaoQuery selecionaContratoAgricolaParaFixacaoQuery;
	private ValidaCalculoAgricolaService validaCalculoAgricolaService;
	private TaxaCalculoAgricolaService taxaAgricolaService;
	private ContratoAgricolaParcelaService contratoAgricolaParcelaService;
	private ContratoAgricolaDescontoService contratoAgricolaDescontoService;
	private ContratoAgricolaMovimentoService contratoAgricolaMovimentoService;
	private ServicoIntegracaoFinanceira servicoIntegracaoFinanceira;
	private ConfigSistemaService configSistemaService;

	@Autowired
	public ContratoAgricolaService(ContratoAgricolaRepository repository, ContratoAgricolaMapper mapper,
			SelecionaContratoAgricolaParaRomaneioQuery selecionaContratoAgricolaParaRomaneioQuery,
			SelecionaContratoAgricolaParaFixacaoQuery selecionaContratoAgricolaParaFixacaoQuery,
			ValidaCalculoAgricolaService validaCalculoAgricolaService, TaxaCalculoAgricolaService taxaAgricolaService,
			ContratoAgricolaParcelaService contratoAgricolaParcelaService,
			ContratoAgricolaDescontoService contratoAgricolaDescontoService,
			ContratoAgricolaMovimentoService contratoAgricolaMovimentoService,
			ServicoIntegracaoFinanceira servicoIntegracaoFinanceira, ConfigSistemaService configSistemaService) {
		super(repository, mapper);
		this.selecionaContratoAgricolaParaRomaneioQuery = selecionaContratoAgricolaParaRomaneioQuery;
		this.selecionaContratoAgricolaParaFixacaoQuery = selecionaContratoAgricolaParaFixacaoQuery;
		this.validaCalculoAgricolaService = validaCalculoAgricolaService;
		this.taxaAgricolaService = taxaAgricolaService;
		this.contratoAgricolaParcelaService = contratoAgricolaParcelaService;
		this.contratoAgricolaDescontoService = contratoAgricolaDescontoService;
		this.contratoAgricolaMovimentoService = contratoAgricolaMovimentoService;
		this.servicoIntegracaoFinanceira = servicoIntegracaoFinanceira;
		this.configSistemaService = configSistemaService;
	}

	@Override
	public Optional<ContratoAgricola> getById(Long id) {
		Optional<ContratoAgricola> contrato = repository.findById(id);
		if (contrato.isPresent()) {
			contrato.get().setParcelas(new ArrayList<>());
			contrato.get().getParcelas()
					.addAll(contratoAgricolaParcelaService.getAllByContratoAgricola(contrato.get().getId()));

			contrato.get().setDescontos(new ArrayList<>());
			contrato.get().getDescontos()
					.addAll(contratoAgricolaDescontoService.getAllByContratoAgricola(contrato.get().getId()));
		}
		return contrato;
	}

	public List<ContratoAgricolaSelecaoResponse> buscarParaRomaneio(Long parceiroId, Long itemId, Long safraId) {
		return selecionaContratoAgricolaParaRomaneioQuery.executeSQL(parceiroId, itemId, safraId);
	}

	public List<ContratoAgricolaSelecaoResponse> buscarParaFixacao(Long parceiroId, Long itemId) {
		return selecionaContratoAgricolaParaFixacaoQuery.executeSQL(parceiroId, itemId);
	}

	public List<CalculoDescontoContratoResponse> calcularDescontos(CalculoDescontoContratoRequest request) {
		List<CalculoDescontoContratoResponse> descontos = new ArrayList<>();

		List<ValidaCalculoAgricola> validacoes = validaCalculoAgricolaService.getValidacoesVigente(request.getItemId(),
				request.getOperacaoInternaId(), request.getGrupoOperacaoAgricolaId(), request.getRegraAtividadeId(),
				request.getDataBase());
		List<TipoCalculoAgricola> tiposCalculos = validacoes.stream().map(ValidaCalculoAgricola::getTipoCalculoAgricola)
				.collect(Collectors.toList());
		tiposCalculos.forEach(tipo -> {
			BigDecimal valor = BigDecimal.ZERO;
			TaxaCalculoAgricola taxaCalculoAgricola = taxaAgricolaService.getValidacaoVigente(request.getItemId(),
					tipo.getId(), request.getRegraAtividadeId(), BigDecimal.valueOf(9999999), request.getDataBase());
			if (taxaCalculoAgricola.getTipoTaxaAgricola().equals(TipoTaxaAgricola.PERCENTUAL)) {
				valor = request.getValorBruto().multiply(taxaCalculoAgricola.getFatorCalculo())
						.divide(new BigDecimal("100"), 0, RoundingMode.HALF_UP);
			} else if (taxaCalculoAgricola.getTipoTaxaAgricola().equals(TipoTaxaAgricola.INDICE)) {
				valor = request.getValorBruto().multiply(taxaCalculoAgricola.getFatorCalculo());
			} else {
				valor = taxaCalculoAgricola.getFatorCalculo();
			}

			CalculoDescontoContratoResponse desconto = CalculoDescontoContratoResponse.builder()
					.contratoAgricolaId(request.getContratoAgricolaId()).itemId(request.getItemId())
					.departamentoId(request.getDepartamentoId()).tipoCalculoAgricolaId(tipo.getId())
					.percentualTaxaContrato(taxaCalculoAgricola.getFatorCalculo())
					.percentualTaxaAtual(taxaCalculoAgricola.getFatorCalculo()).valor(valor).build();
			descontos.add(desconto);
		});
		return descontos;
	}

	@Override
	@Transactional
	public ContratoAgricola save(ContratoAgricola contratoAgricola) {
		if (contratoAgricola.getId() != null) {
			throw new BusinessException("Não é possível alterar um contrato agrícola");
		}
		checkParcelasFinanceiro(contratoAgricola);
		checkPremioContrato(contratoAgricola);
		checkSafra(contratoAgricola);

		ContratoAgricola contratoAgricolaSaved = repository.save(contratoAgricola);
		if (contratoAgricola.getParcelas() != null)
			contratoAgricola.getParcelas().forEach(parc -> parc.setContratoAgricola(contratoAgricolaSaved));
		contratoAgricolaParcelaService.persistList(contratoAgricolaSaved.getId(), contratoAgricola.getParcelas());

		if (contratoAgricola.getDescontos() != null)
			contratoAgricola.getDescontos().forEach(desc -> desc.setContratoAgricola(contratoAgricolaSaved));
		contratoAgricolaDescontoService.persistList(contratoAgricolaSaved.getId(), contratoAgricola.getDescontos());

		if (!contratoAgricolaSaved.getOperacaoInterna().getOperacaoInternaAgricola().isContratoAfixar()) {
			gravarContratoAgricolaMovimento(contratoAgricolaSaved);
		}

		if (contratoAgricolaSaved.getOperacaoInterna().getOperacaoInternaFinanceiro() != null
				&& contratoAgricolaSaved.getOperacaoInterna().isCaracteristicaFinanceira()) {
			integrarFinanceiro(contratoAgricolaSaved);
		}

		return getById(contratoAgricolaSaved.getId()).get();
	}

	private void integrarFinanceiro(ContratoAgricola contratoAgricola) {
		ConfigSistema configSistema = configSistemaService.getById(1L).get();
		TipoOperacaoFinanceira tipoOperacaoFinanceira = configSistema.getConfiguracoesFinanceiro().get(0)
				.getTipoOperacaoFinanceiraLancamento();
		TipoTitulo tipoTitulo = contratoAgricola.getOperacaoInterna().getOperacaoInternaFinanceiro().getTipoTitulo();
		GrupoFinanceiro grupoFinanceiro = contratoAgricola.getOperacaoInterna().getOperacaoInternaFinanceiro()
				.getGrupoFinanceiro();
		CaracteristicaMovimentoFinanceiro caracteristicaMovimentoFinanceiro = contratoAgricola.getOperacaoInterna()
				.getOperacaoInternaFinanceiro().getCaracteristicaMovimentoFinanceiro();
		HistoricoPadrao historicoPadrao = contratoAgricola.getOperacaoInterna().getOperacaoInternaFinanceiro()
				.getHistoricoPadrao();
		OperacaoMovimentoFinanceiro operacaoMovimentoFinanceiro = configSistema.getConfiguracoesFinanceiro().get(0)
				.getOperacaoMovimentoFinanceiroInclusao();
		OperacaoAcessoriaFinanceira operacaoAcessoriaFinanceira = configSistema.getConfiguracoesFinanceiro().get(0)
				.getOperacaoAcessoriaFinanceiraPrincipal();

		servicoIntegracaoFinanceira.iniciarIntegracao(TipoOperacaoIntegracaoFinanceira.INCLUSAOPORLANCAMENTO);
		String observacao = "Inclusão através da emissão do Contrato nº " + contratoAgricola.getNumero();
		servicoIntegracaoFinanceira.definirOperacao(tipoOperacaoFinanceira, contratoAgricola.getValorLiquido(),
				contratoAgricola.getDepartamento(), contratoAgricola.getDataMovimento(), observacao, null,
				contratoAgricola.getCarteiraFinanceira());
		servicoIntegracaoFinanceira.atribuiValoresTitulo(tipoTitulo,
				contratoAgricola.getDepartamento().getEmpresaFilial(), contratoAgricola.getDepartamento(),
				grupoFinanceiro, contratoAgricola.getParceiroLocal(), contratoAgricola.getFatoGerador(),
				caracteristicaMovimentoFinanceiro, historicoPadrao, contratoAgricola.getCarteiraFinanceira(),
				String.valueOf(contratoAgricola.getNumero()), observacao, contratoAgricola.getDataDocumento(),
				String.valueOf(contratoAgricola.getNumero()), contratoAgricola.getValorLiquido(), null,
				NossoNumeroGenerator.gerarNossoNumero());
		contratoAgricola.getParcelas().forEach(parc -> {
			servicoIntegracaoFinanceira.atribuiValoresParcela(parc.getNumeroParcela(), parc.getDataVencimento(),
					parc.getValorParcela());

			servicoIntegracaoFinanceira.atribuiValoresMovimento(parc.getNumeroParcela(), 1, 1, tipoOperacaoFinanceira,
					operacaoMovimentoFinanceiro, operacaoAcessoriaFinanceira, contratoAgricola.getCarteiraFinanceira(),
					grupoFinanceiro, parc.getValorParcela(), contratoAgricola.getDataMovimento(),
					parc.getValorParcela(), null);
		});
		servicoIntegracaoFinanceira.executarIntegracao();
	}

	private void gravarContratoAgricolaMovimento(ContratoAgricola contratoAgricola) {
		ContratoAgricolaMovimento contratoAgricolaMovimento = new ContratoAgricolaMovimento();
		contratoAgricolaMovimento.setContratoAgricola(contratoAgricola);
		contratoAgricolaMovimento.setOperacaoInterna(contratoAgricola.getOperacaoInterna());
		contratoAgricolaMovimento.setNumeroNota(0);
		contratoAgricolaMovimento.setDataMovimento(contratoAgricola.getDataMovimento());
		contratoAgricolaMovimento.setQuantidade(contratoAgricola.getQuantidadeContratada());
		contratoAgricolaMovimento.setValor(contratoAgricola.getValorLiquido());
		contratoAgricolaMovimentoService.save(contratoAgricolaMovimento);
	}

	private void checkSafra(ContratoAgricola contratoAgricola) {
		if (contratoAgricola.getSafra() == null) {
			throw new BusinessException("Safra agrícola deve ser informada para um contrato agrícola");
		}
	}

	private void checkPremioContrato(ContratoAgricola contratoAgricola) {
		OperacaoInternaAgricola operacaoInternaAgricola = contratoAgricola.getOperacaoInterna()
				.getOperacaoInternaAgricola();
		if (operacaoInternaAgricola.isContratoAfixar()) {
			if (contratoAgricola.getQuantidadeAcordoPremio()
					.compareTo(contratoAgricola.getQuantidadeContratada()) > 0) {
				throw new BusinessException(
						"Quantidade do Acordo do Prémio é maior que a Quantidade Contratada do contrato");
			}
		}

	}

	private void checkParcelasFinanceiro(ContratoAgricola contratoAgricola) {
		OperacaoInternaAgricola operacaoInternaAgricola = contratoAgricola.getOperacaoInterna()
				.getOperacaoInternaAgricola();
		if (!operacaoInternaAgricola.isContratoAfixar()) {
			List<ContratoAgricolaParcela> parcelas = contratoAgricola.getParcelas();
			if (parcelas == null || parcelas.isEmpty()) {
				throw new BusinessException("Não foram informadas parcelas para o contrato");
			}
			BigDecimal totalParcelas = parcelas.stream().map(ContratoAgricolaParcela::getValorParcela)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			if (contratoAgricola.getValorLiquido().setScale(2, RoundingMode.HALF_UP)
					.compareTo(totalParcelas.setScale(2, RoundingMode.HALF_UP)) != 0) {
				throw new BusinessException("Soma das Parcelas é maior que o valor líquido do contrato");
			}
		}

	}
}