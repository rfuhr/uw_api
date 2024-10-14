package br.com.ultraworks.erp.api.agricola.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.basecalculoagricola.BaseCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.classificacaoagricola.container.ContainerCalculadoraCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.classificacaoagricola.container.ContainerCalculadoraItemCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.query.SelecionaOrdemCalculoAgricolaParaCalculo;
import br.com.ultraworks.erp.api.agricola.domain.subitemclassificacaoagricola.SubItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.taxacalculoagricola.TaxaCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipotaxaagricola.TipoTaxaAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validacalculoagricola.ValidaCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validaitemclassificacaoagricola.ValidaItemClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.repository.query.SelecionaOrdemCalculoAgricolaParaCalculoRomaneioQuery;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;

@Service
public class CalculadoraDescontoAgricolaService {

	private SelecionaOrdemCalculoAgricolaParaCalculoRomaneioQuery selecionaOrdemCalculoAgricolaParaCalculoRomaneioQuery;
	private ValidaItemClassificacaoAgricolaService validaItemClassificacaoAgricolaService;
	private ValidaCalculoAgricolaService validaCalculoAgricolaService;
	private TaxaCalculoAgricolaService taxaCalculoAgricolaService;
	private SubItemClassificacaoAgricolaService subItemClassificacaoAgricolaService;

	public CalculadoraDescontoAgricolaService(
			SelecionaOrdemCalculoAgricolaParaCalculoRomaneioQuery selecionaOrdemCalculoAgricolaParaCalculoRomaneioQuery,
			ValidaItemClassificacaoAgricolaService validaItemClassificacaoAgricolaService,
			ValidaCalculoAgricolaService validaCalculoAgricolaService,
			TaxaCalculoAgricolaService taxaCalculoAgricolaService,
			SubItemClassificacaoAgricolaService subItemClassificacaoAgricolaService) {
		this.selecionaOrdemCalculoAgricolaParaCalculoRomaneioQuery = selecionaOrdemCalculoAgricolaParaCalculoRomaneioQuery;
		this.validaItemClassificacaoAgricolaService = validaItemClassificacaoAgricolaService;
		this.validaCalculoAgricolaService = validaCalculoAgricolaService;
		this.taxaCalculoAgricolaService = taxaCalculoAgricolaService;
		this.subItemClassificacaoAgricolaService = subItemClassificacaoAgricolaService;

	}

	public void calcularDescontoAgricolaNoRomaneio(
			ContainerCalculadoraCalculoAgricola containerCalculadoraCalculoAgricola) {

		List<ContainerCalculadoraItemCalculoAgricola> itens = containerCalculadoraCalculoAgricola
				.getItensClassificacao();
		if (containerCalculadoraCalculoAgricola != null) {
			if (containerCalculadoraCalculoAgricola.getItemClassificacaoAgricolaCalcularId() != null
					&& !containerCalculadoraCalculoAgricola.getItemClassificacaoAgricolaCalcularId().equals(0L)) {
				itens = itens.stream()
						.filter(f -> f.getItemClassificacaoAgricolaId()
								.equals(containerCalculadoraCalculoAgricola.getItemClassificacaoAgricolaCalcularId()))
						.collect(Collectors.toList());
			}

			itens.forEach(itemContainer -> {
				executarCalculo(containerCalculadoraCalculoAgricola, itemContainer);
			});
		}
	}

