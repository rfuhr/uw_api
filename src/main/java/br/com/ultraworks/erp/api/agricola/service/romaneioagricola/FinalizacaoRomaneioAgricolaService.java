package br.com.ultraworks.erp.api.agricola.service.romaneioagricola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.caracteristicacontratoagricola.CaracteristicaContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricolaSelecaoResponse;
import br.com.ultraworks.erp.api.agricola.domain.melhoriaagricola.MelhoriaAgricola;
import br.com.ultraworks.erp.api.agricola.domain.precoagricola.PrecoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.predefinicaoprecoagricola.PredefinicaoPrecoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricola.RomaneioAgricola;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaclassificacao.RomaneioAgricolaClassificacao;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolanota.RomaneioAgricolaNota;
import br.com.ultraworks.erp.api.agricola.domain.situacaoromaneio.SituacaoRomaneio;
import br.com.ultraworks.erp.api.agricola.domain.subitemclassificacaoagricola.SubItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validaitemclassificacaoagricola.ValidaItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validaoperacaointernaagricola.ValidaOperacaoInternaAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validaprecoagricolaitem.ValidaPrecoAgricolaItem;
import br.com.ultraworks.erp.api.agricola.integrator.ServicoIntegracaoFixacao;
import br.com.ultraworks.erp.api.agricola.integrator.TipoOperacaoIntegracaoFixacao;
import br.com.ultraworks.erp.api.agricola.repository.PredefinicaoPrecoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.service.MelhoriaAgricolaService;
import br.com.ultraworks.erp.api.agricola.service.PrecoAgricolaService;
import br.com.ultraworks.erp.api.agricola.service.ValidaItemClassificacaoAgricolaService;
import br.com.ultraworks.erp.api.agricola.service.ValidaOperacaoInternaAgricolaService;
import br.com.ultraworks.erp.api.agricola.service.ValidaPrecoAgricolaItemService;
import br.com.ultraworks.erp.api.agricola.service.contratoagricola.ContratoAgricolaService;
import br.com.ultraworks.erp.api.configuracao.domain.configsistema.ConfigSistema;
import br.com.ultraworks.erp.api.configuracao.service.ConfigSistemaService;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.util.BigDecimalHelper;
import br.com.ultraworks.erp.core.util.CommonUtils;
import br.com.ultraworks.erp.core.util.NossoNumeroGenerator;

@Service
@Scope("prototype")
public class FinalizacaoRomaneioAgricolaService {

	private ConfigSistemaService configSistemaService;
	private RomaneioAgricolaService romaneioAgricolaService;
	private ContratoAgricolaService contratoAgricolaService;
	private ValidaItemClassificacaoAgricolaService validaItemClassificacaoAgricolaService;
	private ValidaOperacaoInternaAgricolaService validaOperacaoInternaAgricolaService;
	private ValidaPrecoAgricolaItemService validaPrecoAgricolaItemService;
	private PredefinicaoPrecoAgricolaRepository predefinicaoPrecoAgricolaRepository;
	private MelhoriaAgricolaService melhoriaAgricolaService;
	private PredefinicaoPrecoAgricola predefinicaoPrecoAgricola;
	private PrecoAgricolaService precoAgricolaService;
	private ApplicationContext context;

	public FinalizacaoRomaneioAgricolaService(ApplicationContext context, ConfigSistemaService configSistemaService,
			RomaneioAgricolaService romaneioAgricolaService, ContratoAgricolaService contratoAgricolaService,
			ValidaItemClassificacaoAgricolaService validaItemClassificacaoAgricolaService,
			ValidaOperacaoInternaAgricolaService validaOperacaoInternaAgricolaService,
			ValidaPrecoAgricolaItemService validaPrecoAgricolaItemService,
			PredefinicaoPrecoAgricolaRepository predefinicaoPrecoAgricolaRepository,
			MelhoriaAgricolaService melhoriaAgricolaService, PrecoAgricolaService precoAgricolaService) {
		this.context = context;
		this.configSistemaService = configSistemaService;
		this.romaneioAgricolaService = romaneioAgricolaService;
		this.contratoAgricolaService = contratoAgricolaService;
		this.validaItemClassificacaoAgricolaService = validaItemClassificacaoAgricolaService;
		this.validaOperacaoInternaAgricolaService = validaOperacaoInternaAgricolaService;
		this.validaPrecoAgricolaItemService = validaPrecoAgricolaItemService;
		this.predefinicaoPrecoAgricolaRepository = predefinicaoPrecoAgricolaRepository;
		this.melhoriaAgricolaService = melhoriaAgricolaService;
		this.precoAgricolaService = precoAgricolaService;
	}