	private void executarCalculo(ContainerCalculadoraCalculoAgricola containerCalculadoraCalculoAgricola,
			ContainerCalculadoraItemCalculoAgricola itemContainer) {
		List<SelecionaOrdemCalculoAgricolaParaCalculo> listOrdemParaCalculo = this.selecionaOrdemCalculoAgricolaParaCalculoRomaneioQuery
				.executeSQL(containerCalculadoraCalculoAgricola.getItemId(),
						itemContainer.getItemClassificacaoAgricolaId(),
						containerCalculadoraCalculoAgricola.getOperacaoInternaId(),
						containerCalculadoraCalculoAgricola.getDataBase());
		if (listOrdemParaCalculo != null && !listOrdemParaCalculo.isEmpty()) {
			listOrdemParaCalculo.forEach(ordemParaCalculo -> {
				BigDecimal baseCalculo = BigDecimal.ZERO;
				BigDecimal baseCalculoComplementar = BigDecimal.ZERO;

				List<ValidaItemClassificacaoAgricola> validacaoVigenteUsoRomaneio = this.validaItemClassificacaoAgricolaService
						.getValidacaoVigenteUsoRomaneio(containerCalculadoraCalculoAgricola.getItemId(),
								itemContainer.getItemClassificacaoAgricolaId(),
								containerCalculadoraCalculoAgricola.getDataBase());
				if (validacaoVigenteUsoRomaneio != null && !validacaoVigenteUsoRomaneio.isEmpty()) {
					ValidaCalculoAgricola validaCalculoAgricola = validaCalculoAgricolaService.getValidacaoVigente(
							containerCalculadoraCalculoAgricola.getItemId(),
							ordemParaCalculo.getTipoCalculoAgricolaId(),
							containerCalculadoraCalculoAgricola.getOperacaoInternaId(),
							containerCalculadoraCalculoAgricola.getGrupoOperacaoAgricolaId(),
							containerCalculadoraCalculoAgricola.getRegraAtividadeId(),
							containerCalculadoraCalculoAgricola.getDataBase());
					if (validaCalculoAgricola != null) {
						BigDecimal indiceReferencia = getIndiceReferencia(
								itemContainer.getSubItemClassificacaoAgricolaId());
						TaxaCalculoAgricola taxaCalculoAgricola = taxaCalculoAgricolaService.getValidacaoVigente(
								containerCalculadoraCalculoAgricola.getItemId(),
								validaCalculoAgricola.getTipoCalculoAgricola().getId(),
								validaCalculoAgricola.getRegraAtividade().getId(), indiceReferencia,
								containerCalculadoraCalculoAgricola.getDataBase());
						if (taxaCalculoAgricola != null) {
							baseCalculoComplementar = definirBaseCalculoComplementar();
							baseCalculo = definirBaseCalculo(ordemParaCalculo, containerCalculadoraCalculoAgricola);
							baseCalculo = baseCalculo.add(baseCalculoComplementar);

							checkValoresParaCalculo();
							if (!containerCalculadoraCalculoAgricola.isCalcularGrupo()) {
								BigDecimal valorCalculo = definirValorCalculo(baseCalculo, taxaCalculoAgricola);
								itemContainer.setIndicadorDC(ordemParaCalculo.getIdnDC());
								itemContainer.setValor(valorCalculo);
								itemContainer.setTipoTaxaAgricola(taxaCalculoAgricola.getTipoTaxaAgricola().getValue());
								itemContainer.setBaseCalculoAgricola(ordemParaCalculo.getBaseCalculoAgricola());
								itemContainer.setValorBaseCalculo(baseCalculo);
								itemContainer.setValorBaseCalculoComplementar(baseCalculoComplementar);
								itemContainer.setOrdem(ordemParaCalculo.getOrdem());
								itemContainer.setFatorCalculo(taxaCalculoAgricola.getFatorCalculo());

								checkPercentualMaximo();
							}
						} else {
							throw new BusinessException(
									"Parâmetrização da Taxa de Calculo não está Cadastrada - Verifique!!!");
						}
					} else {
						throw new BusinessException("Validação de Cálculo não está Cadastrada - Verifique!!!");
					}
				}
			});
		}
		gerarClassificacaoVinculada(containerCalculadoraCalculoAgricola,
				itemContainer.getSubItemClassificacaoAgricolaId());
	}

	private void gerarClassificacaoVinculada(ContainerCalculadoraCalculoAgricola containerCalculadoraCalculoAgricola,
			Long subItemClassificacaoAgricolaId) {
		LocalDate dataBase = containerCalculadoraCalculoAgricola.getDataBase();

		SubItemClassificacaoAgricola subItemClassificacaoAgricola = subItemClassificacaoAgricolaService
				.getById(subItemClassificacaoAgricolaId).get();
		if (dataBase.isAfter(subItemClassificacaoAgricola.getDataInicioVigencia())
				&& dataBase.isBefore(subItemClassificacaoAgricola.getDataFinalVigencia())) {
			if (subItemClassificacaoAgricola.getItemClassificacaoAgricolaGerado() != null
					&& subItemClassificacaoAgricola.getSubItemClassificacaoAgricolaGerado() != null) {
				List<ContainerCalculadoraItemCalculoAgricola> itensClassificacao = containerCalculadoraCalculoAgricola
						.getItensClassificacao();
				itensClassificacao.stream()
						.filter(f -> f.getSubItemClassificacaoAgricolaId()
								.equals(subItemClassificacaoAgricola.getSubItemClassificacaoAgricolaGerado().getId()))
						.findFirst().ifPresentOrElse(subItem -> {
							subItem.setIndicadorDC("N");
							subItem.setGerado(true);
						}, () -> {
							ContainerCalculadoraItemCalculoAgricola subItem = ContainerCalculadoraItemCalculoAgricola
									.builder()
									.itemClassificacaoAgricolaId(
											subItemClassificacaoAgricola.getItemClassificacaoAgricolaGerado().getId())
									.subItemClassificacaoAgricolaId(subItemClassificacaoAgricola
											.getSubItemClassificacaoAgricolaGerado().getId())
									.grupoClassificacaoAgricolaId(subItemClassificacaoAgricola
											.getItemClassificacaoAgricola().getGrupoClassificacaoAgricola().getId())
									.itemClassificacaoAgricolaNome(
											subItemClassificacaoAgricola.getItemClassificacaoAgricola().getNome())
									.grupoClassificacaoAgricolaNome(subItemClassificacaoAgricola
											.getItemClassificacaoAgricola().getGrupoClassificacaoAgricola().getNome())
									.gerado(true).build();
							itensClassificacao.add(subItem);
						});

				ContainerCalculadoraItemCalculoAgricola subItem = itensClassificacao.stream()
						.filter(f -> f.getSubItemClassificacaoAgricolaId()
								.equals(subItemClassificacaoAgricola.getSubItemClassificacaoAgricolaGerado().getId()))
						.findFirst().orElseThrow(() -> new IllegalStateException("Item não encontrado após adição"));

				executarCalculo(containerCalculadoraCalculoAgricola, subItem);
			}
		}
	}

	private BigDecimal getIndiceReferencia(Long subItemClassificacaoAgricolaId) {
		SubItemClassificacaoAgricola subItem = this.subItemClassificacaoAgricolaService
				.getById(subItemClassificacaoAgricolaId).orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado sub. item de classificação com id " + subItemClassificacaoAgricolaId));
		return subItem.getIndiceReferencia();
	}

	private void checkPercentualMaximo() {
		// TODO Auto-generated method stub

	}

	private BigDecimal definirValorCalculo(BigDecimal baseCalculo, TaxaCalculoAgricola taxaCalculoAgricola) {
		if (taxaCalculoAgricola.getTipoTaxaAgricola().equals(TipoTaxaAgricola.PERCENTUAL)) {
			return baseCalculo.multiply(taxaCalculoAgricola.getFatorCalculo()).divide(new BigDecimal("100"), 0,
					RoundingMode.HALF_UP);
		} else if (taxaCalculoAgricola.getTipoTaxaAgricola().equals(TipoTaxaAgricola.INDICE)) {
			return baseCalculo.multiply(taxaCalculoAgricola.getFatorCalculo());
		} else {
			return taxaCalculoAgricola.getFatorCalculo();
		}
	}

	private void checkValoresParaCalculo() {

	}

	private BigDecimal definirBaseCalculo(SelecionaOrdemCalculoAgricolaParaCalculo ordemParaCalculo,
			ContainerCalculadoraCalculoAgricola containerCalculadoraCalculoAgricola) {

		switch (BaseCalculoAgricola.fromValue(ordemParaCalculo.getBaseCalculoAgricola())) {
		case PESO_BRUTO:
			return containerCalculadoraCalculoAgricola.getPesoBruto();
		case TARA:
			return containerCalculadoraCalculoAgricola.getPesoTara();
		case PESO_LIQUIDO:
			return containerCalculadoraCalculoAgricola.getPesoLiquido();
		default:
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal definirBaseCalculoComplementar() {
		return BigDecimal.ZERO;
	}
}