	public void finalizarRomaneioAgricola(RomaneioAgricola romaneioAgricola) {
		romaneioAgricola.setSituacao(SituacaoRomaneio.FINALIZADO);
		romaneioAgricolaService.validarDadosRomaneioAgricola(romaneioAgricola);
		validacaoContratoAgricola(romaneioAgricola);
		validacaoClassificacaoAgricola(romaneioAgricola);
		validacaoFixacao(romaneioAgricola);
		validacaoNotasEntrada(romaneioAgricola);
//		validacaoOrdensCompra(romaneioAgricola);
		validacaoPrecificacao(romaneioAgricola);
		calcularMelhorias(romaneioAgricola);
		definirPrecoDeposito(romaneioAgricola);
		// calcularSubProduto(romaneioAgricola);
		ajustarPesoseSaldo(romaneioAgricola);
		ajustarValoresParcelasFixacao(romaneioAgricola);
		gerarControleRomaneioAgricola(romaneioAgricola);
		definirRegraNotaFiscalDeposito(romaneioAgricola);
		salvarRomaneioAgricola(romaneioAgricola);
//		vincularRomaneioAoCompras(romaneioAgricola);
//		lancarNotasEntradaNoFiscal(romaneioAgricola);
//		atualizarProdutorProduto(romaneioAgricola);
//		gerarRomaneioSubProduto(romaneioAgricola);
		realizaFixacaoAutomatica(romaneioAgricola);
		atualizacaoContratoAgricola(romaneioAgricola);
	}

	private void atualizacaoContratoAgricola(RomaneioAgricola romaneioAgricola) {
		// TODO ATUALIZACAO CONTRATO AGRICOLA
		if (!romaneioAgricola.isFixarAutomatico() && romaneioAgricola.getContratoAgricola() != null) {
			ContratoAgricola contratoAgricola = romaneioAgricola.getContratoAgricola();
			if (contratoAgricola.getTipoContratoAgricola().getCaracteristicaContratoAgricola()
					.equals(CaracteristicaContratoAgricola.DEPOSITO_FIXAR)) {

			} else {

			}
		}
	}

	private void realizaFixacaoAutomatica(RomaneioAgricola romaneioAgricola) {
		// TODO REALIZAR FIXAÇÃO AUTOMATICA
		if (romaneioAgricola.isFixarAutomatico()) {
			ConfigSistema configSistema = configSistemaService.getById(1L).get();
			if (configSistema.getConfiguracoesAgricola().get(0).getOperacaoInternaFixacao() == null) {
				throw new BusinessException("Parametrização da operação interna para fixação não configurada");
			}
			if (romaneioAgricola.getSaldoFixar().doubleValue() <= 0) {
				throw new BusinessException("Este romaneio agrícola não será fixado, pois não tem saldo a fixar");
			}
			List<RomaneioAgricolaNota> notas = romaneioAgricola.getNotas();
			BigDecimal valorTotalNotas = notas.stream().map(RomaneioAgricolaNota::getValorTotal).reduce(BigDecimal.ZERO,
					BigDecimal::add);
			BigDecimal quantidadeTotalNotas = notas.stream().map(RomaneioAgricolaNota::getQuantidade)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			BigDecimal valorUnitario = valorTotalNotas.divide(quantidadeTotalNotas, 5, RoundingMode.HALF_UP);

			ServicoIntegracaoFixacao servicoIntegracaoFixacao = context.getBean(ServicoIntegracaoFixacao.class);
			servicoIntegracaoFixacao.iniciarIntegracao(TipoOperacaoIntegracaoFixacao.FIXARROMANEIOS);
			servicoIntegracaoFixacao
					.definirOperacao(configSistema.getConfiguracoesAgricola().get(0).getOperacaoInternaFixacao());
			servicoIntegracaoFixacao.atribuirConfiguracaoParaFixacao(romaneioAgricola,
					romaneioAgricola.getDepartamento().getEmpresaFilial(), romaneioAgricola.getDepartamento(),
					romaneioAgricola.getItem(), romaneioAgricola.getGrupoOperacaoAgricola(),
					romaneioAgricola.getContratoAgricola(), romaneioAgricola.getSaldoFixar(), false, false,
					LocalDate.now(), valorUnitario, valorTotalNotas);
			romaneioAgricola.getParcelas().forEach(parc -> servicoIntegracaoFixacao
					.atribuirParcelaFixacao(parc.getDataVencimento(), parc.getValorParcela()));
			servicoIntegracaoFixacao.executarIntegracao();
		}
	}

	private void gerarRomaneioSubProduto(RomaneioAgricola romaneioAgricola) {
		// TODO Gerar Romaneio do SubProduto

	}

	private void atualizarProdutorProduto(RomaneioAgricola romaneioAgricola) {
		// TODO ATUALIZAR PRODUTOR PRODUTO

	}

	private void lancarNotasEntradaNoFiscal(RomaneioAgricola romaneioAgricola) {
		// TODO LANÇAR NOTAS DE ENTRADA NO FISCAL

	}

	private void vincularRomaneioAoCompras(RomaneioAgricola romaneioAgricola) {
		// TODO VINCULAR ROMANEIO AGRICOLA AO MODULO COMPRAS

	}

	private void salvarRomaneioAgricola(RomaneioAgricola romaneioAgricola) {
		romaneioAgricola = romaneioAgricolaService.salvarFinalizacao(romaneioAgricola);
	}

	private void definirRegraNotaFiscalDeposito(RomaneioAgricola romaneioAgricola) {
		if (romaneioAgricola.getNotas() != null && !romaneioAgricola.getNotas().isEmpty()) {
			romaneioAgricola.setNumeroNfDeposito(0);
		} else {
			romaneioAgricola.setNumeroNfDeposito(-2);
		}
	}

	private void gerarControleRomaneioAgricola(RomaneioAgricola romaneioAgricola) {
		String numeroControle = StringUtils.leftPad(Long.toString(NossoNumeroGenerator.gerarNossoNumero()), 10, '0');
		int dvControle = CommonUtils.calcularDigitoVerificador(numeroControle);
		romaneioAgricola.setControle(numeroControle);
		romaneioAgricola.setControleDv(dvControle);
	}

	private void ajustarValoresParcelasFixacao(RomaneioAgricola romaneioAgricola) {
		if (romaneioAgricola.isFixarAutomatico()) {
			List<RomaneioAgricolaNota> notas = romaneioAgricola.getNotas();
			if (notas != null) {
				BigDecimal valorTotalFixacao = notas.stream().map(RomaneioAgricolaNota::getValorTotal)
						.reduce(BigDecimal.ZERO, BigDecimal::add);
				int qtdParcelas = romaneioAgricola.getParcelas().size();
				BigDecimal valorParcela = valorTotalFixacao.divide(BigDecimal.valueOf(qtdParcelas), 2,
						RoundingMode.HALF_UP);
				BigDecimal valorTotalArredondado = valorParcela.multiply(BigDecimal.valueOf(qtdParcelas));
				BigDecimal diferenca = valorTotalFixacao.subtract(valorTotalArredondado);
				for (int i = 0; i < qtdParcelas; i++) {
					if (i == 0) {
						romaneioAgricola.getParcelas().get(i).setValorParcela(valorParcela.add(diferenca));
					} else {
						romaneioAgricola.getParcelas().get(i).setValorParcela(valorParcela);
					}
				}
			}
		}
	}

	private void ajustarPesoseSaldo(RomaneioAgricola romaneioAgricola) {
		List<RomaneioAgricolaClassificacao> classificacoes = romaneioAgricola.getClassificacoes();
		romaneioAgricola.setDescontosAcrescimo(BigDecimal.ZERO);
		if (classificacoes != null) {
			classificacoes.stream().forEach(c -> {
				if (c.getIndicadorDc().equals("D")) {
					romaneioAgricola
							.setDescontosAcrescimo(romaneioAgricola.getDescontosAcrescimo().subtract(c.getValor()));
				}
				if (c.getIndicadorDc().equals("C")) {
					romaneioAgricola.setDescontosAcrescimo(romaneioAgricola.getDescontosAcrescimo().add(c.getValor()));
				}
			});
		}

		if (romaneioAgricola.isFixarAutomatico()) {
			List<RomaneioAgricolaNota> notas = romaneioAgricola.getNotas();
			if (notas != null) {
				BigDecimal somaQuantidade = notas.stream().map(RomaneioAgricolaNota::getQuantidade)
						.reduce(BigDecimal.ZERO, BigDecimal::add);
				romaneioAgricola.setPesoFinalFaturar(somaQuantidade.add(romaneioAgricola.getDescontosAcrescimo()));
				romaneioAgricola.setSaldoFixar(romaneioAgricola.getPesoFinalFaturar());
			} else {
				romaneioAgricola.setPesoFinalFaturar(
						romaneioAgricola.getPesoLiquido().add(romaneioAgricola.getDescontosAcrescimo()));
				romaneioAgricola.setSaldoFixar(romaneioAgricola.getPesoFinalFaturar());
			}
		}

	}

	private void calcularSubProduto(RomaneioAgricola romaneioAgricola) {
		// TODO CALCULARSUBPRODUTO

	}

	private void definirPrecoDeposito(RomaneioAgricola romaneioAgricola) {
		if (romaneioAgricola.isFixarAutomatico()) {
			List<RomaneioAgricolaNota> notas = romaneioAgricola.getNotas();
			if (notas != null) {
				BigDecimal somaQuantidade = notas.stream().map(RomaneioAgricolaNota::getQuantidade)
						.reduce(BigDecimal.ZERO, BigDecimal::add);
				BigDecimal somaValor = notas.stream().map(RomaneioAgricolaNota::getValorTotal).reduce(BigDecimal.ZERO,
						BigDecimal::add);
				BigDecimal resultado = somaValor.divide(somaQuantidade, 5, RoundingMode.HALF_UP);
				romaneioAgricola.setPrecoDeposito(resultado);
			} else {
				romaneioAgricola.setPrecoDeposito(BigDecimal.ZERO);
			}

		} else {
			List<RomaneioAgricolaClassificacao> classificacoes = romaneioAgricola.getClassificacoes();
			SubItemClassificacaoAgricola nivel1PredPreco = null;
			SubItemClassificacaoAgricola nivel2PredPreco = null;
			SubItemClassificacaoAgricola nivel3PredPreco = null;
			SubItemClassificacaoAgricola nivel4PredPreco = null;

			if (predefinicaoPrecoAgricola.getItemClassificacaoAgricola1() != null) {
				Optional<RomaneioAgricolaClassificacao> optionalClassificacao = classificacoes.stream()
						.filter(c -> c.getItemClassificacaoAgricola().getId()
								.equals(predefinicaoPrecoAgricola.getItemClassificacaoAgricola1().getId()))
						.findFirst();
				if (optionalClassificacao.isPresent()) {
					nivel1PredPreco = optionalClassificacao.get().getSubItemClassificacaoAgricola();
				} else {
					throw new BusinessException(
							"Pre-Definição de preço do 1º nível, não conferere com a classificação do romaneio agrícola");
				}
			}
			if (predefinicaoPrecoAgricola.getItemClassificacaoAgricola2() != null) {
				Optional<RomaneioAgricolaClassificacao> optionalClassificacao = classificacoes.stream()
						.filter(c -> c.getItemClassificacaoAgricola().getId()
								.equals(predefinicaoPrecoAgricola.getItemClassificacaoAgricola2().getId()))
						.findFirst();
				if (optionalClassificacao.isPresent()) {
					nivel2PredPreco = optionalClassificacao.get().getSubItemClassificacaoAgricola();
				} else {
					throw new BusinessException(
							"Pre-Definição de preço do 2º nível, não conferere com a classificação do romaneio agrícola");
				}
			}
			if (predefinicaoPrecoAgricola.getItemClassificacaoAgricola3() != null) {
				Optional<RomaneioAgricolaClassificacao> optionalClassificacao = classificacoes.stream()
						.filter(c -> c.getItemClassificacaoAgricola().getId()
								.equals(predefinicaoPrecoAgricola.getItemClassificacaoAgricola3().getId()))
						.findFirst();
				if (optionalClassificacao.isPresent()) {
					nivel3PredPreco = optionalClassificacao.get().getSubItemClassificacaoAgricola();
				} else {
					throw new BusinessException(
							"Pre-Definição de preço do 3º nível, não conferere com a classificação do romaneio agrícola");
				}
			}
			if (predefinicaoPrecoAgricola.getItemClassificacaoAgricola4() != null) {
				Optional<RomaneioAgricolaClassificacao> optionalClassificacao = classificacoes.stream()
						.filter(c -> c.getItemClassificacaoAgricola().getId()
								.equals(predefinicaoPrecoAgricola.getItemClassificacaoAgricola4().getId()))
						.findFirst();
				if (optionalClassificacao.isPresent()) {
					nivel4PredPreco = optionalClassificacao.get().getSubItemClassificacaoAgricola();
				} else {
					throw new BusinessException(
							"Pre-Definição de preço do 4º nível, não conferere com a classificação do romaneio agrícola");
				}
			}

			romaneioAgricola.setNivel1PredPreco(nivel1PredPreco);
			romaneioAgricola.setNivel2PredPreco(nivel2PredPreco);
			romaneioAgricola.setNivel3PredPreco(nivel3PredPreco);
			romaneioAgricola.setNivel4PredPreco(nivel4PredPreco);

			PrecoAgricola precoAgricolaVigente = precoAgricolaService.getPrecoAgricolaVigente(
					romaneioAgricola.getItem().getId(), romaneioAgricola.getTipoPrecoAgricola().getId(),
					romaneioAgricola.getDepartamento().getEmpresaFilial().getEmpresa().getId(),
					romaneioAgricola.getDepartamento().getEmpresaFilial().getId(),
					romaneioAgricola.getDepartamento().getId(), predefinicaoPrecoAgricola.getId(),
					nivel1PredPreco.getId(), nivel2PredPreco.getId(), nivel3PredPreco.getId(), nivel4PredPreco.getId(),
					romaneioAgricola.getDataDocumento());
			if (precoAgricolaVigente == null) {
				throw new BusinessException("Parâmetrização de Preço não existe para esta Classificação");
			}
			romaneioAgricola.setPrecoDeposito(precoAgricolaVigente.getValorUnitario());

		}

	}

	private void calcularMelhorias(RomaneioAgricola romaneioAgricola) {
		List<MelhoriaAgricola> melhoriasPossiveis = melhoriaAgricolaService
				.getItensVigentes(romaneioAgricola.getItem().getId(), romaneioAgricola.getDataDocumento());
		List<RomaneioAgricolaClassificacao> classificacoes = romaneioAgricola.getClassificacoes();
		List<MelhoriaAgricola> melhoriasVerificar = melhoriasPossiveis.stream()
				.filter(melhoria -> classificacoes.stream()
						.anyMatch(classificacao -> classificacao.getItemClassificacaoAgricola().getId()
								.equals(melhoria.getItemClassificacaoAgricolaPrincipal().getId())
								&& classificacao.getSubItemClassificacaoAgricola().getId()
										.equals(melhoria.getSubItemClassificacaoAgricolaPrincipal().getId()))
						&& classificacoes.stream()
								.anyMatch(classificacao -> classificacao.getItemClassificacaoAgricola().getId()
										.equals(melhoria.getItemClassificacaoAgricolaSecundario().getId())
										&& classificacao.getSubItemClassificacaoAgricola().getId()
												.equals(melhoria.getSubItemClassificacaoAgricolaSecundario().getId())))
				.collect(Collectors.toList());

		melhoriasVerificar.forEach(melhoriaVerificar -> {
			Optional<RomaneioAgricolaClassificacao> optionalClassificacao = classificacoes.stream()
					.filter(c -> c.getItemClassificacaoAgricola().getId()
							.equals(melhoriaVerificar.getSubItemClassificacaoAgricolaGerado().getId()))
					.findFirst();
			if (optionalClassificacao.isPresent()) {
				RomaneioAgricolaClassificacao romaneioClassificacao = optionalClassificacao.get();
				romaneioClassificacao
						.setSubItemClassificacaoAgricola(melhoriaVerificar.getSubItemClassificacaoAgricolaGerado());
				romaneioClassificacao.setIndicadorDc("N");
			} else {
				RomaneioAgricolaClassificacao romaneioClassificacao = new RomaneioAgricolaClassificacao();
				romaneioClassificacao.setRomaneioAgricola(romaneioAgricola);
				romaneioClassificacao
						.setItemClassificacaoAgricola(melhoriaVerificar.getItemClassificacaoAgricolaGerado());
				romaneioClassificacao
						.setSubItemClassificacaoAgricola(melhoriaVerificar.getSubItemClassificacaoAgricolaGerado());
				romaneioClassificacao.setIndicadorDc("N");
				romaneioAgricola.getClassificacoes().add(romaneioClassificacao);
			}

			if (melhoriaVerificar.getSubItemClassificacaoAgricolaGerado()
					.getItemClassificacaoAgricolaGerado() != null) {
				optionalClassificacao = classificacoes.stream()
						.filter(c -> c.getItemClassificacaoAgricola().getId().equals(melhoriaVerificar
								.getSubItemClassificacaoAgricolaGerado().getItemClassificacaoAgricolaGerado().getId()))
						.findFirst();
				if (optionalClassificacao.isPresent()) {
					RomaneioAgricolaClassificacao romaneioClassificacao = optionalClassificacao.get();
					romaneioClassificacao.setSubItemClassificacaoAgricola(melhoriaVerificar
							.getSubItemClassificacaoAgricolaGerado().getSubItemClassificacaoAgricolaGerado());
					romaneioClassificacao.setIndicadorDc("N");
				} else {
					RomaneioAgricolaClassificacao romaneioClassificacao = new RomaneioAgricolaClassificacao();
					romaneioClassificacao.setRomaneioAgricola(romaneioAgricola);
					romaneioClassificacao.setItemClassificacaoAgricola(melhoriaVerificar
							.getSubItemClassificacaoAgricolaGerado().getItemClassificacaoAgricolaGerado());
					romaneioClassificacao.setSubItemClassificacaoAgricola(melhoriaVerificar
							.getSubItemClassificacaoAgricolaGerado().getSubItemClassificacaoAgricolaGerado());
					romaneioClassificacao.setIndicadorDc("N");
					romaneioAgricola.getClassificacoes().add(romaneioClassificacao);
				}
			}
		});

	}

	private void validacaoPrecificacao(RomaneioAgricola romaneioAgricola) {
		ValidaOperacaoInternaAgricola validacaoOperacaoInternaAgricola = validaOperacaoInternaAgricolaService
				.getValidacaoVigente(romaneioAgricola.getItem().getId(), romaneioAgricola.getOperacaoInterna().getId(),
						romaneioAgricola.getGrupoOperacaoAgricola().getId(), romaneioAgricola.getDataDocumento());
		if (validacaoOperacaoInternaAgricola == null) {
			throw new BusinessException("Definição do tipo de preço para romaneio agrícola não parametrizado");
		}
		romaneioAgricola.setTipoPrecoAgricola(validacaoOperacaoInternaAgricola.getTipoPrecoAgricola());

		ValidaPrecoAgricolaItem validaPrecoAgricolaItem = validaPrecoAgricolaItemService.getValidacaoVigente(
				romaneioAgricola.getItem().getId(), validacaoOperacaoInternaAgricola.getTipoPrecoAgricola().getId(),
				romaneioAgricola.getDataDocumento());
		if (validaPrecoAgricolaItem == null) {
			throw new BusinessException("Validação de tipo de preço agrícola com item não parametrizado");
		}

		predefinicaoPrecoAgricola = predefinicaoPrecoAgricolaRepository
				.findById(validaPrecoAgricolaItem.getPredefinicaoPrecoAgricola().getId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado predefinição de agrícola com id "
						+ validaPrecoAgricolaItem.getPredefinicaoPrecoAgricola().getId()));
		if (romaneioAgricola.getDataDocumento().isBefore(predefinicaoPrecoAgricola.getDataInicioVigencia())
				|| romaneioAgricola.getDataDocumento().isAfter(predefinicaoPrecoAgricola.getDataFinalVigencia())) {
			throw new BusinessException("Predefinição de preço agrícola não está vigente");
		}
	}

	private void validacaoOrdensCompra(RomaneioAgricola romaneioAgricola) {
		// TODO VALIDAR ORDENS COMPRA

	}

	private void validacaoNotasEntrada(RomaneioAgricola romaneioAgricola) {
		boolean deveInformaNotasEntrada = romaneioAgricola.getOperacaoInterna().getOperacaoInternaAgricola()
				.isExigeNotaEntrada();
		if (deveInformaNotasEntrada && (romaneioAgricola.getNotas() == null || romaneioAgricola.getNotas().isEmpty())) {
			throw new BusinessException("Configuração do romaneio exige nota fiscal de entrada");
		}
	}

	private void validacaoFixacao(RomaneioAgricola romaneioAgricola) {
		boolean deveFazerFixacaoAutomatica = romaneioAgricola.getOperacaoInterna().getOperacaoInternaAgricola()
				.isFixaAutomatico();
		if (deveFazerFixacaoAutomatica && !romaneioAgricola.isFixarAutomatico()) {
			throw new BusinessException("Configuração do romaneio agrícola exige fixação automática");
		}
		if (romaneioAgricola.isFixarAutomatico()) {
			if (romaneioAgricola.getParcelas() == null || romaneioAgricola.getParcelas().isEmpty()) {
				throw new BusinessException(
						"Para romaneio agrícola com fixação automática deve ser informado as parcelas");
			}
			if (romaneioAgricola.getNotas() == null || romaneioAgricola.getNotas().isEmpty()) {
				throw new BusinessException(
						"Para romaneio agrícola com fixação automática deve ser informado nota de entrada");
			}
		}
	}

	private void validacaoClassificacaoAgricola(RomaneioAgricola romaneioAgricola) {
		// TODO VALIDAR PERCENTUAL
		validaItemClassificacaoObrigatorioInformado(romaneioAgricola);
	}

	private void validaItemClassificacaoObrigatorioInformado(RomaneioAgricola romaneioAgricola) {
		List<RomaneioAgricolaClassificacao> classificacoes = romaneioAgricola.getClassificacoes();
		List<ValidaItemClassificacaoAgricola> itensClassificacaoSolicitados = validaItemClassificacaoAgricolaService
				.getItensValidacaoVigenteUsoRomaneio(romaneioAgricola.getItem().getId(),
						romaneioAgricola.getDataDocumento());

		List<Long> idsItensInformados = classificacoes.stream().filter(f -> f.getSubItemClassificacaoAgricola() != null)
				.map(f -> f.getItemClassificacaoAgricola().getId()).collect(Collectors.toList());
		itensClassificacaoSolicitados.stream().filter(f -> f.isObrigatorio()).forEach(f -> {
			if (!idsItensInformados.contains(f.getItemClassificacaoAgricola().getId())) {
				throw new BusinessException("Itens de classificação requeridos, não foram informados");
			}
		});
	}

	private void validacaoContratoAgricola(RomaneioAgricola romaneioAgricola) {
		List<ContratoAgricolaSelecaoResponse> contratos = contratoAgricolaService.buscarParaRomaneio(
				romaneioAgricola.getParceiroLocal().getParceiro().getId(), romaneioAgricola.getItem().getId(),
				romaneioAgricola.getSafra().getId());
		if (!contratos.isEmpty()) {
			if (romaneioAgricola.getContratoAgricola() == null) {
				throw new BusinessException(
						"Parceiro possui contrato para o produto e safra, favor informar um contrato para o romaneio agrícola");
			}
		}
		ContratoAgricola contrato = romaneioAgricola.getContratoAgricola();
		if (romaneioAgricola.getNotas() != null) {
			romaneioAgricola.getNotas().forEach(nota -> {
				BigDecimal valorUnitarioNota = nota.getValorUnitario().setScale(5, RoundingMode.HALF_UP);
				BigDecimal valorUnitarioContrato = contrato.getValorUnitarioLiquido().setScale(5, RoundingMode.HALF_UP);
				BigDecimal valorDiferenca = valorUnitarioNota.subtract(valorUnitarioContrato).abs();
				if (valorDiferenca.doubleValue() > 0.01) {
					String strValorUnitarioNota = BigDecimalHelper.toCurrencyString(valorUnitarioNota);
					String strValorUnitarioContrato = BigDecimalHelper.toCurrencyString(valorUnitarioContrato);
					String mensagem = String.format(
							"O valor unitário %s da NF %s é diferente do valor unitário %s do contrato %s, favor verificar! A tolerância é de 0.01 para valor acima ou abaixo.",
							strValorUnitarioNota, nota.getNumeroNota(), strValorUnitarioContrato, contrato.getNumero());
					throw new BusinessException(mensagem);
				}
			});

		}
	}
}
